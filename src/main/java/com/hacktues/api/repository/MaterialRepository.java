package com.hacktues.api.repository;

import com.hacktues.api.entity.Assignment;
import com.hacktues.api.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findBySubjectName(String subjectName);
}
