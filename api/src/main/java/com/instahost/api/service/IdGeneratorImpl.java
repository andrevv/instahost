package com.instahost.api.service;

import java.util.Random;

import static java.util.stream.Collectors.joining;

public class IdGeneratorImpl implements IdGenerator {

    private static final char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'  };
    private static final int length = 7;
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public String generate() {
        return random.ints(length, 0, alphabet.length)
                .mapToObj(i -> String.valueOf(alphabet[i]))
                .collect(joining());
    }
}
