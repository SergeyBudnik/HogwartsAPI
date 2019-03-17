package com.bdev.hogwarts_api.rest_service.admin.user_request

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.user_request.UserRequest

interface AdminUserRequestRestService {
    fun getAllUserRequests(userInfo: MunicipaliUserInfo): List<UserRequest>
    fun deleteUserRequest(userInfo: MunicipaliUserInfo, id: Long)
}
