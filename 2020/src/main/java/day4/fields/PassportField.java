package day4.fields;

public interface PassportField<T> {

    T getValue();

    boolean isValid();

}
