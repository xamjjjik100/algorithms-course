package education.graph;

import java.util.ArrayList;
import java.util.List;

public final class GraphRearrangement {
    private GraphRearrangement() {
    }

    public static List<Edge> adjacencyMatrixToEdgeList(int[][] matrix) {

        // Проверка матрицы
        if (matrix == null) {
            throw new IllegalArgumentException("Матрица пустая");
        }
        final int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i] == null || matrix[i].length != n) {
                throw new IllegalArgumentException("Матрица должна быть квадратная");
            }
        }

        // Перекладка матрицы в список ребер
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int w = matrix[i][j];
                if (w != 0) {
                    edges.add(new Edge(i, j, w));
                }
            }
        }
        return edges;
    }
}
