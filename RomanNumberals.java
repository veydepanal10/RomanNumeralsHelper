import java.util.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RomanNumerals {
    private static Map<Character, Integer> fromRomanMap = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    private static Map<Integer, String> toRomanMap = new TreeMap<>(Comparator.reverseOrder()) {{
        put(1, "I");
        put(4, "IV");
        put(5, "V");
        put(9, "IX");
        put(10, "X");
        put(40, "XL");
        put(50, "L");
        put(90, "XC");
        put(100, "C");
        put(400, "CD");
        put(500, "D");
        put(900, "CM");
        put(1000, "M");
    }};

    public static String toRoman(int n) {
        StringBuilder result = new StringBuilder();
        for (Integer key : toRomanMap.keySet()) {
            while (n >= key) {
                result.append(toRomanMap.get(key));
                n -= key;
            }
        }
        return result.toString();
    }

    public static int fromRoman(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            if (i > 0 && fromRomanMap.get(roman.charAt(i)) > fromRomanMap.get(roman.charAt(i - 1))) {
                result += fromRomanMap.get(roman.charAt(i)) - 2 * fromRomanMap.get(roman.charAt(i - 1));
            } else {
                result += fromRomanMap.get(roman.charAt(i));
            }
        }
        return result;
    }
}

// Testing

public class RomanNumeralsTest {

    @Test
    public void testToRoman() throws Exception {
        assertThat("1 converts to 'I'", RomanNumerals.toRoman(1), is("I"));
        assertThat("2 converts to 'II'", RomanNumerals.toRoman(2), is("II"));
    }

    @Test
    public void testFromRoman() throws Exception {
        assertThat("'I' converts to 1", RomanNumerals.fromRoman("I"), is(1));
        assertThat("'II' converts to 2", RomanNumerals.fromRoman("II"), is(2));
    }
}
