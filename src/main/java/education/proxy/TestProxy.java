package education.proxy;

import java.util.List;

public final class TestProxy {
    private TestProxy() {
    }

    public static void test(List<?> list) {
        if (!list.isEmpty()) {
            duplicateFirst(list);
        }
    }

    private static <T> void duplicateFirst(List<T> list) {
        T first = list.get(0);
        list.add(first);
        list.add(0, first);
        list.add(first);
        list.add(0, first);
    }
}
