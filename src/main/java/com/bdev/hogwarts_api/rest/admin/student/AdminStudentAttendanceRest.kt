package com.bdev.hogwarts_api.rest.admin.student

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceId
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.student.student_actions.StudentAttendanceRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/students/attendances")
@Api(tags = ["Admin Students Attendances"], description = "PROTECTED")
class AdminStudentAttendanceRest : CommonRest() {
    @Autowired
    private lateinit var studentAttendanceRestService: StudentAttendanceRestService

    @GetMapping("")
    fun getAllAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<StudentAttendance> {
        return studentAttendanceRestService.getAllAttendances(
                userInfo = getUserInfo(authToken)
        )
    }

    @GetMapping("/student/{studentLogin}")
    fun getAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentLogin: String
    ): List<StudentAttendance> {
        return studentAttendanceRestService.getStudentAttendances(
                userInfo = getUserInfo(authToken),
                studentLogin = studentLogin
        )
    }

    @PostMapping("")
    fun addAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody attendance: StudentAttendance
    ) {
        return studentAttendanceRestService.addAttendance(
                userInfo = getUserInfo(authToken),
                attendance = attendance
        )
    }

    @DeleteMapping("")
    fun deleteAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody attendanceId: StudentAttendanceId
    ) {
        studentAttendanceRestService.deleteAttendance(
                userInfo = getUserInfo(authToken),
                attendanceId = attendanceId
        )
    }
}
