package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.io.File;
import java.nio.ByteBuffer;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    public static boolean copyToFile(java.io.File r5, java.io.InputStream r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:25:0x0053 in {9, 11, 13, 15, 17, 18, 22, 24} preds:[]
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
        r0 = android.os.StrictMode.allowThreadDiskWrites();
        r1 = 0;
        r2 = 0;
        r3 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x002a }
        r3.<init>(r5, r1);	 Catch:{ IOException -> 0x002a }
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ IOException -> 0x0025, all -> 0x0022 }
    L_0x000f:
        r2 = r6.read(r5);	 Catch:{ IOException -> 0x0025, all -> 0x0022 }
        r4 = -1;	 Catch:{ IOException -> 0x0025, all -> 0x0022 }
        if (r2 == r4) goto L_0x001a;	 Catch:{ IOException -> 0x0025, all -> 0x0022 }
    L_0x0016:
        r3.write(r5, r1, r2);	 Catch:{ IOException -> 0x0025, all -> 0x0022 }
        goto L_0x000f;
    L_0x001a:
        r5 = 1;
        closeQuietly(r3);
        android.os.StrictMode.setThreadPolicy(r0);
        return r5;
    L_0x0022:
        r5 = move-exception;
        r2 = r3;
        goto L_0x004c;
    L_0x0025:
        r5 = move-exception;
        r2 = r3;
        goto L_0x002b;
    L_0x0028:
        r5 = move-exception;
        goto L_0x004c;
    L_0x002a:
        r5 = move-exception;
    L_0x002b:
        r6 = "TypefaceCompatUtil";	 Catch:{ all -> 0x0028 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0028 }
        r3.<init>();	 Catch:{ all -> 0x0028 }
        r4 = "Error copying resource contents to temp file: ";	 Catch:{ all -> 0x0028 }
        r3.append(r4);	 Catch:{ all -> 0x0028 }
        r5 = r5.getMessage();	 Catch:{ all -> 0x0028 }
        r3.append(r5);	 Catch:{ all -> 0x0028 }
        r5 = r3.toString();	 Catch:{ all -> 0x0028 }
        android.util.Log.e(r6, r5);	 Catch:{ all -> 0x0028 }
        closeQuietly(r2);
        android.os.StrictMode.setThreadPolicy(r0);
        return r1;
    L_0x004c:
        closeQuietly(r2);
        android.os.StrictMode.setThreadPolicy(r0);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.copyToFile(java.io.File, java.io.InputStream):boolean");
    }

    private TypefaceCompatUtil() {
    }

    @android.support.annotation.Nullable
    public static java.io.File getTempFile(android.content.Context r5) {
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
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = ".font";
        r0.append(r1);
        r1 = android.os.Process.myPid();
        r0.append(r1);
        r1 = "-";
        r0.append(r1);
        r1 = android.os.Process.myTid();
        r0.append(r1);
        r1 = "-";
        r0.append(r1);
        r0 = r0.toString();
        r1 = 0;
    L_0x0027:
        r2 = 100;
        if (r1 >= r2) goto L_0x004d;
    L_0x002b:
        r2 = new java.io.File;
        r3 = r5.getCacheDir();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r0);
        r4.append(r1);
        r4 = r4.toString();
        r2.<init>(r3, r4);
        r3 = r2.createNewFile();	 Catch:{ IOException -> 0x004a }
        if (r3 == 0) goto L_0x004a;
    L_0x0049:
        return r2;
    L_0x004a:
        r1 = r1 + 1;
        goto L_0x0027;
    L_0x004d:
        r5 = 0;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.getTempFile(android.content.Context):java.io.File");
    }

    @android.support.annotation.Nullable
    @android.support.annotation.RequiresApi(19)
    private static java.nio.ByteBuffer mmap(java.io.File r9) {
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
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0032 }
        r1.<init>(r9);	 Catch:{ IOException -> 0x0032 }
        r2 = r1.getChannel();	 Catch:{ Throwable -> 0x001d, all -> 0x001a }
        r6 = r2.size();	 Catch:{ Throwable -> 0x001d, all -> 0x001a }
        r3 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Throwable -> 0x001d, all -> 0x001a }
        r4 = 0;	 Catch:{ Throwable -> 0x001d, all -> 0x001a }
        r9 = r2.map(r3, r4, r6);	 Catch:{ Throwable -> 0x001d, all -> 0x001a }
        r1.close();	 Catch:{ IOException -> 0x0032 }
        return r9;
    L_0x001a:
        r9 = move-exception;
        r2 = r0;
        goto L_0x0023;
    L_0x001d:
        r9 = move-exception;
        throw r9;	 Catch:{ all -> 0x001f }
    L_0x001f:
        r2 = move-exception;
        r8 = r2;
        r2 = r9;
        r9 = r8;
    L_0x0023:
        if (r2 == 0) goto L_0x002e;
    L_0x0025:
        r1.close();	 Catch:{ Throwable -> 0x0029 }
        goto L_0x0031;
    L_0x0029:
        r1 = move-exception;
        r2.addSuppressed(r1);	 Catch:{ IOException -> 0x0032 }
        goto L_0x0031;	 Catch:{ IOException -> 0x0032 }
    L_0x002e:
        r1.close();	 Catch:{ IOException -> 0x0032 }
    L_0x0031:
        throw r9;	 Catch:{ IOException -> 0x0032 }
    L_0x0032:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    @android.support.annotation.Nullable
    @android.support.annotation.RequiresApi(19)
    public static java.nio.ByteBuffer mmap(android.content.Context r8, android.os.CancellationSignal r9, android.net.Uri r10) {
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
        r8 = r8.getContentResolver();
        r0 = 0;
        r1 = "r";	 Catch:{ IOException -> 0x0067 }
        r8 = r8.openFileDescriptor(r10, r1, r9);	 Catch:{ IOException -> 0x0067 }
        if (r8 != 0) goto L_0x0013;	 Catch:{ IOException -> 0x0067 }
    L_0x000d:
        if (r8 == 0) goto L_0x0012;	 Catch:{ IOException -> 0x0067 }
    L_0x000f:
        r8.close();	 Catch:{ IOException -> 0x0067 }
    L_0x0012:
        return r0;
    L_0x0013:
        r9 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
        r10 = r8.getFileDescriptor();	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
        r9.<init>(r10);	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
        r1 = r9.getChannel();	 Catch:{ Throwable -> 0x0038, all -> 0x0035 }
        r5 = r1.size();	 Catch:{ Throwable -> 0x0038, all -> 0x0035 }
        r2 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Throwable -> 0x0038, all -> 0x0035 }
        r3 = 0;	 Catch:{ Throwable -> 0x0038, all -> 0x0035 }
        r10 = r1.map(r2, r3, r5);	 Catch:{ Throwable -> 0x0038, all -> 0x0035 }
        r9.close();	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
        if (r8 == 0) goto L_0x0034;
    L_0x0031:
        r8.close();	 Catch:{ IOException -> 0x0067 }
    L_0x0034:
        return r10;
    L_0x0035:
        r10 = move-exception;
        r1 = r0;
        goto L_0x003e;
    L_0x0038:
        r10 = move-exception;
        throw r10;	 Catch:{ all -> 0x003a }
    L_0x003a:
        r1 = move-exception;
        r7 = r1;
        r1 = r10;
        r10 = r7;
    L_0x003e:
        if (r1 == 0) goto L_0x0049;
    L_0x0040:
        r9.close();	 Catch:{ Throwable -> 0x0044, all -> 0x004d }
        goto L_0x004c;
    L_0x0044:
        r9 = move-exception;
        r1.addSuppressed(r9);	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
        goto L_0x004c;	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
    L_0x0049:
        r9.close();	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
    L_0x004c:
        throw r10;	 Catch:{ Throwable -> 0x0050, all -> 0x004d }
    L_0x004d:
        r9 = move-exception;
        r10 = r0;
        goto L_0x0056;
    L_0x0050:
        r9 = move-exception;
        throw r9;	 Catch:{ all -> 0x0052 }
    L_0x0052:
        r10 = move-exception;
        r7 = r10;
        r10 = r9;
        r9 = r7;
    L_0x0056:
        if (r8 == 0) goto L_0x0066;
    L_0x0058:
        if (r10 == 0) goto L_0x0063;
    L_0x005a:
        r8.close();	 Catch:{ Throwable -> 0x005e }
        goto L_0x0066;
    L_0x005e:
        r8 = move-exception;
        r10.addSuppressed(r8);	 Catch:{ IOException -> 0x0067 }
        goto L_0x0066;	 Catch:{ IOException -> 0x0067 }
    L_0x0063:
        r8.close();	 Catch:{ IOException -> 0x0067 }
    L_0x0066:
        throw r9;	 Catch:{ IOException -> 0x0067 }
    L_0x0067:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @Nullable
    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources resources, int i) {
        context = getTempFile(context);
        if (context == null) {
            return null;
        }
        try {
            if (copyToFile(context, resources, i) == null) {
                return null;
            }
            resources = mmap(context);
            context.delete();
            return resources;
        } finally {
            context.delete();
        }
    }

    public static boolean copyToFile(File file, Resources resources, int i) {
        try {
            resources = resources.openRawResource(i);
            try {
                file = copyToFile(file, resources);
                closeQuietly(resources);
                return file;
            } catch (Throwable th) {
                file = th;
                closeQuietly(resources);
                throw file;
            }
        } catch (Throwable th2) {
            file = th2;
            resources = null;
            closeQuietly(resources);
            throw file;
        }
    }

    public static void closeQuietly(java.io.Closeable r0) {
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
        if (r0 == 0) goto L_0x0005;
    L_0x0002:
        r0.close();	 Catch:{ IOException -> 0x0005 }
    L_0x0005:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.closeQuietly(java.io.Closeable):void");
    }
}
