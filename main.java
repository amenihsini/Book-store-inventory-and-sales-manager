package project;

import java.util.Scanner;
import java.util.UUID;

public class main_project {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxAttempts = 3;
        int attempts = 0;

        while (attempts < maxAttempts) {
            Login login = new Login(scanner);

            if (AuthenticationManager.authenticate(login.getUsername(), login.getPassword())) {
                // Proceed with the inventory management system after successful login
                System.out.println("Now you can access the inventory management system.");
                InventoryManager inventoryManager = new InventoryManager();
                ReportGenerator reportGenerator = new ReportGenerator();

                while (true) {
                    System.out.println("1-Display all books");
                    System.out.println("2-Add a book");
                    System.out.println("3-Update a book");
                    System.out.println("4-Delete a book");
                    System.out.println("5-Record a sale");
                    System.out.println("6-Record a purchase");
                    System.out.println("7-View Sales Transaction History");
                    System.out.println("8-View Purchase Transaction History");
                    System.out.println("9-View Reports");
                    System.out.println("10-Exit");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            inventoryManager.displayInventory();
                            break;
                        case 2:
                        	System.out.println("Enter book details:");
                        	System.out.print("Name: ");
                        	String name = scanner.nextLine();
                        	System.out.print("Author: ");
                        	String author = scanner.nextLine();
                        	System.out.print("Quantity: ");
                        	int quantity = Integer.parseInt(scanner.nextLine());
                        	System.out.print("Price: ");
                        	double price = Double.parseDouble(scanner.nextLine());
                        	System.out.print("Category: ");
                        	String category = scanner.nextLine();

                        	inventoryManager.addBook(name, author, price, category, quantity);
                        	System.out.println("Book added successfully!");


                            inventoryManager.addBook(name, author, price, category, quantity);
                            System.out.println("Book added successfully!");
                            break;

                        case 3:
                            System.out.println("Enter the UUID of the book to update:");
                            UUID updateBookId = UUID.fromString(scanner.next());
                            scanner.nextLine();
                            System.out.print("Enter new name (press Enter to skip): ");
                            String newName = scanner.nextLine().trim();
                            System.out.print("Enter new author (press Enter to skip): ");
                            String newAuthor = scanner.nextLine().trim();
                            System.out.print("Enter new quantity (press Enter to skip): ");
                            String newQuantityStr = scanner.nextLine().trim();
                            int newQuantity = newQuantityStr.isEmpty() ? -1 : Integer.parseInt(newQuantityStr);
                            System.out.print("Enter new price (press Enter to skip): ");
                            String newPriceStr = scanner.nextLine().trim();
                            double newPrice = newPriceStr.isEmpty() ? -1 : Double.parseDouble(newPriceStr);
                            System.out.print("Enter new category (press Enter to skip): ");
                            String newCategory = scanner.nextLine().trim();

                            inventoryManager.updateBook(updateBookId, newName, newAuthor, newQuantity, newPrice, newCategory);
                            System.out.println("Book updated successfully!");
                            break;
                        case 4:
                            System.out.println("Enter the UUID of the book to delete:");
                            UUID deleteBookId = UUID.fromString(scanner.next());
                            inventoryManager.removeBook(deleteBookId);
                            System.out.println("Book deleted successfully!");
                            break;
                        case 5:
                      
                      
                            System.out.print("Enter book name for sale: ");
                            String saleBookName = scanner.nextLine(); // Use nextLine() to handle spaces in the book name
                            System.out.print("Enter quantity for sale: ");
                            int saleQuantity = scanner.nextInt();
                            System.out.print("Enter price for sale: ");
                            double salePrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline character after nextDouble()

                            System.out.print("Enter category for sale: ");
                            String saleCategory = scanner.nextLine();

                            TransactionSale saleTransaction = new TransactionSale(saleBookName, saleQuantity, salePrice, saleCategory);
                            saleTransaction.recordTransaction();
                            inventoryManager.updateBookQuantity(saleBookName, saleQuantity, "Sale");
                            System.out.println("Sale recorded successfully!");
                            break;

                      
                        case 6:
                            System.out.print("Enter book name for purchase: ");
                            String purchaseBookName = scanner.nextLine(); // Use nextLine() to handle spaces in the book name
                            System.out.print("Enter quantity for purchase: ");
                            int purchaseQuantity = scanner.nextInt();
                            System.out.print("Enter price for purchase: ");
                            double purchasePrice = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline character after nextDouble()

                            System.out.print("Enter category for purchase: ");
                            String purchaseCategory = scanner.nextLine();

                            TransactionPurchase purchaseTransaction = new TransactionPurchase(purchaseBookName, purchasePrice, purchaseCategory, purchaseQuantity);
                            purchaseTransaction.recordTransaction();
                            inventoryManager.updateBookQuantity(purchaseBookName, purchaseQuantity, "Purchase");
                            System.out.println("Purchase recorded successfully!");
                            break;

                        case 7:
                            TransactionSale.displayTransactionHistory();
                            break;
                        case 8:
                            TransactionPurchase.displayTransactionHistory();
                            break;
                     
                    
                        case 9:
                            System.out.println("Viewing and generating Reports...");
                            reportGenerator.loadTransactionHistory(TransactionSale.FILE_NAME, TransactionPurchase.FILE_NAME);
                            reportGenerator.generateAndPrintReports(); // Generate and print reports to the console
                            System.out.println("Reports generated and printed successfully!");
                            break;


                           
                        case 10:
                            System.exit(0);


                        	System.out.println("Transaction deleted successfully!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                    }
                }
            } else {
                System.out.println("Login failed. Please try again.");
                attempts++;
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("Maximum login attempts reached. Exiting program.");
        }

        scanner.close();
    }
}
