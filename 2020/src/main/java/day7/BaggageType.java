package day7;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BaggageType {

    private final List<BagRule> bagRules = new LinkedList<>();
    private final String colour;

    public BaggageType(final String colour) {
        this.colour = colour;
    }

    public Optional<BagRule> getRuleForColour(final String bagColour) {
        return bagRules.stream().filter(rule -> rule.getBaggageType().getColour().equals(bagColour)).findAny();
    }

    public String getColour() {
        return colour;
    }

    public void addBagRule(BaggageType baggageType, int limit) {
        this.bagRules.add(new BagRule(baggageType, limit));
    }

    public List<BagRule> getBagRules() {
        return bagRules;
    }
}
