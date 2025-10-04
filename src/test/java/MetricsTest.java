import com.anuar.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MetricsTest {

    @Test
    void testMetricsCounters() {
        Metrics metrics = new Metrics();
        metrics.incrementComparisons();
        metrics.incrementArrayAccesses();
        metrics.incrementMemoryAllocations();

        assertEquals(1, metrics.getComparisons());
        assertEquals(1, metrics.getArrayAccesses());
        assertEquals(1, metrics.getMemoryAllocations());
    }

    @Test
    void testReset() {
        Metrics metrics = new Metrics();
        metrics.incrementComparisons();
        metrics.reset();
        assertEquals(0, metrics.getComparisons());
    }
}