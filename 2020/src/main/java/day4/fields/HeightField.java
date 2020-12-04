package day4.fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class HeightField implements PassportField<String> {

    private static final Pattern PATTERN = Pattern.compile("^(\\d+)(in|cm)$");

    private enum Unit {
        INCHES("in"), CENTIMETRES("cm");

        private String unitString;
        Unit(String unitString) {
            this.unitString = unitString;
        }

        public String getUnitString() {
            return unitString;
        }
    }

    private final int height;
    private final Unit unit;

    private HeightField(final int height, final Unit unit) {
        this.height = height;
        this.unit = unit;
    }

    @Override
    public String getValue() {
        return height + unit.getUnitString();
    }

    @Override
    public boolean isValid() {
        switch (unit){
            case INCHES:
                return height >= 59 && height <= 76;
            case CENTIMETRES:
                return height >= 150 && height <= 193;
            default:
                return false;
        }
    }

    public static PassportField<String> parse(String height) {
        Matcher matcher = PATTERN.matcher(height);
        if (matcher.matches()) {
            String unitString = matcher.group(2);
            Unit unit;
            if ("in".equals(unitString)) {
                unit = Unit.INCHES;
            } else  {
                unit = Unit.CENTIMETRES;
            }
            return new HeightField(Integer.parseInt(matcher.group(1)), unit);
        } else {
            return new InvalidField<>();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final HeightField that = (HeightField) o;

        return new EqualsBuilder()
            .append(height, that.height)
            .append(unit, that.unit)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(height)
            .append(unit)
            .toHashCode();
    }
}
