package guis;

import db_objs.MyJDBC;
import db_objs.Transaction;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class BankAppDialog extends JDialog implements ActionListener {
    private User user;
    private BankAppGui bankAppGui;
    private JLabel balanceLabel, enterAmountLabel, enterUserLabel;
    private JTextField enterAmountField, enterUserField;
    private JButton actionButton;
    private JPanel allTransactionsPanel;
    private ArrayList<Transaction> allTransactions;

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
        balanceLabel = new JLabel("Balance: " + user.getCurrentBalance() + " RUB");
        balanceLabel.setBounds(0, 50, getWidth(), 20);
        balanceLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        enterAmountLabel = new JLabel("Enter amount:");
        enterAmountLabel.setBounds(0, 90, getWidth(), 20);
        enterAmountLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);

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
        actionButton.addActionListener(this);
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

    public void addAllTransactionsComponents() {
        allTransactionsPanel = new JPanel();
        allTransactionsPanel.setLayout(new BoxLayout(allTransactionsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(allTransactionsPanel);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 20, getWidth() - 20, getHeight() - 20);

        allTransactions = MyJDBC.getAllTransactions(user);

        for (int i = 0; i < allTransactions.size(); i++) {
            Transaction transaction = allTransactions.get(i);

            JPanel allTransactionsContainer = new JPanel();
            allTransactionsContainer.setLayout(new BorderLayout());

            JLabel transactionTypeLabel = new JLabel(transaction.getTransactionType());
            transactionTypeLabel.setFont(new Font("Montserrat", Font.BOLD, 18));

            JLabel transactionDateLabel = new JLabel(String.valueOf(transaction.getTransactionDate()));
            transactionDateLabel.setFont(new Font("Montserrat", Font.PLAIN, 16));

            JLabel transactionAmountLabel = new JLabel(String.valueOf(transaction.getTransactionAmount() + " RUB"));
            transactionAmountLabel.setFont(new Font("Montserrat", Font.BOLD, 18));

            allTransactionsContainer.add(transactionTypeLabel, BorderLayout.WEST);
            allTransactionsContainer.add(transactionDateLabel, BorderLayout.SOUTH);
            allTransactionsContainer.add(transactionAmountLabel, BorderLayout.EAST);

            allTransactionsContainer.setBackground(Color.WHITE);
            allTransactionsContainer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            allTransactionsPanel.add(allTransactionsContainer);
        }

        add(scrollPane);
    }

    private void handleTransaction(String transactionType, double amountValue) {
        Transaction transaction;

        if (transactionType.equalsIgnoreCase("Deposit")) {
            user.setCurrentBalance(user.getCurrentBalance().add(new BigDecimal(amountValue)));

            transaction = new Transaction(user.getId(), transactionType, null, new BigDecimal(amountValue));
        } else {
            user.setCurrentBalance(user.getCurrentBalance().subtract(new BigDecimal(amountValue)));

            transaction = new Transaction(user.getId(), transactionType, null, BigDecimal.valueOf(-amountValue));
        }

        if (MyJDBC.addTransactionToDB(transaction) && MyJDBC.updateBalance(user)) {
            JOptionPane.showMessageDialog(this, transactionType + " successfully!");

            resetFieldsAndUpdateBalance();
        } else {
            JOptionPane.showMessageDialog(this, transactionType + " failed...");
        }
    }

    private void resetFieldsAndUpdateBalance() {
        enterAmountField.setText("");

        if (enterUserField != null) {
            enterUserField.setText("");
        }

        balanceLabel.setText("Balance: " + user.getCurrentBalance() + " RUB");
        bankAppGui.getBalanceField().setText(user.getCurrentBalance() + " RUB");
    }

    private void handleTransfer(User user, String receiverUser, double amount) {
        if (MyJDBC.transfer(user, receiverUser, amount)) {
            JOptionPane.showMessageDialog(this, "Transfer successfully!");
            resetFieldsAndUpdateBalance();
        } else {
            JOptionPane.showMessageDialog(this, "Transfer failed...");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();

        double amountValue = Double.parseDouble(enterAmountField.getText());

        if (buttonPressed.equalsIgnoreCase("Deposit")) {
            handleTransaction(buttonPressed, amountValue);
        } else {
            int result = user.getCurrentBalance().compareTo(BigDecimal.valueOf(amountValue));
            if (result < 0) {
                JOptionPane.showMessageDialog(this, "Error: Insufficient funds on the card...");
                return;
            }

            if (buttonPressed.equalsIgnoreCase("Withdraw")) {
                handleTransaction(buttonPressed, amountValue);
            } else {
                String receiverUser = enterUserField.getText();

                handleTransfer(user, receiverUser, amountValue);
            }
        }
    }
}
