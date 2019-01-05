package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.group.GroupModel
import org.springframework.data.jpa.repository.JpaRepository

interface GroupDao : JpaRepository<GroupModel, Long>
