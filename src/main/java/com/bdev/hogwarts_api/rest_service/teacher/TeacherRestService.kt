package com.bdev.hogwarts_api.rest_service.teacher

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.teacher.Teacher

interface TeacherRestService {
    fun getAllTeachers(userInfo: MunicipaliUserInfo): List<Teacher>
    fun getTeacherById(userInfo: MunicipaliUserInfo, id: Long): Teacher
    fun getTeacherByLogin(userInfo: MunicipaliUserInfo, login: String): Teacher
    fun createTeacher(userInfo: MunicipaliUserInfo, teacher: Teacher): Long
    fun editTeacher(userInfo: MunicipaliUserInfo, teacher: Teacher)
    fun deleteTeacher(userInfo: MunicipaliUserInfo, id: Long)
}
