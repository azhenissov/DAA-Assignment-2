package com.anuar.algorithms;

import com.anuar.metrics.Metrics;

public class KadaneAlgorithm {

    private final Metrics metrics;

    public KadaneAlgorithm(Metrics metrics) {
        this.metrics = metrics;
    }

    public static class Result {
        private final int maxSum;
        private final int startIndex;
        private final int endIndex;

        public Result(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getMaxSum() { return maxSum; }
        public int getStartIndex() { return startIndex; }
        public int getEndIndex() { return endIndex; }

        @Override
        public String toString() {
            return String.format("MaxSum=%d, Start=%d, End=%d", maxSum, startIndex, endIndex);
        }
    }

//      Kadane’s Algorithm with:
//       - cached element access to reduce array indexing
//       - early exit for all-negative arrays
//       - edge-case handling for empty and single-element arrays

    public Result findMaxSubarray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        // Edge case: empty array
        if (arr.length == 0) {
            return new Result(0, -1, -1); // convention: no subarray
        }

        //  Edge case: single element
        if (arr.length == 1) {
            metrics.incrementArrayAccesses();
            metrics.incrementMemoryAllocations();
            return new Result(arr[0], 0, 0);
        }

        metrics.incrementMemoryAllocations(); // for result object later

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, tempStart = 0, end = 0;

        boolean hasPositive = arr[0] > 0;
        int maxElement = arr[0];

        // iterate from second element
        for (int i = 1; i < arr.length; i++) {
            metrics.incrementArrayAccesses();
            int current = arr[i]; // cache current element

            // Track if there's any positive element
            if (current > 0) hasPositive = true;

            // Track max element (for all-negative array case)
            if (current > maxElement) {
                maxElement = current;
            }

            metrics.incrementComparisons();
            if (current > maxEndingHere + current) {
                maxEndingHere = current;
                tempStart = i;
            } else {
                maxEndingHere += current;
            }

            metrics.incrementComparisons();
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        // Early exit optimization: if no positive numbers exist, return max element only
        if (!hasPositive) {
            int index = indexOf(arr, maxElement);
            return new Result(maxElement, index, index);
        }

        return new Result(maxSoFar, start, end);
    }

    // Helper: find index of an element (used for all-negative case)
    private int indexOf(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            metrics.incrementArrayAccesses();
            metrics.incrementComparisons();
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; // should never happen
    }
}
