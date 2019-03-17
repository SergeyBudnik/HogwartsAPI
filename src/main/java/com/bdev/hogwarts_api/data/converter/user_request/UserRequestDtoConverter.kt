package com.bdev.hogwarts_api.data.converter.user_request

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.data.model.user_request.UserRequestModel
import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64

object UserRequestDtoConverter {
    fun convert(userRequest: UserRequest): UserRequestModel {
        val userRequestModel = UserRequestModel()

        userRequestModel.id = userRequest.id
        userRequestModel.name = toBase64(userRequest.name)
        userRequestModel.phone = toBase64(userRequest.phone)
        userRequestModel.date = userRequest.date

        return userRequestModel
    }
}
