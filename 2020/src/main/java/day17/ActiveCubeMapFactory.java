package day17;

import day17.point.CubePoint;
import java.util.Set;

@FunctionalInterface
public interface ActiveCubeMapFactory<P extends CubePoint> {

    ActiveCubeMap<P> build(Set<P> activePoints, int[] lowerLimits, int[] upperLimits);
}
