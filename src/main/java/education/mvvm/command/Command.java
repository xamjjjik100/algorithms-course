package education.mvvm.command;

import education.mvvm.property.MutableProperty;
import education.mvvm.property.ReadOnlyProperty;

public class Command {
    private final Runnable action;
    private final MutableProperty<Boolean> enabled = new MutableProperty<>(true);

    public Command(Runnable action) {
        this.action = action;
    }

    public void execute() {
        if (Boolean.TRUE.equals(enabled.get()))
            action.run();
    }

    public void setEnabled(boolean v) {
        enabled.set(v);
    }

    public ReadOnlyProperty<Boolean> enabled() {
        return enabled;
    }
}
