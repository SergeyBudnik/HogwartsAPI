package com.bdev.hogwarts_api.rest;

import acropollis.municipali.security.common.dto.MunicipaliUserCredentials;
import acropollis.municipali.security.common.dto.MunicipaliUserToken;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Api(tags = "Login", description = "PROTECTED")
public class LoginRest extends CommonRest {
    @PostMapping("")
    public MunicipaliUserToken login(@RequestBody MunicipaliUserCredentials credentials) {
        return super.login(credentials);
    }

    @DeleteMapping("")
    public void logoff(@RequestBody String authToken) {
        super.logoff(authToken);
    }
}