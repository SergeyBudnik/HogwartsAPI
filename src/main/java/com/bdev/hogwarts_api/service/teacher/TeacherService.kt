package com.bdev.hogwarts_api.service.teacher

import com.bdev.hogwarts_api.data.dto.teacher.Teacher

interface TeacherService {
    fun getAllTeachers(): List<Teacher>
    fun exists(id: Long): Boolean
    fun getTeacherById(id: Long): Teacher?
    fun getTeacherByLogin(login: String): Teacher?
    fun createTeacher(teacher: Teacher): Long
    fun editTeacher(teacher: Teacher)
    fun deleteTeacher(id: Long)
}
