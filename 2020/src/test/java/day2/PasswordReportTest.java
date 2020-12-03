package day2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class PasswordReportTest {

    @Test
    void part1() throws IOException {
        System.out.println(new PasswordReport(PolicyParser::parseNumberPolicy).countValidPasswords());
    }

    @Test
    void part2() throws IOException {
        System.out.println(new PasswordReport(PolicyParser::parsePositionPolicy).countValidPasswords());
    }

}