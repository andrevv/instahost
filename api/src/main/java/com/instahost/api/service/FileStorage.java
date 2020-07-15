package com.instahost.api.service;

public interface FileStorage {
    void store(String id, byte[] data);
    byte[] retrieve(String id);
}
