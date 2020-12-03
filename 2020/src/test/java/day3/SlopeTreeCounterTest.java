package day3;


import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SlopeTreeCounterTest {

    public static final String DAY_3_TEST_DATA_TXT = "day3TestData.txt";
    public static final String DAY_3_TXT = "day3.txt";

    @Test
    void countTreesOnSlope_testData() throws IOException {
        Mountain mountain = MountainParser.parseMountain(DAY_3_TEST_DATA_TXT);

        int numberOfTrees = SlopeTreeCounter.countTreesOnSlope(mountain, new Slope(3, 1));

        then(numberOfTrees).isEqualTo(7);
    }

    @Test
    void part1() throws IOException {
        Mountain mountain = MountainParser.parseMountain(DAY_3_TXT);

        int numberOfTrees = SlopeTreeCounter.countTreesOnSlope(mountain, new Slope(3, 1));

        then(numberOfTrees).isEqualTo(209);
    }


    @Test
    void part2_testData() throws IOException {
        Mountain mountain = MountainParser.parseMountain(DAY_3_TEST_DATA_TXT);

        List<Slope> slopes = new LinkedList<>();
        slopes.add(new Slope(1,1));
        slopes.add(new Slope(3,1));
        slopes.add(new Slope(5,1));
        slopes.add(new Slope(7,1));
        slopes.add(new Slope(1,2));

        int part2Count = 1;

        for (Slope slope : slopes) {
            part2Count *= SlopeTreeCounter.countTreesOnSlope(mountain, slope);
        }

        then(part2Count).isEqualTo(336);
    }

    @Test
    void part2() throws IOException {
        Mountain mountain = MountainParser.parseMountain(DAY_3_TXT);

        Slope[] slopes = new Slope[] {
            new Slope(1,1),
            new Slope(3,1),
            new Slope(5,1),
            new Slope(7,1),
            new Slope(1,2)};
        
        int part2Count = 1;

        for (Slope slope : slopes) {
            part2Count *= SlopeTreeCounter.countTreesOnSlope(mountain, slope);
        }

        then(part2Count).isEqualTo(1574890240);
    }
}