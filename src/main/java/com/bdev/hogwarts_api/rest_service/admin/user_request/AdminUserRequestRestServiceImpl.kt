package com.bdev.hogwarts_api.rest_service.admin.user_request

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import com.bdev.hogwarts_api.service.user_request.UserRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AdminUserRequestRestServiceImpl : AdminUserRequestRestService {
    @Autowired
    private lateinit var userRequestService: UserRequestService

    @Transactional(readOnly = true)
    override fun getAllUserRequests(userInfo: MunicipaliUserInfo): List<UserRequest> {
        return userRequestService.getAllUserRequests()
    }

    @Transactional
    override fun deleteUserRequest(userInfo: MunicipaliUserInfo, id: Long) {
        userRequestService.deleteUserRequest(id)
    }
}
