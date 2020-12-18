package day17;

import day17.point.Point4D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ActiveCubeMap4D implements ActiveCubeMap<Point4D> {

    public static final List<int[]> CARDINAL_DIRECTIONS = new LinkedList<>();

    static {
        for (int z = -1; z <= 1; z++) {
            for (int y = -1; y <= 1; y ++) {
                for (int x = -1; x <= 1; x++) {
                    for (int w = -1; w <= 1; w++) {
                        if (w==0 && x == 0 && y == 0 && z == 0) {
                            continue;
                        }
                        CARDINAL_DIRECTIONS.add(new int[]{w, x, y, z});
                    }
                }
            }
        }
    }

    private final boolean[][][][] activeSpaces;
    private final Set<int[]> activeCubes;
    private int[] lowerLimits;

    public ActiveCubeMap4D(final Set<Point4D> activeCubes,
                           final int[] lowerLimits,
                           final int[] upperLimits) {
        this.lowerLimits = lowerLimits.clone();

        // + 1 as upper range is inclusive
        int wRange = upperLimits[0] - lowerLimits[0] + 1;
        int xRange = upperLimits[1] - lowerLimits[1] + 1;
        int yRange = upperLimits[2] - lowerLimits[2] + 1;
        int zRange = upperLimits[3] - lowerLimits[3] + 1;
        System.out.println(String.format("limits %s,%s,%s,%s", wRange, xRange, yRange, zRange));
        this.activeSpaces = new boolean[zRange][yRange][xRange][wRange];
        this.activeCubes = activeCubes.stream().map(Point4D::asArray).collect(Collectors.toSet());
        for (Point4D point : activeCubes) {
            activeSpaces[convertIndex(point.getZ(), 3)][convertIndex(point.getY(), 2)][convertIndex(point.getX(), 1)][convertIndex(point.getW(), 0)] = true;
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
            int w = point[0] + cardinalDirection[0];
            int x = point[1] + cardinalDirection[1];
            int y = point[2] + cardinalDirection[2];
            int z = point[3] + cardinalDirection[3];
            cubes.add(new int[] {w, x, y ,z});
        }

        return cubes;
    }

    @Override
    public Set<int[]> getAdjacentActiveCubes(final int[] point) {
        final Set<int[]> activeCubes = new HashSet<>();
        for (int[] cardinalDirection : CARDINAL_DIRECTIONS) {
            int w = point[0] + cardinalDirection[0];
            int wIndex = convertIndex(w, 0);
            int x = point[1] + cardinalDirection[1];
            int xIndex = convertIndex(x, 1);
            int y = point[2] + cardinalDirection[2];
            int yIndex = convertIndex(y, 2);
            int z = point[3] + cardinalDirection[3];
            int zIndex = convertIndex(z, 3);
            if (activeSpaces[zIndex][yIndex][xIndex][wIndex]) {
                activeCubes.add(new int[]{w, x, y, z});
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
