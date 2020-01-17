package ru.vkr.service;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import org.springframework.stereotype.Service;
import ru.vkr.model.TaskData;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Base64;
import java.util.Objects;

@Service
public class TaskLoader {

    private Client client;
    private File dir;
    private TaskData task;

    public void loadTorrent(TaskData task) {
        if (task == null)
            return;

        try {
            this.task = task;
            dir = new File(".\\catch\\tasks\\" + this.task.getId());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            client = new Client(InetAddress.getLocalHost(), new SharedTorrent(Base64.getDecoder().decode(this.task.getTorrentFile().getBytes("UTF8")), dir));
            client.download();
            client.waitForCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
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
            e.printStackTrace();
        }
    }
}
