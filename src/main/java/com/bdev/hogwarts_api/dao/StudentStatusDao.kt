package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentStatusDao : JpaRepository<StudentStatusModel, Long> {
    fun findAllByStudentId(studentId: Long): List<StudentStatusModel>
    fun findTopByStudentIdOrderByCreationTimeDesc(studentId: Long): StudentStatusModel?
}
