package Tema1;

import util.MapUtil;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class V26 {
    private static String key;
    private static String plainText;
    private static String dummyText = Utilities.EliminatePunctuation("A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath of that universal love which bears and sustains us, as it floats around us in an eternity of bliss; and then, my friend, when darkness overspreads my eyes, and heaven and earth seem to dwell in my soul and absorb its power, like the form of a beloved mistress, then I often think with longing, Oh, would I could describe these conceptions, could impress upon paper all that is living so full and warm within me, that it might be the mirror of my soul, as my soul is the mirror of the infinite God! O my friend -- but it is too much for my strength -- I sink under the weight of the splendour of these visions! A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath of that universal love which bears and sustains us, as it floats around us in an eternity of bliss; and then, my friend, when darkness overspreads my eyes, and heaven and earth seem to dwell in my soul and absorb its power, like the form of a beloved mistress, then I often think with longing, Oh, would I could describe these conceptions, could impress upon paper all that is living so full and warm within me, that it might be the mirror of my soul, as my soul is the mirror of the infinite God! O my friend -- but it is too much for my strength -- I sink under the weight of the splendour of these visions! A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls like mine. I am so happy, my dear friend, so absorbed in the exquisite sense of mere tranquil existence, that I neglect my talents. I should be incapable of drawing a single stroke at the present moment; and yet I feel that I never was a greater artist than now. When, while the lovely valley teems with vapour around me, and the meridian sun strikes the upper surface of the impenetrable foliage of my trees, and but a few stray gleams steal into the inner sanctuary, I throw myself down among the tall grass by the trickling stream; and, as I lie close to the earth, a thousand unknown plants are noticed by me: when I hear the buzz of the little world among the stalks, and grow familiar with the countless indescribable forms of the insects and flies, then I feel the presence of the Almighty, who formed us in his own image, and the breath of that universal love which bears and sustains us, as it floats around us in an eternity of bliss; and then, my friend, when darkness overspreads my eyes, and heaven and earth seem to dwell in my soul and absorb its power, like the form of a beloved mistress, then I often think with longing, Oh, would I could describe these conceptions, could impress upon paper all that is living so full and warm within me, that it might be the mirror of my soul, as my soul is the mirror of the infinite God! O my friend -- but it is too much for my strength -- I sink under the weight of the splendour of these visions!A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart. I am alone, and feel the charm of existence in this spot, which was created for the bliss of souls");

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
        dummyText = Utilities.EliminatePunctuation(plainText);

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

        //System.out.println("Decrypted text:" + output.toString());

        return output.toString();
    }

    public static String Crack(String cryptoText) {
        String pureText = Utilities.EliminatePunctuation(cryptoText);
        //List<Integer> lengths = FindLength(pureText);
        //lengths.sort(Comparator.naturalOrder());

        int len = FindLengthHard(pureText);
        out.println("\n" + len);

        //return lengths.get(0).toString(); // temporary
        return FindKey(pureText, len);


    }

    private static List<Integer> FindLength(String cryptoText) {
        int length;
        HashMap<Integer, Double> theBest = new HashMap<>();
        for (length = 1; length < cryptoText.length(); length++) {
            double sum = 0;
            for (int i = 0; i < length; i++) {
                sum += Utilities.IC(Utilities.GenerateSubstring(cryptoText, length, i));
            }
            double rez = sum / length;
            theBest.put(length, Math.abs(rez - 0.065));
            //out.println(length + " : " + rez);
        }

        Map<Integer, Double> sorted = MapUtil.sortByValue(theBest);
        return sorted.keySet().stream().limit(20).collect(Collectors.toList());

    }

    private static int FindLengthHard(String cryptoText) {
        int length;
        boolean isGood;
        for (length = 1; length < cryptoText.length(); length++) {
            isGood = true;
            //out.print(length + ": ");
            for (int i = 0; i < length; i++) {
                double rez = Utilities.IC(Utilities.GenerateSubstring(cryptoText, length, i));
                //out.print(rez + " ");
                if (rez < 0.055 || rez > 0.07)
                    isGood = false;
            }
            //out.println();
            if (isGood)
                return length;
        }
        return 0;
    }


    private static String FindKey(String cryptoText, int length) {
        StringBuilder key = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            int s = -1;
            double MIC;
            do {
                s++;
                MIC = Utilities.MIC(dummyText, Decrypt(Utilities.GenerateSubstring(cryptoText, length, i), Character.toString((char) (s + 'A'))));
                if (s > 25)
                    return "!!!Error";
                //out.println(s + " : " + MIC);
            }
            while (MIC < 0.052 || MIC > 0.07);
            out.println((char) (s + 'A'));
            //key.append((char) ((26 - s) % 26 + 'A'));
            key.append((char) (s + 'A'));
        }

        return key.toString();
    }
}
