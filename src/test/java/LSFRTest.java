/**
 * Created by kuhnert on 19.05.16.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LSFRTest {
    private LSFR lsfr;
    private final String SOLUTION = "111111111111111111000000000000001110100000000011111100110000111010110111000111111111110011010100000011011001010110";

    @Before
    public void setUp() {
        lsfr = new LSFR(0x7FFFF, 0x7FFFF, 0x40000, 0x72000);
    }

    @Test
    public void testLSFR() {
        String lsfrOut = "";
        for (int i = 0; i < 114; i++) {
            lsfrOut += Integer.toBinaryString(lsfr.clockGet()).charAt(0);
        }
        assertEquals(SOLUTION, lsfrOut);
    }
}
