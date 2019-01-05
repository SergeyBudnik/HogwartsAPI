package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.teacher.TeacherModel
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherDao : JpaRepository<TeacherModel, Long> {
    fun findFirstByLogin(login: String): TeacherModel?
}
