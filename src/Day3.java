import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Coordinate
{
    public int Num;
    public int starX;
    public int starY;
}


public class Day3 implements Day {

    int inputSize = 0;
    int inputLength = 0;
    char[][] arr;
    int successX = 0;
    int successY = 0;
    char successChar = '.';
    List<Coordinate> gears = new ArrayList<Coordinate>();
    private boolean checkCoord(int x, int y){

        try{
            boolean success = arr[x][y] != '.' && !Character.isDigit(arr[x][y]);
            if(success){ successX = x; successY = y; successChar = arr[x][y];}
            return success;
        }
        catch (ArrayIndexOutOfBoundsException e){ return false; }
    }

    public boolean checkPerimeter(int lineNum, int startIndex, int endIndex, int num){

        for(int i = startIndex;i<endIndex;i++) {
            if(checkCoord(lineNum+1,i) || checkCoord(lineNum-1,i) ||
                checkCoord(lineNum,i+1) || checkCoord(lineNum,i-1) ||
                checkCoord(lineNum+1,i+1) || checkCoord(lineNum-1,i-1) ||
                checkCoord(lineNum+1,i-1) || checkCoord(lineNum-1,i+1))
            {
                if(successX != 0 && successY != 0){
                    if(successChar == '*') {
                        Coordinate c = new Coordinate();
                        c.Num = num;
                        c.starX = successX;
                        c.starY = successY;
                        gears.add(c);
                    }
                    successChar = '.';
                    successX = 0;
                    successY = 0;
                }
                return true;
            }

        }
        return false;
    }
    public void RunDay() {
        List<String> result = Util.GetList(3);
        inputSize = result.size();
        inputLength = result.get(0).length();
        arr = new char [inputSize][inputLength];

        int totalPart1 = 0;
        int totalPart2 = 0;

        for (int i = 0;i<inputSize;i++) {
            String line = result.get(i);
            char[] lineArr = line.toCharArray();
            //SETUP CHAR ARRAY
            if(i==0) {
                arr[i] = lineArr;
            }
            if(i != inputSize - 1){
                arr[i+1] = result.get(i+1).toCharArray();
            }

            Pattern pattern = Pattern.compile("([0-9])+");
            Matcher matcher = pattern.matcher(line);
            // Check all occurrences
            while (matcher.find()) {

                int num = Integer.parseInt(matcher.group());

                if(checkPerimeter(i, matcher.start(), matcher.end(),num)){
                    totalPart1 += num;
                }

            }
        }


        int count = 0;
        for(int i = 0;i<gears.size();i++){
            Coordinate c = gears.get(i);
            Coordinate a = gears.stream().filter((x)->x.starX==c.starX && x.starY==c.starY).findFirst().orElse(null);
            if(a != null && gears.indexOf(a) != i){
                totalPart2 += (c.Num * a.Num);
                count++;
            }
        }
        System.out.println("total gears " + count);
        System.out.println("Part 1: " + totalPart1);
        System.out.println("Part 2: " + totalPart2);
    }
}
