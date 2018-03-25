package com.bdev.hogwarts_api.rest_service.group;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.group.Group;
import com.bdev.hogwarts_api.data.dto.group.GroupLesson;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupRestServiceImpl implements GroupRestService {
    @Autowired
    private GroupService groupService;

    @Transactional(readOnly = true)
    @Override
    public List<Group> getAllGroups(
            MunicipaliUserInfo userInfo
    ) {
        return groupService.getAllGroups();
    }

    @Transactional(readOnly = true)
    @Override
    public Group getGroup(
            MunicipaliUserInfo userInfo,
            long id
    ) {
        return groupService.getGroup(id)
                .orElseThrow(() -> new HttpEntityNotFoundException("Group with id '%d' does not exist", id));
    }

    @Transactional
    @Override
    public long createGroup(
            MunicipaliUserInfo userInfo,
            Group group
    ) {
        if (group.getId() != null) {
            throw new HttpEntityIllegalStateException("Group id should be null during creation");
        }

        return groupService.createGroup(group);
    }

    @Transactional
    @Override
    public void editGroup(
            MunicipaliUserInfo userInfo,
            Group group
    ) {
        if (group.getId() == null) {
            throw new HttpEntityIllegalStateException("Group id should be non-null during edit");
        }

        if (!groupService.exists(group.getId())) {
            throw new HttpEntityNotFoundException("Group with id '%d' does not exist", group.getId());
        }

        groupService.editGroup(group);
    }

    @Transactional
    @Override
    public void deleteGroup(
            MunicipaliUserInfo userInfo,
            long id
    ) {
        if (!groupService.exists(id)) {
            throw new HttpEntityNotFoundException("Group with id '%d' does not exist", id);
        }

        groupService.deleteGroup(id);
    }
}
