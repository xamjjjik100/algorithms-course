package education.mvvm.samples.view;

import education.mvvm.binding.Binder;
import education.mvvm.samples.vm.UserViewModel;

import javax.swing.*;
import java.awt.*;

public class UserForm extends JFrame {
    private final Binder binder = new Binder();

    public UserForm(UserViewModel vm) {
        super("MVVM");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        var field = new JTextField();
        var label = new JLabel();
        var status = new JLabel();
        var button = new JButton("Сохранить");

        var panel = new JPanel(new GridLayout(4, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(field);
        panel.add(label);
        panel.add(button);
        panel.add(status);
        setContentPane(panel);

        binder.bindText(field, vm.name());
        binder.bindLabel(label, vm.greeting());
        binder.bindButton(button, vm.save());
        binder.bindLabel(status, vm.status());
    }

    @Override
    public void dispose() {
        binder.close();
        super.dispose();
    }
}
