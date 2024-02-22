package guis;

import db_objs.User;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    protected User user;

    public BaseFrame(String title, User user) {
        this.user = user;
        initialize(title);
    }
    public BaseFrame(String title) {
        initialize(title);
    }

    private void initialize(String title) {
        setTitle(title);
        setSize(850, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // terminate program when the gui is closed
        setLayout(null); // to have absolute layout
        setResizable(false);
        setLocationRelativeTo(null); // launch the gui in the center of the screen
        addGuiComponents();
    }

    protected abstract void addGuiComponents();
}


