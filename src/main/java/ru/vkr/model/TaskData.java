package ru.vkr.model;


public class TaskData {
    private Long id;
    private String name;
    private String taskType;
    private String version;
    private OS os;
    private OSType osType;
    private String pathToRunFile;
    private String torrentFile;

    public TaskData() {}

    public TaskData(Long id, String name, String taskType, String version, OS os, OSType osType, String pathToRunFile, String torrentFile) {
        this.id = id;
        this.name = name;
        this.taskType = taskType;
        this.version = version;
        this.os = os;
        this.osType = osType;
        this.pathToRunFile = pathToRunFile;
        this.torrentFile = torrentFile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public OSType getOsType() {
        return osType;
    }

    public void setOsType(OSType osType) {
        this.osType = osType;
    }

    public String getPathToRunFile() {
        return pathToRunFile;
    }

    public void setPathToRunFile(String pathToRunFile) {
        this.pathToRunFile = pathToRunFile;
    }

    public String getTorrentFile() {
        return torrentFile;
    }

    public void setTorrentFile(String torrentFile) {
        this.torrentFile = torrentFile;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskType='" + taskType + '\'' +
                ", version='" + version + '\'' +
                ", os=" + os +
                ", osType=" + osType +
                ", pathToRunFile='" + pathToRunFile + '\'' +
                ", torrentFile='" + torrentFile + '\'' +
                '}';
    }
}
