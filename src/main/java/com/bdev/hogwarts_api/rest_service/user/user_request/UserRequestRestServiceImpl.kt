package com.bdev.hogwarts_api.rest_service.user.user_request

import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.service.staff.StaffMemberStorageService
import com.bdev.hogwarts_api.service.user_request.UserRequestService
import com.bdev.hogwarts_api.vk.VkMessageSender
import com.bdev.hogwarts_api.vk.VkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserRequestRestServiceImpl(
        private val staffMemberStorageService: StaffMemberStorageService,
        private val userRequestService: UserRequestService,
        private val vkService: VkService
) : UserRequestRestService {
    @Transactional
    override fun addUserRequest(userRequest: UserRequest) {
        userRequestService.createUserRequest(userRequest)

        val message =
                "Новая заявка.\n" +
                "Студент: ${userRequest.name}\n" +
                "Телефон: ${userRequest.phone}\n"

        staffMemberStorageService
                .getAllStaffMembers()
                .filter { it.subscriptions.freeLessonRequest }
                .filter { it.person.contacts.vkLinks.isNotEmpty() }
                .map { it.person.contacts.vkLinks[0].value }
                .mapNotNull { vkService.getUserId(screenName = it) }
                .forEach {
                    vkService.sendMessage(userId = it, message = message)
                }
    }
}
