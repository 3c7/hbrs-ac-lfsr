import java.util.BitSet;

/**
 * Created by kuhnert on 19.05.16.
 */
public class Main {

    private static final byte ATOB[] = {0x01, 0x36, (byte) 0xBB, 0x69, 0x51, (byte) 0x80, 0x21, 0x22, 0x4F, (byte) 0xFD, 0x5E, (byte) 0xC4, 0x00, (byte) 0x83, 0x40};
    private static final byte BTOA[] = {0x3E, (byte) 0x82, (byte) 0x86, (byte) 0xEB, 0x1C, 0x15, (byte) 0xAF, 0x15, 0x63, 0x17, 0x29, 0x19, (byte) 0xCF, (byte) 0x91, (byte) 0xC0};

    private static int[] firstLFSRSolutions = new int[0x7FFFF];
    private static int[] thirdLFSRSolutions = new int[0x7FFFFF];

    private static byte[] lsfrSolution = new byte[ATOB.length];

    private static final BitSet ATOBBS = BitSet.valueOf(ATOB);
    private static BitSet lsfrBitset;

    /**
     * Breaks a geffe generator and finds out about the initial register states
     *
     * @param args Commandline arguments are not used in this programm
     */
    public static void main(String[] args) {
        LSFR lsfr1, lsfr3;
        int[] lsfr1Solution, lsfr3Solution;

        // Checking LSFR 1
        for (int i = 1; i < 0x7FFFF; i++) {
            lsfr1 = new LSFR(i, 0x7FFFF, 0x40000, 0x72000);
            lsfrSolution = lsfr1.getNextBit(114);
            lsfrBitset = BitSet.valueOf(lsfrSolution);
            firstLFSRSolutions[i] = Util.checkSimilarity(lsfrBitset, ATOBBS, 114);
        }
        lsfr1Solution = Util.maxIndex(firstLFSRSolutions);

        // Checking LSFR 3
        for(int i = 1; i< 0x7FFFFF; i++) {
            lsfr3 = new LSFR(i, 0x7FFFFF, 0x400000, 0x700080);
            lsfrSolution = lsfr3.getNextBit(114);
            lsfrBitset = BitSet.valueOf(lsfrSolution);
            thirdLFSRSolutions[i] = Util.checkSimilarity(lsfrBitset, ATOBBS, 114);
        }
        lsfr3Solution = Util.maxIndex(thirdLFSRSolutions);

        System.out.println("Initial R1 State was: 0x" + Integer.toHexString(lsfr1Solution[0]) + " with " + lsfr1Solution[1] + " similar bits");
        System.out.println("Initial R3 State was: 0x" + Integer.toHexString(lsfr3Solution[0]) + " with " + lsfr3Solution[1] + " similar bits");
    }
}
