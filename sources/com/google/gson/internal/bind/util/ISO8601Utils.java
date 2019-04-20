package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    private static int parseInt(java.lang.String r4, int r5, int r6) throws java.lang.NumberFormatException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x0070 in {8, 10, 11, 15, 17, 19, 21} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        if (r5 < 0) goto L_0x006a;
    L_0x0002:
        r0 = r4.length();
        if (r6 > r0) goto L_0x006a;
    L_0x0008:
        if (r5 > r6) goto L_0x006a;
    L_0x000a:
        r0 = 0;
        r1 = 10;
        if (r5 >= r6) goto L_0x0038;
    L_0x000f:
        r0 = r5 + 1;
        r2 = r4.charAt(r5);
        r2 = java.lang.Character.digit(r2, r1);
        if (r2 < 0) goto L_0x001d;
    L_0x001b:
        r2 = -r2;
        goto L_0x003a;
    L_0x001d:
        r0 = new java.lang.NumberFormatException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid number: ";
        r1.append(r2);
        r4 = r4.substring(r5, r6);
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
    L_0x0038:
        r0 = r5;
        r2 = 0;
    L_0x003a:
        if (r0 >= r6) goto L_0x0068;
    L_0x003c:
        r3 = r0 + 1;
        r0 = r4.charAt(r0);
        r0 = java.lang.Character.digit(r0, r1);
        if (r0 < 0) goto L_0x004d;
    L_0x0048:
        r2 = r2 * 10;
        r2 = r2 - r0;
        r0 = r3;
        goto L_0x003a;
    L_0x004d:
        r0 = new java.lang.NumberFormatException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid number: ";
        r1.append(r2);
        r4 = r4.substring(r5, r6);
        r1.append(r4);
        r4 = r1.toString();
        r0.<init>(r4);
        throw r0;
    L_0x0068:
        r4 = -r2;
        return r4;
    L_0x006a:
        r5 = new java.lang.NumberFormatException;
        r5.<init>(r4);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parseInt(java.lang.String, int, int):int");
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        Calendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder stringBuilder = new StringBuilder((19 + (z ? 4 : 0)) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(stringBuilder, gregorianCalendar.get(1), 4);
        date = 45;
        stringBuilder.append('-');
        padInt(stringBuilder, gregorianCalendar.get(2) + 1, 2);
        stringBuilder.append('-');
        padInt(stringBuilder, gregorianCalendar.get(5), 2);
        stringBuilder.append('T');
        padInt(stringBuilder, gregorianCalendar.get(11), 2);
        stringBuilder.append(':');
        padInt(stringBuilder, gregorianCalendar.get(12), 2);
        stringBuilder.append(':');
        padInt(stringBuilder, gregorianCalendar.get(13), 2);
        if (z) {
            stringBuilder.append(true);
            padInt(stringBuilder, gregorianCalendar.get(true), 3);
        }
        z = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (z) {
            timeZone = z / 60000;
            int abs = Math.abs(timeZone / 60);
            timeZone = Math.abs(timeZone % 60);
            if (z < false) {
                date = 43;
            }
            stringBuilder.append(date);
            padInt(stringBuilder, abs, 2);
            stringBuilder.append(':');
            padInt(stringBuilder, timeZone, 2);
        } else {
            stringBuilder.append('Z');
        }
        return stringBuilder.toString();
    }

    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        StringBuilder stringBuilder;
        Throwable e;
        String message;
        StringBuilder stringBuilder2;
        ParseException parseException;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        try {
            int index = parsePosition.getIndex();
            int i = index + 4;
            index = parseInt(str2, index, i);
            if (checkOffset(str2, i, '-')) {
                i++;
            }
            int i2 = i + 2;
            i = parseInt(str2, i, i2);
            if (checkOffset(str2, i2, '-')) {
                i2++;
            }
            int i3 = i2 + 2;
            i2 = parseInt(str2, i2, i3);
            boolean checkOffset = checkOffset(str2, i3, 'T');
            if (checkOffset || str.length() > i3) {
                int i4;
                int i5;
                int i6;
                int i7;
                if (checkOffset) {
                    i3++;
                    i4 = i3 + 2;
                    i3 = parseInt(str2, i3, i4);
                    if (checkOffset(str2, i4, ':')) {
                        i4++;
                    }
                    i5 = i4 + 2;
                    i4 = parseInt(str2, i4, i5);
                    if (checkOffset(str2, i5, ':')) {
                        i5++;
                    }
                    if (str.length() > i5) {
                        char charAt = str2.charAt(i5);
                        if (!(charAt == 'Z' || charAt == '+' || charAt == '-')) {
                            i6 = i5 + 2;
                            i5 = parseInt(str2, i5, i6);
                            i7 = 59;
                            if (i5 <= 59 || i5 >= 63) {
                                i7 = i5;
                            }
                            if (checkOffset(str2, i6, '.')) {
                                i6++;
                                i5 = indexOfNonDigit(str2, i6 + 1);
                                int min = Math.min(i5, i6 + 3);
                                int parseInt = parseInt(str2, i6, min);
                                switch (min - i6) {
                                    case 1:
                                        parseInt *= 100;
                                        break;
                                    case 2:
                                        parseInt *= 10;
                                        break;
                                    default:
                                        break;
                                }
                                i6 = parseInt;
                            } else {
                                i5 = i6;
                                i6 = 0;
                            }
                        }
                    }
                    i6 = 0;
                    i7 = 0;
                } else {
                    i5 = i3;
                    i3 = 0;
                    i4 = 0;
                    i6 = 0;
                    i7 = 0;
                }
                if (str.length() > i5) {
                    TimeZone timeZone;
                    char charAt2 = str2.charAt(i5);
                    if (charAt2 == 'Z') {
                        timeZone = TIMEZONE_UTC;
                        i5++;
                    } else {
                        StringBuilder stringBuilder3;
                        if (charAt2 != '+') {
                            if (charAt2 != '-') {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Invalid time zone indicator '");
                                stringBuilder.append(charAt2);
                                stringBuilder.append("'");
                                throw new IndexOutOfBoundsException(stringBuilder.toString());
                            }
                        }
                        String substring = str2.substring(i5);
                        if (substring.length() < 5) {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(substring);
                            stringBuilder3.append("00");
                            substring = stringBuilder3.toString();
                        }
                        i5 += substring.length();
                        if (!"+0000".equals(substring)) {
                            if (!"+00:00".equals(substring)) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("GMT");
                                stringBuilder3.append(substring);
                                substring = stringBuilder3.toString();
                                TimeZone timeZone2 = TimeZone.getTimeZone(substring);
                                String id = timeZone2.getID();
                                if (!id.equals(substring)) {
                                    if (!id.replace(":", "").equals(substring)) {
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append("Mismatching time zone indicator: ");
                                        stringBuilder.append(substring);
                                        stringBuilder.append(" given, resolves to ");
                                        stringBuilder.append(timeZone2.getID());
                                        throw new IndexOutOfBoundsException(stringBuilder.toString());
                                    }
                                }
                                timeZone = timeZone2;
                            }
                        }
                        timeZone = TIMEZONE_UTC;
                    }
                    Calendar gregorianCalendar = new GregorianCalendar(timeZone);
                    gregorianCalendar.setLenient(false);
                    gregorianCalendar.set(1, index);
                    gregorianCalendar.set(2, i - 1);
                    gregorianCalendar.set(5, i2);
                    gregorianCalendar.set(11, i3);
                    gregorianCalendar.set(12, i4);
                    gregorianCalendar.set(13, i7);
                    gregorianCalendar.set(14, i6);
                    parsePosition2.setIndex(i5);
                    return gregorianCalendar.getTime();
                }
                throw new IllegalArgumentException("No time zone indicator");
            }
            Calendar gregorianCalendar2 = new GregorianCalendar(index, i - 1, i2);
            parsePosition2.setIndex(i3);
            return gregorianCalendar2.getTime();
        } catch (IndexOutOfBoundsException e2) {
            e = e2;
            if (str2 == null) {
                str2 = null;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append('\"');
                stringBuilder.append(str2);
                stringBuilder.append("'");
                str2 = stringBuilder.toString();
            }
            message = e.getMessage();
            if (message == null || message.isEmpty()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("(");
                stringBuilder.append(e.getClass().getName());
                stringBuilder.append(")");
                message = stringBuilder.toString();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to parse date [");
            stringBuilder2.append(str2);
            stringBuilder2.append("]: ");
            stringBuilder2.append(message);
            parseException = new ParseException(stringBuilder2.toString(), parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        } catch (NumberFormatException e3) {
            e = e3;
            if (str2 == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append('\"');
                stringBuilder.append(str2);
                stringBuilder.append("'");
                str2 = stringBuilder.toString();
            } else {
                str2 = null;
            }
            message = e.getMessage();
            stringBuilder = new StringBuilder();
            stringBuilder.append("(");
            stringBuilder.append(e.getClass().getName());
            stringBuilder.append(")");
            message = stringBuilder.toString();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to parse date [");
            stringBuilder2.append(str2);
            stringBuilder2.append("]: ");
            stringBuilder2.append(message);
            parseException = new ParseException(stringBuilder2.toString(), parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        } catch (IllegalArgumentException e4) {
            e = e4;
            if (str2 == null) {
                str2 = null;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append('\"');
                stringBuilder.append(str2);
                stringBuilder.append("'");
                str2 = stringBuilder.toString();
            }
            message = e.getMessage();
            stringBuilder = new StringBuilder();
            stringBuilder.append("(");
            stringBuilder.append(e.getClass().getName());
            stringBuilder.append(")");
            message = stringBuilder.toString();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed to parse date [");
            stringBuilder2.append(str2);
            stringBuilder2.append("]: ");
            stringBuilder2.append(message);
            parseException = new ParseException(stringBuilder2.toString(), parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return (i >= str.length() || str.charAt(i) != c) ? null : true;
    }

    private static void padInt(StringBuilder stringBuilder, int i, int i2) {
        i = Integer.toString(i);
        for (i2 -= i.length(); i2 > 0; i2--) {
            stringBuilder.append('0');
        }
        stringBuilder.append(i);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt >= '0') {
                if (charAt <= '9') {
                    i++;
                }
            }
            return i;
        }
        return str.length();
    }
}
