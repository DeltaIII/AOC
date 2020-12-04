package day4.fields;

public class AlwaysValidField<T> implements PassportField<T> {

    private final T value;

    public AlwaysValidField(final T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final AlwaysValidField<?> that = (AlwaysValidField<?>) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
            .append(value, that.value)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
            .append(value)
            .toHashCode();
    }
}
