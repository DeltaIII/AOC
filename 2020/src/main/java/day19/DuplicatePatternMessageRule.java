package day19;

import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DuplicatePatternMessageRule implements MessageRule {

    private final int ruleNumber;
    private final List<Integer> ruleCombinations;

    @Override
    public int getRuleNumber() {
        return ruleNumber;
    }

    @Override
    public String getRawPattern(final RuleSet ruleSet) {
        Iterator<String> ruleCombinationPatterns = ruleCombinations.stream()
            .map(r -> this.getRawPatterns(ruleSet, r)).iterator();

        StringBuilder rawPatternBuilder = new StringBuilder();
        rawPatternBuilder.append("(");
        rawPatternBuilder.append(ruleCombinationPatterns.next());
        while (ruleCombinationPatterns.hasNext()) {
            rawPatternBuilder.append("|");
            rawPatternBuilder.append(ruleCombinationPatterns.next());
        }
        rawPatternBuilder.append(")");
        return rawPatternBuilder.toString();
    }

    private String getRawPatterns(final RuleSet ruleSet, final List<Integer> ruleNumbers) {
        return ruleNumbers.stream().map(ruleSet::getRawPatternForRule).reduce("", String::concat);
    }

}
