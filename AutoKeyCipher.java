public class AutoKeyCipher {
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static String generateKey(String str, String key) {
        int strLen = str.length();
        StringBuilder newKey = new StringBuilder(key);
        for (int i = 0; i < strLen - key.length(); i++) {
            newKey.append(str.charAt(i));
        }
        return newKey.toString();
    }

    public static String encrypt(String str, String key) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char x = (char) (((str.charAt(i) + key.charAt(i)) % 26) + 'A');
            cipherText.append(x);
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char x = (char) (((cipherText.charAt(i) - key.charAt(i) + 26) % 26) + 'A');
            plainText.append(x);
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        String str = "HELLO";
        String key = "KEY";

        str = toUpperCase(str);
        key = toUpperCase(key);

        String newKey = generateKey(str, key);
        String cipherText = encrypt(str, newKey);
        String decryptedText = decrypt(cipherText, newKey);

        System.out.println("Plaintext: " + str);
        System.out.println("Key: " + key);
        System.out.println("Generated Key: " + newKey);
        System.out.println("Ciphertext: " + cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
