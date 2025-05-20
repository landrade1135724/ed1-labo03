package ed.lab;

import java.util.*;

public class E01TopKFrequentElements {

    /**
     * Retorna los k elementos más frecuentes en nums.
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        // Contar frecuencias
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Bucket sort por frecuencia
        int n = nums.length;
        List<Integer>[] buckets = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey();
            int f = entry.getValue();
            buckets[f].add(num);
        }

        // Recoger los k más frecuentes
        List<Integer> resultList = new ArrayList<>();
        for (int f = n; f >= 1 && resultList.size() < k; f--) {
            if (!buckets[f].isEmpty()) {
                resultList.addAll(buckets[f]);
            }
        }

        // Convertir a arreglo, tomando solo k elementos
        int[] result = new int[Math.min(k, resultList.size())];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}