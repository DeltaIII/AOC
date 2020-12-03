package day2;

public class CharacterNumberPasswordPolicy implements PasswordPolicy {

    private final char character;
    private final int lowerLimit;
    private final int upperLimit;

    public CharacterNumberPasswordPolicy(final char character, final int lowerLimit, final int upperLimit) {
        this.character = character;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean isValid(final String password) {
        int characterFrequency = getCharacterFrequency(password);
        return characterFrequency >= lowerLimit && characterFrequency <= upperLimit;
    }

    private int getCharacterFrequency(final String password){
        int count = 0;
        for (char c : password.toCharArray()) {
            if (c == character) {
                count++;
            }
        }
        return count;
    }
}
