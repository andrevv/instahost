package com.instahost.api.service;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IdGeneratorImplTest {

    private final IdGeneratorImpl generator = new IdGeneratorImpl();

    @Test
    void generate() {
        // given
        int count = 100;

        // when
        var ids = IntStream.range(0, count)
                .mapToObj(n -> generator.generate())
                .collect(toList());

        // then
        assertEquals(count, new HashSet<>(ids).size());
        ids.forEach(id -> assertEquals(7, id.length()));
    }
}
