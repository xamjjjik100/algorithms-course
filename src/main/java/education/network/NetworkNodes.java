package education.network;

import java.util.*;

public class NetworkNodes {

    static class Connection {
        Node target;
        int speed;
        double lossPercent;

        Connection(Node target, int speed, double lossPercent) {
            this.target = target;
            this.speed = speed;
            this.lossPercent = lossPercent;
        }
    }

    static class Node {
        String name;
        List<Connection> connections = new ArrayList<>();

        Node(String name) {
            this.name = name;
        }

        void addConnection(Node to, int speed, double lossPercent) {
            connections.add(new Connection(to, speed, lossPercent));
        }
    }

    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        A.addConnection(B, 1500, 90);
        B.addConnection(A, 1500, 90);

        A.addConnection(C, 2000, 10);
        C.addConnection(A, 2000, 10);

        A.addConnection(D, 1000, 50);
        D.addConnection(A, 1000, 50);

        B.addConnection(F, 1500, 60);
        F.addConnection(B, 1500, 60);

        C.addConnection(F, 500, 20);
        F.addConnection(C, 500, 20);

        C.addConnection(E, 900, 5);
        E.addConnection(C, 900, 5);

        D.addConnection(E, 2500, 1);
        E.addConnection(D, 2500, 1);

        E.addConnection(F, 300, 85);
        F.addConnection(E, 300, 85);

        List<Node> nodes = List.of(A, B, C, D, E, F);

        System.out.println("Список соединений:");
        for (Node node : nodes) {
            for (Connection Connection : node.connections) {
                System.out.printf("%s -> %s: %d Mbps, %.1f%% loss%n",
                        node.name, Connection.target.name, Connection.speed, Connection.lossPercent);
            }
        }
    }
}
