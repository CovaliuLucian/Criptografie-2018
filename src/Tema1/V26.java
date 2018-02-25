package Tema1;

import java.security.InvalidParameterException;
import java.util.Scanner;

import static java.lang.System.out;

public class V26 {
    private static String key;
    private static String plainText;

    public static void Setup() {
        if (!Read()) {
            throw new InvalidParameterException();
        }
    }

    public static String getKey() {
        return key;
    }

    private static boolean Read() {
        out.println("key: ");
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine().toUpperCase();
        if (!key.matches("[A-Z]+"))
            return false;

        out.println("plaintext: ");
        plainText = scanner.nextLine().toUpperCase();

        return true;
    }

    public static String Encrypt() {
        int pos = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            if (Character.isLetter(plainText.charAt(i))) {
                output.append((char) ((((plainText.charAt(i) + key.charAt(pos++) - 'A') - 'A') % 26) + 'A'));
                if (pos == key.length())
                    pos = 0;
            } else
                output.append(plainText.charAt(i));
        }

        System.out.println("Encrypted text:" + output.toString());

        return output.toString();
    }

    public static String Decrypt(String cryptoText, String key) {
        int pos = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cryptoText.length(); i++) {
            if (Character.isLetter(cryptoText.charAt(i))) {
                int a = cryptoText.charAt(i) - key.charAt(pos++);
                if (a < 0)
                    a += 26;
                output.append((char) (a + 'A'));
                if (pos == key.length())
                    pos = 0;
            } else
                output.append(cryptoText.charAt(i));
        }

        System.out.println("Decrypted text:" + output.toString());

        return output.toString();
    }

    public static String Crack(String cryptoText) {
        String pureText = Utilities.EliminatePunctuacion(cryptoText);
        FindLength(pureText);
        return null;
    }

    private static int FindLength(String cryptoText) {
        int length, bestLength = -1;
        double best = 1;
        for (length = 1; length < 20; length++) {
            double sum = 0;
            for (int i = 0; i < length; i++) {
                sum += Utilities.IC(Utilities.GenerateSubstring(cryptoText, length, i));
            }
            double rez = sum / length;
            if (Math.abs(0.0667 - rez) < best) {
                best = Math.abs(0.0667 - rez);
                bestLength = length;
            }
            out.println(length + " : " + rez);
        }

        out.print(bestLength);
        return bestLength;
    }
}
