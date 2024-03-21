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
?////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;

public class Cipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Input plain text
        System.out.print("Enter plain text: ");
        String plainText = scanner.nextLine();

        // Step 2: Input key
        System.out.print("Enter key: ");
        int key = scanner.nextInt();

        // Step 3: Choose cipher technique
        System.out.println("Choose cipher technique:");
        System.out.println("1. Simple Caesar cipher");
        System.out.println("2. Substitution cipher");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String cipherName;
        String encryptedText;

        // Encrypt based on choice
        if (choice == 1) {
            cipherName = "Simple Caesar cipher";
            encryptedText = caesarCipherEncrypt(plainText, key);
        } else if (choice == 2) {
            cipherName = "Substitution cipher";
            encryptedText = substitutionCipherEncrypt(plainText, key);
        } else {
            System.out.println("Invalid choice");
            return;
        }

        System.out.println("Encrypted text: " + encryptedText);

        // Step 4: Decrypting using cryptanalysis
        System.out.println("Decrypting " + cipherName + " using cryptanalysis:");

        String foundDecryptedText = null;
        int foundKey = -1;

        if (choice == 1) {
            System.out.println("For Simple Caesar cipher:");
            for (int possibleKey = 0; possibleKey < 26; possibleKey++) {
                String possiblePlainText = caesarCipherDecrypt(encryptedText, possibleKey);
                System.out.println("Key: " + possibleKey + ", Decrypted Text: " + possiblePlainText);
                if (possiblePlainText.equals(plainText)) {
                    foundDecryptedText = possiblePlainText;
                    foundKey = possibleKey;
                }
            }
        } else if (choice == 2) {
            System.out.println("For Substitution cipher: (Brute force)");
            System.out.println("All possible keys and decrypted texts:");

            for (int possibleKey = 0; possibleKey < 26; possibleKey++) {
                String possiblePlainText = substitutionCipherDecrypt(encryptedText, possibleKey);
                System.out.println("Key: " + possibleKey + ", Decrypted Text: " + possiblePlainText);
                if (possiblePlainText.equals(plainText)) {
                    foundDecryptedText = possiblePlainText;
                    foundKey = possibleKey;
                }
            }
        }

        // Print the key found in cryptanalysis along with the decrypted text
        if (foundKey != -1 && foundDecryptedText != null) {
            System.out.println("In cryptanalysis, the Key found = " + foundKey + " and Decrypted Text: " + foundDecryptedText);
        } else {
            System.out.println("Key not found in cryptanalysis.");
        }

        // Step 5: Cryptanalysis process completed
        System.out.println("Cryptanalysis process completed");
    }

    // Caesar cipher encryption
    public static String caesarCipherEncrypt(String plainText, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (((c - 'a' + key) % 26) + 'a');
                encryptedText.append(shiftedChar);
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    // Caesar cipher decryption
    public static String caesarCipherDecrypt(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (((c - 'a' - key + 26) % 26) + 'a');
                decryptedText.append(shiftedChar);
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    // Substitution cipher encryption
    public static String substitutionCipherEncrypt(String plainText, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (((c - 'a' + key) % 26) + 'a');
                encryptedText.append(shiftedChar);
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    // Substitution cipher decryption
    public static String substitutionCipherDecrypt(String encryptedText, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (((c - 'a' - key + 26) % 26) + 'a');
                decryptedText.append(shiftedChar);
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }
}

