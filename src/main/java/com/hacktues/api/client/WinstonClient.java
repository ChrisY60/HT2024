package com.hacktues.api.client;

import com.hacktues.api.DTO.WinstonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class WinstonClient {
    @Value("${winston.key}")
    private String apiKey;

    public void checkForAi(String text) {

        RestClient restClient = RestClient.create();

        WinstonRequest body = new WinstonRequest(text);
        ResponseEntity<String> result = restClient.post()
                .uri("https://api.gowinston.ai/functions/v1/predict")
                .header("Authorization", "Bearer " + apiKey)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        System.out.println(result.getBody());
    }

    public void checkForPlagiarism(String text) {
        RestClient restClient = RestClient.create();

        WinstonRequest body = new WinstonRequest(text);
        ResponseEntity<String> result = restClient.post()
                .uri("https://api.gowinston.ai/functions/v1/plagiarism")
                .header("Authorization", "Bearer " + apiKey)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        System.out.println(result.getBody());
    }
}
