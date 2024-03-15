package com.hacktues.api.mapper;

import com.hacktues.api.DTO.SchoolCreateRequest;
import com.hacktues.api.entity.School;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SchoolMapper {
    School fromRequest(SchoolCreateRequest request);
}
