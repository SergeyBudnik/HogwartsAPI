package com.bdev.hogwarts_api.data.converter.user_request

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.data.model.user_request.UserRequestModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object UserRequestModelConverter {
    fun convert(userRequestModel: UserRequestModel): UserRequest {
        return UserRequest(
                userRequestModel.id,
                userRequestModel.name?.let { fromBase64(it) } ?: "?",
                userRequestModel.phone?.let { fromBase64(it) } ?: "?",
                userRequestModel.date ?: 0
        )
    }
}
