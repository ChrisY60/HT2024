package com.hacktues.api.service;

import com.hacktues.api.DTO.SubjectCreateRequest;
import com.hacktues.api.DTO.SubjectCreateResponse;
import com.hacktues.api.DTO.SubjectResponse;

import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getSubjects();
}
