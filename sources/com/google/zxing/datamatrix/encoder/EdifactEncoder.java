package com.google.zxing.datamatrix.encoder;

final class EdifactEncoder implements Encoder {
    public int getEncodingMode() {
        return 4;
    }

    EdifactEncoder() {
    }

    public void encode(EncoderContext encoderContext) {
        CharSequence stringBuilder = new StringBuilder();
        while (encoderContext.hasMoreCharacters()) {
            encodeChar(encoderContext.getCurrentChar(), stringBuilder);
            encoderContext.pos++;
            if (stringBuilder.length() >= 4) {
                encoderContext.writeCodewords(encodeToCodewords(stringBuilder, 0));
                stringBuilder.delete(0, 4);
                if (HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                    encoderContext.signalEncoderChange(0);
                    break;
                }
            }
        }
        stringBuilder.append('\u001f');
        handleEOD(encoderContext, stringBuilder);
    }

    private static void handleEOD(EncoderContext encoderContext, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length != 0) {
                int i = 1;
                if (length == 1) {
                    encoderContext.updateSymbolInfo();
                    int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
                    if (encoderContext.getRemainingCharacters() == 0 && dataCapacity <= 2) {
                        encoderContext.signalEncoderChange(0);
                        return;
                    }
                }
                if (length <= 4) {
                    length--;
                    charSequence = encodeToCodewords(charSequence, 0);
                    if ((encoderContext.hasMoreCharacters() ^ 1) == 0 || length > 2) {
                        i = 0;
                    }
                    if (length <= 2) {
                        encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + length);
                        if (encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount() >= 3) {
                            encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + charSequence.length());
                            i = 0;
                        }
                    }
                    if (i != 0) {
                        encoderContext.resetSymbolInfo();
                        encoderContext.pos -= length;
                    } else {
                        encoderContext.writeCodewords(charSequence);
                    }
                    encoderContext.signalEncoderChange(0);
                    return;
                }
                throw new IllegalStateException("Count must not exceed 4");
            }
        } finally {
            encoderContext.signalEncoderChange(0);
        }
    }

    private static void encodeChar(char c, StringBuilder stringBuilder) {
        if (c >= ' ' && c <= '?') {
            stringBuilder.append(c);
        } else if (c < '@' || c > '^') {
            HighLevelEncoder.illegalCharacter(c);
        } else {
            stringBuilder.append((char) (c - '@'));
        }
    }

    private static String encodeToCodewords(CharSequence charSequence, int i) {
        int length = charSequence.length() - i;
        if (length != 0) {
            char charAt = charSequence.charAt(i);
            int i2 = 0;
            int charAt2 = length >= 2 ? charSequence.charAt(i + 1) : 0;
            int charAt3 = length >= 3 ? charSequence.charAt(i + 2) : 0;
            if (length >= 4) {
                i2 = charSequence.charAt(i + 3);
            }
            charSequence = (((charAt << 18) + (charAt2 << 12)) + (charAt3 << 6)) + i2;
            i = (char) ((charSequence >> 16) & 255);
            charAt = (char) ((charSequence >> 8) & 255);
            charSequence = (char) (charSequence & 255);
            StringBuilder stringBuilder = new StringBuilder(3);
            stringBuilder.append(i);
            if (length >= 2) {
                stringBuilder.append(charAt);
            }
            if (length >= 3) {
                stringBuilder.append(charSequence);
            }
            return stringBuilder.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }
}
