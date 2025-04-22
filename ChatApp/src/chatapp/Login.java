package chatapp;

import java.util.Scanner;

public class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredPhoneNumber;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[!@#\\$%\\^&\\(\\)\\-+=<>?/{}~|].*");
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password, String phoneNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return getUsernameErrorMessage(username);
        }
        if (!checkPasswordComplexity(password)) {
            return getPasswordErrorMessage(password);
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return getCellNumberErrorMessage(phoneNumber);
        }

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredPhoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User registered successfully.";
    }

    // Modified to handle 4 login attempts internally
    public String returnLoginStatus() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        while (attempts < 4) {
            System.out.print("Login - Enter your username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Login - Enter your password: ");
            String inputPassword = scanner.nextLine();

            if (inputUsername.equals(registeredUsername) && inputPassword.equals(registeredPassword)) {
                return getWelcomeMessage();
            } else {
                System.out.println("Incorrect username or password. Try again.");
                attempts++;
            }
        }

        return "Too many failed login attempts. Access denied.";
    }

    public String getWelcomeMessage() {
        return "Welcome " + firstName + " " + lastName + ", it is great to see you.";
    }

    public String getUsernameErrorMessage(String username) {
        return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    public String getPasswordErrorMessage(String password) {
        return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String getCellNumberErrorMessage(String number) {
        return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
    }
}


