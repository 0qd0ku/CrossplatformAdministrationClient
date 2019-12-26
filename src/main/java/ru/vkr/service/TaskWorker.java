package ru.vkr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vkr.model.TaskData;
import ru.vkr.model.TaskIdPackDto;
import ru.vkr.service.rest.RestService;

import java.util.List;

@Service
public class TaskWorker {

    private RestService restService;

    @Autowired
    public TaskWorker(RestService restService) {
        this.restService = restService;
    }


    public void work() {
//        restService.checkin();
        TaskIdPackDto taskIdPackDto = restService.getTaskIds();
        List<Long> taskDataList = taskIdPackDto.getTaskIdList();
        taskDataList.forEach(this::workByTaskId);
    }

    public void workByTaskId(Long taskId) {
        TaskData taskData = restService.getTaskDataById(taskId);
    }
}
