package com.instahost.api.controller;

import com.instahost.api.service.IdGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileUploadController {

    private final IdGenerator idGenerator;

    public FileUploadController(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        log.info("Got an upload request.");

        var bytes = file.getBytes();

        var id = idGenerator.generate();

        var f = new FileOutputStream(id);
        f.write(bytes);
        f.close();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
