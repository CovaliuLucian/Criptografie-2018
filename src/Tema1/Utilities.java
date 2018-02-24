package Tema1;

public class Utilities {
    private static int Frequency(String text, char ch) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ch)
                count++;
        }
        return count;
    }

    public static double IC(String text) {
        if (text.length() < 2)
            return 0;
        double output = 0;
        for (int i = 0; i < 25; i++) {
            double f1 = Frequency(text, (char) (i + 'A')) / text.length();
            double f2 = (Frequency(text, (char) (i + 'A')) - 1) / (text.length() - 1);
            output += f1 * f2;
        }

        return output;
    }
}
