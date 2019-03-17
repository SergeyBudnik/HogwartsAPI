package com.bdev.hogwarts_api.rest_service.user.user_request

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.service.user_request.UserRequestService
import com.bdev.hogwarts_api.vk.VkMessageSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserRequestRestServiceImpl : UserRequestRestService {
    @Autowired
    lateinit var userRequestService: UserRequestService

    @Transactional
    override fun addUserRequest(userRequest: UserRequest) {
        userRequestService.createUserRequest(userRequest)

        val message =
                "Новая заявка.\n" +
                "Студент: ${userRequest.name}\n" +
                "Телефон: ${userRequest.phone}\n"

        val adminIds = listOf(23236615, 30026296)

        adminIds.forEach { adminId -> VkMessageSender().sendMessage(adminId, message) }
    }
}
