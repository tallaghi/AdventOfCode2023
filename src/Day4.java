import java.util.*;

public class Day4 implements Day{
    public void RunDay() {
        List<String> result = Util.GetList(4);
        HashMap<Integer, Integer> dict= new HashMap<>();

        for(int i = 1;i<=result.size();i++){
            dict.put(i,1);
        }

        int totalPart1 = 0;
        int totalPart2 = 0;
        for(String line : result){
            var cardNumSplit = line.split(":");
            int cardNumber = Integer.parseInt(cardNumSplit[0].replaceAll("[^0-9]", ""));
            var numSplit = cardNumSplit[1].split("\\|");
            String winningNumsStr = numSplit[0].trim();
            String myNumsStr = numSplit[1].trim();

            int[] winningNums = Arrays.stream(winningNumsStr.replace("  ", " ").split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] myNums = Arrays.stream(myNumsStr.replace("  ", " ").split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] crossover = Arrays.stream(winningNums)
                                .distinct()
                                .filter(x -> Arrays.stream(myNums).anyMatch(y -> y == x))
                                .toArray();

            int matchedNums = crossover.length;
            totalPart1 += (int)Math.pow(2,(matchedNums- 1));

            for(int i=1;i<=matchedNums;i++){
                for(int j=0;j<dict.get(cardNumber);j++){
                    dict.put(cardNumber+i,dict.get(cardNumber+i)+1);
                }
            }

        }

        totalPart2 = dict.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Part 1: " + totalPart1);
        System.out.println("Part 2: " + totalPart2);
    }
}
