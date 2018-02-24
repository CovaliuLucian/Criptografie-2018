package com.company;

public class Encryptor {

    private String key;
    private String plainText;

    public Encryptor(String key, String plainText) {
        this.key = key;
        this.plainText = plainText;
    }

    public String Encrypt() {
        int pos = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            if(Character.isLetter(plainText.charAt(i)))
            {
                output.append((char)((((plainText.charAt(i)+key.charAt(pos++)-'a') - 'a' ) % 26 ) + 'a'));
                if(pos == key.length())
                    pos = 0;
            }
            else
                output.append(plainText.charAt(i));
        }

        System.out.println("Encrypted text:" + output.toString());

        return output.toString();
    }
}
