package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.student.on_boarding.*
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.student_onboarding.AdminStudentOnBoardingRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/student/on-boarding")
@Api(tags = ["Admin Student OnBoarding"], description = "PROTECTED")
class AdminStudentOnBoardingRest @Autowired constructor(
        private val adminStudentOnBoardingRestService: AdminStudentOnBoardingRestService
) : CommonRest() {
    @GetMapping("/all")
    fun getAllStudentOnBoardings(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.getAllStudentOnBoardings(
                userInfo = getUserInfo(authToken)
        )
    }

    @GetMapping("/login/{login}")
    fun getStudentOnBoarding(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.getStudentOnBoarding(
                userInfo = getUserInfo(authToken),
                login = login
        )
    }

    @PostMapping("")
    fun createStudentOnBoarding(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody newStudentOnBoarding: NewStudentOnBoarding
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.createStudentOnBoarding(
                userInfo = getUserInfo(authToken),
                newStudentOnBoarding = newStudentOnBoarding
        )
    }

    @PutMapping("/{login}/update")
    fun updateStudentOnBoarding(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String,
            @RequestBody studentOnBoardingInfo: StudentOnBoardingInfo
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.updateStudentOnBoarding(
                userInfo = getUserInfo(authToken),
                login = login,
                studentOnBoardingInfo = studentOnBoardingInfo
        )
    }

    @PutMapping("/{login}/complete")
    fun updateStudentOnBoardingResult(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String,
            @RequestBody studentOnBoardingResult: StudentOnBoardingResult
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.updateStudentOnBoardingResult(
                userInfo = getUserInfo(authToken),
                login = login,
                studentOnBoardingResult = studentOnBoardingResult
        )
    }

    @PutMapping("/{login}/complete/action")
    fun completeStudentOnBoardingAction(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String,
            @RequestBody studentOnBoardingAction: NewStudentOnBoardingAction
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.completeStudentOnBoardingAction(
                userInfo = getUserInfo(authToken),
                login = login,
                studentOnBoardingAction = studentOnBoardingAction
        )
    }

    @DeleteMapping("/{login}")
    fun deleteStudentOnBoarding(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String
    ): ResponseEntity<Any> {
        return adminStudentOnBoardingRestService.deleteStudentOnBoarding(
                userInfo = getUserInfo(authToken),
                login = login
        )
    }
}
