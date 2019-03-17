package com.bdev.hogwarts_api.rest_service.user.user_request

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest

interface UserRequestRestService {
    fun addUserRequest(userRequest: UserRequest)
}
