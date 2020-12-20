package day19;

import java.util.Set;

public interface MessageRule {

    int getRuleNumber();

    Set<String> getValidMessages(RuleSet ruleSet);

    boolean isValidMessage(RuleSet ruleSet, String message);
}
