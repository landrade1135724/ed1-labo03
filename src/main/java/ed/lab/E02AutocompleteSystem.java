package ed.lab;

import java.util.*;

public class E02AutocompleteSystem {
    private final Map<String, Integer> counts;
    private final StringBuilder current;

    public E02AutocompleteSystem(String[] sentences, int[] times) {
        counts = new HashMap<>();
        current = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            counts.put(sentences[i], counts.getOrDefault(sentences[i], 0) + times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String s = current.toString();
            counts.put(s, counts.getOrDefault(s, 0) + 1);
            current.setLength(0);
            return Collections.emptyList();
        }
        current.append(c);
        String prefix = current.toString();
        // Filtrar y ordenar
        return counts.keySet().stream()
                .filter(s -> s.startsWith(prefix))
                .sorted((a, b) -> {
                    int freqA = counts.get(a);
                    int freqB = counts.get(b);
                    if (freqA != freqB) {
                        return Integer.compare(freqB, freqA);
                    }
                    return a.compareTo(b);
                })
                .limit(3)
                .toList();
    }
}
