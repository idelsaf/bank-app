package guis;

import db_objs.User;

import javax.swing.*;

public class BankAppDialog extends JDialog {
    private User user;
    private BankAppGui bankAppGui;

    public BankAppDialog(BankAppGui bankAppGui, User user) {
        setSize(450, 450);
        setModal(true); // add focus to the dialog
        setLocationRelativeTo(bankAppGui);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        this.bankAppGui = bankAppGui;
        this.user = user;

    }

}
