package day1;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class ExpenseReportTest {

    private static String TEST_DATA = "day1/testData.txt";
    private static String INPUT = "day1/input.txt";

    @Test
    void verifyExpenses_testData() throws IOException {
        // Given
        Set<Integer> expenseItems = InputFileReader.readInts(TEST_DATA).collect(Collectors.toCollection(HashSet::new));

        // When
        int expenseVerificationInt = ExpenseReport.verifyExpenses(expenseItems);

        // Then
        then(expenseVerificationInt).isEqualTo(514579);
    }

    @Test
    void verifyExpenses_part1() throws IOException {
        // Given
        Set<Integer> expenseItems = InputFileReader.readInts(INPUT).collect(Collectors.toCollection(HashSet::new));

        // When
        int expenseVerificationInt = ExpenseReport.verifyExpenses(expenseItems);

        // Then
        then(expenseVerificationInt).isEqualTo(1010884);
    }

    @Test
    void verifyExpensesPart2() throws IOException {
        // Given
        Set<Integer> expenseItems = InputFileReader.readInts(INPUT).collect(Collectors.toCollection(HashSet::new));

        // When
        int expenseVerificationInt = ExpenseReport.verifyExpensesPart2(expenseItems);

        // Then
        then(expenseVerificationInt).isEqualTo(253928438);
    }
}