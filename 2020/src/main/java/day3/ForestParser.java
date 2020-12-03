package day3;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import util.InputFileReader;

public class ForestParser {

    public static Forest parseForest(String fileName) throws IOException {
        List<String> input = InputFileReader.readStrings(fileName).collect(Collectors.toList());

        int height = input.size();
        int width = input.get(0).length();

        HexType[][] forestHexes = new HexType[height][width];
        int y = 0;
        for (String line : input) {
            int x = 0;
            for (char c : line.toCharArray()) {
                if ('#' == c){
                    forestHexes[y][x] = HexType.TREE;
                } else {
                    forestHexes[y][x] = HexType.OPEN;
                }
                x++;
            }
            y++;
        }

        return new Forest(forestHexes);
    }

}
