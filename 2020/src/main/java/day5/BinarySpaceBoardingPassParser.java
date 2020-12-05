package day5;

public class BinarySpaceBoardingPassParser {

    public static BoardingPass parseBoardingPass(String input) {

        int id = parseId(input);
        int row = id >>> 3; // Logical right shift 3 (replaces moved bits with 0)
        return BoardingPass.builder()
                .id(id)
                .row(row)
                .column(id ^ (row << 3)) // Logical left shift 3 (replaces moved bits with 0). Bitwise XOR, means row bits cancel
                .build();
    }

    private static int parseId(final String input) {
        int id = 0;
        for (char c : input.toCharArray()) {
            id = id << 1; // Logical left shift 2. Puts a bit 0 at end.
            if (c == 'B' || c == 'R') {
                id ++; // Replace bit 0 with a 1 when necessary.
            }
        }
        return id;
    }
}
