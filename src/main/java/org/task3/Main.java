package org.task3;

import java.util.*;

public class Main {
    public static List<int[]> findSumOfPairs(int[] arr, int target) {
        List<int[]> pairs = new ArrayList<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : arr) {
            int difference = target - n;
            if (frequencyMap.getOrDefault(difference, 0) > 0) {
                pairs.add(new int[] {difference, n});
            }
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 7, 1, 5, 3};
        int target = 6;

        List<int[]> pairs = findSumOfPairs(arr, target);

        System.out.println("Pairs of integers with target sum are : " + target + ":");
        for (int[] pair : pairs) {
            System.out.println("(" + pair[0] + ", " + pair[1] + ")");
        }
    }

}