package education.tsort;

import java.util.*;

public final class TopologicalSort {

    private TopologicalSort() {
    }

    public static List<Integer> sort(int[][] m) {

        validateMatrix(m);
        int n = m.length;
        List<Integer> order = new ArrayList<>(n);

        int[] degree = new int[n];
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (m[u][v] != 0) {
                    if (u == v) {
                        throw new IllegalStateException("Вершина зациклена " + u);
                    }
                    degree[v]++;
                }
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (degree[v] == 0)
                q.add(v);
        }

        while (!q.isEmpty()) {
            int u = q.removeFirst();
            order.add(u);
            for (int v = 0; v < n; v++) {
                if (m[u][v] != 0) {
                    degree[v]--;
                    if (degree[v] == 0)
                        q.add(v);
                }
            }
        }

        if (order.size() != n) {
            throw new IllegalStateException("Граф зациклен");
        }
        return order;
    }

    private static void validateMatrix(int[][] m) {
        if (m == null) {
            throw new IllegalArgumentException("Матрица null");
        }

        if (m.length == 0) {
            throw new IllegalArgumentException("Матрица пуста (нет вершин)");
        }
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                throw new IllegalArgumentException("Ряд " + i + " пустой");
            }
            if (m[i].length != m.length) {
                throw new IllegalArgumentException("Матрица должны быть квадратная ");
            }
        }
    }
}
