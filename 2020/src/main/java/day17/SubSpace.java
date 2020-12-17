package day17;

import day17.point.CubePoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class SubSpace<P extends CubePoint> {

    private final Map<P, Boolean> activePoints = new HashMap<>();
    private final int[] lowerLimits;
    private final int[] upperLimits;
    private int numberOfDimensions;
    private final Function<int[], P> pointBoxer;
    private ActiveCubeMapFactory<P> activeCubeMapFactory;

    public SubSpace(final int numberOfDimensions,
                    final Function<int[], P> pointBoxer,
                    final ActiveCubeMapFactory<P> activeCubeMapFactory) {
        this.lowerLimits = new int[numberOfDimensions];
        this.upperLimits = new int[numberOfDimensions];
        this.numberOfDimensions = numberOfDimensions;
        this.pointBoxer = pointBoxer;
        this.activeCubeMapFactory = activeCubeMapFactory;
    }

    public void setActive(final int[] point) {
        updateLimits(point);
        activePoints.put(pointBoxer.apply(point), true);
    }

    public void setInactive(final int[] point) {
        activePoints.remove(pointBoxer.apply(point));
    }

    public ActiveCubeMap<P> getActiveCubeMap() {
        return activeCubeMapFactory.build(getActiveCubePoints(), lowerLimits, upperLimits);
    }

    public Set<P> getActiveCubePoints() {
        return activePoints.keySet();
    }

    private void updateLimits(final int[] point) {
        for (int index = 0; index < numberOfDimensions; index++) {
            if (lowerLimits[index] >= point[index] - 2) {
                lowerLimits[index] = point[index] - 2;
            }
            if (upperLimits[index] <= point[index] + 2) {
                upperLimits[index] = point[index] + 2;
            }
        }
    }
}
