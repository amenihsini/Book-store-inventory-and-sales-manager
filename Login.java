package project;

import java.util.Scanner;

public class Login {
    private Scanner sc;
    private String username;
    private String password;

    public Login(Scanner sc) {
        this.sc = sc;
        insertData();
    }

    private void insertData() {
        System.out.println("Enter Username:");
        username = sc.nextLine();

        System.out.println("Enter Password:");
        password = sc.nextLine();
        validation();
    }

    private void validation() {
        if (AuthenticationManager.authenticate(username, password)) {
            System.out.println("Successfully logged in. Welcome, " + username + "!");
            // You can add additional logic or proceed to the inventory management system here
        } else {
            System.out.println("Invalid username or password. Please try again.");
            // Add more detailed error messages or lockout logic for security.
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
