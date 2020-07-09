package com.instahost.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileUploadController {
    @PostMapping
    public ResponseEntity upload() {
        log.info("Got an upload request.");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
