package com.hacktues.api.service;

import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.DTO.MaterialResponse;

import java.util.List;

public interface MaterialService {
    List<MaterialResponse> getMaterialsBySubject(String subjectName);

}
