package day4;

import day4.fields.HeightField;
import day4.fields.PassportField;
import day4.fields.YearField;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Passport {

    private PassportField<Integer> birthYear;
    private PassportField<Integer> issueYear;
    private PassportField<Integer> expirationYear;
    private PassportField<String> height;
    private PassportField<String> hairColour;
    private PassportField<String> eyeColour;
    private PassportField<String> id;
    private PassportField<Long> countryId;

}
