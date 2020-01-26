package ru.vkr.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vkr.model.ClientData;
import ru.vkr.model.SessionData;
import ru.vkr.model.TaskData;
import ru.vkr.model.TaskStatus;
import ru.vkr.model.dto.ClientTaskStatusDto;
import ru.vkr.model.dto.SimpleClientTaskDataDto;
import ru.vkr.model.dto.TaskPackDto;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class RestService extends RootRestService {


    @Value("${service.task.url}")
    private String serviceUrl;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @CheckAuthorisation(disable = true)
    public void checkin(ClientData clientData) {
        String endPointUrl = serviceUrl + "/api/client/checkin";
        sessionData = postRequest(endPointUrl, clientData, SessionData.class);
    }

    public TaskPackDto getTaskIds() {
        String endPointUrl = serviceUrl + "/api/client/tasks";
        UriComponents url = buildUrl("clientId", sessionData.getClientId(), endPointUrl);
        return getRequest(url.toUriString(), TaskPackDto.class);
    }

    public TaskData getTaskDataById(Long taskId) {
        String endPointUrl = serviceUrl + "/api/client/get-task";
        UriComponents url = buildUrl("taskId", taskId, endPointUrl);
        return getRequest(url.toUriString(), TaskData.class);
    }

    public void updateTaskStatus(TaskStatus taskStatus, Long taskId) {
        String endpointUrl = serviceUrl + "/api/client/task/status-toggle";
        ClientTaskStatusDto clientTaskStatusDto = createSimplClStatData(taskStatus, taskId);
        postRequest(endpointUrl, clientTaskStatusDto, String.class);
    }

    private ClientTaskStatusDto createSimplClStatData(TaskStatus taskStatus, Long taskId) {
        ClientTaskStatusDto clientTaskStatusDto = new ClientTaskStatusDto();
        clientTaskStatusDto.setTaskStatus(taskStatus);
        SimpleClientTaskDataDto simpleClientTaskDataDto = new SimpleClientTaskDataDto();
        simpleClientTaskDataDto.setClientId(sessionData.getClientId());
        simpleClientTaskDataDto.setTaskId(taskId);
        clientTaskStatusDto.setClientTaskData(simpleClientTaskDataDto);
        return clientTaskStatusDto;
    }

    /**
     * Сгенерировать URL с парамтерами для get запроса
     * @param key        ключ для подстановки в адрес строки
     * @param param      парамтеры запроса
     * @param requestUrl конечная точка запроса
     * @return URL с парамтерами для get запроса
     */
    private UriComponents buildUrl(Object key, Object param, String requestUrl) {
        return UriComponentsBuilder.fromHttpUrl(requestUrl).queryParam(key.toString(), param.toString()).build();
    }

    /**
     * Функция вызывает метод {@link RestTemplate#postForObject} для отправки post запроса
     * @param url           конечный адрес запроса
     * @param request       запрос
     * @param responseType  тип ответа
     * @param <T>           параметризированный тип, для типа ответа
     * @return ответ
     */
    private <T> T postRequest(String url, Object request, Class<T> responseType) {
       return restTemplate.postForObject(url, request, responseType);
    }

    /**
     * Функция вызывает метод {@link RestTemplate#getForObject} для отправки get запроса
     * @param url           конечный адрес запроса
     * @param responseType  тип ответа
     * @param <T>           параметризированный тип, для типа ответа
     * @return ответ
     */
    private <T> T getRequest(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

}
