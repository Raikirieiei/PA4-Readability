package readability;

import java.io.*;
import java.net.URL;

public class ReadAndCount {

    private static URL url;
    private static int countWord;
    private static int countSyl;
    private static int countSent;
    private static String line;

    public static void Read(String source) {
        if (source.contains("/")) {
            try {
                url = new URL(source);
            } catch (Exception e) {
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                while ((line = br.readLine()) != null) {
                    CountWordMethod();
                    CountSylMethod();
                    CountSentenceMethod();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File(source);
            if (!file.exists() || !file.isFile())
                error(source + " does not exist or is not a regular file.");
            if (!file.canRead())
                error(source + " is not readable");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    CountWordMethod();
                    CountSylMethod();
                    CountSentenceMethod();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void CountWordMethod() {
        boolean isWord = true;
        boolean isConsonant = false;
        boolean isVowel = false;
        try {
            for (String word : line.split("\\s+")) {
                for (char alpha : word.toCharArray()) {
                    if (IsVowel(String.valueOf(alpha)))
                        isVowel = true;
                    else
                        isConsonant = true;
                }
                if (isConsonant && isVowel)
                    isWord = true;
                if (!word.isBlank() && !word.matches("[\\p{Punct}&&[^@',&]]") && isWord)
                    countWord++;
                if (word.equalsIgnoreCase("I"))
                    countWord++;
                isConsonant = false;
                isVowel = false;
                isWord = false;
            }
        } catch (Exception e) {
        }
    }

    public static void CountSylMethod() {
        for (String word : line.split("\\s+")) {
            char[] alphabet1 = word.toCharArray();
            if (!word.isBlank()) {
                if (IsVowel(String.valueOf(alphabet1[0])))
                    countSyl++;
            }
            if ((word.endsWith("e") || word.endsWith("E")) && OnlyE(word)) {
                countSyl++;
                continue;
            }
            boolean firstVowel = false;
            boolean consonant = false;
            char[] alphabet = word.toCharArray();
            for (int i = 0; i < alphabet.length; i++) {
                if (IsVowel(String.valueOf(alphabet[i])))
                    firstVowel = true;
                else
                    consonant = true;
                if (!firstVowel && String.valueOf(alphabet[i]).equalsIgnoreCase("y"))
                    firstVowel = true;
                try {
                    if (firstVowel && consonant && !IsVowel(String.valueOf(alphabet[i + 1]))
                            && !String.valueOf(alphabet[i + 1]).equalsIgnoreCase("y")) {
                        countSyl++;
                        firstVowel = false;
                        consonant = false;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    public static void CountSentenceMethod() {
        for (String word : line.split("\\s+")) {
            try {
                char end = word.toCharArray()[word.length() - 1];
                if (String.valueOf(end).matches("[./,?]")) {
                    countSent++;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    private static boolean IsVowel(String alphabet) {
        if (alphabet.equalsIgnoreCase("a") || alphabet.equalsIgnoreCase("e") || alphabet.equalsIgnoreCase("i")
                || alphabet.equalsIgnoreCase("o") || alphabet.equalsIgnoreCase("u")) {
            return true;
        } else
            return false;
    }

    private static boolean OnlyE(String str) {
        String lowercaseStr = str.toLowerCase();
        if (lowercaseStr.contains("e")) {
            if (lowercaseStr.contains("a") || lowercaseStr.contains("i") || lowercaseStr.contains("o")
                    || lowercaseStr.contains("u"))
                return false;
            else
                return true;
        }
        return false;
    }

    public static int getWord() {
        return ReadAndCount.countWord;
    }

    public static int getSyl() {
        return ReadAndCount.countSyl;
    }

    public static int getSent() {
        return ReadAndCount.countSent;
    }

    public static void ClearCount() {
        ReadAndCount.countSent = 0;
        ReadAndCount.countWord = 0;
        ReadAndCount.countSyl = 0;
    }

    /**
     * Print an error message and exit with exit code 1.
     * 
     * @param message the error text to print
     */
    private static void error(String message) {
        System.out.println(message);
        System.exit(1);
    }
}
