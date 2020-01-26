package ru.vkr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.vkr.model.TaskData;
import ru.vkr.model.TaskStatus;
import ru.vkr.model.dto.SimpleClientTaskDataDto;
import ru.vkr.model.dto.TaskPackDto;
import ru.vkr.service.rest.RestService;
import ru.vkr.util.ClientSystemInformationUtils;

import java.util.List;

@Service
public class TaskWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskWorker.class);
    private RestService restService;
    private final TaskLoader taskLoader;

    @Autowired
    public TaskWorker(RestService restService, TaskLoader taskLoader) {
        this.restService = restService;
        this.taskLoader = taskLoader;
    }


    public void work() throws InterruptedException {
        try {
            restService.checkin(ClientSystemInformationUtils.CLIENT_DATA);
            TaskPackDto taskIdPackDto = restService.getTaskIds();
            List<Long> taskDataList = taskIdPackDto.getTaskDatas();
            taskDataList.forEach(this::workByTaskId);
        } catch (HttpClientErrorException clEx) {
            if (clEx.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                LOGGER.error("Auth error. Service response code: " + clEx.getStatusCode()
                        + ". Response message: " + clEx.getResponseBodyAsString());
                Thread.sleep(2000);
                work();
            }
            LOGGER.error("Error requested service, response code: " + clEx.getStatusCode().toString()
                    + " and response body: " + clEx.getResponseBodyAsString());
        }
    }

    private void workByTaskId(Long taskId) {
        LOGGER.debug("Getting tasks by id: {}", taskId);
        TaskData taskData = restService.getTaskDataById(taskId);
        LOGGER.debug("Load task: {}", taskData);
        TaskStatus taskStatus = TaskStatus.DOWNLOADING;
        restService.updateTaskStatus(taskStatus, taskId);
        try {
            taskLoader.loadTorrent(taskData);
            taskStatus = TaskStatus.INSTALLING;
        } catch (Exception ex) {
            LOGGER.error("Error while work task: " + taskId, ex);
            taskStatus = TaskStatus.ERROR_INSTALLING;
        } finally {
            restService.updateTaskStatus(taskStatus, taskId);
        }
    }
}
