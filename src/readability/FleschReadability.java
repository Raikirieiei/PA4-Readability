package readability;


/**
 * This is a class to count index and calculate Flesch Readability.
 * @author Thornthep Chomchuen
 */
public class FleschReadability {

    /**
     * this method callculate Flesch Readability Index and will use to determine readability later.
     * @param source file or url to read.
     * @return return the result of index calculated by formula.
     */
    public double IndexCalculator(String source){
        ReadAndCount read = new ReadAndCount();
        read.Read(source);
        double words = read.getWord();
        double syllables = read.getSyllable();
        double sentences = read.getSentence();
        double readabilityIndex = (206.835 - 84.6*(syllables/words) - 1.015*(words/sentences)); // Formula to count index
        return readabilityIndex;
    }

    /**
     * this method determine how hard to read the text.
     * @param index calculated readability index.
     * @return readability from the file or url.
     */
    public String GradeCalculator(double index){
        if (index > 100)    return "4th grade student (elementary school) ";
        else if (90 <= index && index <= 100) return "5th grade student";
        else if (80 <= index && index <= 90) return "6th grade student";
        else if (70 <= index && index <= 80) return "7th grade student";
        else if (65 <= index && index <= 70) return "8th grade student";
        else if (60 <= index && index <= 65) return "9th grade student";
        else if (50 <= index && index <= 60) return "High school student";
        else if (30 <= index && index <= 50) return "College student";
        else if (0 <= index && index <= 30) return "College graduate";
        else return "Advance degree graduate";
    }
}
