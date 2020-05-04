package readability;

public class CommandLine {

    public static void Mode(String[] args){
        try{
            if(args.length != 0){ 
                if(args[0].equals("-link")){      
                    if(args.length < 2) System.out.println("Please Insert File");
                    else {
                        String readability = FleschReadability.GradeCalculator(FleschReadability.IndexCalculator(args[1]));
                        PrintResult(args[1], ReadAndCount.getSyl(), ReadAndCount.getWord(), ReadAndCount.getSent(),
                                    FleschReadability.IndexCalculator(args[1]), readability);
                    }
                } 
                else    System.out.println("Wrong command");
            }
        }catch(Exception E){ System.out.println("File not found.");}
    }

    private static void PrintResult(String filename, int syl, int word, int sent, double index, String flesch) {
        System.out.println("File :           " + filename + "\n" 
                            + "Syllables :      " + syl + "\n" 
                            + "Words :          " + word + "\n"
                            + "Sentences :      " + sent + "\n" 
                            + "Flesch Index :   " + index + "\n" 
                            + "Readability :    " + flesch); 
    }
}

