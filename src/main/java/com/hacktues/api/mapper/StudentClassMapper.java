package com.hacktues.api.mapper;

import com.hacktues.api.DTO.StudentClassCreateRequest;
import com.hacktues.api.entity.StudentClass;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface StudentClassMapper {
    StudentClass fromRequest(StudentClassCreateRequest request);
}
