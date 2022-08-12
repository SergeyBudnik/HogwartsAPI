package com.bdev.hogwarts_api.rest_service.admin.lesson_type

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.lesson_type.LessonType
import com.bdev.hogwarts_api.service.lesson_type.LessonTypeStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AdminLessonTypeRestService {
    fun getAll(
        userInfo: MunicipaliUserInfo
    ): ResponseEntity<*>

    fun set(
        userInfo: MunicipaliUserInfo,
        lessonType: LessonType
    ): ResponseEntity<*>

    fun delete(
        userInfo: MunicipaliUserInfo,
        id: String
    ): ResponseEntity<*>
}

@Service
open class AdminLessonTypeRestServiceImpl @Autowired constructor(
    private val lessonTypeStorageService: LessonTypeStorageService
): AdminLessonTypeRestService {
    @Transactional(readOnly = true)
    override fun getAll(
        userInfo: MunicipaliUserInfo
    ): ResponseEntity<*> = ResponseEntity.ok(lessonTypeStorageService.getAll())

    @Transactional(readOnly = false)
    override fun set(
        userInfo: MunicipaliUserInfo,
        lessonType: LessonType
    ): ResponseEntity<*> {
        lessonTypeStorageService.set(lessonType = lessonType)

        return ResponseEntity.ok("Success")
    }

    @Transactional(readOnly = false)
    override fun delete(
        userInfo: MunicipaliUserInfo,
        id: String
    ): ResponseEntity<*> {
        val deleted = lessonTypeStorageService.delete(id = id)

        return if (deleted) {
            ResponseEntity
                .ok("Success")
        } else {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Lesson type for id '${id}' does not exist")
        }
    }
}
