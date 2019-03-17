package com.bdev.hogwarts_api.rest.admin

import acropollis.municipali.security.common.dto.MunicipaliUserCredentials
import acropollis.municipali.security.common.dto.MunicipaliUserToken
import com.bdev.hogwarts_api.rest.CommonRest
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
@Api(tags = ["Login"], description = "PROTECTED")
class AdminLoginRest : CommonRest() {
    @PostMapping("")
    public override fun login(@RequestBody credentials: MunicipaliUserCredentials): MunicipaliUserToken {
        return super.login(credentials)
    }

    @DeleteMapping("")
    public override fun logoff(@RequestBody authToken: String) {
        super.logoff(authToken)
    }
}