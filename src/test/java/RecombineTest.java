/**
 * Nils Kuhnert, 22.05.16.
 * Classname
 * Description
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class RecombineTest {
    private final byte[] z = {0x00};
    private final byte[] a = {0x0A};
    private final byte[] c = {0x0B};
    private final byte[] result = {0x0A};
    private byte[] b = new byte[a.length];
    private byte[] zSolution = new byte[z.length];

    @Before
    public void setUp() {
        b = Util.recombine(result, a, c);
        zSolution = Util.recombine(z, z, z);
    }

    @Test
    public void testRecombineWithZero() {
        assertEquals(z[0], zSolution[0]);
    }

    @Test
    public void recombineTest() {
        assertEquals(0x0F, b[0]);
    }

}
