package ru.vkr.service;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vkr.model.TaskData;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Base64;
import java.util.Objects;

@Service
public class TaskLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskLoader.class);

    public void loadTorrent(TaskData task) {
        if (task == null)
            return;
        try {
            File dir = new File(".\\catch\\tasks\\" + task.getId());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Client client = new Client(InetAddress.getLocalHost(), new SharedTorrent(Base64.getDecoder().decode(task.getTorrentFile().getBytes("UTF8")), dir));
            client.download();
            client.waitForCompletion();
            run(task, dir);
        } catch (Exception e) {
            LOGGER.error("Error while loading torrent file", e);
        }
    }

    private void run(TaskData task, File dir) {
        if (task == null)
            return;

        String command = "";
        switch (task.getTaskType()) {
            case BAT:
                if (task.getPathToRunFile().endsWith(".bat") || task.getPathToRunFile().endsWith(".cmd"))
                    command = "cmd.exe /c \"" + dir.getAbsolutePath() + "\\" + task.getPathToRunFile() + "\"";
                break;
            case POWERSHELL:
                if (task.getPathToRunFile().endsWith(".ps1"))
                    command = "cmd.exe /c powershell -command - < \"" + dir.getAbsolutePath() + "\\" + task.getPathToRunFile() + "\"";
                break;
            case SH:
                if (task.getPathToRunFile().endsWith(".sh"))
                    // TODO command for Linux;
                break;
            case PROGRAM:
                command = "cmd.exe /c \"" + dir.getAbsolutePath() + "\\" + task.getPathToRunFile() + "\"";
                break;
        }
        try {
            if (!command.isEmpty() && !Objects.isNull(command)) {
                Process process = Runtime.getRuntime().exec(command);
            }
        } catch (IOException e) {
            LOGGER.error("Error while installing program", e);
        }
    }
}
