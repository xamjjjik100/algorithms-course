package education.mvvm.samples.vm;

import javax.swing.Timer;

import education.mvvm.command.Command;
import education.mvvm.property.MutableProperty;
import education.mvvm.property.ReadOnlyProperty;
import education.mvvm.samples.model.User;

public class UserViewModel {
    private final User user;

    private final MutableProperty<String> name = new MutableProperty<>("");
    private final MutableProperty<String> greeting = new MutableProperty<>("");
    private final MutableProperty<String> status = new MutableProperty<>("");
    private final Command saveCmd;

    public UserViewModel(User user) {
        this.user = user;

        name.subscribe(n -> greeting.set(n == null || n.isBlank() ? "" : "Привет, " + n + "!"));

        saveCmd = new Command(() -> {
            user.setName(name.get());
            showSavedStatus();
        });
    }

    private void showSavedStatus() {
        status.set("Сохранено!");

        Timer timer = new Timer(2000, e -> status.set(""));
        timer.setRepeats(false);
        timer.start();
    }

    public MutableProperty<String> name() {
        return name;
    }

    public ReadOnlyProperty<String> greeting() {
        return greeting;
    }

    public ReadOnlyProperty<String> status() {
        return status;
    }

    public Command save() {
        return saveCmd;
    }
}
