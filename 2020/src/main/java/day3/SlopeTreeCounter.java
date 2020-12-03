package day3;

public class SlopeTreeCounter {


    public static int countTreesOnSlope(final Forest forest, final Slope slope) {
        int numberOfTrees = 0;

        int x = 0;
        int y = 0;
        do {
            HexType hexAtCoordinate = forest.getHexAtCoordinate(x, y);
            if (hexAtCoordinate == HexType.TREE){
                numberOfTrees++;
            }
            x += slope.getXSlope();
            y += slope.getYSlope();
        } while (y  < forest.getHeight());

        return numberOfTrees;
    }
}
