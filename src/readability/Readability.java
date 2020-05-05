package readability;
/**
 * This is Main class to run application.
 * @author Thornthep Chomchuen
 */
public class Readability  {
    
    public static void main(String[] args) {
        GraphicalUI ui = new GraphicalUI();
        CommandLine commandline = new CommandLine();
        if (args.length == 0)    ui.main(args);
        else    commandline.Mode(args);      
    }
}
