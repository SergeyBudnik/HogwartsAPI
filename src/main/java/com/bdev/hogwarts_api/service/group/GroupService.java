package com.bdev.hogwarts_api.service.group;

import com.bdev.hogwarts_api.data.dto.group.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    boolean exists(long id);
    List<Group> getAllGroups();
    Optional<Group> getGroup(long id);
    long createGroup(Group group);
    void editGroup(Group group);
    void deleteGroup(long id);
}
