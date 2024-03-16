package com.hacktues.api.controller;

import com.hacktues.api.client.WinstonClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final WinstonClient winstonClient;

    @GetMapping("/ai")
    public ResponseEntity<Double> checkForAi(String text) {
        return ResponseEntity.ok(winstonClient.checkForAi(text));
    }

    @GetMapping("/plagiarism")
    public ResponseEntity<List<String>> checkForPlagiarism(String text) {
        return ResponseEntity.ok(winstonClient.checkForPlagiarism(text));
    }
}
