package day3;

public class Forest {

    private final HexType[][] forestHexes;
    private final int width;

    public Forest(final HexType[][] forestHexes) {
        this.forestHexes = forestHexes;
        this.width = forestHexes[0].length;
    }

    public HexType getHexAtCoordinate(int x, int y) {
        int wrappedXOordinate = x % width;
        return forestHexes[y][wrappedXOordinate];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return forestHexes.length;
    }
}
