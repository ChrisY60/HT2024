package com.hacktues.api.mapper;

import com.hacktues.api.DTO.MaterialCreateRequest;
import com.hacktues.api.DTO.MaterialCreateRequestTemp;
import com.hacktues.api.DTO.MaterialResponse;
import com.hacktues.api.entity.Material;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface MaterialMapper {
    MaterialResponse toMaterialResponse(Material material);
    List<MaterialResponse> toMaterialResponseList(List<Material> materials);
    Material toMaterial(MaterialCreateRequest materialCreateRequest);
    Material toMaterial(MaterialCreateRequestTemp materialCreateRequest);
}
