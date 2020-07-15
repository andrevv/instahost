package com.instahost.api.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@Service
public class FileStorageImpl implements FileStorage {
    @SneakyThrows
    @Override
    public void store(String id, byte[] data) {
        try (var fos = new FileOutputStream(id)) {
            fos.write(data);
        }
    }

    @SneakyThrows
    @Override
    public byte[] retrieve(String id) {
        try (var fis = new FileInputStream(id)) {
            return fis.readAllBytes();
        }
    }
}
