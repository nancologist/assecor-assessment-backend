package com.assecor.app.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ColorLookup {
    private static final Map<Integer, String> colors = new HashMap<>(Map.of(
            1, "blau",
            2, "grün",
            3, "violett",
            4, "rot",
            5, "gelb",
            6, "türkis",
            7, "weiß"
    ));

    public static String getColorById(int id) {
        return ColorLookup.colors.get(id);
    }

    public static Optional<Integer> getIdFromColor(String color) {
        return ColorLookup.colors.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), color))
                .map(Map.Entry::getKey).findFirst();
    }

    public static String getValidColors() {
        return colors.values().toString();
    }
}
