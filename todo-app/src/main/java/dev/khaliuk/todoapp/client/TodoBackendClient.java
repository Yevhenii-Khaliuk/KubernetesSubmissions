package dev.khaliuk.todoapp.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TodoBackendClient {
    private static final String BASE_URL = "http://todo-backend:2345/todos";

    private final RestClient restClient;

    public TodoBackendClient() {
        restClient = initRestClient();
    }

    public List<String> getAllTodos() {
        return restClient.get().retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    private RestClient initRestClient() {
        return RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory())
                .baseUrl(BASE_URL)
                .build();
    }
}
