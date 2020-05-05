package readability;

/**
 * this is a class that implement CountStrategy interface to calculate sentences.
 * @author Thornthep Chomchuen
 */
public class CountSentence implements CountStrategy {

    @Override
    public int count(String line) {
        int count = 0;
        for (String word : line.split("\\s+")) {
            try {
                char end = word.toCharArray()[word.length() - 1];
                if (String.valueOf(end).matches("[./,?]")) count++;
            } catch (Exception e) {continue;}
        }
        return count;
    }
}