package education.mvvm.binding;

import education.mvvm.command.Command;
import education.mvvm.property.MutableProperty;
import education.mvvm.property.ReadOnlyProperty;
import education.mvvm.property.Subscription;
import education.mvvm.util.Edt;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;

public class Binder implements AutoCloseable {
    private final List<Subscription> subs = new ArrayList<>();
    private final List<Runnable> unhooks = new ArrayList<>();

    public Binder bindText(JTextField field, MutableProperty<String> prop) {

        subs.add(prop.subscribe(v -> Edt.run(() -> {
            if (!field.getText().equals(v))
                field.setText(v == null ? "" : v);
        })));
        DocumentListener dl = new DocumentListener() {
            private void push() {
                prop.set(field.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                push();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                push();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                push();
            }
        };
        field.getDocument().addDocumentListener(dl);
        unhooks.add(() -> field.getDocument().removeDocumentListener(dl));

        Edt.run(() -> field.setText(prop.get()));
        return this;
    }

    public Binder bindLabel(JLabel label, ReadOnlyProperty<String> prop) {
        subs.add(prop.subscribe(v -> Edt.run(() -> label.setText(v == null ? "" : v))));
        Edt.run(() -> label.setText(prop.get()));
        return this;
    }

    public Binder bindButton(JButton button, Command cmd) {
        java.awt.event.ActionListener al = e -> cmd.execute();
        button.addActionListener(al);
        unhooks.add(() -> button.removeActionListener(al));

        subs.add(cmd.enabled().subscribe(en -> Edt.run(() -> button.setEnabled(Boolean.TRUE.equals(en)))));
        Edt.run(() -> button.setEnabled(Boolean.TRUE.equals(cmd.enabled().get())));
        return this;
    }

    @Override
    public void close() {
        subs.forEach(Subscription::unsubscribe);
        subs.clear();
        unhooks.forEach(Runnable::run);
        unhooks.clear();
    }
}
