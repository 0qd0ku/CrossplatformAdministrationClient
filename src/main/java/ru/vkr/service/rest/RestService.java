package ru.vkr.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vkr.model.TaskPackDto;


@Service
public class RestService {

    private RestTemplate restTemplate;

    private String serviceUrl;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TaskPackDto postTaskRequest() {
        final String endPointUrl = serviceUrl;
        return getRequest(endPointUrl, TaskPackDto.class);
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
