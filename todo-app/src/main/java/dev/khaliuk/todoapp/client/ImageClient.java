package dev.khaliuk.todoapp.client;

import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Service
public class ImageClient {
    private static final String BASE_URL = "https://picsum.photos/400";

    private final RestClient restClient;

    public ImageClient() {
        restClient = initRestClient();
    }

    public byte[] getNewImage() {
        return restClient.get().retrieve().body(byte[].class);
    }

    private RestClient initRestClient() {
        var httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
        return RestClient.builder()
            .requestFactory(new JdkClientHttpRequestFactory(httpClient))
            .configureMessageConverters(config -> config.addCustomConverter(new ByteArrayHttpMessageConverter()))
            .baseUrl(BASE_URL)
            .build();
    }
}
