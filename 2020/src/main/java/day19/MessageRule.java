package day19;

import java.util.Set;

public interface MessageRule {

    int getRuleNumber();

    String getRawPattern(RuleSet ruleSet);
}
