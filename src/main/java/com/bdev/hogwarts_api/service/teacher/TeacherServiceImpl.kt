package com.bdev.hogwarts_api.service.teacher

import com.bdev.hogwarts_api.dao.TeacherDao
import com.bdev.hogwarts_api.data.converter.teacher.TeacherDtoConverter
import com.bdev.hogwarts_api.data.converter.teacher.TeacherModelConverter
import com.bdev.hogwarts_api.data.dto.teacher.Teacher
import com.bdev.hogwarts_api.utils.EncodingUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
class TeacherServiceImpl : TeacherService {
    @Autowired
    private lateinit var teacherDao: TeacherDao

    override fun getAllTeachers(): List<Teacher> {
        return teacherDao
                .findAll()
                .map { TeacherModelConverter.convert(it) }
    }

    override fun exists(id: Long): Boolean {
        return teacherDao.exists(id)
    }

    override fun getTeacherById(id: Long): Teacher? {
        return teacherDao
                .findOne(id)
                ?.let{ TeacherModelConverter.convert(it) }
    }

    override fun getTeacherByLogin(login: String): Teacher? {
        return teacherDao
                .findFirstByLogin(EncodingUtils.toBase64(login))
                ?.let{ TeacherModelConverter.convert(it) }
    }

    override fun createTeacher(teacher: Teacher): Long {
        if (teacher.id != null) {
            throw RuntimeException("Teacher id should be null during creation")
        }

        return teacherDao.save(TeacherDtoConverter.convert(teacher)).id ?: throw RuntimeException()
    }

    override fun editTeacher(teacher: Teacher) {
        if (!teacherDao.exists(teacher.id)) {
            throw RuntimeException(format("Teacher with id '%d' does not exist", teacher.id))
        }

        teacherDao.save(TeacherDtoConverter.convert(teacher))
    }

    override fun deleteTeacher(id: Long) {
        if (!teacherDao.exists(id)) {
            throw RuntimeException(format("Teacher with id '%d' does not exist", id))
        }

        teacherDao.delete(id)
    }
}
