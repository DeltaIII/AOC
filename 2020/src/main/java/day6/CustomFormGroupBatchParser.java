package day6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class CustomFormGroupBatchParser {

    public static Collection<CustomFormGroup> parse(Stream<String> batchData) {
        List<CustomFormGroup> groupForms = new LinkedList<>();
        Iterator<String> it = batchData.iterator();
        CustomFormGroup formGroup = new CustomFormGroup();
        boolean endOfFile = false;
        do {
            endOfFile = it.hasNext();
            final String dataLine = endOfFile? it.next() : "";
            if (dataLine.length() == 0) {
                if (formGroup.size() > 0) {
                    groupForms.add(formGroup);
                }
                formGroup = new CustomFormGroup();
            } else {
                HashSet<Character> form = new HashSet<>();
                for (char questionAnswered : dataLine.toCharArray()) {
                    form.add(questionAnswered);
                }
                formGroup.addForm(form);
            }

        } while (endOfFile);
        return groupForms;
    }
}
