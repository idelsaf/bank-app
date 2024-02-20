package guis;

import javax.swing.*;
import java.awt.*;

public class RegisterGui extends BaseFrame {
    public RegisterGui() {
        super("Bank App Register");

    }

    @Override
    protected void addGuiComponents() {
        JLabel bankingAppLabel = new JLabel("Bank App");
        bankingAppLabel.setBounds(0, 20, super.getWidth(), 45);
        bankingAppLabel.setFont(new Font("Benzin-Medium", Font.BOLD, 32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 100, 150, 24);
        usernameLabel.setFont(new Font("Montserrat", Font.PLAIN,20));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(130 + 150, 100, getWidth() - 530, 30);
        usernameField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(usernameField);

        JLabel fullNameLabel = new JLabel("Full name:");
        fullNameLabel.setBounds(100, 150, 150, 24);
        fullNameLabel.setFont(new Font("Montserrat", Font.PLAIN,20));
        add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(130 + 150, 150, getWidth() - 530, 30);
        fullNameField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(fullNameField);

        JLabel phoneNumberLabel = new JLabel("Phone number:");
        phoneNumberLabel.setBounds(100, 200, 230, 24);
        phoneNumberLabel.setFont(new Font("Montserrat", Font.PLAIN,20));
        add(phoneNumberLabel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(130 + 150, 200, getWidth() - 530, 30);
        phoneNumberField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(phoneNumberField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 250, 150, 24);
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN,20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(130 + 150, 250, getWidth() - 530, 30);
        passwordField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(passwordField);

        JLabel rePasswordLabel = new JLabel("Re-password:");
        rePasswordLabel.setBounds(100, 300, 150, 24);
        rePasswordLabel.setFont(new Font("Montserrat", Font.PLAIN,20));
        add(rePasswordLabel);

        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(130 + 150, 300, getWidth() - 530, 30);
        rePasswordField.setFont(new Font("Montserrat", Font.PLAIN, 20));
        add(rePasswordField);

        JButton loginButton = new JButton("Register");
        loginButton.setBounds(100 + 150 + 75, 370, getWidth() - 650, 30);
        loginButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        loginButton.setBackground(Color.GRAY);
        loginButton.setForeground(Color.WHITE);
        add(loginButton);

        JButton registerButton = new JButton("Back to Login");
        registerButton.setBounds(100 + 150 + 75, 420, getWidth() - 650, 30);
        registerButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setForeground(Color.WHITE);
        add(registerButton);
    }
}
