package day2;

public class PolicyParser {

    /**
     * @param rangeDefinition range given as "x-y"
     * @param character the character for the policy
     */
    public static CharacterNumberPasswordPolicy parseNumberPolicy(String rangeDefinition, String character) {
        String[] limits = rangeDefinition.split("-");
        return new CharacterNumberPasswordPolicy(character.charAt(0), Integer.parseInt(limits[0]), Integer.parseInt(limits[1]));
    }

    public static CharacterPositionPasswordPolicy parsePositionPolicy(String positionDefinition, String character) {
        String[] positions = positionDefinition.split("-");
        return new CharacterPositionPasswordPolicy(character.charAt(0), Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
    }

}
