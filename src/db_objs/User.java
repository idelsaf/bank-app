package db_objs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class User {
    private final int id;
    private final String username, password, fullName;
    private final String phoneNumber, cardNumber, pinCode;
    private BigDecimal currentBalance;

    public User(int id, String username, String password, String fullName, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        currentBalance = BigDecimal.valueOf(0);
        cardNumber = generateCardNumber();
        pinCode = generatePinCode();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal newBalance) {
        currentBalance = newBalance.setScale(2, RoundingMode.FLOOR);
    }

    String generateCardNumber() {
        Random r = new Random();
        String cardNum = String.valueOf(r.nextInt(10000000) + 89999999);
        return cardNum;
    }

    String generatePinCode() {
        Random r = new Random();
        String PinCode = String.valueOf(r.nextInt(1000) + 8999);
        return PinCode;
    }
}
