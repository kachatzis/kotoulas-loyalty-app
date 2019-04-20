package com.google.zxing.aztec.encoder;

import android.support.v4.internal.view.SupportMenu;
import com.google.zxing.common.BitArray;
import java.util.Deque;
import java.util.LinkedList;

final class State {
    static final State INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
    private final int binaryShiftByteCount;
    private final int bitCount;
    private final int mode;
    private final Token token;

    private State(Token token, int i, int i2, int i3) {
        this.token = token;
        this.mode = i;
        this.binaryShiftByteCount = i2;
        this.bitCount = i3;
    }

    int getMode() {
        return this.mode;
    }

    Token getToken() {
        return this.token;
    }

    int getBinaryShiftByteCount() {
        return this.binaryShiftByteCount;
    }

    int getBitCount() {
        return this.bitCount;
    }

    State latchAndAppend(int i, int i2) {
        int i3;
        int i4 = this.bitCount;
        Token token = this.token;
        if (i != this.mode) {
            i3 = HighLevelEncoder.LATCH_TABLE[this.mode][i];
            int i5 = SupportMenu.USER_MASK & i3;
            i3 >>= 16;
            token = token.add(i5, i3);
            i4 += i3;
        }
        i3 = i == 2 ? 4 : 5;
        return new State(token.add(i2, i3), i, 0, i4 + i3);
    }

    State shiftAndAppend(int i, int i2) {
        Token token = this.token;
        int i3 = this.mode == 2 ? 4 : 5;
        return new State(token.add(HighLevelEncoder.SHIFT_TABLE[this.mode][i], i3).add(i2, 5), this.mode, 0, (this.bitCount + i3) + 5);
    }

    State addBinaryShiftChar(int i) {
        State state;
        Token token = this.token;
        int i2 = this.mode;
        int i3 = this.bitCount;
        if (i2 == 4 || i2 == 2) {
            i2 = HighLevelEncoder.LATCH_TABLE[i2][0];
            int i4 = SupportMenu.USER_MASK & i2;
            i2 >>= 16;
            token = token.add(i4, i2);
            i3 += i2;
            i2 = 0;
        }
        int i5 = this.binaryShiftByteCount;
        if (i5 != 0) {
            if (i5 != 31) {
                i5 = i5 == 62 ? 9 : 8;
                state = new State(token, i2, this.binaryShiftByteCount + 1, i3 + i5);
                return state.binaryShiftByteCount != 2078 ? state.endBinaryShift(i + 1) : state;
            }
        }
        i5 = 18;
        state = new State(token, i2, this.binaryShiftByteCount + 1, i3 + i5);
        if (state.binaryShiftByteCount != 2078) {
        }
    }

    State endBinaryShift(int i) {
        int i2 = this.binaryShiftByteCount;
        if (i2 == 0) {
            return this;
        }
        return new State(this.token.addBinaryShift(i - i2, i2), this.mode, 0, this.bitCount);
    }

    boolean isBetterThanOrEqualTo(State state) {
        int i = this.bitCount + (HighLevelEncoder.LATCH_TABLE[this.mode][state.mode] >> 16);
        int i2 = state.binaryShiftByteCount;
        if (i2 > 0) {
            int i3 = this.binaryShiftByteCount;
            if (i3 == 0 || i3 > i2) {
                i += 10;
            }
        }
        return i <= state.bitCount ? true : null;
    }

    BitArray toBitArray(byte[] bArr) {
        Deque<Token> linkedList = new LinkedList();
        for (Token token = endBinaryShift(bArr.length).token; token != null; token = token.getPrevious()) {
            linkedList.addFirst(token);
        }
        BitArray bitArray = new BitArray();
        for (Token appendTo : linkedList) {
            appendTo.appendTo(bitArray, bArr);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{HighLevelEncoder.MODE_NAMES[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount)});
    }
}
