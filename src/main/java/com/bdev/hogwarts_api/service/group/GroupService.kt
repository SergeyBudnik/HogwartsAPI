package com.bdev.hogwarts_api.service.group

import com.bdev.hogwarts_api.data.dto.group.Group

interface GroupService {
    fun getAllGroups(): List<Group>
    fun exists(id: Long): Boolean
    fun getGroup(id: Long): Group?
    fun createGroup(group: Group): Long
    fun editGroup(group: Group)
    fun deleteGroup(id: Long)
}
