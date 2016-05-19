/**
 * Created by kuhnert on 19.05.16.
 */

import org.junit.Test;
import org.junit.Before;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public class SimilarityTest {
    private static final byte[] A = {(byte) 0xFF, (byte) 0xFF};
    private static final byte[] B = {(byte) 0xFF};

    private static BitSet bsA, bsB;
    private int similarBits;

    @Before
    public void setUp() {
        bsA = BitSet.valueOf(A);
        bsB = BitSet.valueOf(B);
        similarBits = Util.checkSimilarity(bsA, bsB);
    }

    @Test
    public void testTwoBitSets() {
        assertEquals(8, similarBits);
    }
}
