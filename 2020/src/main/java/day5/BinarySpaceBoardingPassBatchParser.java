package day5;

import java.io.IOException;
import java.util.stream.Stream;
import util.InputFileReader;

public class BinarySpaceBoardingPassBatchParser {

    public static Stream<BoardingPass> parseBatch(String inputFileName) throws IOException {
        return InputFileReader.readStrings(inputFileName).map(BinarySpaceBoardingPassParser::parseBoardingPass);
    }
}
