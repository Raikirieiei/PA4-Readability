package readability;


public class CountSentence implements CountStrategy{

    @Override
    public int count() {
        int count = 0;
        for (String word : ReadAndCount.getLine().split("\\s+")) {
            try {
                char end = word.toCharArray()[word.length() - 1];
                if (String.valueOf(end).matches("[./,?]")) count++;
            } catch (Exception e) {continue;}
        }
        return count;
    }

    
}