package day4.fields;

import java.util.regex.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PatternMatchField implements PassportField<String> {

    private static final Pattern ID_PATTERN = Pattern.compile("^\\d{9}$");
    private static final Pattern HCL_PATTERN = Pattern.compile("^#[\\da-f]{6}$");

    private final String id;
    private final boolean isValid;

    private PatternMatchField(final String id, final Pattern pattern) {
        this.id = id;
        this.isValid = pattern.matcher(id).matches();
    }

    @Override
    public String getValue() {
        return id;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    public static PassportField<String> idField(String value) {
        return new PatternMatchField(value, ID_PATTERN);
    }

    public static PassportField<String> hairColourField(String value) {
        return new PatternMatchField(value, HCL_PATTERN);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final PatternMatchField that = (PatternMatchField) o;

        return new EqualsBuilder()
            .append(isValid, that.isValid)
            .append(id, that.id)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(isValid)
            .toHashCode();
    }
}
