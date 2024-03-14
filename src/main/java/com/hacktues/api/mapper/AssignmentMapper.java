package com.hacktues.api.mapper;

import com.hacktues.api.DTO.AssignmentResponse;
import com.hacktues.api.entity.Assignment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface AssignmentMapper {
    AssignmentResponse toAssignmentResponse(Assignment assignment);
    List<AssignmentResponse> toAssignmentResponseList(List<Assignment> assignments);
}
