package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.user_request.UserRequestModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRequestDao : JpaRepository<UserRequestModel, Long>
