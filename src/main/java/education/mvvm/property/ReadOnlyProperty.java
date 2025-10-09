package education.mvvm.property;

import java.util.function.Consumer;

public interface ReadOnlyProperty<T> {
    T get();

    Subscription subscribe(Consumer<T> listener);
}
