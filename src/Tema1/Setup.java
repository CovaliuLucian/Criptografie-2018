package com.company;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyException;
import java.util.Scanner;

import static java.lang.System.out;

public class Setup {
    private String key;
    private String plainText;

    public Setup()
    {

        if(!Read())
        {
            throw new InvalidParameterException();
        }

    }

    private boolean Read()
    {
        out.println("key: ");
        Scanner scanner = new Scanner(System.in);
        key = scanner.nextLine();
        if(!key.matches("[a-z]+"))
            return false;

        out.println("plaintext: ");
        plainText = scanner.nextLine();
        //if(!plainText.matches("[a-z]+"))
        //    return false;

        return true;
    }

    public String getKey() {
        return key;
    }

    public String getPlainText() {
        return plainText;
    }
}
