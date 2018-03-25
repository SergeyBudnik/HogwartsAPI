package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.teacher.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<TeacherModel, Long> {
}
