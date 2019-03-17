package com.bdev.hogwarts_api.service.user_request

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest

interface UserRequestService {
    fun getAllUserRequests(): List<UserRequest>
    fun createUserRequest(userRequest: UserRequest): Long
    fun deleteUserRequest(id: Long)
}
