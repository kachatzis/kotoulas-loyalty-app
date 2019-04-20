package okhttp3.internal.cache2;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

final class FileOperator {
    private static final int BUFFER_SIZE = 8192;
    private final byte[] byteArray = new byte[8192];
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(this.byteArray);
    private final FileChannel fileChannel;

    public void read(long r6, okio.Buffer r8, long r9) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x004a in {8, 11, 14, 15, 17} preds:[]
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
        r5 = this;
        r0 = 0;
        r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x0044;
    L_0x0006:
        r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0043;
    L_0x000a:
        r2 = r5.byteBuffer;	 Catch:{ all -> 0x003c }
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;	 Catch:{ all -> 0x003c }
        r3 = java.lang.Math.min(r3, r9);	 Catch:{ all -> 0x003c }
        r4 = (int) r3;	 Catch:{ all -> 0x003c }
        r2.limit(r4);	 Catch:{ all -> 0x003c }
        r2 = r5.fileChannel;	 Catch:{ all -> 0x003c }
        r3 = r5.byteBuffer;	 Catch:{ all -> 0x003c }
        r2 = r2.read(r3, r6);	 Catch:{ all -> 0x003c }
        r3 = -1;	 Catch:{ all -> 0x003c }
        if (r2 == r3) goto L_0x0036;	 Catch:{ all -> 0x003c }
    L_0x0021:
        r2 = r5.byteBuffer;	 Catch:{ all -> 0x003c }
        r2 = r2.position();	 Catch:{ all -> 0x003c }
        r3 = r5.byteArray;	 Catch:{ all -> 0x003c }
        r4 = 0;	 Catch:{ all -> 0x003c }
        r8.write(r3, r4, r2);	 Catch:{ all -> 0x003c }
        r2 = (long) r2;
        r6 = r6 + r2;
        r9 = r9 - r2;
        r2 = r5.byteBuffer;
        r2.clear();
        goto L_0x0006;
    L_0x0036:
        r6 = new java.io.EOFException;	 Catch:{ all -> 0x003c }
        r6.<init>();	 Catch:{ all -> 0x003c }
        throw r6;	 Catch:{ all -> 0x003c }
    L_0x003c:
        r6 = move-exception;
        r7 = r5.byteBuffer;
        r7.clear();
        throw r6;
    L_0x0043:
        return;
    L_0x0044:
        r6 = new java.lang.IndexOutOfBoundsException;
        r6.<init>();
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.FileOperator.read(long, okio.Buffer, long):void");
    }

    public void write(long r7, okio.Buffer r9, long r10) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x004c in {11, 14, 15, 17} preds:[]
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
        r6 = this;
        r0 = 0;
        r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x0046;
    L_0x0006:
        r2 = r9.size();
        r4 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
        if (r4 > 0) goto L_0x0046;
    L_0x000e:
        r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0045;
    L_0x0012:
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2 = java.lang.Math.min(r2, r10);	 Catch:{ all -> 0x003e }
        r3 = (int) r2;	 Catch:{ all -> 0x003e }
        r2 = r6.byteArray;	 Catch:{ all -> 0x003e }
        r4 = 0;	 Catch:{ all -> 0x003e }
        r9.read(r2, r4, r3);	 Catch:{ all -> 0x003e }
        r2 = r6.byteBuffer;	 Catch:{ all -> 0x003e }
        r2.limit(r3);	 Catch:{ all -> 0x003e }
    L_0x0024:
        r2 = r6.fileChannel;	 Catch:{ all -> 0x003e }
        r4 = r6.byteBuffer;	 Catch:{ all -> 0x003e }
        r2 = r2.write(r4, r7);	 Catch:{ all -> 0x003e }
        r4 = (long) r2;	 Catch:{ all -> 0x003e }
        r7 = r7 + r4;	 Catch:{ all -> 0x003e }
        r2 = r6.byteBuffer;	 Catch:{ all -> 0x003e }
        r2 = r2.hasRemaining();	 Catch:{ all -> 0x003e }
        if (r2 != 0) goto L_0x0024;
    L_0x0036:
        r2 = (long) r3;
        r10 = r10 - r2;
        r2 = r6.byteBuffer;
        r2.clear();
        goto L_0x000e;
    L_0x003e:
        r7 = move-exception;
        r8 = r6.byteBuffer;
        r8.clear();
        throw r7;
    L_0x0045:
        return;
    L_0x0046:
        r7 = new java.lang.IndexOutOfBoundsException;
        r7.<init>();
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.FileOperator.write(long, okio.Buffer, long):void");
    }

    FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }
}
