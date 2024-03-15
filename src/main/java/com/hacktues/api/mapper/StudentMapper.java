package com.hacktues.api.mapper;

import com.hacktues.api.DTO.StudentRegisterRequest;
import com.hacktues.api.DTO.StudentResponse;
import com.hacktues.api.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface StudentMapper {
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "middleName", target = "user.middleName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "email", target = "user.email")
    @Mapping(target = "studentClass", ignore = true)
    Student fromRegisterRequest(StudentRegisterRequest studentRegisterRequest);

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.middleName", target = "middleName")
    @Mapping(source = "user.lastName", target = "lastName")
    StudentResponse toStudentResponse(Student student);
    List<StudentResponse> toStudentResponseList(List<Student> students);
}
