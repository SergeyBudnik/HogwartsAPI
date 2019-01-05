package com.bdev.hogwarts_api.service.group

import com.bdev.hogwarts_api.dao.GroupDao
import com.bdev.hogwarts_api.data.converter.group.GroupDtoConverter
import com.bdev.hogwarts_api.data.converter.group.GroupModelConverter
import com.bdev.hogwarts_api.data.dto.group.Group
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
class GroupServiceImpl : GroupService {
    @Autowired
    private lateinit var groupDao: GroupDao

    override fun getAllGroups(): List<Group> {
        return groupDao
                .findAll()
                .map { GroupModelConverter.convert(it) }
    }

    override fun exists(id: Long): Boolean {
        return groupDao.exists(id)
    }

    override fun getGroup(id: Long): Group? {
        return groupDao.getOne(id)?.let { GroupModelConverter.convert(it) }
    }

    override fun createGroup(group: Group): Long {
        if (group.id != null) {
            throw RuntimeException("Group id should be null during creation")
        }

        return groupDao.save(GroupDtoConverter.convert(group)).id ?: throw RuntimeException()
    }

    override fun editGroup(group: Group) {
        if (!groupDao.exists(group.id)) {
            throw RuntimeException(format("Group with id '%d' does not exist", group.id))
        }

        groupDao.save(GroupDtoConverter.convert(group))
    }

    override fun deleteGroup(id: Long) {
        if (!groupDao.exists(id)) {
            throw RuntimeException(format("Group with id '%d' does not exist", id))
        }

        groupDao.delete(id)
    }
}
