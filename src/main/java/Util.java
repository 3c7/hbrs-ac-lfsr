import java.util.BitSet;

/**
 * Util provides some helper methods
 */
public class Util {
    /**
     * Checks a given array for the max value
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
     * @param a First BitSet
     * @param b Second BitSet
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
     * @param a First BitSet
     * @param b Second BitSet
     * @return The amount of similar setted values
     */
    public static int checkSimilarity(BitSet a, BitSet b) {
        return checkSimilarity(a, b, a.length() < b.length() ? a.length() : b.length());
    }
}
