package readability;

public class CountSyllable implements CountStrategy {

    @Override
    public int count() {
        int count = 0;
        for (String word : ReadAndCount.getLine().split("\\s+")) {
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
            for (int i = 0; i < alphabet.length; i++) {
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

    private static boolean IsVowel(String alphabet) {
        if (alphabet.equalsIgnoreCase("a") || alphabet.equalsIgnoreCase("e") || alphabet.equalsIgnoreCase("i")
                || alphabet.equalsIgnoreCase("o") || alphabet.equalsIgnoreCase("u")) return true;
        else return false;
    }

    private static boolean OnlyE(String str) {
        String lowercaseStr = str.toLowerCase();
        if (lowercaseStr.contains("e")) {
            if (lowercaseStr.contains("a") || lowercaseStr.contains("i") || lowercaseStr.contains("o") 
            || lowercaseStr.contains("u")) return false;
            else return true;
        }
        return false;
    }
    
}