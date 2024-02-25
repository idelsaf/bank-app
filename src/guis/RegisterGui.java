package guis;

import db_objs.MyJDBC;

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

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100 + 150 + 75, 370, getWidth() - 650, 30);
        registerButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        registerButton.setBackground(Color.GRAY);
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String fullName = fullNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String rePassword = String.valueOf(rePasswordField.getPassword());

            if (validateUserInput(username, fullName, phoneNumber, password, rePassword)) {
                String details = MyJDBC.register(username, password, fullName, phoneNumber);
                if (details != null) {
                    RegisterGui.this.dispose();

                    LoginGui loginGui = new LoginGui();
                    loginGui.setVisible(true);

                    JOptionPane.showMessageDialog(loginGui,
                            "Registered successfully!\n" +
                                    "Your card details:\n" +
                                    details);
                } else {
                    JOptionPane.showMessageDialog(RegisterGui.this, "Error: Username is already taken.");
                }
            } else {
                JOptionPane.showMessageDialog(RegisterGui.this,
                        """
                                Error: Invalid input...
                                1. All fields must be filled up.
                                2. Username must be at least 6 characters.
                                3. Password must match.
                                4. Make sure you have entered personal information correctly.""");
            }
        });
        add(registerButton);

        JButton loginButton = new JButton("Back to Login");
        loginButton.setBounds(100 + 150 + 75, 420, getWidth() - 650, 30);
        loginButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            RegisterGui.this.dispose();
            new LoginGui().setVisible(true);
        });
        add(loginButton);
    }

    private boolean validateUserInput(String username, String fullName, String phoneNumber, String password, String rePassword) {
        if (username.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            return false;
        }

        if (username.length() < 6)
            return false;

        if (!password.equals(rePassword))
            return false;

        if (!fullName.matches("[a-zA-Z ]+"))
            return false;

        if (!phoneNumber.matches("[0-9]+") || phoneNumber.length() != 11)
            return false;

        return true;
    }
}
