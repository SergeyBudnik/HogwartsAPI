package com.bdev.hogwarts_api.service.student_status

import com.bdev.hogwarts_api.dao.StudentStatusDao
import com.bdev.hogwarts_api.data.converter.student_status.StudentStatusDtoConverter
import com.bdev.hogwarts_api.data.converter.student_status.StudentStatusModelConverter
import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentStatusServiceImpl : StudentStatusService {
    @Autowired
    private lateinit var studentStatusDao: StudentStatusDao

    override fun getAllStudentsStatuses(): List<StudentStatus> {
        return studentStatusDao
                .findAll()
                .map { StudentStatusModelConverter.convert(it) }
                .sortedBy { it.creationTime }
    }

    override fun getAllStudentStatuses(studentLogin: String): List<StudentStatus> {
        return studentStatusDao
                .findAllByStudentLogin(studentLogin)
                .map { StudentStatusModelConverter.convert(it) }
                .sortedBy { it.creationTime }
    }

    override fun getStudentStatus(studentLogin: String): StudentStatus? {
        return studentStatusDao
                .findTopByStudentLoginOrderByCreationTimeDesc(studentLogin)
                ?.let { StudentStatusModelConverter.convert(it) }
    }

    override fun changeStudentStatus(studentStatus: StudentStatus) {
        if (studentStatus.id != null) {
            throw RuntimeException("Student status id should be null during creation")
        }

        studentStatusDao.save(StudentStatusDtoConverter.convert(studentStatus))
    }
}
