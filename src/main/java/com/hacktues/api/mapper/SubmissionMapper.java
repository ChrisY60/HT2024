package com.hacktues.api.mapper;

import com.hacktues.api.DTO.SubmissionRequest;
import com.hacktues.api.entity.Submission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SubmissionMapper {
    Submission toSubmission(SubmissionRequest submissionRequest);
}
