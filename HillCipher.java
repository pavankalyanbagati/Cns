import java.util.Scanner;

public class HillCipher {
    private static final int MATRIX_SIZE = 3;

    
    public static String encrypt(String plainText, int[][] keyMatrix) {
        int[] plainVector = new int[MATRIX_SIZE];
        int[] cipherVector = new int[MATRIX_SIZE];
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i += MATRIX_SIZE) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                plainVector[j] = plainText.charAt(i + j) - 'A';
            }

            for (int j = 0; j < MATRIX_SIZE; j++) {
                cipherVector[j] = 0;
                for (int k = 0; k < MATRIX_SIZE; k++) {
                    cipherVector[j] += keyMatrix[j][k] * plainVector[k];
                }
                cipherVector[j] %= 26;
            }

            for (int j = 0; j < MATRIX_SIZE; j++) {
                cipherText.append((char) (cipherVector[j] + 'A'));
            }
        }

        return cipherText.toString();
    }

    // Method to find the modular inverse of a number
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // Method to find the determinant of a matrix
    public static int determinant(int[][] matrix, int n) {
        int det = 0;
        if (n == 1) {
            return matrix[0][0];
        }
        int[][] temp = new int[MATRIX_SIZE][MATRIX_SIZE];
        int sign = 1;
        for (int f = 0; f < n; f++) {
            getCofactor(matrix, temp, 0, f, n);
            det += sign * matrix[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    // Method to get the cofactor of a matrix
    public static void getCofactor(int[][] matrix, int[][] temp, int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    // Method to find the adjoint of a matrix
    public static void adjoint(int[][] matrix, int[][] adj) {
        if (MATRIX_SIZE == 1) {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        int[][] temp = new int[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                getCofactor(matrix, temp, i, j, MATRIX_SIZE);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = (sign * determinant(temp, MATRIX_SIZE - 1)) % 26;
                if (adj[j][i] < 0) {
                    adj[j][i] += 26;
                }
            }
        }
    }

    // Method to find the inverse of a matrix
    public static boolean inverse(int[][] matrix, int[][] inverse) {
        int det = determinant(matrix, MATRIX_SIZE);
        int invDet = modInverse(det, 26);
        if (det == 0) {
            System.out.println("Inverse doesn't exist");
            return false;
        }
        int[][] adj = new int[MATRIX_SIZE][MATRIX_SIZE];
        adjoint(matrix, adj);
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                inverse[i][j] = (adj[i][j] * invDet) % 26;
                if (inverse[i][j] < 0) {
                    inverse[i][j] += 26;
                }
            }
        }
        return true;
    }

    // Method to decrypt the cipher text using Hill cipher
    public static String decrypt(String cipherText, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        if (!inverse(keyMatrix, inverseKeyMatrix)) {
            return null;
        }
        return encrypt(cipherText, inverseKeyMatrix);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the plain text (length should be a multiple of 3): ");
        String plainText = scanner.nextLine().toUpperCase().replaceAll("\\s", "");

        int[][] keyMatrix = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
        };

        String cipherText = encrypt(plainText, keyMatrix);
        System.out.println("Encrypted Text: " + cipherText);

        String decryptedText = decrypt(cipherText, keyMatrix);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
