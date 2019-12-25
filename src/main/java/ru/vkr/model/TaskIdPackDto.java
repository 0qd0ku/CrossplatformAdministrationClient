package ru.vkr.model;

import java.util.List;

public class TaskIdPackDto {
    List<Long> taskIdList;

    public List<Long> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(List<Long> taskIdList) {
        this.taskIdList = taskIdList;
    }

    @Override
    public String toString() {
        return "TaskIdPackDto{" +
                "taskIdList=" + taskIdList +
                '}';
    }
}
