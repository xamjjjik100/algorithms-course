package education.graph;

public final class Edge {
    public final int from;
    public final int to;
    public final int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // Перевод числовых обозначений в буквенные
    private String idxToLabel(int idx) {
        return String.valueOf((char) ('A' + idx));
    }

    // Для красивого вывода
    @Override
    public String toString() {
        return "Edge{" + idxToLabel(from) + "->" + idxToLabel(to) + ", w=" + weight + "}";
    }
}
