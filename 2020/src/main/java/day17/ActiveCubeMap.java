package day17;

import day17.point.CubePoint;
import java.util.Set;

public interface ActiveCubeMap<P extends CubePoint> {

    boolean isActive(final int[] point);

    Set<int[]> getAdjacentCubes(final int[] point);

    Set<int[]> getAdjacentActiveCubes(final int[] point);

    Set<int[]> getActiveCubes();
}
