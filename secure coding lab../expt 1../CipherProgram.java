aim:
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
write a program in java to perform  Simple Caesar cipher & Substitution cipher techniques 
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.Scanner;

public class CipherProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().toUpperCase();

        System.out.println("Enter the cipher key (a number for Caesar cipher or a substitution key):");
        String cipherKey = scanner.nextLine();

        // Check if the key is a number for Caesar Cipher or a substitution key
        if (cipherKey.matches("\\d+")) {
            int key = Integer.parseInt(cipherKey);
            
            // Perform Caesar Cipher encryption
            String caesarCipherText = caesarCipher(plaintext, key);
            System.out.println("Plain Text Encrypted: " + caesarCipherText);

            // Print the decryption process indicator
            System.out.println("Decrypting Caesar Cipher using cryptanalysis process...");

            // Display all possible decryption attempts for Caesar Cipher
            for (int i = 1; i <= 25; i++) {
                String possibleDecryption = caesarCipher(caesarCipherText, -i);
                System.out.println("Key " + i + ": " + possibleDecryption);
            }

            // Decrypt Caesar Cipher using the same key
            String decryptedCaesarText = caesarCipher(caesarCipherText, -key);
            System.out.println("Cryptanalysis Process: Key found = " + key);
            System.out.println("Decrypted Caesar Text: " + decryptedCaesarText);
            System.out.println("Cryptanalysis Process completed.");
        } else {
            // Perform Substitution Cipher
            String substitutionCipherText = substitutionCipher(plaintext, cipherKey);
            System.out.println("Substitution Cipher Text: " + substitutionCipherText);

            // Decrypt Substitution Cipher
            String decryptedSubstitutionText = substitutionCipher(substitutionCipherText, invertKey(cipherKey));
            System.out.println("Decrypted Substitution Text: " + decryptedSubstitutionText);
        }

        scanner.close();
    }

    // Caesar Cipher
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                result.append((char) ((ch - base + shift + 26) % 26 + base));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Substitution Cipher
    public static String substitutionCipher(String text, String key) {
        key = key.toUpperCase();
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int index = ch - base;
                result.append(key.charAt(index % 26));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Inverts the Substitution Cipher Key
    public static String invertKey(String key) {
        StringBuilder invertedKey = new StringBuilder();
        key = key.toUpperCase();

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            int index = key.indexOf(ch);
            if (index != -1) {
                invertedKey.append((char) ('A' + index));
            } else {
                invertedKey.append(ch);
            }
        }
        return invertedKey.toString();
    }
}
