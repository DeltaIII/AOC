package day4.fields;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class YearField implements PassportField<Integer> {

    private final int year;
    private final int earliestYear;
    private final int latestYear;

    public YearField(final Integer year, final int earliestYear, final int latestYear) {
        this.year = year;
        this.earliestYear = earliestYear;
        this.latestYear = latestYear;
    }

    @Override
    public Integer getValue() {
        return year;
    }

    @Override
    public boolean isValid() {
        return year >= earliestYear && year <= latestYear;
    }

    public static YearField birthYear(Integer year) {
        return new YearField(year, 1920, 2002);
    }

    public static YearField issueYear(Integer year) {
        return new YearField(year, 2010, 2020);
    }

    public static YearField expiryYear(Integer year) {
        return new YearField(year, 2020, 2030);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final YearField yearField = (YearField) o;

        return new EqualsBuilder()
            .append(year, yearField.year)
            .append(earliestYear, yearField.earliestYear)
            .append(latestYear, yearField.latestYear)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(year)
            .append(earliestYear)
            .append(latestYear)
            .toHashCode();
    }
}
