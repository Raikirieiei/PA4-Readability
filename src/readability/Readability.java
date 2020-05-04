package readability;

public class Readability  {
    
    public static void main(String[] args){
        if(args.length == 0)    GraphicalUI.main(args);
        else    CommandLine.Mode(args);      
    }
}
