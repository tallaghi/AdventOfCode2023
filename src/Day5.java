import java.util.*;

class Mapping
{
    Mapping(long destination,long src, long plusMinus){
        Destination = destination;
        Src = src;
        PlusMinus = plusMinus;
        SrcTopRange = src+plusMinus;
        DestTopRange = destination+plusMinus;
    }
    public long Src;
    public long Destination;
    public long PlusMinus;
    public long SrcTopRange;
    public long DestTopRange;
}

class Seed{
    Seed(long start, long range){
        Start = start;
        End = start+range;
    }
    public long Start;
    public long End;
}

public class Day5 implements Day{

    public void Part2(){
        long totalPart1 = 0;
        List<String> result = Util.GetList(5);

        HashMap<Integer, List<Mapping>> dict= new HashMap<>();
        var seeds = Arrays.stream(result.get(0).replace("seeds: ", "").split(" ")).mapToLong(Long::parseUnsignedLong).toArray();

        result.remove(0);
        result.remove(0);

        //String currentMapping = "";
        List<Seed> rangeSeeds = new ArrayList<>();
        for(int i = 0;i<seeds.length;i=i+2){
            rangeSeeds.add(new Seed(seeds[i], seeds[i+1]));
        }
        int mapNum = 0;
        for(String line : result){
            if(line.contains(":")){
                mapNum++;
                dict.put(mapNum, new ArrayList<>());
            } else if (!line.isEmpty()) {
                var nums = Arrays.stream(line.split(" ")).mapToLong(Long::parseUnsignedLong).toArray();
                dict.get(mapNum).add(new Mapping(nums[0],nums[1],nums[2]));
            }
        }

        var finalMap = dict.get(dict.size());
        finalMap.sort(Comparator.comparingLong(x -> x.Destination));
        for(Mapping map : finalMap){
            for(long i = map.Destination;i<map.DestTopRange;i++){
                long currDest = i;
                long nextDest = -1;
                for (int j = dict.size(); j-- > 0;) {
                    List<Mapping> value =dict.get(j);
                    for(Mapping m : value){
                        if(m.Src <= currDest && (m.SrcTopRange)> currDest){
                            long diff = currDest - m.Src;
                            if(nextDest == -1 || nextDest > m.Destination+diff){
                                nextDest =  m.Destination+diff;
                            }
                        }
                    }
                    if(nextDest == -1){break;}
                    else{currDest = nextDest;nextDest = -1;}
                    System.out.println("nextDest: " + currDest);
                    //if(nextDest == -1){ nextDest = currDest; }
                }
                System.out.println("Seed: " + currDest);
            }
        }

        //totalPart1 = min;
        //System.out.println("Part 1: " + totalPart1);
    }
    public void Part1(){
        long totalPart1 = 0;
        List<String> result = Util.GetList(5);

        HashMap<Integer, List<Mapping>> dict= new HashMap<>();
        var seeds = Arrays.stream(result.get(0).replace("seeds: ", "").split(" ")).mapToLong(Long::parseUnsignedLong).toArray();

        result.remove(0);
        result.remove(0);

        //String currentMapping = "";
        int mapNum = -1;
        for(String line : result){
            if(line.contains(":")){
                mapNum++;
                dict.put(mapNum, new ArrayList<>());
            } else if (!line.isEmpty()) {
                var nums = Arrays.stream(line.split(" ")).mapToLong(Long::parseUnsignedLong).toArray();
                dict.get(mapNum).add(new Mapping(nums[0],nums[1],nums[2]));
            }
        }

        //Part 1
        long min = -1;
        for(long seed : seeds){
            long currDest = seed;
            long nextDest = -1;
            for ( List<Mapping> value : dict.values() ) {
                for(Mapping m : value){
                    if(m.Src <= currDest && (m.SrcTopRange)> currDest){
                        long diff = currDest - m.Src;
                        if(nextDest == -1 || nextDest > m.Destination+diff){
                            nextDest =  m.Destination+diff;
                        }
                    }
                }
                if(nextDest == -1){ nextDest = currDest; }
                currDest = nextDest;
                nextDest = -1;
            }
            if(min == -1 || currDest < min){
                min = currDest;
            }
            System.out.println("Location: " + currDest);
        }

        totalPart1 = min;
        System.out.println("Part 1: " + totalPart1);
    }

    public void RunDay() {
        Part1();
        Part2();
    }
}

