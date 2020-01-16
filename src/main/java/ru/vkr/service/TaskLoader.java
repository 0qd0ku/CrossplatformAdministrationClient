package ru.vkr.service;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.InetAddress;

@Service
public class TaskLoader {

    private Client client;
    private File dir;
    private boolean isLoaded = false;

    public void loadTorrent(Long taskId, File torrentFile) {
        try {
            dir = new File("./catch/tasks/" + taskId);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(torrentFile, dir));
            client.download();
            client.waitForCompletion();
//            startTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void startTask() {
//        if (!isLoaded)
//            return;
//            //Process process = Runtime.getRuntime().exec(dir + "/Adobe Acrobat Pro DC 2019.021.20058 RePack by KpoJIuK/Adobe.Acrobat.Pro.DC.v2019.021.20058.exe");
//            execute( dir.getAbsolutePath() + "\\Adobe Acrobat Pro DC 2019.021.20058 RePack by KpoJIuK\\Adobe.Acrobat.Pro.DC.v2019.021.20058.exe\"");
//    }
//
//    public static void executeAsAdmin(String command, String args) {
//        SHELLEXECUTEINFO execInfo = new SHELLEXECUTEINFO();
//        execInfo.lpFile = new WString(command);
//        if (args != null) {
//            execInfo.lpParameters = new WString(args);
//        }
//        execInfo.nShow = Shell32X.SW_SHOWDEFAULT;
//        execInfo.fMask = Shell32X.SEE_MASK_NOCLOSEPROCESS;
//        execInfo.lpVerb = new WString("runas");
//        boolean result = Shell32X.INSTANCE.ShellExecuteEx(execInfo);
//
//        if (!result) {
//            int lastError = Kernel32.INSTANCE.GetLastError();
//            String errorMessage = Kernel32Util.formatMessageFromLastErrorCode(lastError);
//            throw new RuntimeException("Error performing elevation: " + lastError + ": " + errorMessage + " (apperror=" + execInfo.hInstApp + ")");
//        }
//    }
//        String szCmdline = command;
//
//        WinBase.PROCESS_INFORMATION processInformation = new WinBase.PROCESS_INFORMATION();
//        WinBase.STARTUPINFO startupInfo = new WinBase.STARTUPINFO();
//        startupInfo.cb = new WinDef.DWORD(processInformation.size());
//
//        // Create the child process.
//        if (!Kernel32.INSTANCE.CreateProcess(
//                null,
//                szCmdline,
//                null,
//                null,
//                true,
//                new WinDef.DWORD(0x00000020),
//                null,
//                null,
//                startupInfo,
//                processInformation)){
//            System.err.println(Kernel32.INSTANCE.GetLastError());
//        }
//        else {
//            com.sun.jna.platform.win32.Kernel32.INSTANCE.WaitForSingleObject(processInformation.hProcess, 0xFFFFFFFF);
//
//            com.sun.jna.platform.win32.Kernel32.INSTANCE.CloseHandle(processInformation.hProcess);
//            com.sun.jna.platform.win32.Kernel32.INSTANCE.CloseHandle(processInformation.hThread);
//        }
//    }
}
