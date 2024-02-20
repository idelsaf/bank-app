import guis.LoginGui;
import guis.RegisterGui;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new LoginGui().setVisible(true);
                new RegisterGui().setVisible(true);
            }
        });
    }
}
