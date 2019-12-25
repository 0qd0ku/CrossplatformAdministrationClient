package ru.vkr.model;

import java.util.Objects;

public class TaskData {
    private Long id;
    private String name;
    private String taskType;
    private String version;
    private OS os;
    private OSType osType;
    private String pathToRunFile;
    private String torrentFile;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskData)) return false;
        TaskData that = (TaskData) o;
        return id.equals(that.id) &&
                getName().equals(that.getName()) &&
                getTaskType() == that.getTaskType() &&
                getVersion().equals(that.getVersion()) &&
                getOs().getValue() == that.getOs().getValue() &&
                getOsType().getValue() == that.getOsType().getValue() &&
                getPathToRunFile().equals(that.getPathToRunFile()) &&
                getTorrentFile().equals(that.getTorrentFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getTaskType(), getVersion(), getOs().getValue(), getOsType().getValue(), getPathToRunFile(), getTorrentFile());
    }

    @Override
    public String toString() {
        return "TaskDataDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskType=" + taskType +
                ", version='" + version + '\'' +
                ", os=" + os.getValue() +
                ", osType=" + osType.getValue() +
                ", pathToRunFile='" + pathToRunFile + '\'' +
                ", torrentFile=" + torrentFile +
                '}';
    }
}
