package day6;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class CustomFormGroupBatchParserTest {

    private static final String INPUT = "day6/testData.txt";

    @Test
    void testParse_returns_correctFormData() throws IOException {
        // Given
        Stream<String> input = InputFileReader.readStrings(INPUT);
        List<CustomFormGroup> expected = new LinkedList<>();

        CustomFormGroup firstGroup = new CustomFormGroup();
        addForm("abc", firstGroup);
        expected.add(firstGroup);
        CustomFormGroup secondGroup = new CustomFormGroup();
        addForm("a", secondGroup);
        addForm("b", secondGroup);
        addForm("c", secondGroup);
        expected.add(secondGroup);
        CustomFormGroup thirdGroup = new CustomFormGroup();
        addForm("ab", thirdGroup);
        addForm("ac", thirdGroup);
        expected.add(thirdGroup);
        CustomFormGroup fourthGroup = new CustomFormGroup();
        addForm("a", fourthGroup);
        addForm("a", fourthGroup);
        addForm("a", fourthGroup);
        addForm("a", fourthGroup);
        expected.add(fourthGroup);
        CustomFormGroup fifthGroup = new CustomFormGroup();
        addForm("b", fifthGroup);
        expected.add(fifthGroup);

        // When
        Collection<CustomFormGroup> parseOutput = CustomFormGroupBatchParser.parse(input);

        // Then
        then(parseOutput).isNotNull();
        then(parseOutput).isEqualTo(expected);

    }

    private void addForm(final String formData, final CustomFormGroup customFormGroup) {
        Set<Character> form = new HashSet<>();
        for (char c : formData.toCharArray()) {
            form.add(c);
        }
        customFormGroup.addForm(form);
    }
}