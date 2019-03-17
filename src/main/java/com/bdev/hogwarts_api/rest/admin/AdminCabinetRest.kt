package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.cabinet.CabinetRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cabinets")
@Api(tags = ["Cabinet"], description = "PROTECTED")
class AdminCabinetRest : CommonRest() {
    @Autowired
    private lateinit var cabinetRestService: CabinetRestService

    @GetMapping("")
    fun getAllCabinets(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): List<Cabinet> {
        return cabinetRestService.getAllCabinets(
                getUserInfo(authToken)
        )
    }

    @GetMapping("/{id}")
    fun getCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ): Cabinet {
        return cabinetRestService.getCabinet(
                getUserInfo(authToken),
                id
        )
    }

    @PostMapping("")
    fun saveCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody cabinet: Cabinet
    ): Long {
        return cabinetRestService.saveCabinet(
                getUserInfo(authToken),
                cabinet
        )
    }

    @PutMapping("")
    fun editCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody cabinet: Cabinet
    ) {
        cabinetRestService.editCabinet(
                getUserInfo(authToken),
                cabinet
        )
    }

    @DeleteMapping("/{id}")
    fun deleteCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable id: Long
    ) {
        cabinetRestService.deleteCabinet(
                getUserInfo(authToken),
                id
        )
    }
}
