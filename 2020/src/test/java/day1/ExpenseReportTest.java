package day1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ExpenseReportTest {

    @Test
    void verifyExpenses() throws IOException {
        System.out.println(ExpenseReport.verifyExpenses());
    }

    @Test
    void verifyExpensesPart2() throws IOException {
        System.out.println(ExpenseReport.verifyExpensesPart2());
    }
}