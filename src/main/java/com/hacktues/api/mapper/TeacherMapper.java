package com.hacktues.api.mapper;

import com.hacktues.api.DTO.TeacherRegisterRequest;
import com.hacktues.api.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = TeacherMapper.class)
public interface TeacherMapper {
    TeacherMapper TEACHER_MAPPER = Mappers.getMapper(TeacherMapper.class);
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "middleName", target = "user.middleName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    Teacher fromRegisterRequest(TeacherRegisterRequest teacherRegisterRequest);
}
