package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public final class HighLevelEncoder {
    private static final int[][] CHAR_MAP = ((int[][]) Array.newInstance(int.class, new int[]{5, 256}));
    static final int[][] LATCH_TABLE = new int[][]{new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int MODE_DIGIT = 2;
    static final int MODE_LOWER = 1;
    static final int MODE_MIXED = 3;
    static final String[] MODE_NAMES = new String[]{"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int MODE_PUNCT = 4;
    static final int MODE_UPPER = 0;
    static final int[][] SHIFT_TABLE = ((int[][]) Array.newInstance(int.class, new int[]{6, 6}));
    private final byte[] text;

    /* renamed from: com.google.zxing.aztec.encoder.HighLevelEncoder$1 */
    class C03871 implements Comparator<State> {
        C03871() {
        }

        public int compare(State state, State state2) {
            return state.getBitCount() - state2.getBitCount();
        }
    }

    static {
        int i;
        int i2;
        CHAR_MAP[0][32] = 1;
        for (i = 65; i <= 90; i++) {
            CHAR_MAP[0][i] = (i - 65) + 2;
        }
        CHAR_MAP[1][32] = 1;
        for (i = 97; i <= 122; i++) {
            CHAR_MAP[1][i] = (i - 97) + 2;
        }
        CHAR_MAP[2][32] = 1;
        for (i = 48; i <= 57; i++) {
            CHAR_MAP[2][i] = (i - 48) + 2;
        }
        int[][] iArr = CHAR_MAP;
        iArr[2][44] = 12;
        iArr[2][46] = 13;
        int[] iArr2 = new int[]{0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (i2 = 0; i2 < iArr2.length; i2++) {
            CHAR_MAP[3][iArr2[i2]] = i2;
        }
        iArr2 = new int[]{0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (i2 = 0; i2 < iArr2.length; i2++) {
            if (iArr2[i2] > 0) {
                CHAR_MAP[4][iArr2[i2]] = i2;
            }
        }
        for (int[] fill : SHIFT_TABLE) {
            Arrays.fill(fill, -1);
        }
        int[][] iArr3 = SHIFT_TABLE;
        iArr3[0][4] = 0;
        iArr3[1][4] = 0;
        iArr3[1][0] = 28;
        iArr3[3][4] = 0;
        iArr3[2][4] = 0;
        iArr3[2][0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.text = bArr;
    }

    public BitArray encode() {
        Object singletonList = Collections.singletonList(State.INITIAL_STATE);
        int i = 0;
        while (true) {
            byte[] bArr = this.text;
            if (i >= bArr.length) {
                return ((State) Collections.min(singletonList, new C03871())).toBitArray(this.text);
            }
            int i2 = i + 1;
            byte b = i2 < bArr.length ? bArr[i2] : (byte) 0;
            byte b2 = this.text[i];
            int i3 = b2 != (byte) 13 ? b2 != (byte) 44 ? b2 != (byte) 46 ? b2 != (byte) 58 ? 0 : b == (byte) 32 ? 5 : 0 : b == (byte) 32 ? 3 : 0 : b == (byte) 32 ? 4 : 0 : b == (byte) 10 ? 2 : 0;
            if (i3 > 0) {
                singletonList = updateStateListForPair(singletonList, i, i3);
                i = i2;
            } else {
                singletonList = updateStateListForChar(singletonList, i);
            }
            i++;
        }
    }

    private Collection<State> updateStateListForChar(Iterable<State> iterable, int i) {
        Iterable linkedList = new LinkedList();
        for (State updateStateForChar : iterable) {
            updateStateForChar(updateStateForChar, i, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private void updateStateForChar(State state, int i, Collection<State> collection) {
        char c = (char) (this.text[i] & 255);
        int i2 = 0;
        Object obj = CHAR_MAP[state.getMode()][c] > 0 ? 1 : null;
        State state2 = null;
        while (i2 <= 4) {
            int i3 = CHAR_MAP[i2][c];
            if (i3 > 0) {
                if (state2 == null) {
                    state2 = state.endBinaryShift(i);
                }
                if (obj == null || i2 == state.getMode() || i2 == 2) {
                    collection.add(state2.latchAndAppend(i2, i3));
                }
                if (obj == null && SHIFT_TABLE[state.getMode()][i2] >= 0) {
                    collection.add(state2.shiftAndAppend(i2, i3));
                }
            }
            i2++;
        }
        if (state.getBinaryShiftByteCount() > 0 || CHAR_MAP[state.getMode()][c] == 0) {
            collection.add(state.addBinaryShiftChar(i));
        }
    }

    private static Collection<State> updateStateListForPair(Iterable<State> iterable, int i, int i2) {
        Iterable linkedList = new LinkedList();
        for (State updateStateForPair : iterable) {
            updateStateForPair(updateStateForPair, i, i2, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private static void updateStateForPair(State state, int i, int i2, Collection<State> collection) {
        State endBinaryShift = state.endBinaryShift(i);
        collection.add(endBinaryShift.latchAndAppend(4, i2));
        if (state.getMode() != 4) {
            collection.add(endBinaryShift.shiftAndAppend(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(endBinaryShift.latchAndAppend(2, 16 - i2).latchAndAppend(2, 1));
        }
        if (state.getBinaryShiftByteCount() > 0) {
            collection.add(state.addBinaryShiftChar(i).addBinaryShiftChar(i + 1));
        }
    }

    private static Collection<State> simplifyStates(Iterable<State> iterable) {
        Collection linkedList = new LinkedList();
        for (State state : iterable) {
            Object obj = 1;
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                State state2 = (State) it.next();
                if (state2.isBetterThanOrEqualTo(state)) {
                    obj = null;
                    break;
                } else if (state.isBetterThanOrEqualTo(state2)) {
                    it.remove();
                }
            }
            if (obj != null) {
                linkedList.add(state);
            }
        }
        return linkedList;
    }
}
