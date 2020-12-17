package day17.point;

import lombok.Data;

@Data
public class Point3D implements CubePoint {
    private final int x;
    private final int y;
    private final int z;

    @Override
    public int[] asArray() {
        return new int[] {x, y, z};
    }

    public static Point3D fromArray(final int[] array) {
        return new Point3D(array[0], array[1], array[2]);
    }
}
