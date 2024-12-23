public class SubstitutionCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String plainText, String key) {
        plainText = plainText.toUpperCase();
        StringBuilder cipherText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = ALPHABET.indexOf(ch);
                cipherText.append(key.charAt(index));
            } else {
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        cipherText = cipherText.toUpperCase();
        StringBuilder plainText = new StringBuilder();
        for (char ch : cipherText.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = key.indexOf(ch);
                plainText.append(ALPHABET.charAt(index));
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        String plainText = "HELLO WORLD";
        String key = "DEFGHIJKLMNOPQRSTUVWXYZABC"; // Example key

        // Encrypt the plain text
        String cipherText = encrypt(plainText, key);
        System.out.println("Encrypted Text: " + cipherText);

        // Decrypt the cipher text
        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
