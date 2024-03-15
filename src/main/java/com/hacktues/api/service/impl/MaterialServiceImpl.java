package com.hacktues.api.service.impl;

import com.hacktues.api.DTO.MaterialResponse;
import com.hacktues.api.entity.Material;
import com.hacktues.api.mapper.MaterialMapper;
import com.hacktues.api.repository.MaterialRepository;
import com.hacktues.api.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public List<MaterialResponse> getMaterialsBySubjectId(Long subjectId) {
        List<Material> materials = materialRepository.findBySubjectId(subjectId);
        return materialMapper.toMaterialResponseList(materials);
    }
}
