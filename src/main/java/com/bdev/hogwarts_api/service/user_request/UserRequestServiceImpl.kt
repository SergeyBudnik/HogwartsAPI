package com.bdev.hogwarts_api.service.user_request

import com.bdev.hogwarts_api.dao.UserRequestDao
import com.bdev.hogwarts_api.data.converter.user_request.UserRequestDtoConverter
import com.bdev.hogwarts_api.data.converter.user_request.UserRequestModelConverter
import com.bdev.hogwarts_api.data.dto.user_request.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserRequestServiceImpl : UserRequestService {
    @Autowired
    private lateinit var userRequestDao: UserRequestDao

    override fun getAllUserRequests(): List<UserRequest> {
        return userRequestDao
                .findAll()
                .map { UserRequestModelConverter.convert(it) }
    }

    override fun createUserRequest(userRequest: UserRequest): Long {
        return userRequestDao
                .save(UserRequestDtoConverter.convert(userRequest))
                .id ?: throw RuntimeException()
    }

    override fun deleteUserRequest(id: Long) {
        userRequestDao.delete(id)
    }
}
