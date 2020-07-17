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
    private final StaticFileRepository fileRepository;

    public FileController(
            IdGenerator idGenerator,
            FileStorage storage,
            StaticFileRepository fileRepository) {
        this.idGenerator = idGenerator;
        this.storage = storage;
        this.fileRepository = fileRepository;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<UploadFileResult> upload(@RequestParam("file") MultipartFile file) {
        log.info("Got an upload request.");

        var id = idGenerator.generate();
        storage.store(id, file.getBytes());

        var staticFile = new StaticFile(id, file.getBytes());
        fileRepository.save(staticFile);

        var response = new UploadFileResult(id);

        return ResponseEntity.ok(response);
    }
}
