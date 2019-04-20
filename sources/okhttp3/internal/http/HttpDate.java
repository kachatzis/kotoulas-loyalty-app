package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp3.internal.Util;

public final class HttpDate {
    private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    public static final long MAX_DATE = 253402300799999L;
    private static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT = new C04391();

    /* renamed from: okhttp3.internal.http.HttpDate$1 */
    class C04391 extends ThreadLocal<DateFormat> {
        C04391() {
        }

        protected DateFormat initialValue() {
            DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(Util.UTC);
            return simpleDateFormat;
        }
    }

    public static java.util.Date parse(java.lang.String r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:24:0x005f in {2, 5, 13, 17, 18, 20, 23} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = r9.length();
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = new java.text.ParsePosition;
        r2 = 0;
        r0.<init>(r2);
        r3 = STANDARD_DATE_FORMAT;
        r3 = r3.get();
        r3 = (java.text.DateFormat) r3;
        r3 = r3.parse(r9, r0);
        r4 = r0.getIndex();
        r5 = r9.length();
        if (r4 != r5) goto L_0x0025;
    L_0x0024:
        return r3;
    L_0x0025:
        r3 = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
        monitor-enter(r3);
        r4 = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;	 Catch:{ all -> 0x005c }
        r4 = r4.length;	 Catch:{ all -> 0x005c }
        r5 = 0;	 Catch:{ all -> 0x005c }
    L_0x002c:
        if (r5 >= r4) goto L_0x005a;	 Catch:{ all -> 0x005c }
    L_0x002e:
        r6 = BROWSER_COMPATIBLE_DATE_FORMATS;	 Catch:{ all -> 0x005c }
        r6 = r6[r5];	 Catch:{ all -> 0x005c }
        if (r6 != 0) goto L_0x0048;	 Catch:{ all -> 0x005c }
    L_0x0034:
        r6 = new java.text.SimpleDateFormat;	 Catch:{ all -> 0x005c }
        r7 = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;	 Catch:{ all -> 0x005c }
        r7 = r7[r5];	 Catch:{ all -> 0x005c }
        r8 = java.util.Locale.US;	 Catch:{ all -> 0x005c }
        r6.<init>(r7, r8);	 Catch:{ all -> 0x005c }
        r7 = okhttp3.internal.Util.UTC;	 Catch:{ all -> 0x005c }
        r6.setTimeZone(r7);	 Catch:{ all -> 0x005c }
        r7 = BROWSER_COMPATIBLE_DATE_FORMATS;	 Catch:{ all -> 0x005c }
        r7[r5] = r6;	 Catch:{ all -> 0x005c }
    L_0x0048:
        r0.setIndex(r2);	 Catch:{ all -> 0x005c }
        r6 = r6.parse(r9, r0);	 Catch:{ all -> 0x005c }
        r7 = r0.getIndex();	 Catch:{ all -> 0x005c }
        if (r7 == 0) goto L_0x0057;	 Catch:{ all -> 0x005c }
    L_0x0055:
        monitor-exit(r3);	 Catch:{ all -> 0x005c }
        return r6;	 Catch:{ all -> 0x005c }
    L_0x0057:
        r5 = r5 + 1;	 Catch:{ all -> 0x005c }
        goto L_0x002c;	 Catch:{ all -> 0x005c }
    L_0x005a:
        monitor-exit(r3);	 Catch:{ all -> 0x005c }
        return r1;	 Catch:{ all -> 0x005c }
    L_0x005c:
        r9 = move-exception;	 Catch:{ all -> 0x005c }
        monitor-exit(r3);	 Catch:{ all -> 0x005c }
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.HttpDate.parse(java.lang.String):java.util.Date");
    }

    public static String format(Date date) {
        return ((DateFormat) STANDARD_DATE_FORMAT.get()).format(date);
    }

    private HttpDate() {
    }
}
