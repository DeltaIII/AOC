package day2;

public class CharacterPositionPasswordPolicy implements PasswordPolicy {

    private final char character;
    private final int firstPostion;
    private final int secondPosition;

    public CharacterPositionPasswordPolicy(final char character, final int firstPostion, final int secondPosition) {
        this.character = character;
        this.firstPostion = firstPostion;
        this.secondPosition = secondPosition;
    }

    @Override
    public boolean isValid(final String password) {
        boolean first = isCharacterAtPosition(password, firstPostion);
        boolean second = isCharacterAtPosition(password, secondPosition);

        return first != second;
    }

    private boolean isCharacterAtPosition(final String password, final int position) {
        return password.charAt(position - 1) == character;
    }

}
