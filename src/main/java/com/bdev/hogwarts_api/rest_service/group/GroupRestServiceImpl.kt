package com.bdev.hogwarts_api.rest_service.group

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.group.Group
import com.bdev.hogwarts_api.data.dto.group.GroupLesson
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.group.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class GroupRestServiceImpl : GroupRestService {
    @Autowired
    private lateinit var groupService: GroupService

    @Transactional(readOnly = true)
    override fun getAllGroups(userInfo: MunicipaliUserInfo): List<Group> {
        return groupService.getAllGroups()
    }

    @Transactional(readOnly = true)
    override fun getGroup(userInfo: MunicipaliUserInfo, id: Long): Group {
        return groupService.getGroup(id) ?: throw HttpEntityNotFoundException("Group with id '%d' does not exist", id)
    }

    @Transactional
    override fun createGroup(userInfo: MunicipaliUserInfo, group: Group): Long {
        if (group.id != null) {
            throw HttpEntityIllegalStateException("Group id should be null during creation")
        }

        return groupService.createGroup(group)
    }

    @Transactional
    override fun editGroup(
            userInfo: MunicipaliUserInfo,
            group: Group
    ) {
        val groupId = group.id ?:
                throw HttpEntityIllegalStateException("Group id should be non-null during edit")

        if (!groupService.exists(groupId)) {
            throw HttpEntityNotFoundException("Group with id '%d' does not exist", groupId)
        }

        groupService.editGroup(group)
    }

    @Transactional
    override fun deleteGroup(userInfo: MunicipaliUserInfo, id: Long) {
        if (!groupService.exists(id)) {
            throw HttpEntityNotFoundException("Group with id '%d' does not exist", id)
        }

        groupService.deleteGroup(id)
    }
}
