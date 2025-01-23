package com.example.flames.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FlamesService {

    private static final Map<Character, String> FLAMES_MAP = Map.of(
        'F', "Friendship",
        'L', "Love",
        'A', "Affection",
        'M', "Marriage",
        'E', "Enemies",
        'S', "Sibling"
    );

    public String calculateFlames(String name1, String name2) {
        if (name1 == null || name2 == null || name1.isBlank() || name2.isBlank()) {
            throw new IllegalArgumentException("Names cannot be null or empty");
        }

        List<Character> flamesList = new ArrayList<>(List.of('F', 'L', 'A', 'M', 'E', 'S'));

        int count = calculateUniqueCharacterCount(name1, name2);

        int index = 0;
        while (flamesList.size() > 1) {
            index = (index + count - 1) % flamesList.size();
            flamesList.remove(index);
        }

        return FLAMES_MAP.get(flamesList.get(0)); // Return full relationship name
    }

    private int calculateUniqueCharacterCount(String name1, String name2) {
        int[] freq = new int[26];

        name1 = name1.toLowerCase().replaceAll("\\s", "");
        name2 = name2.toLowerCase().replaceAll("\\s", "");

        for (char c : name1.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : name2.toCharArray()) {
            freq[c - 'a']--;
        }

        int count = 0;
        for (int f : freq) {
            count += Math.abs(f);
        }

        return count;
    }
}
