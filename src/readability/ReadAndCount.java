package readability;

import java.io.*;
import java.net.URL;

/**
 * a the class to read the text file or URL and counting the words,syllables and sentences.
 * @author Thornthep Chomchuen
 */
public class ReadAndCount {
    //this is the url attribute that use to receive url and read
    private static URL url;
    // this is attribute to receive counted words from file or URL.
    private static int countWord;
    // this is attribute to receive counted syllables from file or URL.
    private static int countSyl;
    // this is attribute to receive counted sentences from file or URL.
    private static int countSent;
    // this is attribute to receive each line from file or URL.
    private static String line;
    
    // these are attribute to use the counting strategy from interface.
    
    private static CountType cw = new CountType(new CountWord());
    private static CountType cs = new CountType(new CountSentence());
    private static CountType csl = new CountType(new CountSyllable());

    /**
     * method to read a file or URL and counting, then send information of counting to count attribute.
     * @param source file or URL.
     */
    public static void Read(String source) {
        if (source.contains("/")) {
            try {
                url = new URL(source);
            } catch (Exception e) {}
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                while((line = br.readLine()) != null) {
                    countWord += cw.Counting();
                    countSyl += csl.Counting();
                    countSent += cs.Counting();
                }
            } catch (IOException e) {e.printStackTrace();}
        } else {
            File file = new File(source);
            if (!file.exists() || !file.isFile())
                GraphicalUI.error("File does not exist or is not a regular file.");
            if (!file.canRead())
                GraphicalUI.error("File is not readable");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while((line = br.readLine()) != null) {
                    countWord += cw.Counting();
                    countSyl += csl.Counting();
                    countSent += cs.Counting();
                }
            } catch (IOException e) {}
        
        }
    }

    // accessor method for attribute countWord.
    public static int getWord() {return ReadAndCount.countWord;}

    // accessor method for attribute countSyl.
    public static int getSyl() {return ReadAndCount.countSyl;}

    // accessor method for attribute countSent.
    public static int getSent() {return ReadAndCount.countSent;}

    // accessor method for attribute line.
    public static String getLine() {return ReadAndCount.line;}

    /**
     * a method to clear all the count.
     */
    public static void ClearCount() {
        ReadAndCount.countSent = 0;
        ReadAndCount.countWord = 0;
        ReadAndCount.countSyl = 0;
    }
}
