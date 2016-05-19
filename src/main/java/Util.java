import java.util.BitSet;

/**
 * Created by kuhnert on 19.05.16.
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
            if (arr[i] > currentmax) {
                currentmax = arr[i];
                result[0] = i;
            }
        }
        result[1] = currentmax;
        return result;
    }

    /**
     * Compares two BitSets for setted values and returns how much similar setted values there are
     * @param a
     * @param b
     * @return
     */
    public static int checkSimilarity(BitSet a, BitSet b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.get(i) == b.get(i)) count++;
        }
        return count;
    }
}
