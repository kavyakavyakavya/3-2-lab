import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class AESEncryptionExample {
    public static void main(String[] args) {
        try {
            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

            // Prompt the user to enter the string to be encrypted
            System.out.print("Enter the string to be encrypted: ");
            String inputString = scanner.nextLine();

            // Generate a secret key for AES encryption
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // You can change the key size if needed
            SecretKey secretKey = keyGen.generateKey();

            // Encrypt the input string using AES
            byte[] encryptedBytes = encrypt(inputString, secretKey);

            // Print the encrypted message
            System.out.println("Encrypted message: " + bytesToHex(encryptedBytes));

            // Decrypt the encrypted message
            String decryptedString = decrypt(encryptedBytes, secretKey);

            // Print the decrypted message
            System.out.println("Decrypted message: " + decryptedString);

            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to encrypt a string using AES encryption
    public static byte[] encrypt(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input.getBytes());
    }

    // Method to decrypt an encrypted byte array using AES decryption
    public static String decrypt(byte[] encryptedBytes, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    // Method to convert a byte array to a hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
