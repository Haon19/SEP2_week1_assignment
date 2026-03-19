import org.junit.*;
import java.io.*;

import static org.junit.Assert.*;

public class CalculatorTest {

    private final InputStream sysInBackup = System.in;
    private final PrintStream sysOutBackup = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    // Helper to run calculator with mocked input
    private String runCalcWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Calculator.main(new String[]{});
        return outContent.toString();
    }

    @Test
    public void testEnglishLanguageAndSum() {
        // User selects English (1), wants 2 entries, inputs two pairs (2,3) and (4,5)
        // total = (2*3) + (4*5) = 6 + 20 = 26
        String input =
                "1\n" +   // English
                        "2\n" +   // number of pairs
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n";

        String output = runCalcWithInput(input);

        assertTrue(output.contains("Enter the number of items to purchase:")); // or your English prompt1
        assertTrue(output.contains("Enter the price for item:"));   // prompt2
        assertTrue(output.contains("Enter the quantity for item:"));  // prompt3
        assertTrue(output.contains("Total cost: 26.0"));             // sum + total
    }

    @Test
    public void testFrenchLanguageSelection() {
        // Only checking that French prompts appear
        String input =
                "2\n" +  // French
                        "1\n" +
                        "2\n" +
                        "3\n";

        String output = runCalcWithInput(input);

        assertTrue("Should display French prompt1", output.contains("Donne le nombre de produits à acheter:"));
    }

    @Test
    public void testFinnishLanguageSelection() {
        String input =
                "3\n" +
                        "1\n" +
                        "2\n" +
                        "3\n";

        String output = runCalcWithInput(input);

        assertTrue(output.contains("Syötä tuotteen määrä:"));
    }

    @Test
    public void testJapaneseLanguageSelection() {
        String input =
                "4\n" +
                        "1\n" +
                        "2\n" +
                        "3\n";

        String output = runCalcWithInput(input);

        assertTrue(output.contains("数量を入力してください"));
    }

    @Test
    public void testZeroItems() {
        String input =
                "1\n" + // English
                        "0\n";  // 0 items → total should be 0

        String output = runCalcWithInput(input);

        assertTrue(output.contains("Total cost: 0.0"));
    }
}

