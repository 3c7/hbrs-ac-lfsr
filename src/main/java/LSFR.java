/**
 * Created by kuhnert on 19.05.16.
 * LSFR -> Line Shift Feedback Register
 */
public class LSFR {
    private int register, mask, out, taps;

    public LSFR(int initial, int mask, int out, int taps) {
        this.register = initial;
        this. mask = mask;
        this.out = out;
        this.taps = taps;
    }

    private int getParity(int x) {
        x ^= x>>16;
        x ^= x>>8;
        x ^= x>>4;
        x ^= x>>2;
        x ^= x>>1;
        return x&1;
    }

    public void clock() {
        int t = register & taps;
        register = (register << 1) & mask;
        register |= getParity(t);
    }

    public int get() {
        return getParity(register&out);
    }

    public int clockGet() {
        clock();
        return get();
    }

    public byte[] getNextBit(int count) {
        byte[] result = new byte[count/8 + 1];
        for (int i = 0; i<count;i++) {
            result[i/8] |= (byte) this.clockGet() << (7-(i&7));
        }
        return result;
    }

}
