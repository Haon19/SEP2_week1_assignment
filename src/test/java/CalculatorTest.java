import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final InputStream sysInBackup = System.in;
    private final PrintStream sysOutBackup = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    private String runCalcWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Calculator.main(new String[]{});
        return outContent.toString();
    }

    @Test
    void testEnglishLanguageAndSum() {
        String input =
                "1\n" +
                        "2\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n";

        String output = runCalcWithInput(input);

        assertTrue(output.contains("Enter the number of items to purchase:"));
        assertTrue(output.contains("Enter the price for item:"));
        assertTrue(output.contains("Enter the quantity for item:"));
        assertTrue(output.contains("Total cost: 26.0"));
    }

    @Test
    void testFrenchLanguageSelection() {
        String input = "2\n1\n2\n3\n";
        String output = runCalcWithInput(input);

        assertTrue(output.contains("Donne le nombre de produits à acheter:"));
    }

    @Test
    void testFinnishLanguageSelection() {
        String input = "3\n1\n2\n3\n";
        String output = runCalcWithInput(input);

        assertTrue(output.contains("Syötä tuotteen määrä:"));
    }

    @Test
    void testJapaneseLanguageSelection() {
        String input = "4\n1\n2\n3\n";
        String output = runCalcWithInput(input);

        assertTrue(output.contains("数量を入力してください"));
    }

    @Test
    void testZeroItems() {
        String input = "1\n0\n";
        String output = runCalcWithInput(input);

        assertTrue(output.contains("Total cost: 0.0"));
    }
}