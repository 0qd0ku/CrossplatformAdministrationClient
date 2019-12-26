package ru.vkr.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import ru.vkr.config.RootConfig;
import ru.vkr.model.SessionData;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RootRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootConfig.class);

    protected SessionData sessionData;
    protected RestTemplate restTemplate;

    RootRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        restTemplate.getInterceptors().add(this::intercept);
    }

    private ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        Arrays.stream(stackTraces).forEach(stackTraceElement -> setHeaderIfNeed(stackTraceElement, request));
        return execution.execute(request, body);
    }

    private void setHeaderIfNeed(StackTraceElement stackTraceElement, HttpRequest request) {
        try {
            Method[] methods = Class.forName(stackTraceElement.getClassName()).getMethods();
            for (Method method : methods) {
                WithoutAuth annotation = method.getDeclaredAnnotation(WithoutAuth.class);
                if (annotation != null && method.equals(methods[methods.length - 2])) { //проверяем что в стеке вызовов предыдущий метод от текущего был помечен этой аннотацией
                    if (!annotation.offAuth() && sessionData != null) {
                        request.getHeaders().set("Authentication", sessionData.getToken());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error while add headers", e);
        }
    }
}
