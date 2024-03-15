package com.hacktues.api.repository;

import com.hacktues.api.entity.FilePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FilePath, Long> {

}
