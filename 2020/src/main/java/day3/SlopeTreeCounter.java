package day3;

public class SlopeTreeCounter {


    public static int countTreesOnSlope(final Mountain mountain, final Slope slope) {
        int numberOfTrees = 0;

        int x = 0;
        int y = 0;
        do {
            HexType hexAtCoordinate = mountain.getHexAtCoordinate(x, y);
            if (hexAtCoordinate == HexType.TREE){
                numberOfTrees++;
            }
            x += slope.getXSlope();
            y += slope.getYSlope();
        } while (y  < mountain.getHeight());

        return numberOfTrees;
    }
}
