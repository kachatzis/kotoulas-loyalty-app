package android.support.v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class FileProvider extends ContentProvider {
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PATH = "path";
    private static final String[] COLUMNS = new String[]{"_display_name", "_size"};
    private static final File DEVICE_ROOT = new File("/");
    private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    private static final String TAG_EXTERNAL_FILES = "external-files-path";
    private static final String TAG_EXTERNAL_MEDIA = "external-media-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_ROOT_PATH = "root-path";
    @GuardedBy("sCache")
    private static HashMap<String, PathStrategy> sCache = new HashMap();
    private PathStrategy mStrategy;

    interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    static class SimplePathStrategy implements PathStrategy {
        private final String mAuthority;
        private final HashMap<String, File> mRoots = new HashMap();

        public android.net.Uri getUriForFile(java.io.File r6) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:22:0x00d6 in {9, 10, 14, 15, 17, 19, 21} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r5 = this;
            r6 = r6.getCanonicalPath();	 Catch:{ IOException -> 0x00bf }
            r0 = 0;
            r1 = r5.mRoots;
            r1 = r1.entrySet();
            r1 = r1.iterator();
        L_0x000f:
            r2 = r1.hasNext();
            if (r2 == 0) goto L_0x0043;
        L_0x0015:
            r2 = r1.next();
            r2 = (java.util.Map.Entry) r2;
            r3 = r2.getValue();
            r3 = (java.io.File) r3;
            r3 = r3.getPath();
            r4 = r6.startsWith(r3);
            if (r4 == 0) goto L_0x000f;
        L_0x002b:
            if (r0 == 0) goto L_0x0041;
        L_0x002d:
            r3 = r3.length();
            r4 = r0.getValue();
            r4 = (java.io.File) r4;
            r4 = r4.getPath();
            r4 = r4.length();
            if (r3 <= r4) goto L_0x000f;
        L_0x0041:
            r0 = r2;
            goto L_0x000f;
        L_0x0043:
            if (r0 == 0) goto L_0x00a8;
        L_0x0045:
            r1 = r0.getValue();
            r1 = (java.io.File) r1;
            r1 = r1.getPath();
            r2 = "/";
            r2 = r1.endsWith(r2);
            if (r2 == 0) goto L_0x0060;
        L_0x0057:
            r1 = r1.length();
            r6 = r6.substring(r1);
            goto L_0x006a;
        L_0x0060:
            r1 = r1.length();
            r1 = r1 + 1;
            r6 = r6.substring(r1);
        L_0x006a:
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r0 = r0.getKey();
            r0 = (java.lang.String) r0;
            r0 = android.net.Uri.encode(r0);
            r1.append(r0);
            r0 = 47;
            r1.append(r0);
            r0 = "/";
            r6 = android.net.Uri.encode(r6, r0);
            r1.append(r6);
            r6 = r1.toString();
            r0 = new android.net.Uri$Builder;
            r0.<init>();
            r1 = "content";
            r0 = r0.scheme(r1);
            r1 = r5.mAuthority;
            r0 = r0.authority(r1);
            r6 = r0.encodedPath(r6);
            r6 = r6.build();
            return r6;
        L_0x00a8:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Failed to find configured root that contains ";
            r1.append(r2);
            r1.append(r6);
            r6 = r1.toString();
            r0.<init>(r6);
            throw r0;
        L_0x00bf:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Failed to resolve canonical path for ";
            r1.append(r2);
            r1.append(r6);
            r6 = r1.toString();
            r0.<init>(r6);
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.FileProvider.SimplePathStrategy.getUriForFile(java.io.File):android.net.Uri");
        }

        SimplePathStrategy(String str) {
            this.mAuthority = str;
        }

        void addRoot(String str, File file) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                file = file.getCanonicalFile();
                this.mRoots.put(str, file);
            } catch (String str2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to resolve canonical path for ");
                stringBuilder.append(file);
                throw new IllegalArgumentException(stringBuilder.toString(), str2);
            }
        }

        public java.io.File getFileForUri(android.net.Uri r5) {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r4 = this;
            r0 = r5.getEncodedPath();
            r1 = 1;
            r2 = 47;
            r2 = r0.indexOf(r2, r1);
            r3 = r0.substring(r1, r2);
            r3 = android.net.Uri.decode(r3);
            r2 = r2 + r1;
            r0 = r0.substring(r2);
            r0 = android.net.Uri.decode(r0);
            r1 = r4.mRoots;
            r1 = r1.get(r3);
            r1 = (java.io.File) r1;
            if (r1 == 0) goto L_0x005d;
        L_0x0026:
            r5 = new java.io.File;
            r5.<init>(r1, r0);
            r5 = r5.getCanonicalFile();	 Catch:{ IOException -> 0x0046 }
            r0 = r5.getPath();
            r1 = r1.getPath();
            r0 = r0.startsWith(r1);
            if (r0 == 0) goto L_0x003e;
        L_0x003d:
            return r5;
        L_0x003e:
            r5 = new java.lang.SecurityException;
            r0 = "Resolved path jumped beyond configured root";
            r5.<init>(r0);
            throw r5;
        L_0x0046:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Failed to resolve canonical path for ";
            r1.append(r2);
            r1.append(r5);
            r5 = r1.toString();
            r0.<init>(r5);
            throw r0;
        L_0x005d:
            r0 = new java.lang.IllegalArgumentException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Unable to find configured root for ";
            r1.append(r2);
            r1.append(r5);
            r5 = r1.toString();
            r0.<init>(r5);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.FileProvider.SimplePathStrategy.getFileForUri(android.net.Uri):java.io.File");
        }
    }

    private static android.support.v4.content.FileProvider.PathStrategy parsePathStrategy(android.content.Context r9, java.lang.String r10) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:40:0x00bc in {8, 11, 14, 17, 22, 27, 34, 36, 37, 39} preds:[]
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
        r0 = new android.support.v4.content.FileProvider$SimplePathStrategy;
        r0.<init>(r10);
        r1 = r9.getPackageManager();
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r10 = r1.resolveContentProvider(r10, r2);
        r1 = r9.getPackageManager();
        r2 = "android.support.FILE_PROVIDER_PATHS";
        r10 = r10.loadXmlMetaData(r1, r2);
        if (r10 == 0) goto L_0x00b4;
    L_0x001b:
        r1 = r10.next();
        r2 = 1;
        if (r1 == r2) goto L_0x00b3;
    L_0x0022:
        r3 = 2;
        if (r1 != r3) goto L_0x001b;
    L_0x0025:
        r1 = r10.getName();
        r3 = "name";
        r4 = 0;
        r3 = r10.getAttributeValue(r4, r3);
        r5 = "path";
        r5 = r10.getAttributeValue(r4, r5);
        r6 = "root-path";
        r6 = r6.equals(r1);
        r7 = 0;
        if (r6 == 0) goto L_0x0042;
    L_0x003f:
        r4 = DEVICE_ROOT;
        goto L_0x00a4;
    L_0x0042:
        r6 = "files-path";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x004f;
    L_0x004a:
        r4 = r9.getFilesDir();
        goto L_0x00a4;
    L_0x004f:
        r6 = "cache-path";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x005c;
    L_0x0057:
        r4 = r9.getCacheDir();
        goto L_0x00a4;
    L_0x005c:
        r6 = "external-path";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x0069;
    L_0x0064:
        r4 = android.os.Environment.getExternalStorageDirectory();
        goto L_0x00a4;
    L_0x0069:
        r6 = "external-files-path";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x007b;
    L_0x0071:
        r1 = android.support.v4.content.ContextCompat.getExternalFilesDirs(r9, r4);
        r6 = r1.length;
        if (r6 <= 0) goto L_0x00a4;
    L_0x0078:
        r4 = r1[r7];
        goto L_0x00a4;
    L_0x007b:
        r6 = "external-cache-path";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x008d;
    L_0x0083:
        r1 = android.support.v4.content.ContextCompat.getExternalCacheDirs(r9);
        r6 = r1.length;
        if (r6 <= 0) goto L_0x00a4;
    L_0x008a:
        r4 = r1[r7];
        goto L_0x00a4;
    L_0x008d:
        r6 = android.os.Build.VERSION.SDK_INT;
        r8 = 21;
        if (r6 < r8) goto L_0x00a4;
    L_0x0093:
        r6 = "external-media-path";
        r1 = r6.equals(r1);
        if (r1 == 0) goto L_0x00a4;
    L_0x009b:
        r1 = r9.getExternalMediaDirs();
        r6 = r1.length;
        if (r6 <= 0) goto L_0x00a4;
    L_0x00a2:
        r4 = r1[r7];
    L_0x00a4:
        if (r4 == 0) goto L_0x001b;
    L_0x00a6:
        r1 = new java.lang.String[r2];
        r1[r7] = r5;
        r1 = buildPath(r4, r1);
        r0.addRoot(r3, r1);
        goto L_0x001b;
    L_0x00b3:
        return r0;
    L_0x00b4:
        r9 = new java.lang.IllegalArgumentException;
        r10 = "Missing android.support.FILE_PROVIDER_PATHS meta-data";
        r9.<init>(r10);
        throw r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.FileProvider.parsePathStrategy(android.content.Context, java.lang.String):android.support.v4.content.FileProvider$PathStrategy");
    }

    public boolean onCreate() {
        return true;
    }

    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.mStrategy = getPathStrategy(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public static Uri getUriForFile(@NonNull Context context, @NonNull String str, @NonNull File file) {
        return getPathStrategy(context, str).getUriForFile(file);
    }

    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        uri = this.mStrategy.getFileForUri(uri);
        if (strArr == null) {
            strArr = COLUMNS;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i = 0;
        for (Object obj : strArr) {
            int i2;
            if ("_display_name".equals(obj)) {
                strArr3[i] = "_display_name";
                i2 = i + 1;
                objArr[i] = uri.getName();
                i = i2;
            } else if ("_size".equals(obj)) {
                strArr3[i] = "_size";
                i2 = i + 1;
                objArr[i] = Long.valueOf(uri.length());
                i = i2;
            }
        }
        uri = copyOf(strArr3, i);
        strArr = copyOf(objArr, i);
        str = new MatrixCursor(uri, 1);
        str.addRow(strArr);
        return str;
    }

    public String getType(@NonNull Uri uri) {
        uri = this.mStrategy.getFileForUri(uri);
        int lastIndexOf = uri.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            uri = MimeTypeMap.getSingleton().getMimeTypeFromExtension(uri.getName().substring(lastIndexOf + 1));
            if (uri != null) {
                return uri;
            }
        }
        return "application/octet-stream";
    }

    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public int update(@NonNull Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return this.mStrategy.getFileForUri(uri).delete();
    }

    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.mStrategy.getFileForUri(uri), modeToMode(str));
    }

    private static PathStrategy getPathStrategy(Context context, String str) {
        PathStrategy pathStrategy;
        synchronized (sCache) {
            pathStrategy = (PathStrategy) sCache.get(str);
            if (pathStrategy == null) {
                try {
                    pathStrategy = parsePathStrategy(context, str);
                    sCache.put(str, pathStrategy);
                } catch (Context context2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", context2);
                } catch (Context context22) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", context22);
                }
            }
        }
        return pathStrategy;
    }

    private static int modeToMode(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if (!"w".equals(str)) {
            if (!"wt".equals(str)) {
                if ("wa".equals(str)) {
                    return 704643072;
                }
                if ("rw".equals(str)) {
                    return 939524096;
                }
                if ("rwt".equals(str)) {
                    return 1006632960;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Invalid mode: ");
                stringBuilder.append(str);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        return 738197504;
    }

    private static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static String[] copyOf(String[] strArr, int i) {
        Object obj = new String[i];
        System.arraycopy(strArr, 0, obj, 0, i);
        return obj;
    }

    private static Object[] copyOf(Object[] objArr, int i) {
        Object obj = new Object[i];
        System.arraycopy(objArr, 0, obj, 0, i);
        return obj;
    }
}
