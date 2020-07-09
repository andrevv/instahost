package com.instahost.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
@Slf4j
public class PingController {
    @GetMapping
    public String ping() {
        log.info("Got a PING request.");
        return "pong";
    }
}
