import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
    public void RunDay(){
        int red = 12;
        int green = 13;
        int blue = 14;

        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(System.getProperty("user.dir") + "\\txt\\" +"Day2.txt"))) {
            result = lines.collect(Collectors.toList());

            int totalPart1 = 0;
            int totalPart2 = 0;
            for (String line : result) {
                var gameSplit = line.split(":");
                int gameNum = Integer.valueOf(gameSplit[0].replace("Game ", ""));

                var setSplit = gameSplit[1].split(";");

                boolean isValid = true;
                int minRed = 0;
                int minGreen = 0;
                int minBlue = 0;
                for(String set : setSplit){
                    var colors = set.split(",");
                    for(String color : colors){
                        if(color.contains("red")){
                            int tempColor = Integer.valueOf(color.replace("red","").strip());
                            if(tempColor>red){
                                isValid = false;
                            }
                            if(tempColor > minRed){
                                minRed=tempColor;
                            }
                        } else if (color.contains("green")) {
                            int tempColor = Integer.valueOf(color.replace("green","").strip());
                            if(tempColor>green){
                                isValid = false;
                            }
                            if(tempColor > minGreen){
                                minGreen=tempColor;
                            }
                        } else if (color.contains("blue")) {
                            int tempColor = Integer.valueOf(color.replace("blue","").strip());
                            if(tempColor>blue){
                                isValid = false;
                            }
                            if(tempColor > minBlue){
                                minBlue=tempColor;
                            }
                        }
                    }
                }

                if(isValid){
                    totalPart1 += gameNum;
                }

                totalPart2+=(minRed * minBlue * minGreen);
            }

            System.out.println("Part 1: " + totalPart1);
            System.out.println("Part 2: " + totalPart2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
