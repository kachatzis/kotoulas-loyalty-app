package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

@RequiresApi(19)
class DocumentsContractApi19 {
    private static final int FLAG_VIRTUAL_DOCUMENT = 512;
    private static final String TAG = "DocumentFile";

    public static boolean isVirtual(Context context, Uri uri) {
        boolean z = false;
        if (!DocumentsContract.isDocumentUri(context, uri)) {
            return false;
        }
        if ((getFlags(context, uri) & 512) != 0) {
            z = true;
        }
        return z;
    }

    @Nullable
    public static String getName(Context context, Uri uri) {
        return queryForString(context, uri, "_display_name", null);
    }

    @Nullable
    private static String getRawType(Context context, Uri uri) {
        return queryForString(context, uri, "mime_type", null);
    }

    @Nullable
    public static String getType(Context context, Uri uri) {
        context = getRawType(context, uri);
        return "vnd.android.document/directory".equals(context) != null ? null : context;
    }

    public static long getFlags(Context context, Uri uri) {
        return queryForLong(context, uri, "flags", 0);
    }

    public static boolean isDirectory(Context context, Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }

    public static boolean isFile(Context context, Uri uri) {
        context = getRawType(context, uri);
        if ("vnd.android.document/directory".equals(context) == null) {
            if (TextUtils.isEmpty(context) == null) {
                return true;
            }
        }
        return null;
    }

    public static long lastModified(Context context, Uri uri) {
        return queryForLong(context, uri, "last_modified", 0);
    }

    public static long length(Context context, Uri uri) {
        return queryForLong(context, uri, "_size", 0);
    }

    public static boolean canRead(Context context, Uri uri) {
        return context.checkCallingOrSelfUriPermission(uri, 1) == 0 && TextUtils.isEmpty(getRawType(context, uri)) == null;
    }

    public static boolean canWrite(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) != 0) {
            return false;
        }
        CharSequence rawType = getRawType(context, uri);
        context = queryForInt(context, uri, "flags", 0);
        if (TextUtils.isEmpty(rawType) != null) {
            return false;
        }
        if ((context & 4) != null) {
            return true;
        }
        if ("vnd.android.document/directory".equals(rawType) != null && (context & 8) != null) {
            return true;
        }
        if (TextUtils.isEmpty(rawType) != null || (context & 2) == null) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean exists(android.content.Context r8, android.net.Uri r9) {
        /*
        r0 = r8.getContentResolver();
        r8 = 1;
        r6 = 0;
        r7 = 0;
        r2 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0023 }
        r1 = "document_id";
        r2[r6] = r1;	 Catch:{ Exception -> 0x0023 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r1 = r9;
        r7 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0023 }
        r9 = r7.getCount();	 Catch:{ Exception -> 0x0023 }
        if (r9 <= 0) goto L_0x001c;
    L_0x001b:
        goto L_0x001d;
    L_0x001c:
        r8 = 0;
    L_0x001d:
        closeQuietly(r7);
        return r8;
    L_0x0021:
        r8 = move-exception;
        goto L_0x003e;
    L_0x0023:
        r8 = move-exception;
        r9 = "DocumentFile";
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0021 }
        r0.<init>();	 Catch:{ all -> 0x0021 }
        r1 = "Failed query: ";
        r0.append(r1);	 Catch:{ all -> 0x0021 }
        r0.append(r8);	 Catch:{ all -> 0x0021 }
        r8 = r0.toString();	 Catch:{ all -> 0x0021 }
        android.util.Log.w(r9, r8);	 Catch:{ all -> 0x0021 }
        closeQuietly(r7);
        return r6;
    L_0x003e:
        closeQuietly(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.DocumentsContractApi19.exists(android.content.Context, android.net.Uri):boolean");
    }

    @Nullable
    private static String queryForString(Context context, Uri uri, String str, @Nullable String str2) {
        AutoCloseable autoCloseable = null;
        try {
            autoCloseable = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            if (autoCloseable.moveToFirst() == null || autoCloseable.isNull(0) != null) {
                closeQuietly(autoCloseable);
                return str2;
            }
            context = autoCloseable.getString(0);
            return context;
        } catch (Context context2) {
            uri = TAG;
            str = new StringBuilder();
            str.append("Failed query: ");
            str.append(context2);
            context2 = str.toString();
            Log.w(uri, context2);
            return str2;
        } finally {
            closeQuietly(autoCloseable);
        }
    }

    private static int queryForInt(Context context, Uri uri, String str, int i) {
        return (int) queryForLong(context, uri, str, (long) i);
    }

    private static long queryForLong(Context context, Uri uri, String str, long j) {
        AutoCloseable autoCloseable = null;
        try {
            autoCloseable = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            if (autoCloseable.moveToFirst() == null || autoCloseable.isNull(0) != null) {
                closeQuietly(autoCloseable);
                return j;
            }
            context = autoCloseable.getLong(0);
            return context;
        } catch (Context context2) {
            uri = TAG;
            str = new StringBuilder();
            str.append("Failed query: ");
            str.append(context2);
            context2 = str.toString();
            Log.w(uri, context2);
            return j;
        } finally {
            closeQuietly(autoCloseable);
        }
    }

    private static void closeQuietly(@android.support.annotation.Nullable java.lang.AutoCloseable r0) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        if (r0 == 0) goto L_0x0008;
    L_0x0002:
        r0.close();	 Catch:{ RuntimeException -> 0x0006, Exception -> 0x0008 }
        goto L_0x0008;
    L_0x0006:
        r0 = move-exception;
        throw r0;
    L_0x0008:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.DocumentsContractApi19.closeQuietly(java.lang.AutoCloseable):void");
    }

    private DocumentsContractApi19() {
    }
}
