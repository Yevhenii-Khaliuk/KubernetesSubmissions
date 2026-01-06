package dev.khaliuk.todobackend.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WikipediaClient {
    private static final String BASE_URL = "https://en.wikipedia.org/wiki/Special:Random";

    private final RestClient restClient;

    public WikipediaClient() {
        restClient = initRestClient();
    }

    public String getRandomArticleUrl() {
        return restClient.get()
            .header(HttpHeaders.USER_AGENT, "Todo/0.1 (https://devopswithkubernetes.com)")
            .retrieve()
            .toBodilessEntity()
            .getHeaders()
            .getFirst(HttpHeaders.LOCATION);
    }

    private RestClient initRestClient() {
        return RestClient.builder()
            .requestFactory(new JdkClientHttpRequestFactory())
            .baseUrl(BASE_URL)
            .build();
    }
}
