package day19;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DuplicatePatternMessageRule implements MessageRule {

    private final int ruleNumber;
    private final List<Integer> rulesThatCanRepeat;

    @Override
    public int getRuleNumber() {
        return ruleNumber;
    }

    @Override
    public String getRawPattern(final RuleSet ruleSet) {
        List<String> ruleCombinationPatterns = rulesThatCanRepeat.stream()
            .map(ruleSet::getRawPatternForRule).collect(Collectors.toList());

        StringBuilder rawPatternBuilder = new StringBuilder();
        rawPatternBuilder.append("(?:");
        for (String pattern : ruleCombinationPatterns) {
            rawPatternBuilder.append(pattern);
            rawPatternBuilder.append("+");
        }
        rawPatternBuilder.append(")");
        return rawPatternBuilder.toString();
    }


}
