import java.io.*;
import java.util.Scanner;

public class PasswordStrengthMeter {
    private static final String PASSWORDS_FILE = "passwords.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        helper(password);  
        scanner.close();
    }
    public static void helper(String password)
    {
        Scanner scanner = new Scanner(System.in);

        if (isStrongPassword(password)) {
            System.out.println("Password is strong!");

            if (!isPasswordInFile(password)) {
                savePasswordToFile(password);
                System.out.println("Password saved to file.");
            } else {
                System.out.println("Password already exists in the file.");
                String pass = scanner.nextLine();
                helper(pass);  
            }
        } else {
            System.out.println("Password is not strong. Enter a new password");
            String pass = scanner.nextLine();
            helper(pass);  
            
        }
    }
    public static boolean isStrongPassword(String password) {
        // Check password strength requirements
        if (password.length() < 12) {
            return false;
        }

        // Check if password starts with a character
        if (!Character.isLetter(password.charAt(0))) {
            return false;
        }

        // Check if password contains at least one special character
        if (!password.matches(".*[!@#$%^&*()].*")) {
            return false;
        }

        // Check if password contains at least one capital letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if password contains at least one number
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        

        return true;
    }

    public static boolean isPasswordInFile(String password) {
        try (Scanner fileScanner = new Scanner(new File(PASSWORDS_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            return false; // The file doesn't exist yet, so the password is not in the file
        }

        return false;
    }

    public static void savePasswordToFile(String password) {
        try (FileWriter writer = new FileWriter(PASSWORDS_FILE, true)) {
            writer.write(password + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("An error occurred while saving the password to the file.");
            e.printStackTrace();
        }
    }
}












