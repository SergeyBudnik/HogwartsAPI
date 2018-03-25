package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import com.bdev.hogwarts_api.rest_service.cabinet.CabinetRestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabinets")
@Api(tags = "Cabinet", description = "PROTECTED")
public class CabinetRest extends CommonRest {
    @Autowired
    private CabinetRestService cabinetRestService;

    @GetMapping("")
    public List<Cabinet> getAllCabinets(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    ) {
        return cabinetRestService.getAllCabinets(
                getUserInfo(authToken)
        );
    }

    @GetMapping("/{id}")
    public Cabinet getCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        return cabinetRestService.getCabinet(
                getUserInfo(authToken),
                id
        );
    }

    @PostMapping("")
    public long saveCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Cabinet cabinet
    ) {
        return cabinetRestService.saveCabinet(
                getUserInfo(authToken),
                cabinet
        );
    }

    @PutMapping("")
    public void editCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @RequestBody Cabinet cabinet
    ) {
        cabinetRestService.editCabinet(
                getUserInfo(authToken),
                cabinet
        );
    }

    @DeleteMapping("/{id}")
    public void deleteCabinet(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken,
            @PathVariable long id
    ) {
        cabinetRestService.deleteCabinet(
                getUserInfo(authToken),
                id
        );
    }
}
