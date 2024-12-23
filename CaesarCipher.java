public class CaesarCipher {
    public static String encrypt(String plainText, int shift) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            cipherText.append(ch);
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, int shift) {
        return encrypt(cipherText, 26 - shift);
    }

    public static void main(String[] args) {
        String plainText = "Hello World";
        int shift = 3;

        String cipherText = encrypt(plainText, shift);
        System.out.println("Encrypted Text: " + cipherText);

       
        String decryptedText = decrypt(cipherText, shift);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
