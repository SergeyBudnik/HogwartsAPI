package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.student.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<StudentModel, Long> {
}
