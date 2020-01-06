package com.bdev.hogwarts_api.service.student_status

import com.bdev.hogwarts_api.dao.StudentDaoLegacy
import com.bdev.hogwarts_api.dao.StudentStatusDao
import com.bdev.hogwarts_api.data.converter.student_status.StudentStatusDtoConverter
import com.bdev.hogwarts_api.data.converter.student_status.StudentStatusModelConverter
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.lang.String.format

@Service
class StudentStatusServiceImpl : StudentStatusService {
    @Autowired
    private lateinit var studentDao: StudentDaoLegacy
    @Autowired
    private lateinit var studentStatusDao: StudentStatusDao

    override fun getAllStudentsStatuses(): List<StudentStatus> {
        return studentStatusDao
                .findAll()
                .map { StudentStatusModelConverter.convert(it) }
                .sortedBy { it.creationTime }
    }

    override fun getAllStudentStatuses(studentId: Long): List<StudentStatus> {
        return studentStatusDao
                .findAllByStudentId(studentId)
                .map { StudentStatusModelConverter.convert(it) }
                .sortedBy { it.creationTime }
    }

    override fun getStudentStatus(studentId: Long): StudentStatus? {
        return studentStatusDao
                .findTopByStudentIdOrderByCreationTimeDesc(studentId)
                ?.let { StudentStatusModelConverter.convert(it) }
    }

    override fun changeStudentStatus(studentStatus: StudentStatus) {
        if (studentStatus.id != null) {
            throw RuntimeException("Student status id should be null during creation")
        }

        if (!studentDao.exists(studentStatus.studentId)) {
            throw RuntimeException(format("Student with id '%d' does not exist", studentStatus.studentId))
        }

        studentStatusDao.save(StudentStatusDtoConverter.convert(studentStatus))
    }
}
