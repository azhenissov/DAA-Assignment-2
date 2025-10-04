package com.anuar.cli;

import com.anuar.algorithms.KadaneAlgorithm;
import com.anuar.metrics.Metrics;
import com.anuar.metrics.MetricsCSVExporter;

import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        // Default parameters
        int inputSize = 100;
        int runs = 5;
        String csvFile = "benchmark_results.csv";

        // CLI arguments: --size=1000 --runs=10 --out=results.csv
        for (String arg : args) {
            if (arg.startsWith("--size=")) {
                inputSize = Integer.parseInt(arg.split("=")[1]);
            } else if (arg.startsWith("--runs=")) {
                runs = Integer.parseInt(arg.split("=")[1]);
            } else if (arg.startsWith("--out=")) {
                csvFile = arg.split("=")[1];
            }
        }

        System.out.printf("Running Kadane benchmark: size=%d, runs=%d, output=%s%n",
                inputSize, runs, csvFile);

        MetricsCSVExporter exporter = new MetricsCSVExporter(csvFile);
        Random random = new Random();

        for (int r = 1; r <= runs; r++) {
            int[] arr = generateRandomArray(inputSize, random);

            Metrics metrics = new Metrics();
            KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

            long start = System.nanoTime();
            KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);
            long end = System.nanoTime();

            long duration = end - start;

            System.out.printf("Run %d: MaxSum=%d, Range=[%d..%d], Time=%dns%n",
                    r, result.getMaxSum(), result.getStartIndex(), result.getEndIndex(), duration);

            try {
                exporter.export(metrics, "Kadane", inputSize, duration);
            } catch (IOException e) {
                System.err.println("Error writing CSV: " + e.getMessage());
            }
        }

        System.out.println("Benchmark completed.");
    }

    private static int[] generateRandomArray(int size, Random random) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(2001) - 1000; // values between -1000 and 1000
        }
        return arr;
    }
}
