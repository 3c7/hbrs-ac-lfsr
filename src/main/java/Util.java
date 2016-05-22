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
     * Checks which value r needs to calculate x1 & x2 ^ x2 & x3 ^ x3
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
}
