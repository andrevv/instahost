package com.instahost.api.service;

import com.instahost.api.domain.StaticFile;
import com.instahost.api.repository.StaticFileRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class FileStorageImpl implements FileStorage {

    private final StaticFileRepository fileRepository;

    public FileStorageImpl(StaticFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @SneakyThrows
    @Override
    public void store(String id, byte[] data) {
        var staticFile = new StaticFile(id, data);
        fileRepository.save(staticFile);
    }

    @SneakyThrows
    @Override
    public String retrieve(String id) {
        var staticFile = fileRepository.findById(id).orElseThrow();
        return new String(staticFile.getData(), StandardCharsets.UTF_8);
    }
}
