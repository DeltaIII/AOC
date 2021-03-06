package day6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CustomFormGroup {

    private List<Set<Character>> forms = new LinkedList<>();

    public void addForm(Set<Character> form) {
        this.forms.add(form);
    }

    public Set<Character> getUnion() {
        if (forms.isEmpty()) {
            throw new IllegalStateException("Form group is empty");
        }
        return forms.stream().reduce(
            new HashSet<>(),
            (form1, form2) ->
            {form1.addAll(form2);
                return form1;});
    }

    public Set<Character> getIntersect() {
        if (forms.isEmpty()) {
            throw new IllegalStateException("Form group is empty");
        }
        return forms.stream().reduce(
            new HashSet<>(forms.get(0)),
            (form1, form2) ->
            {form1.retainAll(form2);
                return form1;});
    }

    public int size() {
        return this.forms.size();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final CustomFormGroup formGroup = (CustomFormGroup) o;

        return new EqualsBuilder()
            .append(forms, formGroup.forms)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(forms)
            .toHashCode();
    }
}
