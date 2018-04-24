import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;


public class RSA
{
    private BigInteger n, d, e;

    private int bitlen = 1024;


    public RSA(int bits)
    {
        bitlen = bits;
        SecureRandom secureRandom = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, secureRandom);
        BigInteger q = new BigInteger(bitlen / 2, 100, secureRandom);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1)
        {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }

    public synchronized String encrypt(String message)
    {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }

    public synchronized BigInteger encrypt(BigInteger message)
    {
        return message.modPow(e, n);
    }

    public synchronized String decrypt(String message)
    {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

    public synchronized BigInteger decrypt(BigInteger message)
    {
        return message.modPow(d, n);
    }

    /**
     * Generate a new public and private key set.
     */
    public synchronized void generateKeys()
    {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1)
        {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }


    public static void main(String[] args)
    {
        RSA rsa = new RSA(1024);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Message:");
        String text1 = scanner.nextLine();

        System.out.println("Plaintext: " + text1);
        BigInteger plaintext = new BigInteger(text1.getBytes());

        BigInteger cipherText = rsa.encrypt(plaintext);
        System.out.println("CipherText: " + cipherText);
        plaintext = rsa.decrypt(cipherText);

        String text2 = new String(plaintext.toByteArray());
        System.out.println("Plaintext: " + text2);
    }
}