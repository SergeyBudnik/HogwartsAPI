package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentStatusDao : JpaRepository<StudentStatusModel, Long> {
    fun findAllByStudentLogin(studentLogin: String): List<StudentStatusModel>
    fun findTopByStudentLoginOrderByCreationTimeDesc(studentLogin: String): StudentStatusModel?
}
