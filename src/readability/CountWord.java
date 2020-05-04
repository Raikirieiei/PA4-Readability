package readability;

/**
 * This is a class that implements CountStrategy interface to count words.
 * @author Thornthep Chomchuen
 */
public class CountWord implements CountStrategy{

    @Override
    public int count() {
        boolean isWord = true;
        boolean isConsonant = false;
        boolean isVowel = false;
        int count = 0;
        try {
            for(String word : ReadAndCount.getLine().split("\\s+")) {
                for(char alpha : word.toCharArray()) {
                    if (IsVowel(String.valueOf(alpha)))
                        isVowel = true;
                    else    isConsonant = true;
                }
                if (isConsonant && isVowel) isWord = true;
                if (!word.isBlank() && !word.matches("[\\p{Punct}&&[^@',&]]") && isWord)    count++;
                if (word.equalsIgnoreCase("I")) count++;
                isConsonant = false;
                isVowel = false;
                isWord = false;
            }
            
        } catch (Exception e) { }
        return count;
    }
    
    /**
     * method to check alphabet (Vowel,Consonant).
     * @param alphabet alphabet to check vowel.
     * @return True if alphabet is vowel and False if alphabet is consonant.
     */
    private static boolean IsVowel(String alphabet) {
        if (alphabet.equalsIgnoreCase("a") || alphabet.equalsIgnoreCase("e") || alphabet.equalsIgnoreCase("i")
                || alphabet.equalsIgnoreCase("o") || alphabet.equalsIgnoreCase("u")) return true;
        else return false;
    }
}