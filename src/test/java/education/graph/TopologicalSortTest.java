package education.graph;

import org.junit.jupiter.api.Test;
import education.tsort.TopologicalSort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    @Test
    void emptyGraph() {
        int[][] m = new int[0][0];
        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.sort(m));
    }

    @Test
    void simpleChain() {
        int[][] m = {
                { 0, 1, 0 },
                { 0, 0, 1 },
                { 0, 0, 0 }
        };
        var order = TopologicalSort.sort(m);

        assertEquals(List.of(0, 1, 2), order);
        assertEquals(3, order.size());
    }

    @Test
    void branching() {
        int[][] m = {
                { 0, 1, 1, 0 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 0 }
        };
        var order = TopologicalSort.sort(m);
        assertEquals(4, order.size());

        int p0 = order.indexOf(0);
        int p1 = order.indexOf(1);
        int p2 = order.indexOf(2);
        int p3 = order.indexOf(3);

        assertTrue(p0 < p1);
        assertTrue(p0 < p2);
        assertTrue(p1 < p3);
        assertTrue(p2 < p3);
    }

    @Test
    void multipleComponents() {
        int[][] m = {
                { 0, 1, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 0 }
        };
        var order = TopologicalSort.sort(m);
        assertEquals(4, order.size());

        int p0 = order.indexOf(0);
        int p1 = order.indexOf(1);
        int p2 = order.indexOf(2);
        int p3 = order.indexOf(3);

        assertTrue(p0 < p1);
        assertTrue(p2 < p3);
    }

    @Test
    void weightedEdges() {
        int[][] m = {
                { 0, 2, 0 },
                { 0, 0, 5 },
                { 0, 0, 0 }
        };
        var order = TopologicalSort.sort(m);
        assertEquals(3, order.size());

        int p0 = order.indexOf(0);
        int p1 = order.indexOf(1);
        int p2 = order.indexOf(2);

        assertTrue(p0 < p1);
        assertTrue(p1 < p2);
    }

    @Test
    void selfLoop() {
        int[][] m = {
                { 1, 0 },
                { 0, 0 }
        };
        assertThrows(IllegalStateException.class, () -> TopologicalSort.sort(m));
    }

    @Test
    void cycle() {
        int[][] m = {
                { 0, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 }
        };
        assertThrows(IllegalStateException.class, () -> TopologicalSort.sort(m));
    }

    @Test
    void nonSquareMatrix() {
        int[][] m = {
                { 0, 1 },
                { 0, 0 },
                { 0, 0 }
        };
        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.sort(m));
    }

    @Test
    void nullMatrix() {
        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.sort(null));
    }

}
