package day17.point;

import lombok.Data;

@Data
public class Point4D implements CubePoint {
    private final int w;
    private final int x;
    private final int y;
    private final int z;

    @Override
    public int[] asArray() {
        return new int[] {w, x, y, z};
    }

    public static Point4D fromArray(final int[] array) {
        return new Point4D(array[0], array[1], array[2], array[3]);
    }
}
