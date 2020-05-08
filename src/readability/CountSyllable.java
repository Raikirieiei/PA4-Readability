package readability;

/**
 * This is a class that implements CountStrategy interface to count syllable.
 * @author Thornthep Chomchuen
 */
public class CountSyllable implements CountStrategy {

    @Override
    public int count(String line) {
        int count = 0; 
        for(String word : line.split("\\s+")) {
            char[] alphabet1 = word.toCharArray();
            if (!word.isBlank()) {
                if (IsVowel(String.valueOf(alphabet1[0])))  count++;
            }
            if ((word.endsWith("e") || word.endsWith("E")) && OnlyE(word)) {
                count++;
                continue;
            }
            boolean firstVowel = false;
            boolean consonant = false;
            char[] alphabet = word.toCharArray();
            for(int i = 0; i < alphabet.length; i++) {
                if (IsVowel(String.valueOf(alphabet[i])))firstVowel = true;
                else consonant = true;
                if (!firstVowel && String.valueOf(alphabet[i]).equalsIgnoreCase("y"))   firstVowel = true;
                try {
                    if (firstVowel && consonant && !IsVowel(String.valueOf(alphabet[i + 1]))
                            && !String.valueOf(alphabet[i + 1]).equalsIgnoreCase("y")) {
                        count++;
                        firstVowel = false;
                        consonant = false;
                    }
                } catch (Exception e) {continue;}
            }
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

    
    /**
     * method to check that word contain only Vowel e and not a,i,o,u.
     * @param str the alphabet to check
     * @return true if contain only e , False if not.
     */
    private boolean OnlyE(String str) {
        String lowercaseStr = str.toLowerCase();
        if (lowercaseStr.contains("e")) {
            if (lowercaseStr.contains("a") || lowercaseStr.contains("i") || lowercaseStr.contains("o") 
            || lowercaseStr.contains("u")) return false;
            else return true;
        }
        return false;
    }
    
}