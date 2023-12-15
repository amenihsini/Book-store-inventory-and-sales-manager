package project;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionSale extends Transaction {
    private static final long serialVersionUID = 893664019403499356L;

    public TransactionSale(String book, int saleQuantity, double price, String category) {
        super(book, saleQuantity, price, category, "Sale");
    }

    @Override
    protected void recordTransaction() {
        List<TransactionSale> saleHistory = readTransactionHistory(FILE_NAME);
        saleHistory.add(this);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(saleHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<TransactionSale> readTransactionHistory(String fileName) {
        List<TransactionSale> saleHistory;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            saleHistory = (List<TransactionSale>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            saleHistory = new ArrayList<>();
        }
        return saleHistory;
    }

    public static void displayTransactionHistory() {
        List<TransactionSale> saleHistory = readTransactionHistory(FILE_NAME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Transaction transaction : saleHistory) {
            if (transaction instanceof TransactionSale) {
                TransactionSale sale = (TransactionSale) transaction;
                System.out.println("Sale Transaction - Book: " + sale.getBook() +
                        ", Quantity: " + sale.getQuantity() +
                        ", Price: " + sale.getPrice() +
                        ", Category: " + sale.getCategory() +
                        ", Transaction Type: " + sale.getTransactionType() +
                        ", Transaction ID: " + sale.getTransactionId() +
                        (sale.getTransactionTime() != null ?
                                ", Transaction Time: " + sale.getTransactionTime().format(formatter) :
                                ""));
            }
        }
    }
}