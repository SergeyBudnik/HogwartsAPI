package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.student_actions.StudentAttendanceRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student-attendance")
@Api(tags = ["Student Attendance"], description = "PROTECTED")
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

    @GetMapping("/{studentId}")
    fun getAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ): List<StudentAttendance> {
        return studentAttendanceRestService.getAttendances(
                userInfo = getUserInfo(authToken),
                studentId = studentId
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

    @DeleteMapping("/student/{studentId}/start-time/{startTime}")
    fun deleteAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long,
            @PathVariable startTime: Long
    ) {
        studentAttendanceRestService.deleteAttendance(
                userInfo = getUserInfo(authToken),
                studentId = studentId,
                startTime = startTime
        )
    }
}
