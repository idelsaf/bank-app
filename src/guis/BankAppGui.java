package guis;

import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAppGui extends BaseFrame implements ActionListener {
    private JTextField balanceField;

    public JTextField getBalanceField() {
        return balanceField;
    }

    public BankAppGui(User user) {
        super("Bank App", user);
    }

    @Override
    protected void addGuiComponents() {
        String welcomeMessage = "<html>" +
                "<body style='text-align:center'>" +
                "<b>Hello, " + user.getFullName() + "!</b><br>" +
                "What would you like to do?</body></html>";
        JLabel welcomeMessageLabel = new JLabel(welcomeMessage);
        welcomeMessageLabel.setBounds(0, 30, getWidth() - 20, 50);
        welcomeMessageLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);

        JLabel balanceLabel = new JLabel("Current balance:");
        balanceLabel.setBounds(0, 90, getWidth() - 20, 50);
        balanceLabel.setFont(new Font("Montserrat", Font.BOLD, 25));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        balanceField = new JTextField(user.getCurrentBalance() + " RUB");
        balanceField.setBounds(240, 140, getWidth() - 500, 30);
        balanceField.setFont(new Font("Montserrat", Font.BOLD, 20));
        balanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        balanceField.setEditable(false);
        add(balanceField);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(240, 200, getWidth() - 500, 30);
        depositButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        depositButton.setHorizontalAlignment(SwingConstants.CENTER);
        depositButton.setBackground(Color.LIGHT_GRAY);
        depositButton.addActionListener(this);
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(240, 250, getWidth() - 500, 30);
        withdrawButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        withdrawButton.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawButton.setBackground(Color.LIGHT_GRAY);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(240, 300, getWidth() - 500, 30);
        transferButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        transferButton.setHorizontalAlignment(SwingConstants.CENTER);
        transferButton.setBackground(Color.LIGHT_GRAY);
        transferButton.addActionListener(this);
        add(transferButton);

        JButton transactionsButton = new JButton("All transactions");
        transactionsButton.setBounds(240, 350, getWidth() - 500, 30);
        transactionsButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        transactionsButton.setHorizontalAlignment(SwingConstants.CENTER);
        transactionsButton.setBackground(Color.LIGHT_GRAY);
        transactionsButton.addActionListener(this);
        add(transactionsButton);

        JButton cardDetailsButton = new JButton("Card Details");
        cardDetailsButton.setBounds(240, 400, getWidth() - 500, 30);
        cardDetailsButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        cardDetailsButton.setHorizontalAlignment(SwingConstants.CENTER);
        cardDetailsButton.setBackground(Color.LIGHT_GRAY);
        cardDetailsButton.addActionListener(this);
        add(cardDetailsButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(700, 30, getWidth() - 730, 30);
        logoutButton.setFont(new Font("Montserrat", Font.BOLD, 20));
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
        logoutButton.setBackground(new Color(255, 89, 64));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(this);
        add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();

        if (buttonPressed.equalsIgnoreCase("Logout")) {
            new LoginGui().setVisible(true);

            this.dispose();
            return;
        }

        BankAppDialog bankAppDialog = new BankAppDialog(this, user);

        bankAppDialog.setTitle(buttonPressed);

        if (buttonPressed.equalsIgnoreCase("Deposit") || buttonPressed.equalsIgnoreCase("Withdraw")
                || buttonPressed.equalsIgnoreCase("Transfer")) {
            bankAppDialog.addCurrentBalanceAndAmount();

            bankAppDialog.addActionButton(buttonPressed);

            if (buttonPressed.equalsIgnoreCase("Transfer")) {
                bankAppDialog.addUserField();
            }

        } else if (buttonPressed.equalsIgnoreCase("All transactions")) {
            bankAppDialog.addAllTransactionsComponents();
        }

        bankAppDialog.setVisible(true);
    }
}
