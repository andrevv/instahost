package com.instahost.api.controller;

import com.instahost.api.dto.UploadFileResult;
import com.instahost.api.service.FileStorage;
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

@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    private final IdGenerator idGenerator;
    private final FileStorage storage;

    public FileController(IdGenerator idGenerator, FileStorage storage) {
        this.idGenerator = idGenerator;
        this.storage = storage;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<UploadFileResult> upload(@RequestParam("file") MultipartFile file) {
        log.info("Got an upload request.");

        var id = idGenerator.generate();
        storage.store(id, file.getBytes());

        var response = new UploadFileResult(id);

        return ResponseEntity.ok(response);
    }
}
