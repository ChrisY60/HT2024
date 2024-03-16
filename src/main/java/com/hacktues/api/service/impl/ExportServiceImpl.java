package com.hacktues.api.service.impl;

import com.hacktues.api.entity.Grade;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.User;
import com.hacktues.api.enums.GradeType;
import com.hacktues.api.repository.GradeRepository;
import com.hacktues.api.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {
    private final GradeRepository gradeRepository;

    @Override
    public byte[] exportGradesFromSubjectToExcel(Long subjectId) {
        List<Grade> grades = gradeRepository.findAllBySubjectId(subjectId);
        Map<Student, List<Grade>> gradesFirstTerm = grades.stream()
                .filter(grade -> grade.getGradeType().equals(GradeType.FIRST_TERM)
                        || grade.getGradeType().equals(GradeType.FIRST_TERM_FINAL))
                .collect(groupingBy(
                        Grade::getStudent
                )
            );

        Map<Student, List<Grade>> gradesSecondTerm = grades.stream()
                .filter(grade -> grade.getGradeType().equals(GradeType.SECOND_TERM)
                        || grade.getGradeType().equals(GradeType.SECOND_TERM_FINAL))
                .collect(groupingBy(
                        Grade::getStudent
                )
            );


        Map<Student, List<Grade>> gradesYear = grades.stream()
                .filter(grade -> grade.getGradeType().equals(GradeType.YEARLY))
                .collect(groupingBy(
                        Grade::getStudent
                )
            );

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Grades(term 1)");
            int cellNum = 0;
            Row headerRow = sheet.createRow(cellNum);
            headerRow.createCell(cellNum++).setCellValue("Email");
            headerRow.createCell(cellNum++).setCellValue("First Name");
            headerRow.createCell(cellNum++).setCellValue("Middle Name");
            headerRow.createCell(cellNum++).setCellValue("Last Name");
            headerRow.createCell(cellNum++).setCellValue("Class");
            headerRow.createCell(cellNum++).setCellValue("№");

            int maxGradeCount = gradesFirstTerm.values().stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);

            for (int i = 0; i < maxGradeCount - 1; i++) {
                headerRow.createCell(i + cellNum).setCellValue("Grade");
            }
            headerRow.createCell(maxGradeCount + cellNum).setCellValue("Term Grade");

            List<Map.Entry<Student, List<Grade>>> sortedStudents = new ArrayList<>(gradesFirstTerm.entrySet());
            sortedStudents.sort(Comparator.comparing((Map.Entry<Student, List<Grade>> entry) -> {
                Student student = entry.getKey();
                return student.getStudentClass().getName() + student.getClassNumber();
            }));

            int rowNum = 1;
            for (Map.Entry<Student, List<Grade>> entry : sortedStudents) {
                Student student = entry.getKey();
                User user = student.getUser();
                List<Grade> userGrades = entry.getValue();

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getEmail());
                row.createCell(1).setCellValue(user.getFirstName());
                row.createCell(2).setCellValue(user.getMiddleName());
                row.createCell(3).setCellValue(user.getLastName());
                row.createCell(4).setCellValue(student.getStudentClass().getName());
                row.createCell(5).setCellValue(student.getClassNumber());

                for (int i = 0; i < userGrades.size() - 1; i++) {
                    row.createCell(cellNum + i).setCellValue(userGrades.get(i).getGrade());
                }

                row.createCell(maxGradeCount + cellNum).setCellValue(userGrades.get(userGrades.size() - 1).getGrade());
            }

            Sheet sheet2 = workbook.createSheet("Grades(term 2)");
            cellNum = 0;
            headerRow = sheet2.createRow(cellNum);
            headerRow.createCell(cellNum++).setCellValue("Email");
            headerRow.createCell(cellNum++).setCellValue("First Name");
            headerRow.createCell(cellNum++).setCellValue("Middle Name");
            headerRow.createCell(cellNum++).setCellValue("Last Name");
            headerRow.createCell(cellNum++).setCellValue("Class");
            headerRow.createCell(cellNum++).setCellValue("№");

            maxGradeCount = gradesSecondTerm.values().stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);

            for (int i = 0; i < maxGradeCount - 1; i++) {
                headerRow.createCell(i + cellNum).setCellValue("Grade");
            }
            headerRow.createCell(maxGradeCount + cellNum).setCellValue("Term Grade");

            sortedStudents = new ArrayList<>(gradesSecondTerm.entrySet());
            sortedStudents.sort(Comparator.comparing((Map.Entry<Student, List<Grade>> entry) -> {
                Student student = entry.getKey();
                return student.getStudentClass().getName() + student.getClassNumber();
            }));

            rowNum = 1;
            for (Map.Entry<Student, List<Grade>> entry : sortedStudents) {
                Student student = entry.getKey();
                User user = student.getUser();
                List<Grade> userGrades = entry.getValue();

                Row row = sheet2.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getEmail());
                row.createCell(1).setCellValue(user.getFirstName());
                row.createCell(2).setCellValue(user.getMiddleName());
                row.createCell(3).setCellValue(user.getLastName());
                row.createCell(4).setCellValue(student.getStudentClass().getName());
                row.createCell(5).setCellValue(student.getClassNumber());

                for (int i = 0; i < userGrades.size() - 1; i++) {
                    row.createCell(cellNum + i).setCellValue(userGrades.get(i).getGrade());
                }

                row.createCell(maxGradeCount + cellNum).setCellValue(userGrades.get(userGrades.size() - 1).getGrade());
            }

            Sheet sheet3 = workbook.createSheet("Grades(yearly)");
            cellNum = 0;
            headerRow = sheet3.createRow(cellNum);
            headerRow.createCell(cellNum++).setCellValue("Email");
            headerRow.createCell(cellNum++).setCellValue("First Name");
            headerRow.createCell(cellNum++).setCellValue("Middle Name");
            headerRow.createCell(cellNum++).setCellValue("Last Name");
            headerRow.createCell(cellNum++).setCellValue("Class");
            headerRow.createCell(cellNum++).setCellValue("№");
            headerRow.createCell(cellNum).setCellValue("Grade");

            sortedStudents = new ArrayList<>(gradesYear.entrySet());
            sortedStudents.sort(Comparator.comparing((Map.Entry<Student, List<Grade>> entry) -> {
                Student student = entry.getKey();
                return student.getStudentClass().getName() + student.getClassNumber();
            }));

            rowNum = 1;
            for (Map.Entry<Student, List<Grade>> entry : sortedStudents) {
                Student student = entry.getKey();
                User user = student.getUser();
                List<Grade> userGrades = entry.getValue();

                Row row = sheet3.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getEmail());
                row.createCell(1).setCellValue(user.getFirstName());
                row.createCell(2).setCellValue(user.getMiddleName());
                row.createCell(3).setCellValue(user.getLastName());
                row.createCell(4).setCellValue(student.getStudentClass().getName());
                row.createCell(5).setCellValue(student.getClassNumber());
                row.createCell(6).setCellValue(userGrades.get(0).getGrade());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to export grades to Excel!");
        }
    }
}
