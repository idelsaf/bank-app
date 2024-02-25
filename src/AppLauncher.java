import db_objs.User;
import guis.BankAppGui;
import guis.LoginGui;
import guis.RegisterGui;

import javax.swing.*;
import java.math.BigDecimal;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginGui().setVisible(true);
//                new RegisterGui().setVisible(true);
//            new BankAppGui(
//                    new User(1,
//                            "bipbup",
//                            "1111",
//                            "Gay",
//                            "89988987654",
//                            BigDecimal.valueOf(1000),
//                            "3453453453452",
//                            "1323")
//            ).setVisible(true);
        });
    }
}
