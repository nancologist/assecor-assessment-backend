package com.assecor.app.demo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Color {
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
        return Color.colors.get(id);
    }

    public static Optional<Integer> getIdFromColor(String color) {
        return Color.colors.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), color))
                .map(Map.Entry::getKey).findFirst();
    }
}
