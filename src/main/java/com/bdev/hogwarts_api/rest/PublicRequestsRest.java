package com.bdev.hogwarts_api.rest;

import com.bdev.hogwarts_api.rest_service.public_requests.PublicRequestsRestService;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/requests")
@Api(tags = "Public requests", description = "NOT-PROTECTED")
public class PublicRequestsRest {
    @Getter
    @Setter
    private static class Request {
        private String name;
        private String phone;
    }

    @Autowired
    private PublicRequestsRestService publicRequestsRestService;

    @PostMapping("")
    public void addOpenLessonRequest(@RequestBody Request request) {
        publicRequestsRestService.addRequest(
                request.name,
                request.phone
        );
    }
}
