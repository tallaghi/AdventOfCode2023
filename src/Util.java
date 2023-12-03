import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static List<String> GetList(int dayNum){
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(System.getProperty("user.dir") + "\\txt\\" +"Day"+dayNum+".txt"))) {
            result = lines.collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
