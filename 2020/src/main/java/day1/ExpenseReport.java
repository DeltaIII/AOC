package day1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import util.InputFileReader;

public class ExpenseReport {

    public static int verifyExpenses(Set<Integer> expenseItems) throws IOException {
        for (Integer expenseItem : expenseItems) {
            int complement = 2020 - expenseItem;
            if (expenseItems.contains(complement)){
                return expenseItem*complement;
            }
        }
        throw new IllegalStateException("No answer!");
    }


    public static int verifyExpensesPart2(Set<Integer> expenseItems) throws IOException {
        Set<Integer> nestedItems = new HashSet<>(expenseItems);
        for (Integer expenseItem : expenseItems) {
            nestedItems.remove(expenseItem);
            int firstComplement = 2020 - expenseItem;
            for (Integer nestedItem : nestedItems) {
                int secondComplement = firstComplement - nestedItem;
                if (expenseItems.contains(secondComplement)){
                    return expenseItem*nestedItem*secondComplement;
                }
            }
        }
        throw new IllegalStateException("No answer!");
    }
}
