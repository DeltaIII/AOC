package day4.fields;

public class InvalidField<T> implements PassportField<T> {

    @Override
    public T getValue() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
