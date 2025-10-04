import com.anuar.algorithms.KadaneAlgorithm;
import com.anuar.metrics.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KadaneAlgorithmTest {

    @Test
    void testBasicArray() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(6, result.getMaxSum());
        assertEquals(3, result.getStartIndex()); // subarray [4, -1, 2, 1]
        assertEquals(6, result.getEndIndex());
    }

    @Test
    void testAllNegativeNumbers() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {-5, -2, -3, -7};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(-2, result.getMaxSum());
        assertEquals(1, result.getStartIndex());
        assertEquals(1, result.getEndIndex());
    }

    @Test
    void testSingleElementPositive() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {10};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(10, result.getMaxSum());
        assertEquals(0, result.getStartIndex());
        assertEquals(0, result.getEndIndex());
    }

    @Test
    void testSingleElementNegative() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {-42};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(-42, result.getMaxSum());
        assertEquals(0, result.getStartIndex());
        assertEquals(0, result.getEndIndex());
    }

    @Test
    void testAllPositiveNumbers() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {1, 2, 3, 4, 5};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(15, result.getMaxSum());
        assertEquals(0, result.getStartIndex());
        assertEquals(4, result.getEndIndex());
    }

    @Test
    void testArrayWithZeroes() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {0, -1, 2, 0, -3, 4};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(4, result.getMaxSum());
        assertEquals(5, result.getStartIndex()); // [4]
        assertEquals(5, result.getEndIndex());
    }

    @Test
    void testEmptyArrayThrowsException() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        assertThrows(IllegalArgumentException.class, () -> algo.findMaxSubarray(new int[]{}));
    }

    @Test
    void testNullArrayThrowsException() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        assertThrows(IllegalArgumentException.class, () -> algo.findMaxSubarray(null));
    }

    @Test
    void testMetricsAreCollected() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {1, -2, 3};
        algo.findMaxSubarray(arr);

        assertTrue(metrics.getComparisons() > 0, "Should track comparisons");
        assertTrue(metrics.getArrayAccesses() > 0, "Should track array accesses");
    }
}