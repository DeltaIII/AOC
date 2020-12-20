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
    public Set<String> getValidMessages(final RuleSet ruleSet) {
        return Collections.singleton(character);
    }

    @Override
    public boolean isValidMessage(final RuleSet ruleSet, final String message) {
        return character.equals(message);
    }
}
