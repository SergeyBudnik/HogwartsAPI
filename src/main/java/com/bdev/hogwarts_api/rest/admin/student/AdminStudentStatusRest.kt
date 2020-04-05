package com.bdev.hogwarts_api.rest.admin.student

import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.student.student_status.StudentStatusRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/students/statuses")
@Api(tags = ["Admin Student Status"], description = "PROTECTED")
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

    @GetMapping("/student/{studentLogin}")
    fun getStudentStatuses(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentLogin: String
    ): List<StudentStatus> {
        return studentStatusRestService.getStudentStatuses(
                getUserInfo(authToken),
                studentLogin
        )
    }

    @PutMapping("/{studentLogin}/{status}")
    fun changeStudentStatus(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentLogin: String,
            @PathVariable status: StudentStatusType,
            @RequestBody actionTime: Long
    ) {
        studentStatusRestService.changeStudentStatus(
                getUserInfo(authToken),
                studentLogin,
                status,
                actionTime
        )
    }
}
