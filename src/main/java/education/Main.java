package education;

import education.graph.Edge;
import education.graph.GraphRearrangement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Пример матрицы смежности
        int[][] matrix = {
                { 0, 5, 6, 0, 8 },
                { 5, 0, 0, 0, 4 },
                { 6, 0, 0, 3, 0 },
                { 0, 0, 3, 0, 2 },
                { 8, 4, 0, 2, 0 }
        };

        List<Edge> edges = GraphRearrangement.adjacencyMatrixToEdgeList(matrix);

        System.out.println("Edges:");
        for (Edge e : edges) {
            System.out.println(e);
        }
    }
}
