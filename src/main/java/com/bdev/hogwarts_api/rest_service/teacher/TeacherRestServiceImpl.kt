package com.bdev.hogwarts_api.rest_service.teacher

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.teacher.Teacher
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.teacher.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class TeacherRestServiceImpl : TeacherRestService {
    @Autowired
    private lateinit var teacherService: TeacherService

    @Transactional(readOnly = true)
    override fun getAllTeachers(userInfo: MunicipaliUserInfo): List<Teacher> {
        return teacherService.getAllTeachers()
    }

    @Transactional(readOnly = true)
    override fun getTeacherById(userInfo: MunicipaliUserInfo, id: Long): Teacher {
        return teacherService
                .getTeacherById(id)
                ?: throw HttpEntityNotFoundException("Teacher with id '%d' does not exist", id)
    }

    @Transactional(readOnly = true)
    override fun getTeacherByLogin(userInfo: MunicipaliUserInfo, login: String): Teacher {
        return teacherService
                .getTeacherByLogin(login)
                ?: throw HttpEntityNotFoundException("Teacher with login '%s' does not exist", login)
    }

    @Transactional
    override fun createTeacher(userInfo: MunicipaliUserInfo, teacher: Teacher): Long {
        if (teacher.id != null) {
            throw HttpEntityIllegalStateException("Teacher id should be null during creation")
        }

        return teacherService.createTeacher(teacher)
    }

    @Transactional
    override fun editTeacher(
            userInfo: MunicipaliUserInfo,
            teacher: Teacher
    ) {
        val teacherId = teacher.id
                ?: throw HttpEntityIllegalStateException("Teacher id should be non-null during edit")

        if (!teacherService.exists(teacherId)) {
            throw HttpEntityNotFoundException("Teacher with id '%d' does not exist", teacherId)
        }

        teacherService.editTeacher(teacher)
    }

    @Transactional
    override fun deleteTeacher(userInfo: MunicipaliUserInfo, id: Long) {
        if (!teacherService.exists(id)) {
            throw HttpEntityNotFoundException("Teacher with id '%d' does not exist", id)
        }

        teacherService.deleteTeacher(id)
    }
}
