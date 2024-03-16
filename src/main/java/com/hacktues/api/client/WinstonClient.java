package com.hacktues.api.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacktues.api.DTO.WinstonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WinstonClient {
    @Value("${winston.key}")
    private String apiKey;

    public double checkForAi(String text) {

        RestClient restClient = RestClient.create();

        WinstonRequest body = new WinstonRequest(text);
        ResponseEntity<String> result = restClient.post()
                .uri("https://api.gowinston.ai/functions/v1/predict")
                .header("Authorization", "Bearer " + apiKey)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        System.out.println(result.getBody());

        String response = result.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            return jsonNode.get("score").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response from Winston!");
        }
    }

    public List<String> checkForPlagiarism(String text) {
        RestClient restClient = RestClient.create();

        WinstonRequest body = new WinstonRequest(text);
        ResponseEntity<String> result = restClient.post()
                .uri("https://api.gowinston.ai/functions/v1/plagiarism")
                .header("Authorization", "Bearer " + apiKey)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        System.out.println(result.getBody());

        String response = result.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            List<JsonNode> results = jsonNode.findValues("url");
            if (results.isEmpty()) {
                return List.of();
            }

            return results.stream()
                    .map(JsonNode::asText)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response from Winston!");
        }
    }
}
