package com.anuar.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class MetricsCSVExporter {
    private final String filePath;

    public MetricsCSVExporter(String filePath) {
        this.filePath = filePath;
    }

    public void export(Metrics metrics, String algorithmName, int inputSize, long executionTime) throws IOException {
        boolean append = true;
        try (FileWriter writer = new FileWriter(filePath, append)) {
            writer.write(String.format(
                    "%s,%d,%d,%d,%d,%d%n",
                    algorithmName,
                    inputSize,
                    metrics.getComparisons(),
                    metrics.getArrayAccesses(),
                    metrics.getMemoryAllocations(),
                    executionTime
            ));
        }
    }
}
