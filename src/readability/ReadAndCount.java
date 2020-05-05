package readability;

import java.io.*;
import java.net.URL;

/**
 * a the class to read the text file or URL and counting the words,syllables and sentences.
 * @author Thornthep Chomchuen
 */
public class ReadAndCount {
    //this is the url attribute that use to receive url and read
    private URL url;
    // this is attribute to receive counted words from file or URL.
    private int countWord;
    // this is attribute to receive counted syllables from file or URL.
    private int countSyllable;
    // this is attribute to receive counted sentences from file or URL.
    private int countSentence;
    // this is attribute to receive each line from file or URL.
    private String line;
    // this is attribute to call ui class.
    private GraphicalUI ui = new GraphicalUI();

    // these are attribute to use the counting strategy from interface.

    private static CountType countWordStrat = new CountType(new CountWord());
    private static CountType countSentenceStrat = new CountType(new CountSentence());
    private static CountType countSyllableStrat = new CountType(new CountSyllable());

    /**
     * method to read a file or URL and counting, then send information of counting to count attribute.
     * @param source file or URL.
     */
    public void Read(String source) {
        if (source.contains("/")) {
            try {
                url = new URL(source);
            } catch (Exception e) {}
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                while((line = br.readLine()) != null && !line.isEmpty()) {
                    countWord += countWordStrat.Counting(line);
                    countSyllable += countSyllableStrat.Counting(line);
                    countSentence += countSentenceStrat.Counting(line);
                }
            } catch (IOException e) {
                ui.PopupError("File not found");
            }
            
        } else {
            File file = new File(source);
            if (!file.exists() || !file.isFile())
                ui.error("File does not exist or is not a regular file.");
            if (!file.canRead())
                ui.error("File is not readable");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while((line = br.readLine()) != null) {
                    countWord += countWordStrat.Counting(line);
                    countSyllable += countSyllableStrat.Counting(line);
                    countSentence += countSentenceStrat.Counting(line);
                }
            } catch (IOException e) {ui.PopupError("File not found");}
        }
    }

    // accessor method for attribute countWord.
    public int getWord() {return this.countWord;}

    // accessor method for attribute countSyl.
    public int getSyllable() {return this.countSyllable;}

    // accessor method for attribute countSent.
    public int getSentence() {return this.countSentence;}
}
