package com.bdev.hogwarts_api.rest.admin.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.dto.cabinet.NewCabinet
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.cabinet.AdminCabinetRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/cabinets/management")
@Api(description = "Admin cabinets management")
class AdminCabinetRest @Autowired constructor(
    private val adminCabinetRestService: AdminCabinetRestService
): CommonRest() {
    @GetMapping("")
    fun getAllCabinets(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<Any> {
        return adminCabinetRestService.getAllCabinets(
                userInfo = getUserInfo(authToken)
        )
    }

    @GetMapping("/{id}")
    fun getCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ): ResponseEntity<Any> {
        return adminCabinetRestService.getCabinet(
                userInfo = getUserInfo(authToken),
                cabinetId = id
        )
    }

    @PostMapping("")
    fun saveCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody cabinet: NewCabinet
    ): ResponseEntity<Any> {
        return adminCabinetRestService.saveCabinet(
                userInfo = getUserInfo(authToken),
                cabinet = cabinet
        )
    }

    @PutMapping("")
    fun editCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody cabinet: ExistingCabinet
    ): ResponseEntity<Any> {
        return adminCabinetRestService.editCabinet(
                userInfo = getUserInfo(authToken),
                cabinet = cabinet
        )
    }

    @DeleteMapping("/{id}")
    fun deleteCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ): ResponseEntity<Any> {
        return adminCabinetRestService.deleteCabinet(
                userInfo = getUserInfo(authToken),
                cabinetId = id
        )
    }
}
