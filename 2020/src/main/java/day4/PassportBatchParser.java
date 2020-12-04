package day4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportBatchParser {

    public static Collection<Passport> parse(Stream<String> batchData) {
        List<String> passportDataEntries = convertBatchToPassportInputs(batchData);
        return passportDataEntries.stream()
            .map(PassportParser::parse)
            .collect(Collectors.toList());
    }

    private static List<String> convertBatchToPassportInputs(final Stream<String> batchData) {
        List<String> passportDataEntries = new LinkedList<>();
        Iterator<String> it = batchData.iterator();
        StringBuilder passportInput = new StringBuilder();
        boolean endOfFile = false;
        do {
            endOfFile = it.hasNext();
            final String dataLine = endOfFile? it.next() : "";
            if (dataLine.length() == 0) {
                if (passportInput.length() > 0) {
                    passportDataEntries.add(passportInput.toString());
                }
                passportInput = new StringBuilder();
            } else {
                passportInput.append(dataLine);
                passportInput.append(" ");
            }

        } while (endOfFile);
        return passportDataEntries;
    }

}
