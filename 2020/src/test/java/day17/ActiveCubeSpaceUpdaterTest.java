package day17;

import static org.assertj.core.api.BDDAssertions.then;

import day17.point.Point3D;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.InputFileReader;

class ActiveCubeSpaceUpdaterTest {

    private static final String TEST_DATA = "day17/testData.txt";
    private static final String INPUT = "day17/input.txt";

    @ParameterizedTest
    @CsvSource(value = {"1, 11", "2, 21", "3, 38", "6, 112"})
    void testUpdate_testData(int iterations, int expected) throws IOException {
        // Given
        Stream<String> inputStream = InputFileReader.readStrings(TEST_DATA);
        SubSpace<Point3D> subSpace = new SubSpace<>(3, Point3D::fromArray, ActiveCubeMap3D::new);
        populateSubSpace3D(subSpace, inputStream);
        ActiveCubeSpaceUpdater<Point3D> updater = new ActiveCubeSpaceUpdater<>(subSpace);

        // When
        for (int i = 0; i < iterations; i ++) {
            updater.update();
        }

        // Then
        SubSpace<Point3D> cubeSubSpace = updater.getCubeSubSpace();
        Set<Point3D> activePoints = cubeSubSpace.getActiveCubePoints();
        then(activePoints.size()).isEqualTo(expected);
    }

    @Test
    void testUpdate_part1() throws IOException {
        // Given
        Stream<String> inputStream = InputFileReader.readStrings(INPUT);
        SubSpace<Point3D> subSpace = new SubSpace<>(3, Point3D::fromArray, ActiveCubeMap3D::new);
        populateSubSpace3D(subSpace, inputStream);
        ActiveCubeSpaceUpdater<Point3D> updater = new ActiveCubeSpaceUpdater<>(subSpace);

        // When
        for (int i = 0; i < 6; i ++) {
            updater.update();
        }

        // Then
        SubSpace<Point3D> cubeSubSpace = updater.getCubeSubSpace();
        Set<Point3D> activePoints = cubeSubSpace.getActiveCubePoints();
        then(activePoints.size()).isEqualTo(313);
    }

    private void populateSubSpace3D(final SubSpace<Point3D> subSpace, final Stream<String> inputStream) {
        Iterator<String> iterator = inputStream.iterator();
        int z = 0;
        int y = 0;
        while (iterator.hasNext()) {
            char[] chars = iterator.next().toCharArray();
            int x = 0;
            for (char inputChar : chars) {
                if (inputChar == '#') {
                    subSpace.setActive(new int[]{x, y, z});
                }
                x++;
            }
            y++;
        }
    }

}