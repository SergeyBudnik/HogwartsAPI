package com.bdev.hogwarts_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpeakingClubController {
    @RequestMapping("/speaking-club")
    public ModelAndView open() {
        return new ModelAndView("speaking-club");
    }
}
