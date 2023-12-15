package project;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionPurchase extends Transaction {
    private static final long serialVersionUID = 1234567890123456789L;

    public TransactionPurchase(String book, double price, String category, int purchaseQuantity) {
        super(book, purchaseQuantity, price, category, "Purchase");
    }

    @Override
    protected void recordTransaction() {
        List<TransactionPurchase> purchasesHistory = readTransactionHistory(FILE_NAME);
        purchasesHistory.add(this);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(purchasesHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TransactionPurchase> readTransactionHistory(String fileName) {
        List<TransactionPurchase> purchasesHistory;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            purchasesHistory = (List<TransactionPurchase>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            purchasesHistory = new ArrayList<>();
        }
        return purchasesHistory;
    }

    public static void displayTransactionHistory() {
        List<TransactionPurchase> purchasesHistory = readTransactionHistory(FILE_NAME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Transaction transaction : purchasesHistory) {
            if (transaction instanceof TransactionPurchase) {
                TransactionPurchase purchase = (TransactionPurchase) transaction;
                System.out.println("Purchase Transaction - Book: " + purchase.getBook() +
                        ", Quantity: " + purchase.getQuantity() +
                        ", Price: $" + purchase.getPrice() +
                        ", Category: " + purchase.getCategory() +
                        ", Transaction Type: " + purchase.getTransactionType() +
                        ", Transaction ID: " + purchase.getTransactionId() +
                        (purchase.getTransactionTime() != null ?
                                ", Transaction Time: " + purchase.getTransactionTime().format(formatter) :
                                ""));
            }
        }
    }
}

