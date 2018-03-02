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
            double f1 = (double) Frequency(text, (char) (i + 'A')) / text.length();
            double f2 = (double) (Frequency(text, (char) (i + 'A')) - 1) / (text.length() - 1);
            output += f1 * f2;
        }

        return output;
    }

    public static String GenerateSubstring(String text, int pas, int start) {
        StringBuilder output = new StringBuilder();
        for (int i = start; i < text.length(); i += pas) {
            output.append(text.charAt(i));
        }
        //System.out.println(output);
        return output.toString();
    }

    public static String EliminatePunctuation(String text) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i)))
                output.append(text.charAt(i));
        }
        return output.toString();
    }

    public static double MIC(String firstText, String secondText) {
        if (firstText.length() < 2 || secondText.length() < 2)
            return 0;
        double output = 0;
        for (int i = 0; i < 25; i++) {
            double f1 = (double) Frequency(firstText, (char) (i + 'A')) / firstText.length();
            double f2 = (double) Frequency(secondText, (char) (i + 'A')) / secondText.length();
            output += f1 * f2;
        }

        return output;
    }
}
