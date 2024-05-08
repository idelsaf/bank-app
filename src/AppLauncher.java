import guis.LoginGui;
import javax.swing.SwingUtilities;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginGui().setVisible(true);
        });
    }
}
