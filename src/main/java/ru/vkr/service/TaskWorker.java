package ru.vkr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.vkr.model.TaskData;
import ru.vkr.model.TaskIdPackDto;
import ru.vkr.service.rest.RestService;
import ru.vkr.util.ClientSystemInformationUtils;

import java.util.List;

@Service
public class TaskWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskWorker.class);
    private RestService restService;

    @Autowired
    public TaskWorker(RestService restService) {
        this.restService = restService;
    }


    public void work() throws InterruptedException {
        try {
            restService.checkin(ClientSystemInformationUtils.CLIENT_DATA);
            TaskIdPackDto taskIdPackDto = restService.getTaskIds();
            List<Long> taskDataList = taskIdPackDto.getTaskIdList();
            taskDataList.forEach(this::workByTaskId);
        } catch (HttpClientErrorException clEx) {
            if (clEx.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                LOGGER.error("Auth error. Service response code: " + clEx.getStatusCode()
                        + ". Response message: " + clEx.getResponseBodyAsString());
                Thread.sleep(2000);
                work();
            }
        }
    }

    public void workByTaskId(Long taskId) {
        TaskData taskData = restService.getTaskDataById(taskId);
    }
}
