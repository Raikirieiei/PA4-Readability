package readability;

/**
 * This is a class that implements CountStrategy interface to count words.
 * @author Thornthep Chomchuen
 */
public class CountWord implements CountStrategy{

    @Override
    public int count(String line) {
        boolean isWord = true;
        boolean isConsonant = false;
        boolean isVowel = false;
        int count = 0;
        for(String word : line.split("\\s+")) {
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
        return count;
    }
    
    /**
     * method to check alphabet (Vowel,Consonant).
     * @param alphabet alphabet to check vowel.
     * @return True if alphabet is vowel and False if alphabet is consonant.
     */
    private boolean IsVowel(String alphabet) {
        return (alphabet.equalsIgnoreCase("a") || alphabet.equalsIgnoreCase("e") || alphabet.equalsIgnoreCase("i")
        || alphabet.equalsIgnoreCase("o") || alphabet.equalsIgnoreCase("u"));
    }

}