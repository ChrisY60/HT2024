package com.hacktues.api.mapper;

import com.hacktues.api.DTO.TeacherRegisterRequest;
import com.hacktues.api.DTO.TeacherResponse;
import com.hacktues.api.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TeacherMapper {
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "middleName", target = "user.middleName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    Teacher fromRegisterRequest(TeacherRegisterRequest teacherRegisterRequest);

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.middleName", target = "middleName")
    @Mapping(source = "user.lastName", target = "lastName")
    TeacherResponse toTeacherResponse(Teacher teacher);
}
