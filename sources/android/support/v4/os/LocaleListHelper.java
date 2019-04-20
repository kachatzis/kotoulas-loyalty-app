package android.support.v4.os;

import android.os.Build.VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
final class LocaleListHelper {
    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultLocaleList = null;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
    @GuardedBy("sLock")
    private static Locale sLastDefaultLocale = null;
    @GuardedBy("sLock")
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;
    private static final Object sLock = new Object();
    private final Locale[] mList;
    @NonNull
    private final String mStringRepresentation;

    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    LocaleListHelper(@android.support.annotation.NonNull java.util.Locale r9, android.support.v4.os.LocaleListHelper r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:39:0x0098 in {4, 5, 11, 12, 13, 16, 17, 22, 25, 28, 33, 34, 36, 38} preds:[]
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
        r8 = this;
        r8.<init>();
        if (r9 == 0) goto L_0x0090;
    L_0x0005:
        r0 = 0;
        if (r10 != 0) goto L_0x000a;
    L_0x0008:
        r1 = 0;
        goto L_0x000d;
    L_0x000a:
        r1 = r10.mList;
        r1 = r1.length;
    L_0x000d:
        r2 = 0;
    L_0x000e:
        r3 = -1;
        if (r2 >= r1) goto L_0x001f;
    L_0x0011:
        r4 = r10.mList;
        r4 = r4[r2];
        r4 = r9.equals(r4);
        if (r4 == 0) goto L_0x001c;
    L_0x001b:
        goto L_0x0020;
    L_0x001c:
        r2 = r2 + 1;
        goto L_0x000e;
    L_0x001f:
        r2 = -1;
    L_0x0020:
        r4 = 1;
        if (r2 != r3) goto L_0x0025;
    L_0x0023:
        r5 = 1;
        goto L_0x0026;
    L_0x0025:
        r5 = 0;
    L_0x0026:
        r5 = r5 + r1;
        r6 = new java.util.Locale[r5];
        r9 = r9.clone();
        r9 = (java.util.Locale) r9;
        r6[r0] = r9;
        if (r2 != r3) goto L_0x0046;
    L_0x0033:
        r9 = 0;
    L_0x0034:
        if (r9 >= r1) goto L_0x006b;
    L_0x0036:
        r2 = r9 + 1;
        r3 = r10.mList;
        r9 = r3[r9];
        r9 = r9.clone();
        r9 = (java.util.Locale) r9;
        r6[r2] = r9;
        r9 = r2;
        goto L_0x0034;
    L_0x0046:
        r9 = 0;
    L_0x0047:
        if (r9 >= r2) goto L_0x0059;
    L_0x0049:
        r3 = r9 + 1;
        r7 = r10.mList;
        r9 = r7[r9];
        r9 = r9.clone();
        r9 = (java.util.Locale) r9;
        r6[r3] = r9;
        r9 = r3;
        goto L_0x0047;
    L_0x0059:
        r2 = r2 + r4;
    L_0x005a:
        if (r2 >= r1) goto L_0x006b;
    L_0x005c:
        r9 = r10.mList;
        r9 = r9[r2];
        r9 = r9.clone();
        r9 = (java.util.Locale) r9;
        r6[r2] = r9;
        r2 = r2 + 1;
        goto L_0x005a;
    L_0x006b:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
    L_0x0070:
        if (r0 >= r5) goto L_0x0087;
    L_0x0072:
        r10 = r6[r0];
        r10 = android.support.v4.os.LocaleHelper.toLanguageTag(r10);
        r9.append(r10);
        r10 = r5 + -1;
        if (r0 >= r10) goto L_0x0084;
    L_0x007f:
        r10 = 44;
        r9.append(r10);
    L_0x0084:
        r0 = r0 + 1;
        goto L_0x0070;
    L_0x0087:
        r8.mList = r6;
        r9 = r9.toString();
        r8.mStringRepresentation = r9;
        return;
    L_0x0090:
        r9 = new java.lang.NullPointerException;
        r10 = "topLocale is null";
        r9.<init>(r10);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.LocaleListHelper.<init>(java.util.Locale, android.support.v4.os.LocaleListHelper):void");
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale get(int i) {
        if (i >= 0) {
            Locale[] localeArr = this.mList;
            if (i < localeArr.length) {
                return localeArr[i];
            }
        }
        return 0;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    boolean isEmpty() {
        return this.mList.length == 0;
    }

    @IntRange(from = 0)
    @RestrictTo({Scope.LIBRARY_GROUP})
    int size() {
        return this.mList.length;
    }

    @IntRange(from = -1)
    @RestrictTo({Scope.LIBRARY_GROUP})
    int indexOf(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return -1;
            }
            if (localeArr[i].equals(locale)) {
                return i;
            }
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocaleListHelper)) {
            return false;
        }
        obj = ((LocaleListHelper) obj).mList;
        if (this.mList.length != obj.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return true;
            }
            if (!localeArr[i].equals(obj[i])) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int i = 1;
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i2 >= localeArr.length) {
                return i;
            }
            i = (i * 31) + localeArr[i2].hashCode();
            i2++;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i < localeArr.length) {
                stringBuilder.append(localeArr[i]);
                if (i < this.mList.length - 1) {
                    stringBuilder.append(',');
                }
                i++;
            } else {
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    String toLanguageTags() {
        return this.mStringRepresentation;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale... localeArr) {
        if (localeArr.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] localeArr2 = new Locale[localeArr.length];
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < localeArr.length) {
            Locale locale = localeArr[i];
            StringBuilder stringBuilder2;
            if (locale == null) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("list[");
                stringBuilder2.append(i);
                stringBuilder2.append("] is null");
                throw new NullPointerException(stringBuilder2.toString());
            } else if (hashSet.contains(locale)) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("list[");
                stringBuilder2.append(i);
                stringBuilder2.append("] is a repetition");
                throw new IllegalArgumentException(stringBuilder2.toString());
            } else {
                locale = (Locale) locale.clone();
                localeArr2[i] = locale;
                stringBuilder.append(LocaleHelper.toLanguageTag(locale));
                if (i < localeArr.length - 1) {
                    stringBuilder.append(',');
                }
                hashSet.add(locale);
                i++;
            }
        }
        this.mList = localeArr2;
        this.mStringRepresentation = stringBuilder.toString();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    static LocaleListHelper getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    static LocaleListHelper forLanguageTags(@Nullable String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                str = str.split(",", -1);
                Locale[] localeArr = new Locale[str.length];
                for (int i = 0; i < localeArr.length; i++) {
                    localeArr[i] = LocaleHelper.forLanguageTag(str[i]);
                }
                return new LocaleListHelper(localeArr);
            }
        }
        return getEmptyLocaleList();
    }

    private static String getLikelyScript(Locale locale) {
        if (VERSION.SDK_INT < 21) {
            return "";
        }
        locale = locale.getScript();
        return !locale.isEmpty() ? locale : "";
    }

    private static boolean isPseudoLocale(String str) {
        if (!STRING_EN_XA.equals(str)) {
            if (STRING_AR_XB.equals(str) == null) {
                return null;
            }
        }
        return true;
    }

    private static boolean isPseudoLocale(Locale locale) {
        if (!LOCALE_EN_XA.equals(locale)) {
            if (LOCALE_AR_XB.equals(locale) == null) {
                return null;
            }
        }
        return true;
    }

    @IntRange(from = 0, to = 1)
    private static int matchScore(Locale locale, Locale locale2) {
        int i = 1;
        if (locale.equals(locale2)) {
            return 1;
        }
        if (locale.getLanguage().equals(locale2.getLanguage()) && !isPseudoLocale(locale)) {
            if (!isPseudoLocale(locale2)) {
                String likelyScript = getLikelyScript(locale);
                if (!likelyScript.isEmpty()) {
                    return likelyScript.equals(getLikelyScript(locale2));
                }
                locale = locale.getCountry();
                if (!locale.isEmpty()) {
                    if (locale.equals(locale2.getCountry()) == null) {
                        i = 0;
                    }
                }
                return i;
            }
        }
        return 0;
    }

    private int findFirstMatchIndex(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (matchScore(locale, localeArr[i]) > 0) {
                return i;
            }
            i++;
        }
    }

    private int computeFirstMatchIndex(Collection<String> collection, boolean z) {
        Locale[] localeArr = this.mList;
        if (localeArr.length == 1) {
            return 0;
        }
        if (localeArr.length == 0) {
            return -1;
        }
        boolean findFirstMatchIndex;
        if (z) {
            z = findFirstMatchIndex(EN_LATN);
            if (!z) {
                return 0;
            }
            if (z < true) {
                for (String forLanguageTag : collection) {
                    findFirstMatchIndex = findFirstMatchIndex(LocaleHelper.forLanguageTag(forLanguageTag));
                    if (!findFirstMatchIndex) {
                        return 0;
                    }
                    if (findFirstMatchIndex < z) {
                        z = findFirstMatchIndex;
                    }
                }
                if (!z) {
                    return 0;
                }
                return z;
            }
        }
        z = true;
        while (collection.hasNext()) {
            findFirstMatchIndex = findFirstMatchIndex(LocaleHelper.forLanguageTag(forLanguageTag));
            if (!findFirstMatchIndex) {
                return 0;
            }
            if (findFirstMatchIndex < z) {
                z = findFirstMatchIndex;
            }
        }
        if (!z) {
            return z;
        }
        return 0;
    }

    private Locale computeFirstMatch(Collection<String> collection, boolean z) {
        collection = computeFirstMatchIndex(collection, z);
        if (collection == true) {
            return null;
        }
        return this.mList[collection];
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale getFirstMatch(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), false);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    int getFirstMatchIndex(String[] strArr) {
        return computeFirstMatchIndex(Arrays.asList(strArr), false);
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale getFirstMatchWithEnglishSupported(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), true);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    int getFirstMatchIndexWithEnglishSupported(Collection<String> collection) {
        return computeFirstMatchIndex(collection, true);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    int getFirstMatchIndexWithEnglishSupported(String[] strArr) {
        return getFirstMatchIndexWithEnglishSupported(Arrays.asList(strArr));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static boolean isPseudoLocalesOnly(@Nullable String[] strArr) {
        if (strArr == null) {
            return true;
        }
        if (strArr.length > 3) {
            return false;
        }
        for (String str : strArr) {
            if (!str.isEmpty() && !isPseudoLocale(str)) {
                return false;
            }
        }
        return true;
    }

    @Size(min = 1)
    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    static LocaleListHelper getDefault() {
        Locale locale = Locale.getDefault();
        synchronized (sLock) {
            LocaleListHelper localeListHelper;
            if (!locale.equals(sLastDefaultLocale)) {
                sLastDefaultLocale = locale;
                if (sDefaultLocaleList == null || !locale.equals(sDefaultLocaleList.get(0))) {
                    sDefaultLocaleList = new LocaleListHelper(locale, sLastExplicitlySetLocaleList);
                    sDefaultAdjustedLocaleList = sDefaultLocaleList;
                } else {
                    localeListHelper = sDefaultLocaleList;
                    return localeListHelper;
                }
            }
            localeListHelper = sDefaultLocaleList;
            return localeListHelper;
        }
    }

    @Size(min = 1)
    @NonNull
    static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper localeListHelper;
        getDefault();
        synchronized (sLock) {
            localeListHelper = sDefaultAdjustedLocaleList;
        }
        return localeListHelper;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static void setDefault(@Size(min = 1) @NonNull LocaleListHelper localeListHelper) {
        setDefault(localeListHelper, 0);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static void setDefault(@Size(min = 1) @NonNull LocaleListHelper localeListHelper, int i) {
        if (localeListHelper == null) {
            throw new NullPointerException("locales is null");
        } else if (localeListHelper.isEmpty()) {
            throw new IllegalArgumentException("locales is empty");
        } else {
            synchronized (sLock) {
                sLastDefaultLocale = localeListHelper.get(i);
                Locale.setDefault(sLastDefaultLocale);
                sLastExplicitlySetLocaleList = localeListHelper;
                sDefaultLocaleList = localeListHelper;
                if (i == 0) {
                    sDefaultAdjustedLocaleList = sDefaultLocaleList;
                } else {
                    sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
                }
            }
        }
    }
}
