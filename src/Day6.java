import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day6 implements Day{

    public int getNumOfWins(long time, long distance){
        int numberOfWins = 0;
        for(int j = 0;j<=time;j++){
            var timeToMove = time-j;
            var distanceMoved = timeToMove * j;
            if(distanceMoved>distance){
                numberOfWins++;
            }
        }
        return numberOfWins;
    }
    public void RunDay() {
        List<String> result = Util.GetList(6);

        int totalPart1 = 1;
        int totalPart2 = 0;

        var times = result.get(0).replace("Time: ", "").split(" ");
        var distances = result.get(1).replace("Distance: ", "").split(" ");

        for(int i = 0; i<times.length;i++){
            var time = Long.parseLong(times[i]);
            var distance = Long.parseLong(distances[i]);
            var numberOfWins = getNumOfWins(time, distance);
            totalPart1 = totalPart1*numberOfWins;
        }

        totalPart2 = getNumOfWins(Long.parseLong(String.join("", times )),Long.parseLong(String.join("", distances )));

        System.out.println("Part 1: " + totalPart1);
        System.out.println("Part 2: " + totalPart2);
    }
}
