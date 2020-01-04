package ru.vkr.model;

import java.util.List;
import java.util.Objects;

/**
 * Класс запрос клиент содержит информацию о клиенте
 */
public class ClientData {
    private String hostname;
    private OS os;
    private OSType osType;
    private String macAddr;

    public ClientData() {
    }

    public ClientData(String hostname, OS os, OSType osType, String macAddr) {
        this.hostname = hostname;
        this.os = os;
        this.osType = osType;
        this.macAddr = macAddr;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "hostname='" + hostname + '\'' +
                ", os=" + os +
                ", osType=" + osType +
                ", macAddr='" + macAddr + '\'' +
                '}';
    }
}
