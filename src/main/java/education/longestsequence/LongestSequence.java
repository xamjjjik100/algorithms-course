package education.longestsequence;

public final class LongestSequence {
    private LongestSequence() {
    }

    public static String ls(String a, String b) {
        validate(a, b);
        int[][] matrix = buildMatrix(a, b);
        int n = a.length(), m = b.length();

        StringBuilder sb = new StringBuilder(matrix[n - 1][m - 1]);
        int i = n - 1, j = m - 1;

        while (i >= 0 && j >= 0) {
            if (a.charAt(i) == b.charAt(j)) {
                sb.append(a.charAt(i));
                i--;
                j--;
            } else if (i == 0 && j == 0) {
                // выходим
                break;
            } else if (i == 0) {
                // можно только влево
                j--;
            } else if (j == 0) {
                // можно только вверх
                i--;
            } else if (matrix[i - 1][j] >= matrix[i][j - 1]) { // выбираем правильный путь
                i--;
            } else {
                j--;
            }
        }

        return sb.reverse().toString();
    }

    // Строим матрицу
    private static int[][] buildMatrix(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] matrix = new int[n][m];

        matrix[0][0] = (a.charAt(0) == b.charAt(0)) ? 1 : 0;

        for (int j = 1; j < m; j++) {
            int add = (a.charAt(0) == b.charAt(j)) ? 1 : 0;
            matrix[0][j] = Math.max(matrix[0][j - 1], add);
        }

        for (int i = 1; i < n; i++) {
            int add = (a.charAt(i) == b.charAt(0)) ? 1 : 0;
            matrix[i][0] = Math.max(matrix[i - 1][0], add);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix;
    }

    private static void validate(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Аргументы не должны быть null");
        }
        if (a.isEmpty() || b.isEmpty()) {
            throw new IllegalArgumentException("Строки не должны быть пустые");
        }
    }

}
