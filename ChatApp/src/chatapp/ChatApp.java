package chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        String username = "";
        String password = "";
        String phoneNumber = "";
        String firstName = "";
        String lastName = "";

        // Username input with 4 chances
        int attempts = 0;
        while (attempts < 4) {
            System.out.print("Enter a username: ");
            username = scanner.nextLine();

            if (login.checkUserName(username)) {
                break;
            } else {
                System.out.println(login.getUsernameErrorMessage(username));
                attempts++;
            }
        }
        if (attempts == 4) {
            System.out.println("Too many incorrect attempts. Exiting.");
            scanner.close();
            return;
        }

        // Password input with 4 chances
        attempts = 0;
        while (attempts < 4) {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();

            if (login.checkPasswordComplexity(password)) {
                break;
            } else {
                System.out.println(login.getPasswordErrorMessage(password));
                attempts++;
            }
        }
        if (attempts == 4) {
            System.out.println("Too many incorrect attempts. Exiting.");
            scanner.close();
            return;
        }

        // Phone number input with 4 chances
        attempts = 0;
        while (attempts < 4) {
            System.out.print("Enter your South African cell phone number (with +27): ");
            phoneNumber = scanner.nextLine();

            if (login.checkCellPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println(login.getCellNumberErrorMessage(phoneNumber));
                attempts++;
            }
        }
        if (attempts == 4) {
            System.out.println("Too many incorrect attempts. Exiting.");
            scanner.close();
            return;
        }

        // Get first and last name (no validation needed)
        System.out.print("Enter your first name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        lastName = scanner.nextLine();

        // Register user
        String registrationMessage = login.registerUser(username, password, phoneNumber, firstName, lastName);
        System.out.println(registrationMessage);

        if (registrationMessage.equals("User registered successfully.")) {
            // Proceed to login with 4 chances (handled inside the Login class)
            System.out.println("\n--- LOGIN ---");
            System.out.println(login.returnLoginStatus());  // now handled inside Login
        }

        scanner.close();
    }
}


