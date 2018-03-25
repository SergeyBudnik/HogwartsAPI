package com.bdev.hogwarts_api.service.group;

import com.bdev.hogwarts_api.dao.GroupDao;
import com.bdev.hogwarts_api.data.converter.group.GroupDtoConverter;
import com.bdev.hogwarts_api.data.converter.group.GroupModelConverter;
import com.bdev.hogwarts_api.data.dto.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Override
    public boolean exists(long id) {
        return groupDao.exists(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDao.findAll().stream().map(GroupModelConverter::convert).collect(toList());
    }

    @Override
    public Optional<Group> getGroup(long id) {
        return Optional.ofNullable(groupDao.getOne(id)).map(GroupModelConverter::convert);
    }

    @Override
    public long createGroup(Group group) {
        if (group.getId() != null) {
            throw new RuntimeException("Group id should be null during creation");
        }

        return groupDao.save(GroupDtoConverter.convert(group)).getId();
    }

    @Override
    public void editGroup(Group group) {
        if (!groupDao.exists(group.getId())) {
            throw new RuntimeException(format("Group with id '%d' does not exist", group.getId()));
        }

        groupDao.save(GroupDtoConverter.convert(group));
    }

    @Override
    public void deleteGroup(long id) {
        if (!groupDao.exists(id)) {
            throw new RuntimeException(format("Group with id '%d' does not exist", id));
        }

        groupDao.delete(id);
    }
}
