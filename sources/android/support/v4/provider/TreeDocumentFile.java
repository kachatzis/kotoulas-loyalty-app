package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    public android.support.v4.provider.DocumentFile[] listFiles() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:21:0x007c in {6, 13, 17, 18, 20} preds:[]
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
        r9 = this;
        r0 = r9.mContext;
        r1 = r0.getContentResolver();
        r0 = r9.mUri;
        r2 = android.provider.DocumentsContract.getDocumentId(r0);
        r2 = android.provider.DocumentsContract.buildChildDocumentsUriUsingTree(r0, r2);
        r0 = new java.util.ArrayList;
        r0.<init>();
        r3 = 1;
        r7 = 0;
        r8 = 0;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x003f }
        r4 = "document_id";	 Catch:{ Exception -> 0x003f }
        r3[r7] = r4;	 Catch:{ Exception -> 0x003f }
        r4 = 0;	 Catch:{ Exception -> 0x003f }
        r5 = 0;	 Catch:{ Exception -> 0x003f }
        r6 = 0;	 Catch:{ Exception -> 0x003f }
        r8 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x003f }
    L_0x0025:
        r1 = r8.moveToNext();	 Catch:{ Exception -> 0x003f }
        if (r1 == 0) goto L_0x0039;	 Catch:{ Exception -> 0x003f }
    L_0x002b:
        r1 = r8.getString(r7);	 Catch:{ Exception -> 0x003f }
        r2 = r9.mUri;	 Catch:{ Exception -> 0x003f }
        r1 = android.provider.DocumentsContract.buildDocumentUriUsingTree(r2, r1);	 Catch:{ Exception -> 0x003f }
        r0.add(r1);	 Catch:{ Exception -> 0x003f }
        goto L_0x0025;
    L_0x0039:
        closeQuietly(r8);
        goto L_0x0057;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0078;
    L_0x003f:
        r1 = move-exception;
        r2 = "DocumentFile";	 Catch:{ all -> 0x003d }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003d }
        r3.<init>();	 Catch:{ all -> 0x003d }
        r4 = "Failed query: ";	 Catch:{ all -> 0x003d }
        r3.append(r4);	 Catch:{ all -> 0x003d }
        r3.append(r1);	 Catch:{ all -> 0x003d }
        r1 = r3.toString();	 Catch:{ all -> 0x003d }
        android.util.Log.w(r2, r1);	 Catch:{ all -> 0x003d }
        goto L_0x0039;
    L_0x0057:
        r1 = r0.size();
        r1 = new android.net.Uri[r1];
        r0 = r0.toArray(r1);
        r0 = (android.net.Uri[]) r0;
        r1 = r0.length;
        r1 = new android.support.v4.provider.DocumentFile[r1];
    L_0x0066:
        r2 = r0.length;
        if (r7 >= r2) goto L_0x0077;
    L_0x0069:
        r2 = new android.support.v4.provider.TreeDocumentFile;
        r3 = r9.mContext;
        r4 = r0[r7];
        r2.<init>(r9, r3, r4);
        r1[r7] = r2;
        r7 = r7 + 1;
        goto L_0x0066;
    L_0x0077:
        return r1;
    L_0x0078:
        closeQuietly(r8);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.listFiles():android.support.v4.provider.DocumentFile[]");
    }

    TreeDocumentFile(@Nullable DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.mContext = context;
        this.mUri = uri;
    }

    @Nullable
    public DocumentFile createFile(String str, String str2) {
        str = createFile(this.mContext, this.mUri, str, str2);
        return str != null ? new TreeDocumentFile(this, this.mContext, str) : null;
    }

    @android.support.annotation.Nullable
    private static android.net.Uri createFile(android.content.Context r0, android.net.Uri r1, java.lang.String r2, java.lang.String r3) {
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
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x0009 }
        r0 = android.provider.DocumentsContract.createDocument(r0, r1, r2, r3);	 Catch:{ Exception -> 0x0009 }
        return r0;
    L_0x0009:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.createFile(android.content.Context, android.net.Uri, java.lang.String, java.lang.String):android.net.Uri");
    }

    @Nullable
    public DocumentFile createDirectory(String str) {
        str = createFile(this.mContext, this.mUri, "vnd.android.document/directory", str);
        return str != null ? new TreeDocumentFile(this, this.mContext, str) : null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    @Nullable
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Nullable
    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    public boolean delete() {
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
        r2 = this;
        r0 = r2.mContext;	 Catch:{ Exception -> 0x000d }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x000d }
        r1 = r2.mUri;	 Catch:{ Exception -> 0x000d }
        r0 = android.provider.DocumentsContract.deleteDocument(r0, r1);	 Catch:{ Exception -> 0x000d }
        return r0;
    L_0x000d:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.delete():boolean");
    }

    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
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
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.closeQuietly(java.lang.AutoCloseable):void");
    }

    public boolean renameTo(java.lang.String r4) {
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
        r3 = this;
        r0 = 0;
        r1 = r3.mContext;	 Catch:{ Exception -> 0x0014 }
        r1 = r1.getContentResolver();	 Catch:{ Exception -> 0x0014 }
        r2 = r3.mUri;	 Catch:{ Exception -> 0x0014 }
        r4 = android.provider.DocumentsContract.renameDocument(r1, r2, r4);	 Catch:{ Exception -> 0x0014 }
        if (r4 == 0) goto L_0x0013;	 Catch:{ Exception -> 0x0014 }
    L_0x000f:
        r3.mUri = r4;	 Catch:{ Exception -> 0x0014 }
        r4 = 1;
        return r4;
    L_0x0013:
        return r0;
    L_0x0014:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.renameTo(java.lang.String):boolean");
    }
}
