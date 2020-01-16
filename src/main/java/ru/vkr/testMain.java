package ru.vkr;

import ru.vkr.service.TaskLoader;

import java.io.File;

public class testMain {
    public static void main(String[] args) {
        TaskLoader loader = new TaskLoader();
        loader.loadTorrent(2L, new File("C:\\Users\\admin\\Documents\\Client\\src\\main\\resources\\1.torrent"));
       //loader.startTask();
    }
}
