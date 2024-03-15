package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.MaterialCreateRequest;
import com.hacktues.api.DTO.MaterialResponse;
import com.hacktues.api.entity.*;
import com.hacktues.api.mapper.MaterialMapper;
import com.hacktues.api.repository.MaterialRepository;
import com.hacktues.api.repository.SubjectRepository;
import com.hacktues.api.repository.TeacherRepository;
import com.hacktues.api.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final StorageServiceImpl storageService;

    @Override
    public List<MaterialResponse> getMaterialsBySubjectId(Long subjectId) {
        List<Material> materials = materialRepository.findBySubjectId(subjectId);
        return materialMapper.toMaterialResponseList(materials);
    }

    @Override
    public void createMaterial(Long subjectId, MaterialCreateRequest materialCreateRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Teacher teacher = teacherRepository.findTeacherByUserId(user.getId());
        Material material = materialMapper.toMaterial(materialCreateRequest);
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        material.setTeacher(teacher);
        material.setSubject(subject);

        List<FilePath> filePaths = materialCreateRequest.getFiles().stream()
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
        material.setFilePaths(filePaths);
        material.setDate(new Date());

        materialRepository.save(material);
    }
}
