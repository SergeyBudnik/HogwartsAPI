package com.bdev.hogwarts_api.rest_service.admin.student

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.student.studying.Student
import com.bdev.hogwarts_api.rest_service.common.CommonCRUDRestService
import com.bdev.hogwarts_api.service.student.StudentStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AdminStudentRestService : CommonCRUDRestService<String, Student>

@Service
open class AdminStudentRestServiceImpl @Autowired constructor(
        private val studentStorageService: StudentStorageService
): AdminStudentRestService {
    @Transactional(readOnly = true)
    override fun getAll(userInfo: MunicipaliUserInfo): ResponseEntity<Any> {
        return ResponseEntity.ok(studentStorageService.getAll())
    }

    @Transactional(readOnly = true)
    override fun getById(userInfo: MunicipaliUserInfo, id: String): ResponseEntity<Any> {
        val student = studentStorageService.getById(id)

        return if (student == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(student)
        }
    }

    @Transactional
    override fun create(userInfo: MunicipaliUserInfo, o: Student): ResponseEntity<Any> {
        studentStorageService.create(o)

        return ResponseEntity.ok().build()
    }

    @Transactional
    override fun update(userInfo: MunicipaliUserInfo, o: Student): ResponseEntity<Any> {
        studentStorageService.update(o)

        return ResponseEntity.ok().build()
    }

    @Transactional
    override fun delete(userInfo: MunicipaliUserInfo, id: String): ResponseEntity<Any> {
        studentStorageService.delete(id)

        return ResponseEntity.ok().build()
    }
}
