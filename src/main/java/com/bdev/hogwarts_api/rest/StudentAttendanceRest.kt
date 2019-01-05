package com.bdev.hogwarts_api.rest

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance
import com.bdev.hogwarts_api.rest_service.student_actions.StudentAttendanceRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student-attendance")
@Api(tags = ["Student Attendance"], description = "PROTECTED")
class StudentAttendanceRest : CommonRest() {
    @Autowired
    private lateinit var studentAttendanceRestService: StudentAttendanceRestService

    @GetMapping("")
    fun getAllAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<StudentAttendance> {
        return studentAttendanceRestService!!.getAllAttendances(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{studentId}")
    fun getAttendances(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable studentId: Long
    ): List<StudentAttendance> {
        return studentAttendanceRestService.getAttendances(
                getUserInfo(authToken),
                studentId
        )
    }

    @PostMapping
    fun addAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody attendance: StudentAttendance
    ): Long {
        return studentAttendanceRestService.addAttendance(
                getUserInfo(authToken),
                StudentAttendance(
                        studentId = attendance.studentId,
                        type = attendance.type,
                        time = attendance.time
                )
        )
    }

    @DeleteMapping("/{attendanceId}")
    fun deleteAttendance(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable attendanceId: Long
    ) {
        studentAttendanceRestService.deleteAttendance(
                getUserInfo(authToken),
                attendanceId
        )
    }
}
