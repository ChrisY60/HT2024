package com.hacktues.api.service;

public interface ExportService {
    byte[] exportGradesFromSubjectToExcel(Long subjectId);
}
