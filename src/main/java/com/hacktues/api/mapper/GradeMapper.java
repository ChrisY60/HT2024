package com.hacktues.api.mapper;

import com.hacktues.api.DTO.GradeCreateRequest;
import com.hacktues.api.DTO.GradeResponse;
import com.hacktues.api.entity.Grade;
import com.hacktues.api.entity.Teacher;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface GradeMapper {
    List<GradeResponse> toGradeResponseList(List<Grade> grades);

    GradeResponse toGradeResponse(Grade grade);

    Grade toGrade(GradeCreateRequest gradeCreateRequest);
}
