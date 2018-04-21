import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        test();
    }

    public static void test()
    {
        DES des = new DES();

        System.out.println("Message:");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();


        System.out.println("Key1:");
        String key1 = scanner.nextLine();

        System.out.println("Key2:");
        String key2 = scanner.nextLine();

        String encrypted = des.encrypt(key2, des.encrypt(key1, DES.utfToBin(message)));

        System.out.println("Encrypted text: " + DES.binToUTF(encrypted));

        String decrypted = des.decrypt(key1, des.decrypt(key2, (encrypted)));

        System.out.println("Decrypted text: " + DES.binToUTF(decrypted));

    }
}
