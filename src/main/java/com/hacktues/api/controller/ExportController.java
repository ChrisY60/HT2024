package com.hacktues.api.controller;

import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.service.ExportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/export")
public class ExportController {
    private final ExportService exportService;
    private final SubjectRepository subjectRepository;

    @GetMapping(
            value = "/excel/{subjectId}",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    )
    public ResponseEntity<byte[]> exportGradesFromSubjectToExcel(@PathVariable Long subjectId) {
        String fileName = "grades-" + subjectRepository.getReferenceById(subjectId).getName() + ".xlsx";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(fileName, fileName);

        return new ResponseEntity<>(exportService.exportGradesFromSubjectToExcel(subjectId), headers, HttpStatus.OK);
    }
}
