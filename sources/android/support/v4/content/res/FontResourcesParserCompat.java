package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.compat.C0014R;
import android.support.v4.provider.FontRequest;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class FontResourcesParserCompat {
    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FetchStrategy {
    }

    public static final class FontFileResourceEntry {
        @NonNull
        private final String mFileName;
        private boolean mItalic;
        private int mResourceId;
        private int mTtcIndex;
        private String mVariationSettings;
        private int mWeight;

        public FontFileResourceEntry(@NonNull String str, int i, boolean z, @Nullable String str2, int i2, int i3) {
            this.mFileName = str;
            this.mWeight = i;
            this.mItalic = z;
            this.mVariationSettings = str2;
            this.mTtcIndex = i2;
            this.mResourceId = i3;
        }

        @NonNull
        public String getFileName() {
            return this.mFileName;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        @Nullable
        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public int getResourceId() {
            return this.mResourceId;
        }
    }

    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        @NonNull
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(@NonNull FontFileResourceEntry[] fontFileResourceEntryArr) {
            this.mEntries = fontFileResourceEntryArr;
        }

        @NonNull
        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        @NonNull
        private final FontRequest mRequest;
        private final int mStrategy;
        private final int mTimeoutMs;

        public ProviderResourceEntry(@NonNull FontRequest fontRequest, int i, int i2) {
            this.mRequest = fontRequest;
            this.mStrategy = i;
            this.mTimeoutMs = i2;
        }

        @NonNull
        public FontRequest getRequest() {
            return this.mRequest;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    @android.support.annotation.Nullable
    public static android.support.v4.content.res.FontResourcesParserCompat.FamilyResourceEntry parse(org.xmlpull.v1.XmlPullParser r3, android.content.res.Resources r4) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x001a in {4, 7, 9} preds:[]
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
    L_0x0000:
        r0 = r3.next();
        r1 = 2;
        if (r0 == r1) goto L_0x000b;
    L_0x0007:
        r2 = 1;
        if (r0 == r2) goto L_0x000b;
    L_0x000a:
        goto L_0x0000;
    L_0x000b:
        if (r0 != r1) goto L_0x0012;
    L_0x000d:
        r3 = readFamilies(r3, r4);
        return r3;
    L_0x0012:
        r3 = new org.xmlpull.v1.XmlPullParserException;
        r4 = "No start tag found";
        r3.<init>(r4);
        throw r3;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.FontResourcesParserCompat.parse(org.xmlpull.v1.XmlPullParser, android.content.res.Resources):android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry");
    }

    public static java.util.List<java.util.List<byte[]>> readCerts(android.content.res.Resources r5, @android.support.annotation.ArrayRes int r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:26:0x0055 in {2, 9, 18, 19, 20, 22, 25} preds:[]
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
        if (r6 != 0) goto L_0x0007;
    L_0x0002:
        r5 = java.util.Collections.emptyList();
        return r5;
    L_0x0007:
        r0 = r5.obtainTypedArray(r6);
        r1 = r0.length();	 Catch:{ all -> 0x0050 }
        if (r1 != 0) goto L_0x0019;	 Catch:{ all -> 0x0050 }
    L_0x0011:
        r5 = java.util.Collections.emptyList();	 Catch:{ all -> 0x0050 }
        r0.recycle();
        return r5;
    L_0x0019:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0050 }
        r1.<init>();	 Catch:{ all -> 0x0050 }
        r2 = 0;	 Catch:{ all -> 0x0050 }
        r3 = getType(r0, r2);	 Catch:{ all -> 0x0050 }
        r4 = 1;	 Catch:{ all -> 0x0050 }
        if (r3 != r4) goto L_0x0041;	 Catch:{ all -> 0x0050 }
    L_0x0026:
        r6 = 0;	 Catch:{ all -> 0x0050 }
    L_0x0027:
        r3 = r0.length();	 Catch:{ all -> 0x0050 }
        if (r6 >= r3) goto L_0x004c;	 Catch:{ all -> 0x0050 }
    L_0x002d:
        r3 = r0.getResourceId(r6, r2);	 Catch:{ all -> 0x0050 }
        if (r3 == 0) goto L_0x003e;	 Catch:{ all -> 0x0050 }
    L_0x0033:
        r3 = r5.getStringArray(r3);	 Catch:{ all -> 0x0050 }
        r3 = toByteArrayList(r3);	 Catch:{ all -> 0x0050 }
        r1.add(r3);	 Catch:{ all -> 0x0050 }
    L_0x003e:
        r6 = r6 + 1;	 Catch:{ all -> 0x0050 }
        goto L_0x0027;	 Catch:{ all -> 0x0050 }
    L_0x0041:
        r5 = r5.getStringArray(r6);	 Catch:{ all -> 0x0050 }
        r5 = toByteArrayList(r5);	 Catch:{ all -> 0x0050 }
        r1.add(r5);	 Catch:{ all -> 0x0050 }
    L_0x004c:
        r0.recycle();
        return r1;
    L_0x0050:
        r5 = move-exception;
        r0.recycle();
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.FontResourcesParserCompat.readCerts(android.content.res.Resources, int):java.util.List<java.util.List<byte[]>>");
    }

    @Nullable
    private static FamilyResourceEntry readFamilies(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, null, "font-family");
        if (xmlPullParser.getName().equals("font-family")) {
            return readFamily(xmlPullParser, resources);
        }
        skip(xmlPullParser);
        return null;
    }

    @Nullable
    private static FamilyResourceEntry readFamily(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0014R.styleable.FontFamily);
        String string = obtainAttributes.getString(C0014R.styleable.FontFamily_fontProviderAuthority);
        String string2 = obtainAttributes.getString(C0014R.styleable.FontFamily_fontProviderPackage);
        String string3 = obtainAttributes.getString(C0014R.styleable.FontFamily_fontProviderQuery);
        int resourceId = obtainAttributes.getResourceId(C0014R.styleable.FontFamily_fontProviderCerts, 0);
        int integer = obtainAttributes.getInteger(C0014R.styleable.FontFamily_fontProviderFetchStrategy, 1);
        int integer2 = obtainAttributes.getInteger(C0014R.styleable.FontFamily_fontProviderFetchTimeout, DEFAULT_TIMEOUT_MILLIS);
        obtainAttributes.recycle();
        if (string == null || string2 == null || string3 == null) {
            List arrayList = new ArrayList();
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("font")) {
                        arrayList.add(readFont(xmlPullParser, resources));
                    } else {
                        skip(xmlPullParser);
                    }
                }
            }
            if (arrayList.isEmpty() != null) {
                return null;
            }
            return new FontFamilyFilesResourceEntry((FontFileResourceEntry[]) arrayList.toArray(new FontFileResourceEntry[arrayList.size()]));
        }
        while (xmlPullParser.next() != 3) {
            skip(xmlPullParser);
        }
        return new ProviderResourceEntry(new FontRequest(string, string2, string3, readCerts(resources, resourceId)), integer, integer2);
    }

    private static int getType(TypedArray typedArray, int i) {
        if (VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
        return typedValue.type;
    }

    private static List<byte[]> toByteArrayList(String[] strArr) {
        List<byte[]> arrayList = new ArrayList();
        for (String decode : strArr) {
            arrayList.add(Base64.decode(decode, 0));
        }
        return arrayList;
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlPullParser, Resources resources) throws XmlPullParserException, IOException {
        resources = resources.obtainAttributes(Xml.asAttributeSet(xmlPullParser), C0014R.styleable.FontFamilyFont);
        int i = resources.getInt(resources.hasValue(C0014R.styleable.FontFamilyFont_fontWeight) ? C0014R.styleable.FontFamilyFont_fontWeight : C0014R.styleable.FontFamilyFont_android_fontWeight, NORMAL_WEIGHT);
        boolean z = 1 == resources.getInt(resources.hasValue(C0014R.styleable.FontFamilyFont_fontStyle) ? C0014R.styleable.FontFamilyFont_fontStyle : C0014R.styleable.FontFamilyFont_android_fontStyle, 0);
        int i2 = resources.hasValue(C0014R.styleable.FontFamilyFont_ttcIndex) ? C0014R.styleable.FontFamilyFont_ttcIndex : C0014R.styleable.FontFamilyFont_android_ttcIndex;
        String string = resources.getString(resources.hasValue(C0014R.styleable.FontFamilyFont_fontVariationSettings) ? C0014R.styleable.FontFamilyFont_fontVariationSettings : C0014R.styleable.FontFamilyFont_android_fontVariationSettings);
        int i3 = resources.getInt(i2, 0);
        i2 = resources.hasValue(C0014R.styleable.FontFamilyFont_font) ? C0014R.styleable.FontFamilyFont_font : C0014R.styleable.FontFamilyFont_android_font;
        int resourceId = resources.getResourceId(i2, 0);
        String string2 = resources.getString(i2);
        resources.recycle();
        while (xmlPullParser.next() != 3) {
            skip(xmlPullParser);
        }
        return new FontFileResourceEntry(string2, i, z, string, i3, resourceId);
    }

    private static void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
                default:
                    break;
            }
        }
    }

    private FontResourcesParserCompat() {
    }
}
