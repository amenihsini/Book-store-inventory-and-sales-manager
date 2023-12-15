package project;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Transaction implements Serializable {
    private static final long serialVersionUID = -6399356213204462292L;
    protected static final String FILE_NAME = "transactions_history.dat";

    private UUID transactionId;
    private String book;
    private int quantity;
    private String category;
    private double price;
    private String transactionType;
    private LocalDateTime transactionTime;

    public Transaction(String book, int quantity, double price, String category, String transactionType) {
        this.transactionId = UUID.randomUUID();
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.transactionType = transactionType;
        this.transactionTime = LocalDateTime.now();
    }

    protected void recordTransaction() {
        List<Transaction> transactionsHistory = readTransactionHistory();
        transactionsHistory.add(this);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transactionsHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected List<Transaction> readTransactionHistory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            return new ArrayList<>();
        }
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }


}