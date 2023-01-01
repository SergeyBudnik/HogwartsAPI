package com.bdev.hogwarts_api.rest.admin.season

import com.bdev.hogwarts_api.data.dto.season.SeasonInfo
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.season.AdminSeasonRestService
import io.swagger.annotations.Api
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/seasons")
@Api(tags = ["Admin Seasons"], description = "PROTECTED")
class AdminSeasonRest(
    private val adminSeasonRestService: AdminSeasonRestService
) : CommonRest() {
    @GetMapping(value = ["/"])
    fun getAll(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<*> {
        return adminSeasonRestService.getAll(
            userInfo = getUserInfo(authToken = authToken)
        )
    }

    @GetMapping(value = ["/{id}"])
    fun getById(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @PathVariable("id") id: Long
    ): ResponseEntity<*> {
        return adminSeasonRestService.getById(
            userInfo = getUserInfo(authToken = authToken),
            id = id
        )
    }

    @PostMapping(value = ["/"])
    fun create(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody seasonInfo: SeasonInfo
    ): ResponseEntity<*> {
        return adminSeasonRestService.create(
            userInfo = getUserInfo(authToken = authToken),
            seasonInfo = seasonInfo
        )
    }

    @PutMapping(value = ["/{id}"])
    fun update(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @PathVariable("id") id: Long,
        @RequestBody seasonInfo: SeasonInfo
    ): ResponseEntity<*> {
        return adminSeasonRestService.update(
            userInfo = getUserInfo(authToken = authToken),
            id = id,
            seasonInfo = seasonInfo
        )
    }

    @PutMapping(value = ["/{id}/activate"])
    fun activate(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @PathVariable("id") id: Long,
    ): ResponseEntity<*> {
        return adminSeasonRestService.activate(
            userInfo = getUserInfo(authToken = authToken),
            id = id
        )
    }

    @DeleteMapping(value = ["/{id}"])
    fun delete(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @PathVariable("id") id: Long
    ): ResponseEntity<*> {
        return adminSeasonRestService.delete(
            userInfo = getUserInfo(authToken = authToken),
            id = id
        )
    }
}
