package com.instahost.api.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
public class StaticFile {
    private final String id;
    private final byte[] data;
}
