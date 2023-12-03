import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 implements Day  {
    public int GetLineTotal(String line){

        Matcher matcher = Pattern.compile("\\d").matcher(line);
        matcher.find();
        String firstInt = matcher.group();

        matcher = Pattern.compile("\\d").matcher(new StringBuilder().append(line).reverse());
        matcher.find();
        String secondInt = matcher.group();

        int lineTotal = Integer.valueOf(firstInt+secondInt);

        System.out.println(lineTotal);
        return lineTotal;
    }
    public void RunDay(){
        List<String> result = Util.GetList(1);

        int totalPart1 = 0;
        int totalPart2 = 0;
        for (String line : result) {

            totalPart1 += GetLineTotal(line);

            String newLine = line.replace("one", "one1one")
                    .replace("two", "two2two")
                    .replace("three", "three3three")
                    .replace("four", "four4four")
                    .replace("five", "five5five")
                    .replace("six", "six6six")
                    .replace("seven", "seven7seven")
                    .replace("eight", "eight8eight")
                    .replace("nine", "nine9nine");


            System.out.println(newLine);
            totalPart2 += GetLineTotal(newLine);

        }

        System.out.println("Part 1: " + totalPart1);
        System.out.println("Part 2: " + totalPart2);
    }
}
