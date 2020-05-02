package readability;

public class FleschReadability {

    public static double IndexCalculator(String source){
        double words = ReadAndCount.Read(source)[0];
        double syllables = ReadAndCount.Read(source)[1];
        double sentences = ReadAndCount.Read(source)[2];
        double readabilityIndex = 206.835 - 84.6*(syllables/words) - 1.015*(words/sentences);
        return readabilityIndex;
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
