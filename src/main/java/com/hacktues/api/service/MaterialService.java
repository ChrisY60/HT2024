package com.hacktues.api.service;

import com.hacktues.api.DTO.MaterialCreateRequest;
import com.hacktues.api.DTO.MaterialResponse;

import java.util.List;

public interface MaterialService {
    List<MaterialResponse> getMaterialsBySubjectId(Long subjectId);

    void createMaterial(Long subjectId, MaterialCreateRequest materialCreateRequest);
}
