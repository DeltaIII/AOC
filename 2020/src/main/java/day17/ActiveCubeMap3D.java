package day17;

import day17.point.Point3D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ActiveCubeMap3D implements ActiveCubeMap<Point3D> {

    public static final List<int[]> CARDINAL_DIRECTIONS = new LinkedList<>();

    static {
        for (int z = -1; z <= 1; z++) {
            for (int y = -1; y <= 1; y ++) {
                for (int x = -1; x <= 1; x++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    CARDINAL_DIRECTIONS.add(new int[]{x, y, z});
                }
            }
        }
    }

    private final boolean[][][] activeSpaces;
    private final Set<int[]> activeCubes;
    private int[] lowerLimits;

    public ActiveCubeMap3D(final Set<Point3D> activeCubes,
                           final int[] lowerLimits,
                           final int[] upperLimits) {
        this.lowerLimits = lowerLimits.clone();

        // + 1 as upper range is inclusive
        int xRange = upperLimits[0] - lowerLimits[0] + 1;
        int yRange = upperLimits[1] - lowerLimits[1] + 1;
        int zRange = upperLimits[2] - lowerLimits[2] + 1;
        this.activeSpaces = new boolean[zRange][yRange][xRange];
        this.activeCubes = activeCubes.stream().map(Point3D::asArray).collect(Collectors.toSet());
        for (Point3D point : activeCubes) {
            activeSpaces[convertIndex(point.getZ(), 2)][convertIndex(point.getY(), 1)][convertIndex(point.getX(), 0)] = true;
        }
    }

    @Override
    public boolean isActive(final int[] point) {
        return this.activeCubes.contains(point);
    }

    @Override
    public Set<int[]> getAdjacentCubes(final int[] point) {
        final Set<int[]> cubes = new HashSet<>();
        for (int[] cardinalDirection : CARDINAL_DIRECTIONS) {
            int x = point[0] + cardinalDirection[0];
            int y = point[1] + cardinalDirection[1];
            int z = point[2] + cardinalDirection[2];
            cubes.add(new int[] {x, y ,z});
        }

        return cubes;
    }

    @Override
    public Set<int[]> getAdjacentActiveCubes(final int[] point) {
        final Set<int[]> activeCubes = new HashSet<>();
        for (int[] cardinalDirection : CARDINAL_DIRECTIONS) {
            int x = point[0] + cardinalDirection[0];
            int xIndex = convertIndex(x, 0);
            int y = point[1] + cardinalDirection[1];
            int yIndex = convertIndex(y, 1);
            int z = point[2] + cardinalDirection[2];
            int zIndex = convertIndex(z, 2);
            if (activeSpaces[zIndex][yIndex][xIndex]) {
                activeCubes.add(new int[]{x, y, z});
            }
        }

        return activeCubes;
    }

    @Override
    public Set<int[]> getActiveCubes() {
        return activeCubes;
    }

    private int convertIndex(final int raw, final int ordinateIndex) {
        return raw - lowerLimits[ordinateIndex];
    }
}
