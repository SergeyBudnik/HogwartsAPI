package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.student_status.StudentStatusRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students-status")
@Api(tags = ["Student Status"], description = "PROTECTED")
class AdminStudentStatusRest : CommonRest() {
    @Autowired
    private lateinit var studentStatusRestService: StudentStatusRestService

    @GetMapping("/all")
    fun getStudentsStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<StudentStatus> {
        return studentStatusRestService.getStudentsStatuses(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/latest")
    fun getStudentsLatestStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<StudentStatus> {
        return studentStatusRestService.getLatestStudentsStatuses(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{studentId}")
    fun getStudentStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ): List<StudentStatus> {
        return studentStatusRestService.getStudentStatuses(
                getUserInfo(authToken),
                studentId
        )
    }

    @PutMapping("/{studentId}/{status}")
    fun changeStudentStatus(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long,
            @PathVariable status: StudentStatusType,
            @RequestBody actionTime: Long
    ) {
        studentStatusRestService.changeStudentStatus(
                getUserInfo(authToken),
                studentId,
                status,
                actionTime
        )
    }
}
