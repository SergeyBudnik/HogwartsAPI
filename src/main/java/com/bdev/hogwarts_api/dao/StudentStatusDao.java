package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentStatusDao extends JpaRepository<StudentStatusModel, Long> {
    List<StudentStatusModel> findAllByStudentId(long studentId);
    StudentStatusModel findTopByStudentIdOrderByCreationTimeDesc(long studentId);
}
