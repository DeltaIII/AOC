package day4.fields;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EyeColourField implements PassportField<String> {

    private static final ImmutableSet<String> VALID_COLOURS =
        ImmutableSet.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");


    private final String colour;

    public EyeColourField(final String colour) {
        this.colour = colour;
    }

    @Override
    public String getValue() {
        return colour;
    }

    @Override
    public boolean isValid() {
        return VALID_COLOURS.contains(colour);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final EyeColourField that = (EyeColourField) o;

        return new EqualsBuilder()
            .append(colour, that.colour)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(colour)
            .toHashCode();
    }
}
