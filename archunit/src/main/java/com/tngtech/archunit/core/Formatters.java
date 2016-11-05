package com.tngtech.archunit.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class Formatters {
    private static final String FULL_METHOD_NAME_TEMPLATE = "%s.%s(%s)";

    private Formatters() {
    }

    public static String formatMethod(String ownerName, String methodName, List<TypeDetails> parameters) {
        return String.format(FULL_METHOD_NAME_TEMPLATE, ownerName, methodName, formatMethodParameters(parameters));
    }

    public static String formatMethodParameters(List<TypeDetails> parameters) {
        List<String> formatted = new ArrayList<>();
        for (TypeDetails type : parameters) {
            formatted.add(String.format("%s.class", type.getSimpleName()));
        }
        return Joiner.on(", ").join(formatted);
    }
}
