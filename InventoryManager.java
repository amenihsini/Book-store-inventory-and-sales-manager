package project;

import java.io.*;
import java.util.*;

public class InventoryManager {
    private List<Book> bookList; 
    private static final String FILE = "books.dataset"; 

    public InventoryManager() {
        this.bookList = readFromFile(); 
    }

    public void addBook(String name, String author, double price, String category, int quantity) {
        Book newBook = new Book(name, author, price, category, quantity);
        bookList.add(newBook);
        writeToFile();
    }

    public void updateBook(UUID bookId, String newName, String newAuthor, int newQuantity, double newPrice, String newCategory) {
        for (Book book : bookList) {
            if (book.getId().equals(bookId)) {
                if (newName != null && !newName.isEmpty()) {
                    book.setName(newName);
                }
                if (newAuthor != null && !newAuthor.isEmpty()) {
                    book.setAuthor(newAuthor);
                }
                if (newQuantity >= 0) {
                    book.setQuantity(newQuantity);
                }
                if (newPrice >= 0) {
                    book.setPrice(newPrice);
                }
                if (newCategory != null && !newCategory.isEmpty()) {
                    book.setCategory(newCategory);
                }
                writeToFile();
                break;
            }
        }
    }

    public void removeBook(UUID bookId) {
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(bookId)) {
                iterator.remove();
                writeToFile();
                break;
            }
        }
    }

    public void displayInventory() {
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    private void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Book> readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            return new ArrayList<>();
        }
    }

    public void updateBookQuantity(String bookName, int quantityChange, String transactionType) {
        for (Book book : bookList) {
            if (book.getName().equalsIgnoreCase(bookName)) {
                int currentQuantity = book.getQuantity();
                int newQuantity;

                if ("Purchase".equalsIgnoreCase(transactionType)) {
                    newQuantity = currentQuantity + quantityChange;
                } else if ("Sale".equalsIgnoreCase(transactionType)) {
                    newQuantity = currentQuantity - quantityChange;
                    if (newQuantity < 0) {
                        System.out.println("Error: Insufficient quantity in stock for sale.");
                        return;
                    }
                } else {
                    System.out.println("Error: Invalid transaction type.");
                    return;
                }

                book.setQuantity(newQuantity);
                writeToFile();
                break;
            }
        }
    }
}
