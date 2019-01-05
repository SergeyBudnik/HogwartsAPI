package com.bdev.hogwarts_api.rest_service.group

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.group.Group
import com.bdev.hogwarts_api.data.dto.group.GroupLesson

interface GroupRestService {
    fun getAllGroups(userInfo: MunicipaliUserInfo): List<Group>
    fun getGroup(userInfo: MunicipaliUserInfo, id: Long): Group
    fun createGroup(userInfo: MunicipaliUserInfo, group: Group): Long
    fun editGroup(userInfo: MunicipaliUserInfo, group: Group)
    fun deleteGroup(userInfo: MunicipaliUserInfo, id: Long)
}
