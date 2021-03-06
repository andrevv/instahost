package com.instahost.api.controller;

import com.instahost.api.domain.StaticFile;
import com.instahost.api.dto.UploadFileResult;
import com.instahost.api.repository.StaticFileRepository;
import com.instahost.api.service.FileStorage;
import com.instahost.api.service.IdGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    private final IdGenerator idGenerator;
    private final FileStorage storage;

    public FileController(
            IdGenerator idGenerator,
            FileStorage storage) {
        this.idGenerator = idGenerator;
        this.storage = storage;
    }

    @SneakyThrows
    @ResponseBody
    @GetMapping(value = "/{id}")
    public String download(@PathVariable String id) {
        return storage.retrieve(id);
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
