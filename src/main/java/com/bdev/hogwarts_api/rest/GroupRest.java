package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.group.Group;
import com.bdev.hogwarts_api.data.dto.group.GroupLesson;
import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.rest_service.group.GroupRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@Api(tags = "Group", description = "PROTECTED")
public class GroupRest extends CommonRest {
    @Autowired
    private GroupRestService groupRestService;

    @GetMapping("")
    public List<Group> getAllGroups(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return groupRestService.getAllGroups(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{id}")
    public Group getGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        return groupRestService.getGroup(
                getUserInfo(authToken),
                id
        );
    }

    @PostMapping("")
    public long createGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Group group
    ) {
        return groupRestService.createGroup(
                getUserInfo(authToken),
                group
        );
    }

    @PutMapping("")
    public void editGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Group group
    ) {
        groupRestService.editGroup(
                getUserInfo(authToken),
                group
        );
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        groupRestService.deleteGroup(
                getUserInfo(authToken),
                id
        );
    }
}
