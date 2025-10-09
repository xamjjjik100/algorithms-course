package education.mvvm.property;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class MutableProperty<T> implements ReadOnlyProperty<T> {
    private T value;
    private final List<Consumer<T>> listeners = new ArrayList<>();

    public MutableProperty(T initial) {
        this.value = initial;
    }

    public void set(T newValue) {
        if (!Objects.equals(this.value, newValue)) {
            this.value = newValue;
            for (var l : List.copyOf(listeners))
                l.accept(this.value);
        }
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public Subscription subscribe(Consumer<T> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }
}
