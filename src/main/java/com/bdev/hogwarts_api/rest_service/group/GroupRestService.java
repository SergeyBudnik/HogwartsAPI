package com.bdev.hogwarts_api.rest_service.group;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.group.Group;
import com.bdev.hogwarts_api.data.dto.group.GroupLesson;

import java.util.List;

public interface GroupRestService {
    List<Group> getAllGroups(MunicipaliUserInfo userInfo);
    Group getGroup(MunicipaliUserInfo userInfo, long id);
    long createGroup(MunicipaliUserInfo userInfo, Group group);
    void editGroup(MunicipaliUserInfo userInfo, Group group);
    void deleteGroup(MunicipaliUserInfo userInfo, long id);
}
