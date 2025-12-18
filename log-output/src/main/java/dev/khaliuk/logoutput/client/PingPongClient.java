package dev.khaliuk.logoutput.client;

import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PingPongClient {
    private static final String BASE_URL = "http://ping-pong:1234";
    private final RestClient restClient;

    public PingPongClient() {
        restClient = initRestClient();
    }

    public String getPings() {
        return restClient.get()
            .uri("/pings")
            .retrieve()
            .body(String.class);
    }

    private RestClient initRestClient() {
        return RestClient.builder()
            .requestFactory(new JdkClientHttpRequestFactory())
            .configureMessageConverters(config -> config.addCustomConverter(new StringHttpMessageConverter()))
            .baseUrl(BASE_URL)
            .build();
    }
}
