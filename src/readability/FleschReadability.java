package readability;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FleschReadability {

    public static  double IndexCalculator(String source){
        ReadAndCount.Read(source);
        double words = ReadAndCount.getWord();
        double syllables = ReadAndCount.getSyl();
        double sentences = ReadAndCount.getSent();
        double readabilityIndex = (206.835 - 84.6*(syllables/words) - 1.015*(words/sentences));
        BigDecimal bd = new BigDecimal(readabilityIndex).setScale(2, RoundingMode.HALF_UP);
        double result = bd.doubleValue();
        return result;
    }

    public static String GradeCalculator(double index){
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
