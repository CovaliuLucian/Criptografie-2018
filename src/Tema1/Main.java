package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Setup s = new Setup();
        Encryptor encrypter = new Encryptor(s.getKey(),s.getPlainText());
        encrypter.Encrypt();
    }
}
