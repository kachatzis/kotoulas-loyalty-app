package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED = new byte[128];
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 38, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 35, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 43, (byte) 37, (byte) 42, (byte) 61, (byte) 94, (byte) 0, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = new byte[]{(byte) 59, (byte) 60, (byte) 62, (byte) 64, (byte) 91, (byte) 92, (byte) 93, (byte) 95, (byte) 96, (byte) 126, (byte) 33, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 10, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 34, (byte) 124, (byte) 42, (byte) 40, (byte) 41, (byte) 63, (byte) 123, (byte) 125, (byte) 39, (byte) 0};

    private static boolean isAlphaLower(char c) {
        if (c != ' ') {
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        return true;
    }

    private static boolean isAlphaUpper(char c) {
        if (c != ' ') {
            if (c < 'A' || c > 'Z') {
                return false;
            }
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isText(char c) {
        if (!(c == '\t' || c == '\n' || c == '\r')) {
            if (c < ' ' || c > '~') {
                return false;
            }
        }
        return true;
    }

    static {
        Arrays.fill(MIXED, (byte) -1);
        byte b = (byte) 0;
        byte b2 = (byte) 0;
        while (true) {
            byte[] bArr = TEXT_MIXED_RAW;
            if (b2 >= bArr.length) {
                break;
            }
            byte b3 = bArr[b2];
            if (b3 > (byte) 0) {
                MIXED[b3] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr2 = TEXT_PUNCTUATION_RAW;
            if (b < bArr2.length) {
                byte b4 = bArr2[b];
                if (b4 > (byte) 0) {
                    PUNCTUATION[b4] = b;
                }
                b = (byte) (b + 1);
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String encodeHighLevel(String str, Compaction compaction, Charset charset) throws WriterException {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        if (charset == null) {
            charset = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(charset)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name());
            if (characterSetECIByName != null) {
                encodingECI(characterSetECIByName.getValue(), stringBuilder);
            }
        }
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, stringBuilder, 0);
        } else if (compaction == Compaction.BYTE) {
            str = str.getBytes(charset);
            encodeBinary(str, 0, str.length, 1, stringBuilder);
        } else if (compaction == Compaction.NUMERIC) {
            stringBuilder.append('Ά');
            encodeNumeric(str, 0, length, stringBuilder);
        } else {
            compaction = null;
            int i = 0;
            int i2 = 0;
            while (compaction < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, compaction);
                if (determineConsecutiveDigitCount >= 13) {
                    stringBuilder.append('Ά');
                    encodeNumeric(str, compaction, determineConsecutiveDigitCount, stringBuilder);
                    compaction += determineConsecutiveDigitCount;
                    i = 0;
                    i2 = 2;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, compaction);
                    if (determineConsecutiveTextCount < 5) {
                        if (determineConsecutiveDigitCount != length) {
                            determineConsecutiveDigitCount = determineConsecutiveBinaryCount(str, compaction, charset);
                            if (determineConsecutiveDigitCount == 0) {
                                determineConsecutiveDigitCount = 1;
                            }
                            determineConsecutiveDigitCount += compaction;
                            compaction = str.substring(compaction, determineConsecutiveDigitCount).getBytes(charset);
                            if (compaction.length == 1 && i2 == 0) {
                                encodeBinary(compaction, 0, 1, 0, stringBuilder);
                            } else {
                                encodeBinary(compaction, 0, compaction.length, i2, stringBuilder);
                                i = 0;
                                i2 = 1;
                            }
                            compaction = determineConsecutiveDigitCount;
                        }
                    }
                    if (i2 != 0) {
                        stringBuilder.append('΄');
                        i = 0;
                        i2 = 0;
                    }
                    i = encodeText(str, compaction, determineConsecutiveTextCount, stringBuilder, i);
                    compaction += determineConsecutiveTextCount;
                }
            }
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
        r0 = r16;
        r1 = r18;
        r2 = r19;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r1);
        r4 = 2;
        r5 = 1;
        r6 = 0;
        r8 = r20;
        r7 = 0;
    L_0x0011:
        r9 = r17 + r7;
        r10 = r0.charAt(r9);
        r11 = 26;
        r12 = 32;
        r13 = 28;
        r14 = 27;
        r15 = 29;
        switch(r8) {
            case 0: goto L_0x00b7;
            case 1: goto L_0x007c;
            case 2: goto L_0x0034;
            default: goto L_0x0024;
        };
    L_0x0024:
        r9 = isPunctuation(r10);
        if (r9 == 0) goto L_0x0120;
    L_0x002a:
        r9 = PUNCTUATION;
        r9 = r9[r10];
        r9 = (char) r9;
        r3.append(r9);
        goto L_0x00ed;
    L_0x0034:
        r11 = isMixed(r10);
        if (r11 == 0) goto L_0x0044;
    L_0x003a:
        r9 = MIXED;
        r9 = r9[r10];
        r9 = (char) r9;
        r3.append(r9);
        goto L_0x00ed;
    L_0x0044:
        r11 = isAlphaUpper(r10);
        if (r11 == 0) goto L_0x004f;
    L_0x004a:
        r3.append(r13);
        r8 = 0;
        goto L_0x0011;
    L_0x004f:
        r11 = isAlphaLower(r10);
        if (r11 == 0) goto L_0x005a;
    L_0x0055:
        r3.append(r14);
        r8 = 1;
        goto L_0x0011;
    L_0x005a:
        r9 = r9 + 1;
        if (r9 >= r1) goto L_0x006f;
    L_0x005e:
        r9 = r0.charAt(r9);
        r9 = isPunctuation(r9);
        if (r9 == 0) goto L_0x006f;
    L_0x0068:
        r8 = 3;
        r9 = 25;
        r3.append(r9);
        goto L_0x0011;
    L_0x006f:
        r3.append(r15);
        r9 = PUNCTUATION;
        r9 = r9[r10];
        r9 = (char) r9;
        r3.append(r9);
        goto L_0x00ed;
    L_0x007c:
        r9 = isAlphaLower(r10);
        if (r9 == 0) goto L_0x008f;
    L_0x0082:
        if (r10 != r12) goto L_0x0088;
    L_0x0084:
        r3.append(r11);
        goto L_0x00ed;
    L_0x0088:
        r10 = r10 + -97;
        r9 = (char) r10;
        r3.append(r9);
        goto L_0x00ed;
    L_0x008f:
        r9 = isAlphaUpper(r10);
        if (r9 == 0) goto L_0x009f;
    L_0x0095:
        r3.append(r14);
        r10 = r10 + -65;
        r9 = (char) r10;
        r3.append(r9);
        goto L_0x00ed;
    L_0x009f:
        r9 = isMixed(r10);
        if (r9 == 0) goto L_0x00ab;
    L_0x00a5:
        r3.append(r13);
        r8 = 2;
        goto L_0x0011;
    L_0x00ab:
        r3.append(r15);
        r9 = PUNCTUATION;
        r9 = r9[r10];
        r9 = (char) r9;
        r3.append(r9);
        goto L_0x00ed;
    L_0x00b7:
        r9 = isAlphaUpper(r10);
        if (r9 == 0) goto L_0x00ca;
    L_0x00bd:
        if (r10 != r12) goto L_0x00c3;
    L_0x00bf:
        r3.append(r11);
        goto L_0x00ed;
    L_0x00c3:
        r10 = r10 + -65;
        r9 = (char) r10;
        r3.append(r9);
        goto L_0x00ed;
    L_0x00ca:
        r9 = isAlphaLower(r10);
        if (r9 == 0) goto L_0x00d6;
    L_0x00d0:
        r3.append(r14);
        r8 = 1;
        goto L_0x0011;
    L_0x00d6:
        r9 = isMixed(r10);
        if (r9 == 0) goto L_0x00e2;
    L_0x00dc:
        r3.append(r13);
        r8 = 2;
        goto L_0x0011;
    L_0x00e2:
        r3.append(r15);
        r9 = PUNCTUATION;
        r9 = r9[r10];
        r9 = (char) r9;
        r3.append(r9);
    L_0x00ed:
        r7 = r7 + 1;
        if (r7 < r1) goto L_0x0011;
    L_0x00f1:
        r0 = r3.length();
        r1 = 0;
        r7 = 0;
    L_0x00f7:
        if (r1 >= r0) goto L_0x0115;
    L_0x00f9:
        r9 = r1 % 2;
        if (r9 == 0) goto L_0x00ff;
    L_0x00fd:
        r9 = 1;
        goto L_0x0100;
    L_0x00ff:
        r9 = 0;
    L_0x0100:
        if (r9 == 0) goto L_0x010e;
    L_0x0102:
        r7 = r7 * 30;
        r9 = r3.charAt(r1);
        r7 = r7 + r9;
        r7 = (char) r7;
        r2.append(r7);
        goto L_0x0112;
    L_0x010e:
        r7 = r3.charAt(r1);
    L_0x0112:
        r1 = r1 + 1;
        goto L_0x00f7;
    L_0x0115:
        r0 = r0 % r4;
        if (r0 == 0) goto L_0x011f;
    L_0x0118:
        r7 = r7 * 30;
        r7 = r7 + r15;
        r0 = (char) r7;
        r2.append(r0);
    L_0x011f:
        return r8;
    L_0x0120:
        r3.append(r15);
        r8 = 0;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder stringBuilder) {
        int i4;
        int i5 = i2;
        StringBuilder stringBuilder2 = stringBuilder;
        if (i5 == 1 && i3 == 0) {
            stringBuilder2.append('Α');
        } else {
            if ((i5 % 6 == 0 ? 1 : null) != null) {
                stringBuilder2.append('Μ');
            } else {
                stringBuilder2.append('΅');
            }
        }
        if (i5 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i5) - i4 >= 6) {
                int i6;
                long j = 0;
                for (i6 = 0; i6 < 6; i6++) {
                    j = (j << 8) + ((long) (bArr[i4 + i6] & 255));
                }
                for (i6 = 0; i6 < 5; i6++) {
                    cArr[i6] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (i6 = cArr.length - 1; i6 >= 0; i6--) {
                    stringBuilder2.append(cArr[i6]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i5) {
            stringBuilder2.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2) {
            stringBuilder2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append('1');
            int i4 = i + i3;
            stringBuilder3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(stringBuilder3.toString());
            do {
                stringBuilder2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = stringBuilder2.length() - 1; length >= 0; length--) {
                stringBuilder.append(stringBuilder2.charAt(length));
            }
            i3 += min;
        }
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != '￿';
    }

    private static boolean isPunctuation(char c) {
        return PUNCTUATION[c] != '￿';
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    private static int determineConsecutiveTextCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(r2) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
            if (i3 >= 13) {
                return (i2 - i) - i3;
            }
            if (i3 <= 0) {
                if (!isText(charSequence.charAt(i2))) {
                    break;
                }
                i2++;
            }
        }
        return i2 - i;
    }

    private static int determineConsecutiveBinaryCount(String str, int i, Charset charset) throws WriterException {
        charset = charset.newEncoder();
        int length = str.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(r2)) {
                i3++;
                int i4 = i2 + i3;
                if (i4 >= length) {
                    break;
                }
                charAt = str.charAt(i4);
            }
            if (i3 >= 13) {
                return i2 - i;
            }
            charAt = str.charAt(i2);
            if (charset.canEncode(charAt)) {
                i2++;
            } else {
                i = new StringBuilder();
                i.append("Non-encodable character detected: ");
                i.append(charAt);
                i.append(" (Unicode: ");
                i.append(charAt);
                i.append(')');
                throw new WriterException(i.toString());
            }
        }
        return i2 - i;
    }

    private static void encodingECI(int i, StringBuilder stringBuilder) throws WriterException {
        if (i >= 0 && i < LATCH_TO_TEXT) {
            stringBuilder.append('Ο');
            stringBuilder.append((char) i);
        } else if (i < 810900) {
            stringBuilder.append('Ξ');
            stringBuilder.append((char) ((i / LATCH_TO_TEXT) - 1));
            stringBuilder.append((char) (i % LATCH_TO_TEXT));
        } else if (i < 811800) {
            stringBuilder.append('Ν');
            stringBuilder.append((char) (810900 - i));
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ECI number not in valid range from 0..811799, but was ");
            stringBuilder2.append(i);
            throw new WriterException(stringBuilder2.toString());
        }
    }
}
