package com.hacktues.api.mapper;

import com.hacktues.api.DTO.SubjectCreateResponse;
import com.hacktues.api.DTO.SubjectResponse;
import com.hacktues.api.entity.Subject;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SubjectMapper {
    SubjectResponse toSubjectResponse(Subject subject);
    List<SubjectResponse> toSubjectResponseList(List<Subject> subjects);

    SubjectCreateResponse toSubjectCreateResponse(Subject subject);
}
