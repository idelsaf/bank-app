package guis;

import db_objs.User;

import javax.swing.*;
import java.awt.*;

public class BankAppDialog extends JDialog {
    private User user;
    private BankAppGui bankAppGui;
    private JLabel balanceLabel, enterAmountLabel, enterUserLabel;
    private JTextField enterAmountField, enterUserField;
    private JButton actionButton;

    public BankAppDialog(BankAppGui bankAppGui, User user) {
        setSize(450, 420);
        setModal(true); // add focus to the dialog
        setLocationRelativeTo(bankAppGui);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        this.bankAppGui = bankAppGui;
        this.user = user;
    }

    public void addCurrentBalanceAndAmount() {
        enterAmountLabel = new JLabel("Balance: " + user.getCurrentBalance() + " RUB");
        enterAmountLabel.setBounds(0, 50, getWidth(), 20);
        enterAmountLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

        balanceLabel = new JLabel("Enter amount:");
        balanceLabel.setBounds(0, 90, getWidth(), 20);
        balanceLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        enterAmountField = new JTextField();
        enterAmountField.setBounds(50, 120, getWidth() - 100, 30);
        enterAmountField.setFont(new Font("Montserrat", Font.PLAIN, 18));
        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountField);
    }

    public void addActionButton(String actionButtonType) {
        actionButton = new JButton(actionButtonType);
        actionButton.setBounds(50, 240, getWidth() - 100, 30);
        actionButton.setFont(new Font("Montserrat", Font.BOLD, 18));
        actionButton.setBackground(Color.LIGHT_GRAY);
        add(actionButton);
    }

    public void addUserField() {
        enterUserLabel = new JLabel("Enter user:");
        enterUserLabel.setBounds(0, 160, getWidth(), 20);
        enterUserLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        enterUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserLabel);

        enterUserField = new JTextField();
        enterUserField.setBounds(50, 190, getWidth() - 100, 30);
        enterUserField.setFont(new Font("Montserrat", Font.PLAIN, 18));
        enterUserField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterUserField);

    }

}
