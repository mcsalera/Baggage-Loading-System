package com.uplift.baggageloadingsystem.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.security.SecureRandom;
import java.util.*;
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

    public static void copyProperties(Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        String[] excluded =  Arrays
                                    .stream(src.getPropertyDescriptors())
                                    .filter(e -> src.getPropertyValue(e.getName()) == null && e.getName().equals("id"))
                                    .map(PropertyDescriptor::getName)
                                    .toArray(String[]::new);

        BeanUtils.copyProperties(source, target, excluded);
    }
}
