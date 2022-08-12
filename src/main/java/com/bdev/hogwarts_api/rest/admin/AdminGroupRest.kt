package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.group.Group
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.group.GroupRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/groups")
@Api(tags = ["Admin Groups"], description = "PROTECTED")
class AdminGroupRest : CommonRest() {
    @Autowired
    private lateinit var groupRestService: GroupRestService

    @GetMapping("")
    fun getAllGroups(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<Group> {
        return groupRestService.getAllGroups(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{id}")
    fun getGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ): Group {
        return groupRestService.getGroup(
                getUserInfo(authToken),
                id
        )
    }

    @PostMapping("")
    fun createGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody group: Group
    ): Long {
        return groupRestService.createGroup(
                getUserInfo(authToken),
                group
        )
    }

    @PutMapping("")
    fun editGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody group: Group
    ) {
        groupRestService.editGroup(
                getUserInfo(authToken),
                group
        )
    }

    @DeleteMapping("/{id}")
    fun deleteGroup(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ) {
        groupRestService.deleteGroup(
                getUserInfo(authToken),
                id
        )
    }
}
