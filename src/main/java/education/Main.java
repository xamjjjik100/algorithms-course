package education;

import education.mvvm.samples.model.User;
import education.mvvm.samples.view.UserForm;
import education.mvvm.samples.vm.UserViewModel;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User user = new User();
            UserViewModel vm = new UserViewModel(user);
            new UserForm(vm).setVisible(true);
        });
    }
}
