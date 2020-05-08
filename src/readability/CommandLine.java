package readability;

/**
 * This is class for commandline, if user input command it will use this class instead of GUI.
 * @author Thornthep Chomchuen
 */
public class CommandLine {

    /**
     * a method to modified the command line.
     * @param args input from user.
     */
    public void Mode(String[] args)  {
        try {
            ReadAndCount read = new ReadAndCount();
            FleschReadability flesch = new FleschReadability();
            if (args.length != 0) { 
                if (args[0].equals("-link")) {      
                    if (args.length < 2) System.out.println("Please Insert File");
                    else {
                        String readability = flesch.GradeCalculator(flesch.IndexCalculator(args[1]));
                        PrintResult(args[1], read.getSyllable(), read.getWord(), read.getSentence(),
                                    flesch.IndexCalculator(args[1]), readability);
                    }
                } 
                else    System.out.println("Wrong command");
            }
        } catch (Exception E) { System.out.println("File not found.");}
    }

    /**
     * the method to print result in terminal.
     * @param filename file or url from user input.
     * @param syllable counted syllables.
     * @param word counted words.
     * @param sentence counted sentences.
     * @param index calculated flesch readability index.
     * @param flesch result from flesch reability.
     */
    private void PrintResult(String filename, int syllable, int word, int sentence, double index, String flesch) {
        System.out.println("File :           " + filename + "\n" 
                            + "Syllables :      " + syllable + "\n" 
                            + "Words :          " + word + "\n"
                            + "Sentences :      " + sentence + "\n" 
                            + "Flesch Index :   " + index + "\n" 
                            + "Readability :    " + flesch); 
    }
}

