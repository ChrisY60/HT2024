package com.hacktues.api.entity;

import com.hacktues.api.enums.GradeType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "grades")
@Data
@Cacheable
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "grade_type")
    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    @Column(name = "comment")
    private String comment;

    @Column(name = "graded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gradedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;
}
