package com.hacktues.api.mapper;

import com.hacktues.api.DTO.StudentRegisterRequest;
import com.hacktues.api.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = StudentMapper.class)
public interface StudentMapper {
    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "middleName", target = "user.middleName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    @Mapping(target = "studentClass", ignore = true)
    Student fromRegisterRequest(StudentRegisterRequest studentRegisterRequest);
}
