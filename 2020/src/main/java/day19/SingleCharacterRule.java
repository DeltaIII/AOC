package day19;

import java.util.Collections;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SingleCharacterRule implements MessageRule {

    private final int ruleNumber;
    private final String character;

    @Override
    public int getRuleNumber() {
        return ruleNumber;
    }

    @Override
    public String getRawPattern(final RuleSet ruleSet) {
        return character;
    }

}
