package education.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public final class AddCountingProxy {
    private static int counter = 0;

    private AddCountingProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> wrap(List<T> target) {

        InvocationHandler h = (proxy, method, args) -> {
            if ("add".equals(method.getName()))
                counter++;
            return method.invoke(target, args);
        };

        return (List<T>) Proxy.newProxyInstance(
                List.class.getClassLoader(),
                new Class<?>[] { List.class },
                h);
    }

    public static int getCounter() {
        return counter;
    }

    public static void resetCounter() {
        counter = 0;
    }
}