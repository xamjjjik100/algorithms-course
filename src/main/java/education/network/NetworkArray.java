package education.network;

public class NetworkArray {
    static class Connection {
        int speed;
        double lossPercent;

        Connection(int speed, double lossPercent) {
            this.speed = speed;
            this.lossPercent = lossPercent;
        }

    }

    public static void main(String[] args) {
        String[] nodes = { "A", "B", "C", "D", "E", "F" };
        Connection[][] graph = new Connection[6][6];

        // A
        graph[0][1] = new Connection(1500, 90); // A-B
        graph[0][2] = new Connection(2000, 10); // A-C
        graph[0][3] = new Connection(1000, 50); // A-D

        // B
        graph[1][0] = new Connection(1500, 90); // B-A
        graph[1][5] = new Connection(1500, 60); // B-F

        // C
        graph[2][0] = new Connection(2000, 10); // C-A
        graph[2][5] = new Connection(500, 20); // C-F
        graph[2][4] = new Connection(900, 5); // C-E

        // D
        graph[3][0] = new Connection(1000, 50); // D-A
        graph[3][4] = new Connection(2500, 1); // D-E

        // E
        graph[4][2] = new Connection(900, 5); // E-C
        graph[4][3] = new Connection(2500, 1); // E-D
        graph[4][5] = new Connection(300, 85); // E-F

        // F
        graph[5][1] = new Connection(1500, 60); // B-F
        graph[5][2] = new Connection(500, 20); // F-C
        graph[5][4] = new Connection(300, 85); // F-E

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != null) {
                    System.out.println(nodes[i] + " -> " + nodes[j] + ": Скорость = " + graph[i][j].speed + " Потери = "
                            + graph[i][j].lossPercent + "%");
                }
            }
        }
    }
}
