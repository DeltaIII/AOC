package day4;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Passport {

    private Integer birthYear;
    private Integer issueYear;
    private Integer expirationYear;
    private String height;
    private String hairColour;
    private String eyeColour;
    private Long id;
    private Long countryId;

}
