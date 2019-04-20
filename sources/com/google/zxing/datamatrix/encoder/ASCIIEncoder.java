package com.google.zxing.datamatrix.encoder;

final class ASCIIEncoder implements Encoder {
    public int getEncodingMode() {
        return 0;
    }

    ASCIIEncoder() {
    }

    public void encode(EncoderContext encoderContext) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(encoderContext.getMessage(), encoderContext.pos) >= 2) {
            encoderContext.writeCodeword(encodeASCIIDigits(encoderContext.getMessage().charAt(encoderContext.pos), encoderContext.getMessage().charAt(encoderContext.pos + 1)));
            encoderContext.pos += 2;
        } else {
            char currentChar = encoderContext.getCurrentChar();
            int lookAheadTest = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode());
            if (lookAheadTest != getEncodingMode()) {
                switch (lookAheadTest) {
                    case 1:
                        encoderContext.writeCodeword('æ');
                        encoderContext.signalEncoderChange(1);
                        return;
                    case 2:
                        encoderContext.writeCodeword('ï');
                        encoderContext.signalEncoderChange(2);
                        break;
                    case 3:
                        encoderContext.writeCodeword('î');
                        encoderContext.signalEncoderChange(3);
                        break;
                    case 4:
                        encoderContext.writeCodeword('ð');
                        encoderContext.signalEncoderChange(4);
                        break;
                    case 5:
                        encoderContext.writeCodeword('ç');
                        encoderContext.signalEncoderChange(5);
                        return;
                    default:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Illegal mode: ");
                        stringBuilder.append(lookAheadTest);
                        throw new IllegalStateException(stringBuilder.toString());
                }
            } else if (HighLevelEncoder.isExtendedASCII(currentChar)) {
                encoderContext.writeCodeword('ë');
                encoderContext.writeCodeword((char) ((currentChar - 128) + 1));
                encoderContext.pos++;
            } else {
                encoderContext.writeCodeword((char) (currentChar + 1));
                encoderContext.pos++;
            }
        }
    }

    private static char encodeASCIIDigits(char c, char c2) {
        if (HighLevelEncoder.isDigit(c) && HighLevelEncoder.isDigit(c2)) {
            return (char) ((((c - 48) * 10) + (c2 - 48)) + 130);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("not digits: ");
        stringBuilder.append(c);
        stringBuilder.append(c2);
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
