import java.util.BitSet;

/**
 * Util provides some helper methods
 */
public class Util {
    /**
     * Checks a given array for the max value
     *
     * @param arr input array
     * @return array containing {index, value}
     */
    public static int[] maxIndex(int[] arr) {
        int currentmax = 0;
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= currentmax) {
                currentmax = arr[i];
                result[0] = i;
            }
        }
        result[1] = currentmax;
        return result;
    }

    /**
     * Compares two BitSets for setted values and returns how much similar setted values there are
     *
     * @param a      First BitSet
     * @param b      Second BitSet
     * @param length how many bits to check
     * @return The amount of similar setted values
     */
    public static int checkSimilarity(BitSet a, BitSet b, int length) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (a.get(i) == b.get(i)) count++;
        }
        return count;
    }

    /**
     * Compares two BitSets for setted values and returns how much similar setted values there are
     * (same as checkSimilarity(BitSet, BitSet, int), you just don't need to specify the length, it uses the lower length of the arrays)
     *
     * @param a First BitSet
     * @param b Second BitSet
     * @return The amount of similar setted values
     */
    public static int checkSimilarity(BitSet a, BitSet b) {
        return checkSimilarity(a, b, a.length() < b.length() ? a.length() : b.length());
    }

    /**
     * Checks which value r needs to calculate x1 AND x2 ^ x2 AND x3 ^ x3
     *
     * @param result the given result
     * @param a first byte array - x1
     * @param b second byte array - x3
     * @return x2 as byte array
     */
    public static byte[] recombine(byte[] result, byte[] a, byte[] b) {
        if (!(result.length == a.length) && !(a.length == b.length))
            throw new IllegalArgumentException("Parameter must have the same length.");
        byte[] returnArray = new byte[result.length];
        for (int i = 0; i < result.length; i++) {
            int solution = 0;
            int tmp = a[i]&0b11111111^b[i]&0b11111111^b[i];
            for (int j = 0; j < 8; j++) {
                if(((tmp>>j)&0b00000001) == (result[i]>>j&0b00000001)) solution |= 1<<j;
            }
            returnArray[i] = (byte) solution;
        }
        return returnArray;
    }

    /**
     * Bruteforces the starting value of lsfr2 and return that value as integer
     * Because the calculation is known, we just check every possible starting value and try to get x1 AND x2 XOR x2 AND x3 XOR x3
     * @param givenResult the given result of x1 AND x2 XOR x2 AND x3 XOR x3
     * @param lsfr1InitialValue LSFR1 initial value as integer
     * @param lsfr3InitialValue LSFR3 initial value as integer
     * @return LSFR2 starting value
     */
    public static int bruteforceLSFR2(byte[] givenResult, int lsfr1InitialValue, int lsfr3InitialValue) {
        LSFR lsfr1, lsfr2, lsfr3;
        lsfr1 = new LSFR(lsfr1InitialValue, 0x7FFFF, 0x40000, 0x72000);
        lsfr3 = new LSFR(lsfr3InitialValue, 0x7FFFFF, 0x400000, 0x700080);
        byte[] lsfr1Bytes = lsfr1.getNextBit(114);
        byte[] lsfr3Bytes = lsfr3.getNextBit(114);
        for(int i = 1; i<0x3FFFFF; i++) {
            lsfr2 = new LSFR(i, 0x3FFFFF, 0x200000, 0x300000);
            byte[] lsfr2Bytes = lsfr2.getNextBit(114);
            int correctCount = 0;
            for(int j = 0; j<114; j++) {
                int x1 = (lsfr1Bytes[j/8] >> j) & 0b00000001;
                int x2 = (lsfr2Bytes[j/8] >> j) & 0b00000001;
                int x3 = (lsfr3Bytes[j/8] >> j) & 0b00000001;
                int z = givenResult[j/8] >> j & 0b00000001;
                if (((x1&x2) ^ (x2&x3) ^ x3) == z) correctCount++;
            }
            if (correctCount >= 113) return i;
        }
        return 0;
    }
}