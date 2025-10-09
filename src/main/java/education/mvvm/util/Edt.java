package education.mvvm.util;

import javax.swing.SwingUtilities;

public final class Edt {
    private Edt() {
    }

    public static void run(Runnable r) {
        if (SwingUtilities.isEventDispatchThread())
            r.run();
        else
            SwingUtilities.invokeLater(r);
    }
}
