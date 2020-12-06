package day6;

import java.util.Collection;
import java.util.Set;

public class CustomFormGroupProcessor {

    public static int countUniqueQuestionsAnsweredByGroups(Collection<CustomFormGroup> formGroups) {
        return formGroups.stream().map(CustomFormGroup::getUnion).map(Set::size).reduce(0, Integer::sum);
    }

    public static int countQuestionsAnsweredByAllIndividualsInGroups(Collection<CustomFormGroup> formGroups) {
        return formGroups.stream().map(CustomFormGroup::getIntersect).map(Set::size).reduce(0, Integer::sum);
    }
}
