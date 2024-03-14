package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.entity.Assignment;
import com.hacktues.api.mapper.AssignmentMapper;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assigmentRepository;
    private final AssignmentMapper assignmentMapper;
    @Override
    public List<AssignmentResponse> getAssignmentsBySubject(String subjectName) {
        List<Assignment> assignments = assigmentRepository.findBySubjectName(subjectName);
        return assignmentMapper.toAssignmentResponseList(assignments);
    }
}
