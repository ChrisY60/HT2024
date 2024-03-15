package com.hacktues.api.service.impl;

import com.hacktues.api.entity.Grade;
import com.hacktues.api.entity.Student;
import com.hacktues.api.entity.User;
import com.hacktues.api.repository.GradeRepository;
import com.hacktues.api.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
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
        Map<Student, List<Grade>> gradesByStudent = grades.stream()
                .collect(groupingBy(
                        Grade::getStudent
                )
            );

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Grades");
            int cellNum = 0;
            Row headerRow = sheet.createRow(cellNum);
            headerRow.createCell(cellNum++).setCellValue("Email");
            headerRow.createCell(cellNum++).setCellValue("First Name");
            headerRow.createCell(cellNum++).setCellValue("Middle Name");
            headerRow.createCell(cellNum++).setCellValue("Last Name");
            headerRow.createCell(cellNum++).setCellValue("Class");
            headerRow.createCell(cellNum++).setCellValue("№");

            int maxGradeCount = gradesByStudent.values().stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);

            for (int i = 0; i < maxGradeCount; i++) {
                headerRow.createCell(i + cellNum).setCellValue("Grade " + (i + 1));
            }
            headerRow.createCell(maxGradeCount + cellNum).setCellValue("Average Grade");

            List<Map.Entry<Student, List<Grade>>> sortedStudents = new ArrayList<>(gradesByStudent.entrySet());
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

                double sumGrades = 0.0;
                for (int i = 0; i < userGrades.size(); i++) {
                    row.createCell(cellNum + i).setCellValue(userGrades.get(i).getGrade());
                    sumGrades += userGrades.get(i).getGrade();
                }

                DecimalFormat df = new DecimalFormat("#.##");
                double finalGrade = Double.parseDouble(df.format(sumGrades / userGrades.size()));
                row.createCell(maxGradeCount + cellNum).setCellValue(finalGrade);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to export grades to Excel!");
        }
    }
}
