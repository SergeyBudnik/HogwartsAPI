package com.bdev.hogwarts_api.service.student

import com.bdev.hogwarts_api.dao.StudentDao
import com.bdev.hogwarts_api.dao.StudentStatusDao
import com.bdev.hogwarts_api.data.converter.student.StudentDtoConverter
import com.bdev.hogwarts_api.data.converter.student.StudentModelConverter
import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.data.model.student.StudentModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
class StudentServiceImpl : StudentService {
    @Autowired
    private lateinit var studentDao: StudentDao
    @Autowired
    private lateinit var studentStatusDao: StudentStatusDao

    override fun getAllStudents(): List<Student> {
        return studentDao
                .findAll()
                .map { getStudent(it) }
    }

    override fun getStudentById(studentId: Long): Student? {
        return studentDao
                .findOne(studentId)
                ?.let { getStudent(it) }
    }

    private fun getStudent(studentModel: StudentModel): Student {
        val id = studentModel.id ?: throw RuntimeException()

        return StudentModelConverter.convert(
                studentModel,
                studentStatusDao.findTopByStudentIdOrderByCreationTimeDesc(id) ?: throw RuntimeException()
        )
    }

    override fun exists(studentId: Long): Boolean {
        return studentDao.exists(studentId)
    }

    override fun createStudent(student: Student): Long {
        if (student.id != null) {
            throw RuntimeException("Student id should be null during creation")
        }

        return studentDao.save(StudentDtoConverter.convert(student)).id ?: throw RuntimeException()
    }

    override fun editStudent(student: Student) {
        if (!studentDao.exists(student.id)) {
            throw RuntimeException(format("Student with id '%d' does not exist", student.id))
        }

        studentDao.save(StudentDtoConverter.convert(student))
    }

    override fun deleteStudent(studentId: Long) {
        if (!studentDao.exists(studentId)) {
            throw RuntimeException(format("Student with id '%d' does not exist", studentId))
        }

        studentDao.delete(studentId)
    }
}
