package com.hacktues.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "submissions")
@Data
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "submission")
    private List<FilePath> filePaths;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "comment")
    private String comment;

    @Column(name = "graded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gradedDate;

    @ManyToOne
    @JoinColumn(name = "assignment_id", referencedColumnName = "id", nullable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;
}
