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
    void testSingleElement() {
        Metrics metrics = new Metrics();
        KadaneAlgorithm algo = new KadaneAlgorithm(metrics);

        int[] arr = {10};
        KadaneAlgorithm.Result result = algo.findMaxSubarray(arr);

        assertEquals(10, result.getMaxSum());
        assertEquals(0, result.getStartIndex());
        assertEquals(0, result.getEndIndex());
    }
}