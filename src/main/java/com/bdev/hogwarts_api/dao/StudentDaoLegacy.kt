package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student.StudentModelLegacy
import org.springframework.data.jpa.repository.JpaRepository

interface StudentDaoLegacy : JpaRepository<StudentModelLegacy, Long>
