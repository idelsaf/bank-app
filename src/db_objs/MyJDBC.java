package db_objs;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class MyJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank-app";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";

    public static User validateLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                String phoneNumber = resultSet.getString("phone_number");
                BigDecimal currentBalance = resultSet.getBigDecimal("current_balance");
                String cardNumber = resultSet.getString("card_number");
                String pinCode = resultSet.getString("pin_code");

                return new User(userId, username, password, fullName, phoneNumber, currentBalance, cardNumber, pinCode);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static String register(String username, String password, String fullName, String phoneNumber) {
        try {
            if (isUsernameUnique(username)) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO  users(username, password, full_name, phone_number, card_number, pin_code)"
                                + "VALUES(?, ?, ?, ?, ?, ?)"
                );

                String cardNumber = generateCardNumber();
                String pinCode = generatePinCode();

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, fullName);
                preparedStatement.setString(4, phoneNumber);
                preparedStatement.setString(5, cardNumber);
                preparedStatement.setString(6, pinCode);

                preparedStatement.executeUpdate();

                return "Card number: " + cardNumber + "\nPin code: " + pinCode;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static boolean isUsernameUnique(String username) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private static String generateCardNumber() {
        Random random = new Random();
        String cardNum;
        do {
            cardNum = String.valueOf(1000_0000_0000_0000L + random.nextLong(9000_0000_0000_0000L));
        } while (!isCardNumberUnique(cardNum));

        return cardNum;
    }

    private static String generatePinCode() {
        Random random = new Random();

        return String.valueOf(1000 + random.nextInt(9000));
    }

    private static boolean isCardNumberUnique(String cardNum) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE card_number = ?");

            preparedStatement.setString(1, cardNum);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public static boolean addTransactionToDB(Transaction transaction) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement insertTransaction = connection.prepareStatement(
                    "INSERT transactions(user_id, transaction_type, transaction_date, transaction_amount)" +
                            "VALUES(?, ?, NOW(), ?)"
            );

            insertTransaction.setInt(1, transaction.getUserId());
            insertTransaction.setString(2, transaction.getTransactionType());
            insertTransaction.setBigDecimal(3, transaction.getTransactionAmount());

            insertTransaction.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateBalance(User user) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement updateBalance = connection.prepareStatement(
                    "UPDATE users SET current_balance = ? WHERE id = ?"
            );

            updateBalance.setBigDecimal(1, user.getCurrentBalance());
            updateBalance.setInt(2, user.getId());

            updateBalance.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean transfer(User user, String receiverUsername, double transferAmount) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement queryUser = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );

            queryUser.setString(1, receiverUsername);
            ResultSet resultSet = queryUser.executeQuery();

            while (resultSet.next()) {
                User receiverUser = new User(
                        resultSet.getInt("id"),
                        receiverUsername,
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("phone_number"),
                        resultSet.getBigDecimal("current_balance"),
                        resultSet.getString("card_number"),
                        resultSet.getString("pin_code")
                );

                Transaction transferTransaction = new Transaction(
                        user.getId(),
                        "Transfer",
                        null,
                        new BigDecimal(transferAmount)
                );

                Transaction receivedTransaction = new Transaction(
                        receiverUser.getId(),
                        "Transfer",
                        null,
                        new BigDecimal(transferAmount)
                );

                receiverUser.setCurrentBalance(receiverUser.getCurrentBalance().add(BigDecimal.valueOf(transferAmount)));
                updateBalance(receiverUser);

                user.setCurrentBalance(user.getCurrentBalance().subtract(BigDecimal.valueOf(transferAmount)));
                updateBalance(user);

                addTransactionToDB(transferTransaction);
                addTransactionToDB(receivedTransaction);

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
