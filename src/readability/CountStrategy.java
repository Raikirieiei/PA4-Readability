package readability;

/**
 * interface for Strategy to Counting word, syllable or sentence.
 * @author Thornthep Chomchuen
 */
public interface CountStrategy {

    /**
     * method for counting word, syllable or sentence.
     * @return result.
     */
    public int count(String line);
    
}