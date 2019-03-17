package com.bdev.hogwarts_api.rest.user

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.rest_service.user.user_request.UserRequestRestService
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/public/user-requests")
@Api(tags = ["User Requests"], description = "NOT-PROTECTED")
class UserUserRequestsRest {
    class UserRequestRestDto @JsonCreator constructor(
            @JsonProperty("name") val name: String,
            @JsonProperty("phone") val phone: String
    )

    @Autowired
    private lateinit var userRequestRestService: UserRequestRestService

    @PostMapping("")
    fun addUserRequest(
            @RequestBody userRequest: UserRequestRestDto
    ) {
        userRequestRestService.addUserRequest(
                UserRequest(
                        id = null,
                        name = userRequest.name,
                        phone = userRequest.phone,
                        date = Date().time
                )
        )
    }
}