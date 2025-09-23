package education;

import education.proxy.AddCountingProxy;
import education.proxy.TestProxy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> base = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        List<Integer> countingList = AddCountingProxy.wrap(base);

        AddCountingProxy.resetCounter();

        TestProxy.test(countingList);

        System.out.println("add вызван: " + AddCountingProxy.getCounter());
    }
}
