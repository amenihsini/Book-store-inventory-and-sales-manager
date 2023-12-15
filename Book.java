package project;

import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID bookId = UUID.randomUUID();
    private String name;
    private String author;
    private int quantity;
    private double price;
    private String category;

    public Book(String name, String author, double price, String category, int quantity) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public UUID getId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
