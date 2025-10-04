package com.anuar.cli;

import com.anuar.algorithms.KadaneAlgorithm;
import com.anuar.metrics.Metrics;

public class KadaneDemo {

    public static void main(String[] args) {
        System.out.println("Kadane's Algorithm - Maximum Subarray Sum Demo");
        System.out.println("===============================================\n");

        // Test cases
        int[][] testArrays = {
                {-2, 1, -3, 4, -1, 2, 1, -5, 4},  // Classic example
                {-2, -3, -1, -5},                 // All negative
                {5, 4, -1, 7, 8},                 // Mostly positive
                {1},                              // Single element
                {-1, 2, 3, -4, 5, 6, -7, 8},     // Mixed
                {}                                // Empty array
        };

        String[] descriptions = {
                "Classic example",
                "All negative numbers",
                "Mostly positive",
                "Single element",
                "Mixed positive/negative",
                "Empty array"
        };

        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("Test " + (i + 1) + ": " + descriptions[i]);

            // Print array
            if (testArrays[i].length == 0) {
                System.out.println("Array: []");
            } else {
                System.out.print("Array: [");
                for (int j = 0; j < testArrays[i].length; j++) {
                    System.out.print(testArrays[i][j]);
                    if (j < testArrays[i].length - 1) System.out.print(", ");
                }
                System.out.println("]");
            }

            // Run Kadane’s Algorithm with metrics
            Metrics metrics = new Metrics();
            KadaneAlgorithm algorithm = new KadaneAlgorithm(metrics);

            long start = System.nanoTime();
            KadaneAlgorithm.Result result = null;
            try {
                result = algorithm.findMaxSubarray(testArrays[i]);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            long end = System.nanoTime();
            long durationNs = end - start;

            if (result != null) {
                System.out.println("Result: " + result);

                // Extract subarray
                int[] subarray = new int[result.getEndIndex() - result.getStartIndex() + 1];
                for (int j = result.getStartIndex(); j <= result.getEndIndex(); j++) {
                    subarray[j - result.getStartIndex()] = testArrays[i][j];
                }

                System.out.print("Subarray: [");
                for (int j = 0; j < subarray.length; j++) {
                    System.out.print(subarray[j]);
                    if (j < subarray.length - 1) System.out.print(", ");
                }
                System.out.println("]");
            }

            // Performance info
            System.out.printf("Execution time: %.3f ms%n", durationNs / 1_000_000.0);
            System.out.println("Array accesses: " + metrics.getArrayAccesses());
            System.out.println("Comparisons: " + metrics.getComparisons());
            System.out.println("Memory allocations: " + metrics.getMemoryAllocations());

            System.out.println();
        }

        System.out.println("Demo completed! Kadane's algorithm finds the maximum sum subarray in O(n) time.");
    }
}
