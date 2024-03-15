package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.AssignmentCreateRequest;
import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.entity.Assignment;
import com.hacktues.api.entity.Subject;
import com.hacktues.api.entity.Teacher;
import com.hacktues.api.entity.User;
import com.hacktues.api.mapper.AssignmentMapper;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assigmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<AssignmentResponse> getAssignmentsBySubjectId(Long subjectId) {
        List<Assignment> assignments = assigmentRepository.findBySubjectId(subjectId);
        return assignmentMapper.toAssignmentResponseList(assignments);
    }

    @Override
    public void createAssignment(Long subjectId, AssignmentCreateRequest assignmentCreateRequest) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Teacher teacher = teacherRepository.findTeacherByUserId(currentUser.getId());
        Assignment assignment = assignmentMapper.toAssignment(assignmentCreateRequest);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        assignment.setSubject(subject);
        assignment.setTeacher(teacher);

        assigmentRepository.save(assignment);
    }
}
