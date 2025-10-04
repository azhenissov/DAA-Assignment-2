package com.anuar.algorithms;

import com.anuar.metrics.Metrics;

public class KadaneAlgorithm {

    private final Metrics metrics;

    public KadaneAlgorithm(Metrics metrics) {
        this.metrics = metrics;
    }

    /**
     * Finds maximum subarray sum using Kadane's Algorithm.
     * Tracks positions of the subarray as well.
     *
     * @param arr input array of integers
     * @return result object containing max sum and subarray indices
     */
    public Result findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, end = 0, tempStart = 0;

        // Metrics: 2 array accesses for arr[0]
        metrics.incrementArrayAccesses();
        metrics.incrementArrayAccesses();

        for (int i = 1; i < arr.length; i++) {
            metrics.incrementComparisons();
            metrics.incrementArrayAccesses(); // arr[i]

            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere = maxEndingHere + arr[i];
            }

            metrics.incrementComparisons();
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        return new Result(maxSoFar, start, end);
    }

    /**
     * Simple result record for Kadane's algorithm.
     */
    public static class Result {
        private final int maxSum;
        private final int startIndex;
        private final int endIndex;

        public Result(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getMaxSum() {
            return maxSum;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        @Override
        public String toString() {
            return String.format("MaxSum=%d, Range=[%d..%d]", maxSum, startIndex, endIndex);
        }
    }
}
