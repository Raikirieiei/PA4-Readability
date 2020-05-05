package readability;

/**
 * this class is use to call a counting Strategy.
 * @author Thornthep Chomchuen
 */
public class CountType {
    
    private CountStrategy strategy;

    // constructor
    public CountType(CountStrategy strategy) {
        this.strategy = strategy;
    }
    
    // call to counting the strategy you choose.
    public int Counting(String line) {  
        return strategy.count(line);
     }  
}