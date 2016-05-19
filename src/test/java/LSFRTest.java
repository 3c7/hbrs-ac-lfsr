/**
 * Created by kuhnert on 19.05.16.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LSFRTest {
    private LSFR lsfr, lsfr2;
    private String lsfrOut = "";
    private final String SOLUTION = "111111111111111111000000000000001110100000000011111100110000111010110111000111111111110011010100000011011001010110";
    private final byte[] BYTESOLUTION = {(byte) 0b11111111, (byte) 0b11111111, (byte) 0b11000000, 0b00000000,
            (byte) 0b11101000, 0b00000011, (byte) 0b11110011, 0b00001110,
            (byte) 0b10110111, 0b00011111, (byte) 0b11111100, (byte) 0b11010100,
            0b00001101, (byte) 0b10010101, 0b10};

    @Before
    public void setUp() {
        lsfr = new LSFR(0x7FFFF, 0x7FFFF, 0x40000, 0x72000);
        lsfr2 = new LSFR(0x7FFFF, 0x7FFFF, 0x40000, 0x72000);
    }

    @Test
    public void testLSFR() {
        for (int i = 0; i < 114; i++) {
            lsfrOut += Integer.toBinaryString(lsfr.clockGet()).charAt(0);
        }
        assertEquals(SOLUTION, lsfrOut);
    }

    @Test
    public void testLSFRgetNextBit() {
        byte[] result = lsfr2.getNextBit(114);
        assertTrue(result.length == BYTESOLUTION.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(BYTESOLUTION[i], result[i]);
        }
    }

}
