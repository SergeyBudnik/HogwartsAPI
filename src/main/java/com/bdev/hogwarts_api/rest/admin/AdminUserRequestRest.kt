package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.user_request.AdminUserRequestRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-requests")
@Api(tags = ["Admin User Requests"], description = "PROTECTED")
class AdminUserRequestRest : CommonRest() {
    @Autowired
    private lateinit var adminUserRequestRestService: AdminUserRequestRestService

    @GetMapping("")
    fun getAllUserRequests(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<UserRequest> {
        return adminUserRequestRestService.getAllUserRequests(
                getUserInfo(authToken)
        )
    }
}
