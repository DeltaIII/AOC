package day7;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BaggageTypeTest {

    @Test
    void testGetColour() {
        // Given
        String colour = "red";
        BaggageType baggageType = new BaggageType(colour);

        // When
        String typeColour = baggageType.getColour();

        // Then
        then(typeColour).isEqualTo(colour);
    }

    @Test
    void testGetRuleForColour_missing() {
        // Given
        String colour = "red";
        BaggageType baggageType = new BaggageType(colour);

        // When
        Optional<BagRule> typeColour = baggageType.getRuleForColour("something");

        // Then
        then(typeColour).isEmpty();
    }

    @Test
    void testGetRuleForColour_existing() {
        // Given
        String colour = "red";
        BaggageType baggageType = new BaggageType(colour);
        String colourToFind = "Blue";
        BaggageType typeInRule = new BaggageType(colourToFind);
        BagRule expected = new BagRule(typeInRule, 5);
        baggageType.addBagRule(typeInRule, 5);

        // When
        Optional<BagRule> bagRule = baggageType.getRuleForColour(colourToFind);

        // Then
        then(bagRule).isPresent();
        then(bagRule.get()).isEqualTo(expected);
    }

    @Test
    void testGetBagRules() {
        // Given
        String colour = "red";
        BaggageType baggageType = new BaggageType(colour);
        String colourToFind = "Blue";
        BaggageType typeInRule = new BaggageType(colourToFind);
        BagRule expectedRule = new BagRule(typeInRule, 5);
        baggageType.addBagRule(typeInRule, 5);

        // When
        List<BagRule> bagRules = baggageType.getBagRules();

        // Then
        then(bagRules).isNotNull();
        then(bagRules).isEqualTo(Collections.singletonList(expectedRule));


    }

}