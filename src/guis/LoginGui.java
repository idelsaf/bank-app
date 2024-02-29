package guis;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;

public class LoginGui extends BaseFrame {
    public LoginGui() {
        super("Bank App Login");
    }

    @Override
    protected void addGuiComponents() {
        JLabel bankingAppLabel = new JLabel("Bank App");
        bankingAppLabel.setBounds(0, 20, super.getWidth(), 45);
        bankingAppLabel.setFont(new Font("Benzin-Medium", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(120, 140, 150, 24);
        usernameLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(100 + 150, 140, getWidth() - 500, 30);
        usernameField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(120, 200, 150, 24);
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100 + 150, 200, getWidth() - 500, 30);
        passwordField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100 + 150 + 75, 270, getWidth() - 650, 30);
        loginButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        loginButton.setBackground(Color.GRAY);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            User user = MyJDBC.validateLogin(username, password);

            if (user != null) {
                LoginGui.this.dispose();
                BankAppGui bankAppGui = new BankAppGui(user);
                bankAppGui.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(LoginGui.this, "Login failed...");
            }
        });
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100 + 150 + 75, 320, getWidth() - 650, 30);
        registerButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(e -> {
            LoginGui.this.dispose();
            RegisterGui registerGui = new RegisterGui();
            registerGui.setVisible(true);
        });
        add(registerButton);
    }
}
