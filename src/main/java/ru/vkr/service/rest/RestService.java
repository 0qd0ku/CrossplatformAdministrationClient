package ru.vkr.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.vkr.model.TaskData;
import ru.vkr.model.TaskIdPackDto;

import java.util.HashMap;
import java.util.Map;


@Service
public class RestService {

    private RestTemplate restTemplate;

    @Value("${service.task.url}")
    private String serviceUrl;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TaskIdPackDto getTaskIds(Long clientId) {
        final String endPointUrl = serviceUrl;
        UriComponents url = buildUrl("clientId", clientId, endPointUrl);
        return getRequest(url.toUriString(), TaskIdPackDto.class);
    }

    public TaskData getTaskDataById(Long taskId) {
        final String endPointUrl = serviceUrl;
        UriComponents url = buildUrl("taskId", taskId, endPointUrl);
        return getRequest(url.toUriString(), TaskData.class);
    }

    /**
     * Сгенерировать URL с парамтерами для get запроса
     * @param key        ключ для подстановки в адрес строки
     * @param param      парамтеры запроса
     * @param requestUrl конечная точка запроса
     * @return URL с парамтерами для get запроса
     */
    private UriComponents buildUrl(Object key, Object param, String requestUrl) {
        Map<Object, Object> urlParams = new HashMap<>();
        urlParams.put(key, param);
        return UriComponentsBuilder.fromHttpUrl(requestUrl).buildAndExpand(urlParams);
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
