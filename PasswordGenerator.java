import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PasswordGenerator {
    public static void main(String[] args) {
        int min = 12, max = 20;
        int randomInt;
        String filePath = "passwords.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            for (int i = 0; i < 50000; i++) {
                randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
                char[] content = generatePassword(randomInt);
                String password = new String(content);

                writer.println(password);
            }
            System.out.println("Content has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }

        return password;
    }
}
