package readability;

public class CountType {
    
    private CountStrategy strategy;

    public CountType(CountStrategy strategy){
        this.strategy = strategy;
    }

    public int Counting(){  
        return strategy.count();
     }  
}