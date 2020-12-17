package day17;

import day17.point.CubePoint;
import java.util.HashSet;
import java.util.Set;

public class ActiveCubeSpaceUpdater<P extends CubePoint> {

    private final SubSpace<P> cubeSubSpace;

    public ActiveCubeSpaceUpdater(final SubSpace<P> cubeSubSpace) {
        this.cubeSubSpace = cubeSubSpace;
    }

    public void update() {
        ActiveCubeMap<P> activeCubeMap3D = cubeSubSpace.getActiveCubeMap();
        Set<int[]> activeCubes = activeCubeMap3D.getActiveCubes();
        Set<int[]> cubesToCheck = new HashSet<>(activeCubes);
        for (int[] activePoint : activeCubes) {
            cubesToCheck.addAll(activeCubeMap3D.getAdjacentCubes(activePoint));
        }

        for (int[] cube : cubesToCheck) {
            Set<int[]> adjacentActiveCubes = activeCubeMap3D.getAdjacentActiveCubes(cube);
            if (activeCubeMap3D.isActive(cube)) {
                if (adjacentActiveCubes.size() < 2 || adjacentActiveCubes.size() > 3) {
                    cubeSubSpace.setInactive(cube);
                }
            } else {
                if (adjacentActiveCubes.size() == 3) {
                    cubeSubSpace.setActive(cube);
                }
            }
        }
    }

    public SubSpace<P> getCubeSubSpace() {
        return cubeSubSpace;
    }
}
