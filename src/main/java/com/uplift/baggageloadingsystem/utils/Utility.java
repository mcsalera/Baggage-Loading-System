package com.uplift.baggageloadingsystem.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Utility {

    public static String generateToken(int size) {
        final String ref = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        return IntStream.range(0, size)
                .mapToObj(i -> ""+ref.charAt(random.nextInt(ref.length())))
                .collect(Collectors.joining(""));
    }
}
