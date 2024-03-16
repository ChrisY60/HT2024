package com.hacktues.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "materials")
@Data
@Cacheable
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "material_files",
            joinColumns = @JoinColumn(name = "material_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private List<FilePath> filePaths;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;
}
