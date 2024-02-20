package guis;

import javax.swing.*;
import java.awt.*;

public class LoginGui extends BaseFrame{
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
        usernameLabel.setBounds(100, 140, 150, 24);
        usernameLabel.setFont(new Font("Benzin-Medium", Font.PLAIN,20));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(100 + 150, 135, getWidth() - 500, 30);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 200, 150, 24);
        passwordLabel.setFont(new Font("Benzin-Medium", Font.PLAIN,20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100 + 150, 195, getWidth() - 500, 30);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100 + 150 + 75, 260, getWidth() - 650, 30);
        loginButton.setFont(new Font("Benzin-Medium", Font.BOLD, 15));
        loginButton.setBackground(Color.LIGHT_GRAY);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100 + 150 + 75, 310, getWidth() - 650, 30);
        registerButton.setFont(new Font("Benzin-Medium", Font.BOLD, 15));
        registerButton.setBackground(Color.LIGHT_GRAY);
        add(registerButton);
    }
}
