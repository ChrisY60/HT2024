package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.AssignmentCreateRequest;
import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.entity.*;
import com.hacktues.api.mapper.AssignmentMapper;
import com.hacktues.api.repository.AssignmentRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assigmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StorageServiceImpl storageService;

    @Override
    public List<AssignmentResponse> getAssignmentsBySubjectId(Long subjectId) {
        List<Assignment> assignments = assigmentRepository.findBySubjectId(subjectId);
        return assignmentMapper.toAssignmentResponseList(assignments);
    }

    @Override
    public void createAssignment(Long subjectId, AssignmentCreateRequest assignmentCreateRequest, List<MultipartFile> files) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Teacher teacher = teacherRepository.findTeacherByUserId(user.getId());
        Assignment assignment = assignmentMapper.toAssignment(assignmentCreateRequest);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        assignment.setSubject(subject);
        assignment.setTeacher(teacher);

        List<FilePath> filePaths = files.stream()
                .map(file -> {
                    FilePath filePath = new FilePath();
                    filePath.setPath(storageService.uploadFile(
                                    file,
                                    user.getSchool() + "-" + user.getClass() + "-" + UUID.randomUUID()
                            )
                    );
                    return filePath;
                })
                .toList();
        assignment.setFilePaths(filePaths);

        assigmentRepository.save(assignment);
    }
}
