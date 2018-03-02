package Tema1;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        V26.Setup();
        String encrypted = V26.Encrypt();
        String decrypted = V26.Decrypt(encrypted, V26.getKey());

        out.println(V26.Crack(encrypted));

    }
}
