package Tema1;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        V26.Setup();
        String encrypted = V26.Encrypt();
        String decrypted = V26.Decrypt(encrypted, V26.getKey());

        out.print("Decrypted text:" + decrypted);

        String key = V26.Crack(encrypted);

        out.println("\nKey: " + key + "\nInitial text: " + decrypted + "\nCracked text: " + V26.Decrypt(encrypted, key));



    }
}
