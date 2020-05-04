package readability;

import java.io.*;
import java.net.URL;

public class ReadAndCount {

    private static URL url;
    private static int countWord;
    private static int countSyl;
    private static int countSent;
    private static String line;
    
    private static CountType cw = new CountType(new CountWord());
    private static CountType cs = new CountType(new CountSentence());
    private static CountType csl = new CountType(new CountSyllable());

    public static void Read(String source) {
        if (source.contains("/")) {
            try {
                url = new URL(source);
            } catch (Exception e) {
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                while ((line = br.readLine()) != null) {
                    countWord += cw.Counting();
                    countSyl += csl.Counting();
                    countSent += cs.Counting();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File(source);
            if (!file.exists() || !file.isFile())
                GraphicalUI.error("File does not exist or is not a regular file.");
            if (!file.canRead())
                GraphicalUI.error("File is not readable");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    countWord += cw.Counting();
                    countSyl += csl.Counting();
                    countSent += cs.Counting();
                }
            } catch (IOException e) {}
        
        }
    }

    public static int getWord() {return ReadAndCount.countWord;}

    public static int getSyl() {return ReadAndCount.countSyl;}

    public static int getSent() {return ReadAndCount.countSent;}

    public static String getLine(){return ReadAndCount.line;}

    public static void ClearCount() {
        ReadAndCount.countSent = 0;
        ReadAndCount.countWord = 0;
        ReadAndCount.countSyl = 0;
    }
}
