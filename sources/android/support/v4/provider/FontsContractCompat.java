package android.support.v4.provider;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontsContractCompat {
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    private static final Comparator<byte[]> sByteArrayComparator = new C02215();
    static final Object sLock = new Object();
    @GuardedBy("sLock")
    static final SimpleArrayMap<String, ArrayList<ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);

    /* renamed from: android.support.v4.provider.FontsContractCompat$5 */
    static class C02215 implements Comparator<byte[]> {
        C02215() {
        }

        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    }

    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontFamilyResult(int i, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i;
            this.mWeight = i2;
            this.mItalic = z;
            this.mResultCode = i3;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        @RestrictTo({Scope.LIBRARY_GROUP})
        public static final int RESULT_OK = 0;

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRequestFailed(int i) {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }
    }

    private static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(@Nullable Typeface typeface, int i) {
            this.mTypeface = typeface;
            this.mResult = i;
        }
    }

    @android.support.annotation.VisibleForTesting
    @android.support.annotation.NonNull
    static android.support.v4.provider.FontsContractCompat.FontInfo[] getFontFromProvider(android.content.Context r20, android.support.v4.provider.FontRequest r21, java.lang.String r22, android.os.CancellationSignal r23) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:41:0x014a in {4, 5, 14, 15, 17, 18, 20, 21, 23, 24, 28, 29, 31, 32, 34, 36, 39, 40} preds:[]
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
        r0 = r22;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = new android.net.Uri$Builder;
        r2.<init>();
        r3 = "content";
        r2 = r2.scheme(r3);
        r2 = r2.authority(r0);
        r2 = r2.build();
        r3 = new android.net.Uri$Builder;
        r3.<init>();
        r4 = "content";
        r3 = r3.scheme(r4);
        r0 = r3.authority(r0);
        r3 = "file";
        r0 = r0.appendPath(r3);
        r0 = r0.build();
        r10 = 0;
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x0143 }
        r4 = 16;	 Catch:{ all -> 0x0143 }
        r5 = 6;	 Catch:{ all -> 0x0143 }
        r6 = 5;	 Catch:{ all -> 0x0143 }
        r7 = 4;	 Catch:{ all -> 0x0143 }
        r8 = 3;	 Catch:{ all -> 0x0143 }
        r9 = 2;	 Catch:{ all -> 0x0143 }
        r11 = 7;	 Catch:{ all -> 0x0143 }
        r12 = 1;	 Catch:{ all -> 0x0143 }
        r13 = 0;	 Catch:{ all -> 0x0143 }
        if (r3 <= r4) goto L_0x0079;	 Catch:{ all -> 0x0143 }
    L_0x0042:
        r3 = r20.getContentResolver();	 Catch:{ all -> 0x0143 }
        r11 = new java.lang.String[r11];	 Catch:{ all -> 0x0143 }
        r4 = "_id";	 Catch:{ all -> 0x0143 }
        r11[r13] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "file_id";	 Catch:{ all -> 0x0143 }
        r11[r12] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_ttc_index";	 Catch:{ all -> 0x0143 }
        r11[r9] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_variation_settings";	 Catch:{ all -> 0x0143 }
        r11[r8] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_weight";	 Catch:{ all -> 0x0143 }
        r11[r7] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_italic";	 Catch:{ all -> 0x0143 }
        r11[r6] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "result_code";	 Catch:{ all -> 0x0143 }
        r11[r5] = r4;	 Catch:{ all -> 0x0143 }
        r6 = "query = ?";	 Catch:{ all -> 0x0143 }
        r7 = new java.lang.String[r12];	 Catch:{ all -> 0x0143 }
        r4 = r21.getQuery();	 Catch:{ all -> 0x0143 }
        r7[r13] = r4;	 Catch:{ all -> 0x0143 }
        r8 = 0;	 Catch:{ all -> 0x0143 }
        r4 = r2;	 Catch:{ all -> 0x0143 }
        r5 = r11;	 Catch:{ all -> 0x0143 }
        r9 = r23;	 Catch:{ all -> 0x0143 }
        r3 = r3.query(r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x0143 }
        r10 = r3;	 Catch:{ all -> 0x0143 }
        goto L_0x00ad;	 Catch:{ all -> 0x0143 }
    L_0x0079:
        r3 = r20.getContentResolver();	 Catch:{ all -> 0x0143 }
        r11 = new java.lang.String[r11];	 Catch:{ all -> 0x0143 }
        r4 = "_id";	 Catch:{ all -> 0x0143 }
        r11[r13] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "file_id";	 Catch:{ all -> 0x0143 }
        r11[r12] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_ttc_index";	 Catch:{ all -> 0x0143 }
        r11[r9] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_variation_settings";	 Catch:{ all -> 0x0143 }
        r11[r8] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_weight";	 Catch:{ all -> 0x0143 }
        r11[r7] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "font_italic";	 Catch:{ all -> 0x0143 }
        r11[r6] = r4;	 Catch:{ all -> 0x0143 }
        r4 = "result_code";	 Catch:{ all -> 0x0143 }
        r11[r5] = r4;	 Catch:{ all -> 0x0143 }
        r6 = "query = ?";	 Catch:{ all -> 0x0143 }
        r7 = new java.lang.String[r12];	 Catch:{ all -> 0x0143 }
        r4 = r21.getQuery();	 Catch:{ all -> 0x0143 }
        r7[r13] = r4;	 Catch:{ all -> 0x0143 }
        r8 = 0;	 Catch:{ all -> 0x0143 }
        r4 = r2;	 Catch:{ all -> 0x0143 }
        r5 = r11;	 Catch:{ all -> 0x0143 }
        r3 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0143 }
        r10 = r3;	 Catch:{ all -> 0x0143 }
    L_0x00ad:
        if (r10 == 0) goto L_0x0135;	 Catch:{ all -> 0x0143 }
    L_0x00af:
        r3 = r10.getCount();	 Catch:{ all -> 0x0143 }
        if (r3 <= 0) goto L_0x0135;	 Catch:{ all -> 0x0143 }
    L_0x00b5:
        r1 = "result_code";	 Catch:{ all -> 0x0143 }
        r1 = r10.getColumnIndex(r1);	 Catch:{ all -> 0x0143 }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x0143 }
        r3.<init>();	 Catch:{ all -> 0x0143 }
        r4 = "_id";	 Catch:{ all -> 0x0143 }
        r4 = r10.getColumnIndex(r4);	 Catch:{ all -> 0x0143 }
        r5 = "file_id";	 Catch:{ all -> 0x0143 }
        r5 = r10.getColumnIndex(r5);	 Catch:{ all -> 0x0143 }
        r6 = "font_ttc_index";	 Catch:{ all -> 0x0143 }
        r6 = r10.getColumnIndex(r6);	 Catch:{ all -> 0x0143 }
        r7 = "font_weight";	 Catch:{ all -> 0x0143 }
        r7 = r10.getColumnIndex(r7);	 Catch:{ all -> 0x0143 }
        r8 = "font_italic";	 Catch:{ all -> 0x0143 }
        r8 = r10.getColumnIndex(r8);	 Catch:{ all -> 0x0143 }
    L_0x00de:
        r9 = r10.moveToNext();	 Catch:{ all -> 0x0143 }
        if (r9 == 0) goto L_0x0134;	 Catch:{ all -> 0x0143 }
    L_0x00e4:
        r9 = -1;	 Catch:{ all -> 0x0143 }
        if (r1 == r9) goto L_0x00ee;	 Catch:{ all -> 0x0143 }
    L_0x00e7:
        r11 = r10.getInt(r1);	 Catch:{ all -> 0x0143 }
        r19 = r11;	 Catch:{ all -> 0x0143 }
        goto L_0x00f0;	 Catch:{ all -> 0x0143 }
    L_0x00ee:
        r19 = 0;	 Catch:{ all -> 0x0143 }
    L_0x00f0:
        if (r6 == r9) goto L_0x00f9;	 Catch:{ all -> 0x0143 }
    L_0x00f2:
        r11 = r10.getInt(r6);	 Catch:{ all -> 0x0143 }
        r16 = r11;	 Catch:{ all -> 0x0143 }
        goto L_0x00fb;	 Catch:{ all -> 0x0143 }
    L_0x00f9:
        r16 = 0;	 Catch:{ all -> 0x0143 }
    L_0x00fb:
        if (r5 != r9) goto L_0x0107;	 Catch:{ all -> 0x0143 }
    L_0x00fd:
        r14 = r10.getLong(r4);	 Catch:{ all -> 0x0143 }
        r11 = android.content.ContentUris.withAppendedId(r2, r14);	 Catch:{ all -> 0x0143 }
        r15 = r11;	 Catch:{ all -> 0x0143 }
        goto L_0x0110;	 Catch:{ all -> 0x0143 }
    L_0x0107:
        r14 = r10.getLong(r5);	 Catch:{ all -> 0x0143 }
        r11 = android.content.ContentUris.withAppendedId(r0, r14);	 Catch:{ all -> 0x0143 }
        r15 = r11;	 Catch:{ all -> 0x0143 }
    L_0x0110:
        if (r7 == r9) goto L_0x0119;	 Catch:{ all -> 0x0143 }
    L_0x0112:
        r11 = r10.getInt(r7);	 Catch:{ all -> 0x0143 }
        r17 = r11;	 Catch:{ all -> 0x0143 }
        goto L_0x011d;	 Catch:{ all -> 0x0143 }
    L_0x0119:
        r11 = 400; // 0x190 float:5.6E-43 double:1.976E-321;	 Catch:{ all -> 0x0143 }
        r17 = 400; // 0x190 float:5.6E-43 double:1.976E-321;	 Catch:{ all -> 0x0143 }
    L_0x011d:
        if (r8 == r9) goto L_0x0128;	 Catch:{ all -> 0x0143 }
    L_0x011f:
        r9 = r10.getInt(r8);	 Catch:{ all -> 0x0143 }
        if (r9 != r12) goto L_0x0128;	 Catch:{ all -> 0x0143 }
    L_0x0125:
        r18 = 1;	 Catch:{ all -> 0x0143 }
        goto L_0x012a;	 Catch:{ all -> 0x0143 }
    L_0x0128:
        r18 = 0;	 Catch:{ all -> 0x0143 }
    L_0x012a:
        r9 = new android.support.v4.provider.FontsContractCompat$FontInfo;	 Catch:{ all -> 0x0143 }
        r14 = r9;	 Catch:{ all -> 0x0143 }
        r14.<init>(r15, r16, r17, r18, r19);	 Catch:{ all -> 0x0143 }
        r3.add(r9);	 Catch:{ all -> 0x0143 }
        goto L_0x00de;
    L_0x0134:
        r1 = r3;
    L_0x0135:
        if (r10 == 0) goto L_0x013a;
    L_0x0137:
        r10.close();
    L_0x013a:
        r0 = new android.support.v4.provider.FontsContractCompat.FontInfo[r13];
        r0 = r1.toArray(r0);
        r0 = (android.support.v4.provider.FontsContractCompat.FontInfo[]) r0;
        return r0;
    L_0x0143:
        r0 = move-exception;
        if (r10 == 0) goto L_0x0149;
    L_0x0146:
        r10.close();
    L_0x0149:
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontFromProvider(android.content.Context, android.support.v4.provider.FontRequest, java.lang.String, android.os.CancellationSignal):android.support.v4.provider.FontsContractCompat$FontInfo[]");
    }

    @android.support.annotation.VisibleForTesting
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    @android.support.annotation.Nullable
    public static android.content.pm.ProviderInfo getProvider(@android.support.annotation.NonNull android.content.pm.PackageManager r5, @android.support.annotation.NonNull android.support.v4.provider.FontRequest r6, @android.support.annotation.Nullable android.content.res.Resources r7) throws android.content.pm.PackageManager.NameNotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:17:0x008a in {9, 10, 12, 14, 16} preds:[]
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
        r0 = r6.getProviderAuthority();
        r1 = 0;
        r2 = r5.resolveContentProvider(r0, r1);
        if (r2 == 0) goto L_0x0073;
    L_0x000b:
        r3 = r2.packageName;
        r4 = r6.getProviderPackage();
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0050;
    L_0x0017:
        r0 = r2.packageName;
        r3 = 64;
        r5 = r5.getPackageInfo(r0, r3);
        r5 = r5.signatures;
        r5 = convertToByteArrayList(r5);
        r0 = sByteArrayComparator;
        java.util.Collections.sort(r5, r0);
        r6 = getCertificates(r6, r7);
    L_0x002e:
        r7 = r6.size();
        if (r1 >= r7) goto L_0x004e;
    L_0x0034:
        r7 = new java.util.ArrayList;
        r0 = r6.get(r1);
        r0 = (java.util.Collection) r0;
        r7.<init>(r0);
        r0 = sByteArrayComparator;
        java.util.Collections.sort(r7, r0);
        r7 = equalsByteArrayList(r5, r7);
        if (r7 == 0) goto L_0x004b;
    L_0x004a:
        return r2;
    L_0x004b:
        r1 = r1 + 1;
        goto L_0x002e;
    L_0x004e:
        r5 = 0;
        return r5;
    L_0x0050:
        r5 = new android.content.pm.PackageManager$NameNotFoundException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r1 = "Found content provider ";
        r7.append(r1);
        r7.append(r0);
        r0 = ", but package was not ";
        r7.append(r0);
        r6 = r6.getProviderPackage();
        r7.append(r6);
        r6 = r7.toString();
        r5.<init>(r6);
        throw r5;
    L_0x0073:
        r5 = new android.content.pm.PackageManager$NameNotFoundException;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "No package found for authority: ";
        r6.append(r7);
        r6.append(r0);
        r6 = r6.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getProvider(android.content.pm.PackageManager, android.support.v4.provider.FontRequest, android.content.res.Resources):android.content.pm.ProviderInfo");
    }

    private FontsContractCompat() {
    }

    @android.support.annotation.NonNull
    static android.support.v4.provider.FontsContractCompat.TypefaceResult getFontInternal(android.content.Context r3, android.support.v4.provider.FontRequest r4, int r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = 0;
        r4 = fetchFonts(r3, r0, r4);	 Catch:{ NameNotFoundException -> 0x002b }
        r1 = r4.getStatusCode();
        r2 = -3;
        if (r1 != 0) goto L_0x001d;
    L_0x000c:
        r4 = r4.getFonts();
        r3 = android.support.v4.graphics.TypefaceCompat.createFromFontInfo(r3, r0, r4, r5);
        r4 = new android.support.v4.provider.FontsContractCompat$TypefaceResult;
        if (r3 == 0) goto L_0x0019;
    L_0x0018:
        r2 = 0;
    L_0x0019:
        r4.<init>(r3, r2);
        return r4;
    L_0x001d:
        r3 = r4.getStatusCode();
        r4 = 1;
        if (r3 != r4) goto L_0x0025;
    L_0x0024:
        r2 = -2;
    L_0x0025:
        r3 = new android.support.v4.provider.FontsContractCompat$TypefaceResult;
        r3.<init>(r0, r2);
        return r3;
    L_0x002b:
        r3 = new android.support.v4.provider.FontsContractCompat$TypefaceResult;
        r4 = -1;
        r3.<init>(r0, r4);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontInternal(android.content.Context, android.support.v4.provider.FontRequest, int):android.support.v4.provider.FontsContractCompat$TypefaceResult");
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static android.graphics.Typeface getFontSync(final android.content.Context r2, final android.support.v4.provider.FontRequest r3, @android.support.annotation.Nullable final android.support.v4.content.res.ResourcesCompat.FontCallback r4, @android.support.annotation.Nullable final android.os.Handler r5, boolean r6, int r7, final int r8) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r3.getIdentifier();
        r0.append(r1);
        r1 = "-";
        r0.append(r1);
        r0.append(r8);
        r0 = r0.toString();
        r1 = sTypefaceCache;
        r1 = r1.get(r0);
        r1 = (android.graphics.Typeface) r1;
        if (r1 == 0) goto L_0x0028;
    L_0x0022:
        if (r4 == 0) goto L_0x0027;
    L_0x0024:
        r4.onFontRetrieved(r1);
    L_0x0027:
        return r1;
    L_0x0028:
        if (r6 == 0) goto L_0x0045;
    L_0x002a:
        r1 = -1;
        if (r7 != r1) goto L_0x0045;
    L_0x002d:
        r2 = getFontInternal(r2, r3, r8);
        if (r4 == 0) goto L_0x0042;
    L_0x0033:
        r3 = r2.mResult;
        if (r3 != 0) goto L_0x003d;
    L_0x0037:
        r3 = r2.mTypeface;
        r4.callbackSuccessAsync(r3, r5);
        goto L_0x0042;
    L_0x003d:
        r3 = r2.mResult;
        r4.callbackFailAsync(r3, r5);
    L_0x0042:
        r2 = r2.mTypeface;
        return r2;
    L_0x0045:
        r1 = new android.support.v4.provider.FontsContractCompat$1;
        r1.<init>(r2, r3, r8, r0);
        r2 = 0;
        if (r6 == 0) goto L_0x0059;
    L_0x004d:
        r3 = sBackgroundThread;	 Catch:{ InterruptedException -> 0x0058 }
        r3 = r3.postAndWait(r1, r7);	 Catch:{ InterruptedException -> 0x0058 }
        r3 = (android.support.v4.provider.FontsContractCompat.TypefaceResult) r3;	 Catch:{ InterruptedException -> 0x0058 }
        r2 = r3.mTypeface;	 Catch:{ InterruptedException -> 0x0058 }
        return r2;
    L_0x0058:
        return r2;
    L_0x0059:
        if (r4 != 0) goto L_0x005d;
    L_0x005b:
        r3 = r2;
        goto L_0x0062;
    L_0x005d:
        r3 = new android.support.v4.provider.FontsContractCompat$2;
        r3.<init>(r4, r5);
    L_0x0062:
        r4 = sLock;
        monitor-enter(r4);
        r5 = sPendingReplies;	 Catch:{ all -> 0x0097 }
        r5 = r5.containsKey(r0);	 Catch:{ all -> 0x0097 }
        if (r5 == 0) goto L_0x007c;	 Catch:{ all -> 0x0097 }
    L_0x006d:
        if (r3 == 0) goto L_0x007a;	 Catch:{ all -> 0x0097 }
    L_0x006f:
        r5 = sPendingReplies;	 Catch:{ all -> 0x0097 }
        r5 = r5.get(r0);	 Catch:{ all -> 0x0097 }
        r5 = (java.util.ArrayList) r5;	 Catch:{ all -> 0x0097 }
        r5.add(r3);	 Catch:{ all -> 0x0097 }
    L_0x007a:
        monitor-exit(r4);	 Catch:{ all -> 0x0097 }
        return r2;	 Catch:{ all -> 0x0097 }
    L_0x007c:
        if (r3 == 0) goto L_0x008b;	 Catch:{ all -> 0x0097 }
    L_0x007e:
        r5 = new java.util.ArrayList;	 Catch:{ all -> 0x0097 }
        r5.<init>();	 Catch:{ all -> 0x0097 }
        r5.add(r3);	 Catch:{ all -> 0x0097 }
        r3 = sPendingReplies;	 Catch:{ all -> 0x0097 }
        r3.put(r0, r5);	 Catch:{ all -> 0x0097 }
    L_0x008b:
        monitor-exit(r4);	 Catch:{ all -> 0x0097 }
        r3 = sBackgroundThread;
        r4 = new android.support.v4.provider.FontsContractCompat$3;
        r4.<init>(r0);
        r3.postAndReply(r1, r4);
        return r2;
    L_0x0097:
        r2 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0097 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.getFontSync(android.content.Context, android.support.v4.provider.FontRequest, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    public static void requestFont(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull final FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        final Handler handler2 = new Handler();
        handler.post(new Runnable() {

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$1 */
            class C02111 implements Runnable {
                C02111() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-1);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$2 */
            class C02122 implements Runnable {
                C02122() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-2);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$3 */
            class C02133 implements Runnable {
                C02133() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$4 */
            class C02144 implements Runnable {
                C02144() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$5 */
            class C02155 implements Runnable {
                C02155() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(1);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$6 */
            class C02166 implements Runnable {
                C02166() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$8 */
            class C02188 implements Runnable {
                C02188() {
                }

                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            public void run() {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
                /*
                r6 = this;
                r0 = r2;	 Catch:{ NameNotFoundException -> 0x0095 }
                r1 = r3;	 Catch:{ NameNotFoundException -> 0x0095 }
                r2 = 0;	 Catch:{ NameNotFoundException -> 0x0095 }
                r0 = android.support.v4.provider.FontsContractCompat.fetchFonts(r0, r2, r1);	 Catch:{ NameNotFoundException -> 0x0095 }
                r1 = r0.getStatusCode();
                if (r1 == 0) goto L_0x0037;
            L_0x000f:
                r0 = r0.getStatusCode();
                switch(r0) {
                    case 1: goto L_0x002c;
                    case 2: goto L_0x0021;
                    default: goto L_0x0016;
                };
            L_0x0016:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$4;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x0021:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$3;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x002c:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$2;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x0037:
                r0 = r0.getFonts();
                if (r0 == 0) goto L_0x008a;
            L_0x003d:
                r1 = r0.length;
                if (r1 != 0) goto L_0x0041;
            L_0x0040:
                goto L_0x008a;
            L_0x0041:
                r1 = r0.length;
                r3 = 0;
            L_0x0043:
                if (r3 >= r1) goto L_0x006c;
            L_0x0045:
                r4 = r0[r3];
                r5 = r4.getResultCode();
                if (r5 == 0) goto L_0x0069;
            L_0x004d:
                r0 = r4.getResultCode();
                if (r0 >= 0) goto L_0x005e;
            L_0x0053:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$6;
                r1.<init>();
                r0.post(r1);
                goto L_0x0068;
            L_0x005e:
                r1 = r0;
                r2 = new android.support.v4.provider.FontsContractCompat$4$7;
                r2.<init>(r0);
                r1.post(r2);
            L_0x0068:
                return;
            L_0x0069:
                r3 = r3 + 1;
                goto L_0x0043;
            L_0x006c:
                r1 = r2;
                r0 = android.support.v4.provider.FontsContractCompat.buildTypeface(r1, r2, r0);
                if (r0 != 0) goto L_0x007f;
            L_0x0074:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$8;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x007f:
                r1 = r0;
                r2 = new android.support.v4.provider.FontsContractCompat$4$9;
                r2.<init>(r0);
                r1.post(r2);
                return;
            L_0x008a:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$5;
                r1.<init>();
                r0.post(r1);
                return;
            L_0x0095:
                r0 = r0;
                r1 = new android.support.v4.provider.FontsContractCompat$4$1;
                r1.<init>();
                r0.post(r1);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.FontsContractCompat.4.run():void");
            }
        });
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @RequiresApi(19)
    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        Map hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(null, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        List<byte[]> arrayList = new ArrayList();
        for (Signature toByteArray : signatureArr) {
            arrayList.add(toByteArray.toByteArray());
        }
        return arrayList;
    }
}
