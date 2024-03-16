package com.hacktues.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "submissions")
@Data
@Cacheable
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "handed_in")
    private boolean handedIn;

    @Column(name = "graded")
    private boolean graded;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "submission_files",
            joinColumns = @JoinColumn(name = "submission_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private List<FilePath> filePaths;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id", referencedColumnName = "id", nullable = true)
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", referencedColumnName = "id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;
}
