package com.bdev.hogwarts_api.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import springfox.documentation.annotations.ApiIgnore

@ApiIgnore
@Controller
class SignUpController {
    @RequestMapping("/pages/sign-up/{platform}")
    fun openSignUpForm(
            @PathVariable platform: EventsController.Platform
    ): ModelAndView {
        return ModelAndView("sign-up/" + platform.path)
    }
}
