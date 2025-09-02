package education.graph;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphRearrangementTest {

    @Test
    void matrix_is_converted_to_edges() {
        int[][] m = {
                { 0, 5, 6, 0, 8 },
                { 5, 0, 0, 0, 4 },
                { 6, 0, 0, 3, 0 },
                { 0, 0, 3, 0, 2 },
                { 8, 4, 0, 2, 0 }
        };

        List<Edge> edges = GraphRearrangement.adjacencyMatrixToEdgeList(m);

        // проверим количество рёбер
        assertEquals(12, edges.size());

        // проверим несколько конкретных рёбер
        assertTrue(edges.stream().anyMatch(e -> e.from == 0 && e.to == 1 && e.weight == 5));
        assertTrue(edges.stream().anyMatch(e -> e.from == 2 && e.to == 3 && e.weight == 3));
        assertTrue(edges.stream().anyMatch(e -> e.from == 4 && e.to == 1 && e.weight == 4));
    }

    // Проверка на пустую матрицу
    @Test
    void empty_matrix_returns_empty_list() {
        int[][] m = new int[0][0];
        List<Edge> edges = GraphRearrangement.adjacencyMatrixToEdgeList(m);
        assertTrue(edges.isEmpty());
    }

    // Проверка на "кривую" матрицу
    @Test
    void non_square_matrix_throws_exception() {
        int[][] bad = {
                { 0, 1, 0 },
                { 1, 0 }
        };
        assertThrows(IllegalArgumentException.class,
                () -> GraphRearrangement.adjacencyMatrixToEdgeList(bad));
    }
}
