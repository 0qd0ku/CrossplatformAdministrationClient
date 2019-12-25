package ru.vkr.model;

import java.util.List;

/**
 * Объект оболочка для отправки списка задач наружу
 */
public class TaskPackDto {

    public TaskPackDto(List<TaskData> taskDatas) {
        this.taskDatas = taskDatas;
    }

    private List<TaskData> taskDatas;

    public List<TaskData> getTaskDatas() {
        return taskDatas;
    }

    public void setTaskDatas(List<TaskData> taskDatas) {
        this.taskDatas = taskDatas;
    }

    @Override
    public String toString() {
        return "TaskPackDto{" +
                "taskDatas=" + taskDatas +
                '}';
    }
}
