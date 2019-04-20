package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public final class DiskLruCache implements Closeable, Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long ANY_SEQUENCE_NUMBER = -1;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Runnable cleanupRunnable = new C04371();
    boolean closed;
    final File directory;
    private final Executor executor;
    final FileSystem fileSystem;
    boolean hasJournalErrors;
    boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    BufferedSink journalWriter;
    final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75f, true);
    private long maxSize;
    boolean mostRecentRebuildFailed;
    boolean mostRecentTrimFailed;
    private long nextSequenceNumber = 0;
    int redundantOpCount;
    private long size = 0;
    final int valueCount;

    /* renamed from: okhttp3.internal.cache.DiskLruCache$1 */
    class C04371 implements Runnable {
        C04371() {
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
            r5 = this;
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            monitor-enter(r0);
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ all -> 0x0045 }
            r1 = r1.initialized;	 Catch:{ all -> 0x0045 }
            r2 = 0;	 Catch:{ all -> 0x0045 }
            r3 = 1;	 Catch:{ all -> 0x0045 }
            if (r1 != 0) goto L_0x000d;	 Catch:{ all -> 0x0045 }
        L_0x000b:
            r1 = 1;	 Catch:{ all -> 0x0045 }
            goto L_0x000e;	 Catch:{ all -> 0x0045 }
        L_0x000d:
            r1 = 0;	 Catch:{ all -> 0x0045 }
        L_0x000e:
            r4 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ all -> 0x0045 }
            r4 = r4.closed;	 Catch:{ all -> 0x0045 }
            r1 = r1 | r4;	 Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0017;	 Catch:{ all -> 0x0045 }
        L_0x0015:
            monitor-exit(r0);	 Catch:{ all -> 0x0045 }
            return;
        L_0x0017:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x001d }
            r1.trimToSize();	 Catch:{ IOException -> 0x001d }
            goto L_0x0021;
        L_0x001d:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ all -> 0x0045 }
            r1.mostRecentTrimFailed = r3;	 Catch:{ all -> 0x0045 }
        L_0x0021:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x0033 }
            r1 = r1.journalRebuildRequired();	 Catch:{ IOException -> 0x0033 }
            if (r1 == 0) goto L_0x0043;	 Catch:{ IOException -> 0x0033 }
        L_0x0029:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x0033 }
            r1.rebuildJournal();	 Catch:{ IOException -> 0x0033 }
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x0033 }
            r1.redundantOpCount = r2;	 Catch:{ IOException -> 0x0033 }
            goto L_0x0043;
        L_0x0033:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ all -> 0x0045 }
            r1.mostRecentRebuildFailed = r3;	 Catch:{ all -> 0x0045 }
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ all -> 0x0045 }
            r2 = okio.Okio.blackhole();	 Catch:{ all -> 0x0045 }
            r2 = okio.Okio.buffer(r2);	 Catch:{ all -> 0x0045 }
            r1.journalWriter = r2;	 Catch:{ all -> 0x0045 }
        L_0x0043:
            monitor-exit(r0);	 Catch:{ all -> 0x0045 }
            return;	 Catch:{ all -> 0x0045 }
        L_0x0045:
            r1 = move-exception;	 Catch:{ all -> 0x0045 }
            monitor-exit(r0);	 Catch:{ all -> 0x0045 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.1.run():void");
        }
    }

    /* renamed from: okhttp3.internal.cache.DiskLruCache$3 */
    class C04383 implements Iterator<Snapshot> {
        final Iterator<Entry> delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
        Snapshot nextSnapshot;
        Snapshot removeSnapshot;

        public boolean hasNext() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0032 in {2, 9, 14, 17, 19, 22} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
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
            r4 = this;
            r0 = r4.nextSnapshot;
            r1 = 1;
            if (r0 == 0) goto L_0x0006;
        L_0x0005:
            return r1;
        L_0x0006:
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            monitor-enter(r0);
            r2 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ all -> 0x002f }
            r2 = r2.closed;	 Catch:{ all -> 0x002f }
            r3 = 0;	 Catch:{ all -> 0x002f }
            if (r2 == 0) goto L_0x0012;	 Catch:{ all -> 0x002f }
        L_0x0010:
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            return r3;	 Catch:{ all -> 0x002f }
        L_0x0012:
            r2 = r4.delegate;	 Catch:{ all -> 0x002f }
            r2 = r2.hasNext();	 Catch:{ all -> 0x002f }
            if (r2 == 0) goto L_0x002d;	 Catch:{ all -> 0x002f }
        L_0x001a:
            r2 = r4.delegate;	 Catch:{ all -> 0x002f }
            r2 = r2.next();	 Catch:{ all -> 0x002f }
            r2 = (okhttp3.internal.cache.DiskLruCache.Entry) r2;	 Catch:{ all -> 0x002f }
            r2 = r2.snapshot();	 Catch:{ all -> 0x002f }
            if (r2 != 0) goto L_0x0029;	 Catch:{ all -> 0x002f }
        L_0x0028:
            goto L_0x0012;	 Catch:{ all -> 0x002f }
        L_0x0029:
            r4.nextSnapshot = r2;	 Catch:{ all -> 0x002f }
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            return r1;	 Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            return r3;	 Catch:{ all -> 0x002f }
        L_0x002f:
            r1 = move-exception;	 Catch:{ all -> 0x002f }
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            throw r1;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.3.hasNext():boolean");
        }

        C04383() {
        }

        public Snapshot next() {
            if (hasNext()) {
                this.removeSnapshot = this.nextSnapshot;
                this.nextSnapshot = null;
                return this.removeSnapshot;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
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
            r3 = this;
            r0 = r3.removeSnapshot;
            if (r0 == 0) goto L_0x0016;
        L_0x0004:
            r1 = 0;
            r2 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x0013, all -> 0x000f }
            r0 = r0.key;	 Catch:{ IOException -> 0x0013, all -> 0x000f }
            r2.remove(r0);	 Catch:{ IOException -> 0x0013, all -> 0x000f }
            goto L_0x0013;
        L_0x000f:
            r0 = move-exception;
            r3.removeSnapshot = r1;
            throw r0;
        L_0x0013:
            r3.removeSnapshot = r1;
            return;
        L_0x0016:
            r0 = new java.lang.IllegalStateException;
            r1 = "remove() before next()";
            r0.<init>(r1);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.3.remove():void");
        }
    }

    public final class Editor {
        private boolean done;
        final Entry entry;
        final boolean[] written;

        Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable != null ? null : new boolean[DiskLruCache.this.valueCount];
        }

        void detach() {
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
            r3 = this;
            r0 = r3.entry;
            r0 = r0.currentEditor;
            if (r0 != r3) goto L_0x0022;
        L_0x0006:
            r0 = 0;
        L_0x0007:
            r1 = okhttp3.internal.cache.DiskLruCache.this;
            r1 = r1.valueCount;
            if (r0 >= r1) goto L_0x001d;
        L_0x000d:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x001a }
            r1 = r1.fileSystem;	 Catch:{ IOException -> 0x001a }
            r2 = r3.entry;	 Catch:{ IOException -> 0x001a }
            r2 = r2.dirtyFiles;	 Catch:{ IOException -> 0x001a }
            r2 = r2[r0];	 Catch:{ IOException -> 0x001a }
            r1.delete(r2);	 Catch:{ IOException -> 0x001a }
        L_0x001a:
            r0 = r0 + 1;
            goto L_0x0007;
        L_0x001d:
            r0 = r3.entry;
            r1 = 0;
            r0.currentEditor = r1;
        L_0x0022:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.detach():void");
        }

        public okio.Source newSource(int r5) {
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
            r4 = this;
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            monitor-enter(r0);
            r1 = r4.done;	 Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0029;	 Catch:{ all -> 0x002f }
        L_0x0007:
            r1 = r4.entry;	 Catch:{ all -> 0x002f }
            r1 = r1.readable;	 Catch:{ all -> 0x002f }
            r2 = 0;	 Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x0027;	 Catch:{ all -> 0x002f }
        L_0x000e:
            r1 = r4.entry;	 Catch:{ all -> 0x002f }
            r1 = r1.currentEditor;	 Catch:{ all -> 0x002f }
            if (r1 == r4) goto L_0x0015;
        L_0x0014:
            goto L_0x0027;
        L_0x0015:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ FileNotFoundException -> 0x0025 }
            r1 = r1.fileSystem;	 Catch:{ FileNotFoundException -> 0x0025 }
            r3 = r4.entry;	 Catch:{ FileNotFoundException -> 0x0025 }
            r3 = r3.cleanFiles;	 Catch:{ FileNotFoundException -> 0x0025 }
            r5 = r3[r5];	 Catch:{ FileNotFoundException -> 0x0025 }
            r5 = r1.source(r5);	 Catch:{ FileNotFoundException -> 0x0025 }
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            return r5;	 Catch:{ all -> 0x002f }
        L_0x0025:
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            return r2;	 Catch:{ all -> 0x002f }
        L_0x0027:
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            return r2;	 Catch:{ all -> 0x002f }
        L_0x0029:
            r5 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x002f }
            r5.<init>();	 Catch:{ all -> 0x002f }
            throw r5;	 Catch:{ all -> 0x002f }
        L_0x002f:
            r5 = move-exception;	 Catch:{ all -> 0x002f }
            monitor-exit(r0);	 Catch:{ all -> 0x002f }
            throw r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSource(int):okio.Source");
        }

        public okio.Sink newSink(int r4) {
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
            r3 = this;
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            monitor-enter(r0);
            r1 = r3.done;	 Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x0039;	 Catch:{ all -> 0x003f }
        L_0x0007:
            r1 = r3.entry;	 Catch:{ all -> 0x003f }
            r1 = r1.currentEditor;	 Catch:{ all -> 0x003f }
            if (r1 == r3) goto L_0x0013;	 Catch:{ all -> 0x003f }
        L_0x000d:
            r4 = okio.Okio.blackhole();	 Catch:{ all -> 0x003f }
            monitor-exit(r0);	 Catch:{ all -> 0x003f }
            return r4;	 Catch:{ all -> 0x003f }
        L_0x0013:
            r1 = r3.entry;	 Catch:{ all -> 0x003f }
            r1 = r1.readable;	 Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x001e;	 Catch:{ all -> 0x003f }
        L_0x0019:
            r1 = r3.written;	 Catch:{ all -> 0x003f }
            r2 = 1;	 Catch:{ all -> 0x003f }
            r1[r4] = r2;	 Catch:{ all -> 0x003f }
        L_0x001e:
            r1 = r3.entry;	 Catch:{ all -> 0x003f }
            r1 = r1.dirtyFiles;	 Catch:{ all -> 0x003f }
            r4 = r1[r4];	 Catch:{ all -> 0x003f }
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ FileNotFoundException -> 0x0033 }
            r1 = r1.fileSystem;	 Catch:{ FileNotFoundException -> 0x0033 }
            r4 = r1.sink(r4);	 Catch:{ FileNotFoundException -> 0x0033 }
            r1 = new okhttp3.internal.cache.DiskLruCache$Editor$1;	 Catch:{ all -> 0x003f }
            r1.<init>(r4);	 Catch:{ all -> 0x003f }
            monitor-exit(r0);	 Catch:{ all -> 0x003f }
            return r1;	 Catch:{ all -> 0x003f }
        L_0x0033:
            r4 = okio.Okio.blackhole();	 Catch:{ all -> 0x003f }
            monitor-exit(r0);	 Catch:{ all -> 0x003f }
            return r4;	 Catch:{ all -> 0x003f }
        L_0x0039:
            r4 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x003f }
            r4.<init>();	 Catch:{ all -> 0x003f }
            throw r4;	 Catch:{ all -> 0x003f }
        L_0x003f:
            r4 = move-exception;	 Catch:{ all -> 0x003f }
            monitor-exit(r0);	 Catch:{ all -> 0x003f }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSink(int):okio.Sink");
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.done = true;
            }
        }

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    DiskLruCache.this.completeEdit(this, false);
                }
                this.done = true;
            }
        }

        public void abortUnlessCommitted() {
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
            r3 = this;
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            monitor-enter(r0);
            r1 = r3.done;	 Catch:{ all -> 0x0015 }
            if (r1 != 0) goto L_0x0013;	 Catch:{ all -> 0x0015 }
        L_0x0007:
            r1 = r3.entry;	 Catch:{ all -> 0x0015 }
            r1 = r1.currentEditor;	 Catch:{ all -> 0x0015 }
            if (r1 != r3) goto L_0x0013;
        L_0x000d:
            r1 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x0013 }
            r2 = 0;	 Catch:{ IOException -> 0x0013 }
            r1.completeEdit(r3, r2);	 Catch:{ IOException -> 0x0013 }
        L_0x0013:
            monitor-exit(r0);	 Catch:{ all -> 0x0015 }
            return;	 Catch:{ all -> 0x0015 }
        L_0x0015:
            r1 = move-exception;	 Catch:{ all -> 0x0015 }
            monitor-exit(r0);	 Catch:{ all -> 0x0015 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.abortUnlessCommitted():void");
        }
    }

    private final class Entry {
        final File[] cleanFiles;
        Editor currentEditor;
        final File[] dirtyFiles;
        final String key;
        final long[] lengths;
        boolean readable;
        long sequenceNumber;

        void setLengths(java.lang.String[] r5) throws java.io.IOException {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:13:0x0023 in {7, 8, 10, 12} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
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
            r4 = this;
            r0 = r5.length;
            r1 = okhttp3.internal.cache.DiskLruCache.this;
            r1 = r1.valueCount;
            if (r0 != r1) goto L_0x001e;
        L_0x0007:
            r0 = 0;
        L_0x0008:
            r1 = r5.length;	 Catch:{ NumberFormatException -> 0x0019 }
            if (r0 >= r1) goto L_0x0018;	 Catch:{ NumberFormatException -> 0x0019 }
        L_0x000b:
            r1 = r4.lengths;	 Catch:{ NumberFormatException -> 0x0019 }
            r2 = r5[r0];	 Catch:{ NumberFormatException -> 0x0019 }
            r2 = java.lang.Long.parseLong(r2);	 Catch:{ NumberFormatException -> 0x0019 }
            r1[r0] = r2;	 Catch:{ NumberFormatException -> 0x0019 }
            r0 = r0 + 1;
            goto L_0x0008;
        L_0x0018:
            return;
        L_0x0019:
            r5 = r4.invalidLengths(r5);
            throw r5;
        L_0x001e:
            r5 = r4.invalidLengths(r5);
            throw r5;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Entry.setLengths(java.lang.String[]):void");
        }

        okhttp3.internal.cache.DiskLruCache.Snapshot snapshot() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:20:0x005d in {6, 8, 13, 15, 17, 19} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
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
            r10 = this;
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            r0 = java.lang.Thread.holdsLock(r0);
            if (r0 == 0) goto L_0x0057;
        L_0x0008:
            r0 = okhttp3.internal.cache.DiskLruCache.this;
            r0 = r0.valueCount;
            r0 = new okio.Source[r0];
            r1 = r10.lengths;
            r1 = r1.clone();
            r7 = r1;
            r7 = (long[]) r7;
            r8 = 0;
            r1 = 0;
        L_0x0019:
            r2 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ FileNotFoundException -> 0x003e }
            r2 = r2.valueCount;	 Catch:{ FileNotFoundException -> 0x003e }
            if (r1 >= r2) goto L_0x0030;	 Catch:{ FileNotFoundException -> 0x003e }
        L_0x001f:
            r2 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ FileNotFoundException -> 0x003e }
            r2 = r2.fileSystem;	 Catch:{ FileNotFoundException -> 0x003e }
            r3 = r10.cleanFiles;	 Catch:{ FileNotFoundException -> 0x003e }
            r3 = r3[r1];	 Catch:{ FileNotFoundException -> 0x003e }
            r2 = r2.source(r3);	 Catch:{ FileNotFoundException -> 0x003e }
            r0[r1] = r2;	 Catch:{ FileNotFoundException -> 0x003e }
            r1 = r1 + 1;	 Catch:{ FileNotFoundException -> 0x003e }
            goto L_0x0019;	 Catch:{ FileNotFoundException -> 0x003e }
        L_0x0030:
            r9 = new okhttp3.internal.cache.DiskLruCache$Snapshot;	 Catch:{ FileNotFoundException -> 0x003e }
            r2 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ FileNotFoundException -> 0x003e }
            r3 = r10.key;	 Catch:{ FileNotFoundException -> 0x003e }
            r4 = r10.sequenceNumber;	 Catch:{ FileNotFoundException -> 0x003e }
            r1 = r9;	 Catch:{ FileNotFoundException -> 0x003e }
            r6 = r0;	 Catch:{ FileNotFoundException -> 0x003e }
            r1.<init>(r3, r4, r6, r7);	 Catch:{ FileNotFoundException -> 0x003e }
            return r9;
        L_0x003e:
            r1 = okhttp3.internal.cache.DiskLruCache.this;
            r1 = r1.valueCount;
            if (r8 >= r1) goto L_0x0050;
        L_0x0044:
            r1 = r0[r8];
            if (r1 == 0) goto L_0x0050;
        L_0x0048:
            r1 = r0[r8];
            okhttp3.internal.Util.closeQuietly(r1);
            r8 = r8 + 1;
            goto L_0x003e;
        L_0x0050:
            r0 = okhttp3.internal.cache.DiskLruCache.this;	 Catch:{ IOException -> 0x0055 }
            r0.removeEntry(r10);	 Catch:{ IOException -> 0x0055 }
        L_0x0055:
            r0 = 0;
            return r0;
        L_0x0057:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Entry.snapshot():okhttp3.internal.cache.DiskLruCache$Snapshot");
        }

        Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append('.');
            str = stringBuilder.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                stringBuilder.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, stringBuilder.toString());
                stringBuilder.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, stringBuilder.toString());
                stringBuilder.setLength(str);
            }
        }

        void writeLengths(BufferedSink bufferedSink) throws IOException {
            for (long writeDecimalLong : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(writeDecimalLong);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unexpected journal line: ");
            stringBuilder.append(Arrays.toString(strArr));
            throw new IOException(stringBuilder.toString());
        }
    }

    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;

        Snapshot(String str, long j, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = sourceArr;
            this.lengths = jArr;
        }

        public String key() {
            return this.key;
        }

        @Nullable
        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public Source getSource(int i) {
            return this.sources[i];
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        public void close() {
            for (Closeable closeQuietly : this.sources) {
                Util.closeQuietly(closeQuietly);
            }
        }
    }

    private void readJournal() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x00b1 in {15, 19, 20, 22, 25, 28} preds:[]
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
        r0 = r8.fileSystem;
        r1 = r8.journalFile;
        r0 = r0.source(r1);
        r0 = okio.Okio.buffer(r0);
        r1 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00ac }
        r2 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00ac }
        r3 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00ac }
        r4 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00ac }
        r5 = r0.readUtf8LineStrict();	 Catch:{ all -> 0x00ac }
        r6 = "libcore.io.DiskLruCache";	 Catch:{ all -> 0x00ac }
        r6 = r6.equals(r1);	 Catch:{ all -> 0x00ac }
        if (r6 == 0) goto L_0x0078;	 Catch:{ all -> 0x00ac }
    L_0x0028:
        r6 = "1";	 Catch:{ all -> 0x00ac }
        r6 = r6.equals(r2);	 Catch:{ all -> 0x00ac }
        if (r6 == 0) goto L_0x0078;	 Catch:{ all -> 0x00ac }
    L_0x0030:
        r6 = r8.appVersion;	 Catch:{ all -> 0x00ac }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ all -> 0x00ac }
        r3 = r6.equals(r3);	 Catch:{ all -> 0x00ac }
        if (r3 == 0) goto L_0x0078;	 Catch:{ all -> 0x00ac }
    L_0x003c:
        r3 = r8.valueCount;	 Catch:{ all -> 0x00ac }
        r3 = java.lang.Integer.toString(r3);	 Catch:{ all -> 0x00ac }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x00ac }
        if (r3 == 0) goto L_0x0078;	 Catch:{ all -> 0x00ac }
    L_0x0048:
        r3 = "";	 Catch:{ all -> 0x00ac }
        r3 = r3.equals(r5);	 Catch:{ all -> 0x00ac }
        if (r3 == 0) goto L_0x0078;
    L_0x0050:
        r1 = 0;
    L_0x0051:
        r2 = r0.readUtf8LineStrict();	 Catch:{ EOFException -> 0x005b }
        r8.readJournalLine(r2);	 Catch:{ EOFException -> 0x005b }
        r1 = r1 + 1;
        goto L_0x0051;
    L_0x005b:
        r2 = r8.lruEntries;	 Catch:{ all -> 0x00ac }
        r2 = r2.size();	 Catch:{ all -> 0x00ac }
        r1 = r1 - r2;	 Catch:{ all -> 0x00ac }
        r8.redundantOpCount = r1;	 Catch:{ all -> 0x00ac }
        r1 = r0.exhausted();	 Catch:{ all -> 0x00ac }
        if (r1 != 0) goto L_0x006e;	 Catch:{ all -> 0x00ac }
    L_0x006a:
        r8.rebuildJournal();	 Catch:{ all -> 0x00ac }
        goto L_0x0074;	 Catch:{ all -> 0x00ac }
    L_0x006e:
        r1 = r8.newJournalWriter();	 Catch:{ all -> 0x00ac }
        r8.journalWriter = r1;	 Catch:{ all -> 0x00ac }
    L_0x0074:
        okhttp3.internal.Util.closeQuietly(r0);
        return;
    L_0x0078:
        r3 = new java.io.IOException;	 Catch:{ all -> 0x00ac }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ac }
        r6.<init>();	 Catch:{ all -> 0x00ac }
        r7 = "unexpected journal header: [";	 Catch:{ all -> 0x00ac }
        r6.append(r7);	 Catch:{ all -> 0x00ac }
        r6.append(r1);	 Catch:{ all -> 0x00ac }
        r1 = ", ";	 Catch:{ all -> 0x00ac }
        r6.append(r1);	 Catch:{ all -> 0x00ac }
        r6.append(r2);	 Catch:{ all -> 0x00ac }
        r1 = ", ";	 Catch:{ all -> 0x00ac }
        r6.append(r1);	 Catch:{ all -> 0x00ac }
        r6.append(r4);	 Catch:{ all -> 0x00ac }
        r1 = ", ";	 Catch:{ all -> 0x00ac }
        r6.append(r1);	 Catch:{ all -> 0x00ac }
        r6.append(r5);	 Catch:{ all -> 0x00ac }
        r1 = "]";	 Catch:{ all -> 0x00ac }
        r6.append(r1);	 Catch:{ all -> 0x00ac }
        r1 = r6.toString();	 Catch:{ all -> 0x00ac }
        r3.<init>(r1);	 Catch:{ all -> 0x00ac }
        throw r3;	 Catch:{ all -> 0x00ac }
    L_0x00ac:
        r1 = move-exception;
        okhttp3.internal.Util.closeQuietly(r0);
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.readJournal():void");
    }

    public synchronized void close() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0047 in {6, 11, 12, 15, 19, 22} preds:[]
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
        r6 = this;
        monitor-enter(r6);
        r0 = r6.initialized;	 Catch:{ all -> 0x0044 }
        r1 = 1;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x0040;	 Catch:{ all -> 0x0044 }
    L_0x0006:
        r0 = r6.closed;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x000b;	 Catch:{ all -> 0x0044 }
    L_0x000a:
        goto L_0x0040;	 Catch:{ all -> 0x0044 }
    L_0x000b:
        r0 = r6.lruEntries;	 Catch:{ all -> 0x0044 }
        r0 = r0.values();	 Catch:{ all -> 0x0044 }
        r2 = r6.lruEntries;	 Catch:{ all -> 0x0044 }
        r2 = r2.size();	 Catch:{ all -> 0x0044 }
        r2 = new okhttp3.internal.cache.DiskLruCache.Entry[r2];	 Catch:{ all -> 0x0044 }
        r0 = r0.toArray(r2);	 Catch:{ all -> 0x0044 }
        r0 = (okhttp3.internal.cache.DiskLruCache.Entry[]) r0;	 Catch:{ all -> 0x0044 }
        r2 = r0.length;	 Catch:{ all -> 0x0044 }
        r3 = 0;	 Catch:{ all -> 0x0044 }
    L_0x0021:
        if (r3 >= r2) goto L_0x0031;	 Catch:{ all -> 0x0044 }
    L_0x0023:
        r4 = r0[r3];	 Catch:{ all -> 0x0044 }
        r5 = r4.currentEditor;	 Catch:{ all -> 0x0044 }
        if (r5 == 0) goto L_0x002e;	 Catch:{ all -> 0x0044 }
    L_0x0029:
        r4 = r4.currentEditor;	 Catch:{ all -> 0x0044 }
        r4.abort();	 Catch:{ all -> 0x0044 }
    L_0x002e:
        r3 = r3 + 1;	 Catch:{ all -> 0x0044 }
        goto L_0x0021;	 Catch:{ all -> 0x0044 }
    L_0x0031:
        r6.trimToSize();	 Catch:{ all -> 0x0044 }
        r0 = r6.journalWriter;	 Catch:{ all -> 0x0044 }
        r0.close();	 Catch:{ all -> 0x0044 }
        r0 = 0;	 Catch:{ all -> 0x0044 }
        r6.journalWriter = r0;	 Catch:{ all -> 0x0044 }
        r6.closed = r1;	 Catch:{ all -> 0x0044 }
        monitor-exit(r6);
        return;
    L_0x0040:
        r6.closed = r1;	 Catch:{ all -> 0x0044 }
        monitor-exit(r6);
        return;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.close():void");
    }

    synchronized void completeEdit(okhttp3.internal.cache.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:50:0x00fe in {17, 18, 21, 28, 29, 30, 35, 36, 40, 41, 43, 46, 49} preds:[]
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
        r9 = this;
        monitor-enter(r9);
        r0 = r10.entry;	 Catch:{ all -> 0x00fb }
        r1 = r0.currentEditor;	 Catch:{ all -> 0x00fb }
        if (r1 != r10) goto L_0x00f5;	 Catch:{ all -> 0x00fb }
    L_0x0007:
        r1 = 0;	 Catch:{ all -> 0x00fb }
        if (r11 == 0) goto L_0x0047;	 Catch:{ all -> 0x00fb }
    L_0x000a:
        r2 = r0.readable;	 Catch:{ all -> 0x00fb }
        if (r2 != 0) goto L_0x0047;	 Catch:{ all -> 0x00fb }
    L_0x000e:
        r2 = 0;	 Catch:{ all -> 0x00fb }
    L_0x000f:
        r3 = r9.valueCount;	 Catch:{ all -> 0x00fb }
        if (r2 >= r3) goto L_0x0047;	 Catch:{ all -> 0x00fb }
    L_0x0013:
        r3 = r10.written;	 Catch:{ all -> 0x00fb }
        r3 = r3[r2];	 Catch:{ all -> 0x00fb }
        if (r3 == 0) goto L_0x002d;	 Catch:{ all -> 0x00fb }
    L_0x0019:
        r3 = r9.fileSystem;	 Catch:{ all -> 0x00fb }
        r4 = r0.dirtyFiles;	 Catch:{ all -> 0x00fb }
        r4 = r4[r2];	 Catch:{ all -> 0x00fb }
        r3 = r3.exists(r4);	 Catch:{ all -> 0x00fb }
        if (r3 != 0) goto L_0x002a;	 Catch:{ all -> 0x00fb }
    L_0x0025:
        r10.abort();	 Catch:{ all -> 0x00fb }
        monitor-exit(r9);
        return;
    L_0x002a:
        r2 = r2 + 1;
        goto L_0x000f;
    L_0x002d:
        r10.abort();	 Catch:{ all -> 0x00fb }
        r10 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x00fb }
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fb }
        r11.<init>();	 Catch:{ all -> 0x00fb }
        r0 = "Newly created entry didn't create value for index ";	 Catch:{ all -> 0x00fb }
        r11.append(r0);	 Catch:{ all -> 0x00fb }
        r11.append(r2);	 Catch:{ all -> 0x00fb }
        r11 = r11.toString();	 Catch:{ all -> 0x00fb }
        r10.<init>(r11);	 Catch:{ all -> 0x00fb }
        throw r10;	 Catch:{ all -> 0x00fb }
    L_0x0047:
        r10 = r9.valueCount;	 Catch:{ all -> 0x00fb }
        if (r1 >= r10) goto L_0x007f;	 Catch:{ all -> 0x00fb }
    L_0x004b:
        r10 = r0.dirtyFiles;	 Catch:{ all -> 0x00fb }
        r10 = r10[r1];	 Catch:{ all -> 0x00fb }
        if (r11 == 0) goto L_0x0077;	 Catch:{ all -> 0x00fb }
    L_0x0051:
        r2 = r9.fileSystem;	 Catch:{ all -> 0x00fb }
        r2 = r2.exists(r10);	 Catch:{ all -> 0x00fb }
        if (r2 == 0) goto L_0x007c;	 Catch:{ all -> 0x00fb }
    L_0x0059:
        r2 = r0.cleanFiles;	 Catch:{ all -> 0x00fb }
        r2 = r2[r1];	 Catch:{ all -> 0x00fb }
        r3 = r9.fileSystem;	 Catch:{ all -> 0x00fb }
        r3.rename(r10, r2);	 Catch:{ all -> 0x00fb }
        r10 = r0.lengths;	 Catch:{ all -> 0x00fb }
        r3 = r10[r1];	 Catch:{ all -> 0x00fb }
        r10 = r9.fileSystem;	 Catch:{ all -> 0x00fb }
        r5 = r10.size(r2);	 Catch:{ all -> 0x00fb }
        r10 = r0.lengths;	 Catch:{ all -> 0x00fb }
        r10[r1] = r5;	 Catch:{ all -> 0x00fb }
        r7 = r9.size;	 Catch:{ all -> 0x00fb }
        r7 = r7 - r3;	 Catch:{ all -> 0x00fb }
        r7 = r7 + r5;	 Catch:{ all -> 0x00fb }
        r9.size = r7;	 Catch:{ all -> 0x00fb }
        goto L_0x007c;	 Catch:{ all -> 0x00fb }
    L_0x0077:
        r2 = r9.fileSystem;	 Catch:{ all -> 0x00fb }
        r2.delete(r10);	 Catch:{ all -> 0x00fb }
    L_0x007c:
        r1 = r1 + 1;	 Catch:{ all -> 0x00fb }
        goto L_0x0047;	 Catch:{ all -> 0x00fb }
    L_0x007f:
        r10 = r9.redundantOpCount;	 Catch:{ all -> 0x00fb }
        r1 = 1;	 Catch:{ all -> 0x00fb }
        r10 = r10 + r1;	 Catch:{ all -> 0x00fb }
        r9.redundantOpCount = r10;	 Catch:{ all -> 0x00fb }
        r10 = 0;	 Catch:{ all -> 0x00fb }
        r0.currentEditor = r10;	 Catch:{ all -> 0x00fb }
        r10 = r0.readable;	 Catch:{ all -> 0x00fb }
        r10 = r10 | r11;	 Catch:{ all -> 0x00fb }
        r2 = 10;	 Catch:{ all -> 0x00fb }
        r3 = 32;	 Catch:{ all -> 0x00fb }
        if (r10 == 0) goto L_0x00bb;	 Catch:{ all -> 0x00fb }
    L_0x0091:
        r0.readable = r1;	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r1 = "CLEAN";	 Catch:{ all -> 0x00fb }
        r10 = r10.writeUtf8(r1);	 Catch:{ all -> 0x00fb }
        r10.writeByte(r3);	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r1 = r0.key;	 Catch:{ all -> 0x00fb }
        r10.writeUtf8(r1);	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r0.writeLengths(r10);	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r10.writeByte(r2);	 Catch:{ all -> 0x00fb }
        if (r11 == 0) goto L_0x00d9;	 Catch:{ all -> 0x00fb }
    L_0x00b1:
        r10 = r9.nextSequenceNumber;	 Catch:{ all -> 0x00fb }
        r1 = 1;	 Catch:{ all -> 0x00fb }
        r1 = r1 + r10;	 Catch:{ all -> 0x00fb }
        r9.nextSequenceNumber = r1;	 Catch:{ all -> 0x00fb }
        r0.sequenceNumber = r10;	 Catch:{ all -> 0x00fb }
        goto L_0x00d9;	 Catch:{ all -> 0x00fb }
    L_0x00bb:
        r10 = r9.lruEntries;	 Catch:{ all -> 0x00fb }
        r11 = r0.key;	 Catch:{ all -> 0x00fb }
        r10.remove(r11);	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r11 = "REMOVE";	 Catch:{ all -> 0x00fb }
        r10 = r10.writeUtf8(r11);	 Catch:{ all -> 0x00fb }
        r10.writeByte(r3);	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r11 = r0.key;	 Catch:{ all -> 0x00fb }
        r10.writeUtf8(r11);	 Catch:{ all -> 0x00fb }
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r10.writeByte(r2);	 Catch:{ all -> 0x00fb }
    L_0x00d9:
        r10 = r9.journalWriter;	 Catch:{ all -> 0x00fb }
        r10.flush();	 Catch:{ all -> 0x00fb }
        r10 = r9.size;	 Catch:{ all -> 0x00fb }
        r0 = r9.maxSize;	 Catch:{ all -> 0x00fb }
        r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));	 Catch:{ all -> 0x00fb }
        if (r2 > 0) goto L_0x00ec;	 Catch:{ all -> 0x00fb }
    L_0x00e6:
        r10 = r9.journalRebuildRequired();	 Catch:{ all -> 0x00fb }
        if (r10 == 0) goto L_0x00f3;	 Catch:{ all -> 0x00fb }
    L_0x00ec:
        r10 = r9.executor;	 Catch:{ all -> 0x00fb }
        r11 = r9.cleanupRunnable;	 Catch:{ all -> 0x00fb }
        r10.execute(r11);	 Catch:{ all -> 0x00fb }
    L_0x00f3:
        monitor-exit(r9);
        return;
    L_0x00f5:
        r10 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x00fb }
        r10.<init>();	 Catch:{ all -> 0x00fb }
        throw r10;	 Catch:{ all -> 0x00fb }
    L_0x00fb:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.completeEdit(okhttp3.internal.cache.DiskLruCache$Editor, boolean):void");
    }

    public synchronized void evictAll() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:11:0x002c in {4, 7, 10} preds:[]
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
        r5 = this;
        monitor-enter(r5);
        r5.initialize();	 Catch:{ all -> 0x0029 }
        r0 = r5.lruEntries;	 Catch:{ all -> 0x0029 }
        r0 = r0.values();	 Catch:{ all -> 0x0029 }
        r1 = r5.lruEntries;	 Catch:{ all -> 0x0029 }
        r1 = r1.size();	 Catch:{ all -> 0x0029 }
        r1 = new okhttp3.internal.cache.DiskLruCache.Entry[r1];	 Catch:{ all -> 0x0029 }
        r0 = r0.toArray(r1);	 Catch:{ all -> 0x0029 }
        r0 = (okhttp3.internal.cache.DiskLruCache.Entry[]) r0;	 Catch:{ all -> 0x0029 }
        r1 = r0.length;	 Catch:{ all -> 0x0029 }
        r2 = 0;	 Catch:{ all -> 0x0029 }
        r3 = 0;	 Catch:{ all -> 0x0029 }
    L_0x001b:
        if (r3 >= r1) goto L_0x0025;	 Catch:{ all -> 0x0029 }
    L_0x001d:
        r4 = r0[r3];	 Catch:{ all -> 0x0029 }
        r5.removeEntry(r4);	 Catch:{ all -> 0x0029 }
        r3 = r3 + 1;	 Catch:{ all -> 0x0029 }
        goto L_0x001b;	 Catch:{ all -> 0x0029 }
    L_0x0025:
        r5.mostRecentTrimFailed = r2;	 Catch:{ all -> 0x0029 }
        monitor-exit(r5);
        return;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.evictAll():void");
    }

    synchronized void rebuildJournal() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x00bf in {4, 12, 14, 18, 21, 25, 28} preds:[]
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
        r6 = this;
        monitor-enter(r6);
        r0 = r6.journalWriter;	 Catch:{ all -> 0x00bc }
        if (r0 == 0) goto L_0x000a;	 Catch:{ all -> 0x00bc }
    L_0x0005:
        r0 = r6.journalWriter;	 Catch:{ all -> 0x00bc }
        r0.close();	 Catch:{ all -> 0x00bc }
    L_0x000a:
        r0 = r6.fileSystem;	 Catch:{ all -> 0x00bc }
        r1 = r6.journalFileTmp;	 Catch:{ all -> 0x00bc }
        r0 = r0.sink(r1);	 Catch:{ all -> 0x00bc }
        r0 = okio.Okio.buffer(r0);	 Catch:{ all -> 0x00bc }
        r1 = "libcore.io.DiskLruCache";	 Catch:{ all -> 0x00b7 }
        r1 = r0.writeUtf8(r1);	 Catch:{ all -> 0x00b7 }
        r2 = 10;	 Catch:{ all -> 0x00b7 }
        r1.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        r1 = "1";	 Catch:{ all -> 0x00b7 }
        r1 = r0.writeUtf8(r1);	 Catch:{ all -> 0x00b7 }
        r1.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        r1 = r6.appVersion;	 Catch:{ all -> 0x00b7 }
        r3 = (long) r1;	 Catch:{ all -> 0x00b7 }
        r1 = r0.writeDecimalLong(r3);	 Catch:{ all -> 0x00b7 }
        r1.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        r1 = r6.valueCount;	 Catch:{ all -> 0x00b7 }
        r3 = (long) r1;	 Catch:{ all -> 0x00b7 }
        r1 = r0.writeDecimalLong(r3);	 Catch:{ all -> 0x00b7 }
        r1.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        r0.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        r1 = r6.lruEntries;	 Catch:{ all -> 0x00b7 }
        r1 = r1.values();	 Catch:{ all -> 0x00b7 }
        r1 = r1.iterator();	 Catch:{ all -> 0x00b7 }
    L_0x004b:
        r3 = r1.hasNext();	 Catch:{ all -> 0x00b7 }
        if (r3 == 0) goto L_0x0084;	 Catch:{ all -> 0x00b7 }
    L_0x0051:
        r3 = r1.next();	 Catch:{ all -> 0x00b7 }
        r3 = (okhttp3.internal.cache.DiskLruCache.Entry) r3;	 Catch:{ all -> 0x00b7 }
        r4 = r3.currentEditor;	 Catch:{ all -> 0x00b7 }
        r5 = 32;	 Catch:{ all -> 0x00b7 }
        if (r4 == 0) goto L_0x006f;	 Catch:{ all -> 0x00b7 }
    L_0x005d:
        r4 = "DIRTY";	 Catch:{ all -> 0x00b7 }
        r4 = r0.writeUtf8(r4);	 Catch:{ all -> 0x00b7 }
        r4.writeByte(r5);	 Catch:{ all -> 0x00b7 }
        r3 = r3.key;	 Catch:{ all -> 0x00b7 }
        r0.writeUtf8(r3);	 Catch:{ all -> 0x00b7 }
        r0.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        goto L_0x004b;	 Catch:{ all -> 0x00b7 }
    L_0x006f:
        r4 = "CLEAN";	 Catch:{ all -> 0x00b7 }
        r4 = r0.writeUtf8(r4);	 Catch:{ all -> 0x00b7 }
        r4.writeByte(r5);	 Catch:{ all -> 0x00b7 }
        r4 = r3.key;	 Catch:{ all -> 0x00b7 }
        r0.writeUtf8(r4);	 Catch:{ all -> 0x00b7 }
        r3.writeLengths(r0);	 Catch:{ all -> 0x00b7 }
        r0.writeByte(r2);	 Catch:{ all -> 0x00b7 }
        goto L_0x004b;
    L_0x0084:
        r0.close();	 Catch:{ all -> 0x00bc }
        r0 = r6.fileSystem;	 Catch:{ all -> 0x00bc }
        r1 = r6.journalFile;	 Catch:{ all -> 0x00bc }
        r0 = r0.exists(r1);	 Catch:{ all -> 0x00bc }
        if (r0 == 0) goto L_0x009a;	 Catch:{ all -> 0x00bc }
    L_0x0091:
        r0 = r6.fileSystem;	 Catch:{ all -> 0x00bc }
        r1 = r6.journalFile;	 Catch:{ all -> 0x00bc }
        r2 = r6.journalFileBackup;	 Catch:{ all -> 0x00bc }
        r0.rename(r1, r2);	 Catch:{ all -> 0x00bc }
    L_0x009a:
        r0 = r6.fileSystem;	 Catch:{ all -> 0x00bc }
        r1 = r6.journalFileTmp;	 Catch:{ all -> 0x00bc }
        r2 = r6.journalFile;	 Catch:{ all -> 0x00bc }
        r0.rename(r1, r2);	 Catch:{ all -> 0x00bc }
        r0 = r6.fileSystem;	 Catch:{ all -> 0x00bc }
        r1 = r6.journalFileBackup;	 Catch:{ all -> 0x00bc }
        r0.delete(r1);	 Catch:{ all -> 0x00bc }
        r0 = r6.newJournalWriter();	 Catch:{ all -> 0x00bc }
        r6.journalWriter = r0;	 Catch:{ all -> 0x00bc }
        r0 = 0;	 Catch:{ all -> 0x00bc }
        r6.hasJournalErrors = r0;	 Catch:{ all -> 0x00bc }
        r6.mostRecentRebuildFailed = r0;	 Catch:{ all -> 0x00bc }
        monitor-exit(r6);
        return;
    L_0x00b7:
        r1 = move-exception;
        r0.close();	 Catch:{ all -> 0x00bc }
        throw r1;	 Catch:{ all -> 0x00bc }
    L_0x00bc:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.rebuildJournal():void");
    }

    DiskLruCache(FileSystem fileSystem, File file, int i, int i2, long j, Executor executor) {
        this.fileSystem = fileSystem;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor;
    }

    public synchronized void initialize() throws IOException {
        if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (Throwable e) {
                    Platform platform = Platform.get();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("DiskLruCache ");
                    stringBuilder.append(this.directory);
                    stringBuilder.append(" is corrupt: ");
                    stringBuilder.append(e.getMessage());
                    stringBuilder.append(", removing");
                    platform.log(5, stringBuilder.toString(), e);
                    delete();
                } finally {
                    this.closed = false;
                }
            }
            rebuildJournal();
            this.initialized = true;
        }
    }

    public static DiskLruCache create(FileSystem fileSystem, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            return new DiskLruCache(fileSystem, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class cls = DiskLruCache.class;
            }

            protected void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }

    private void readJournalLine(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            String substring;
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                substring = str.substring(i);
                if (indexOf == 6 && str.startsWith(REMOVE)) {
                    this.lruEntries.remove(substring);
                    return;
                }
            }
            substring = str.substring(i, indexOf2);
            Entry entry = (Entry) this.lruEntries.get(substring);
            if (entry == null) {
                entry = new Entry(substring);
                this.lruEntries.put(substring, entry);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith(CLEAN)) {
                str = str.substring(indexOf2 + 1).split(" ");
                entry.readable = true;
                entry.currentEditor = null;
                entry.setLengths(str);
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith(DIRTY)) {
                entry.currentEditor = new Editor(entry);
            } else if (!(indexOf2 == -1 && indexOf == 4 && str.startsWith(READ))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unexpected journal line: ");
                stringBuilder.append(str);
                throw new IOException(stringBuilder.toString());
            }
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("unexpected journal line: ");
        stringBuilder.append(str);
        throw new IOException(stringBuilder.toString());
    }

    private void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int i = 0;
            if (entry.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += entry.lengths[i];
                    i++;
                }
            } else {
                entry.currentEditor = null;
                while (i < this.valueCount) {
                    this.fileSystem.delete(entry.cleanFiles[i]);
                    this.fileSystem.delete(entry.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized okhttp3.internal.cache.DiskLruCache.Snapshot get(java.lang.String r4) throws java.io.IOException {
        /*
        r3 = this;
        monitor-enter(r3);
        r3.initialize();	 Catch:{ all -> 0x0050 }
        r3.checkNotClosed();	 Catch:{ all -> 0x0050 }
        r3.validateKey(r4);	 Catch:{ all -> 0x0050 }
        r0 = r3.lruEntries;	 Catch:{ all -> 0x0050 }
        r0 = r0.get(r4);	 Catch:{ all -> 0x0050 }
        r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0;	 Catch:{ all -> 0x0050 }
        r1 = 0;
        if (r0 == 0) goto L_0x004e;
    L_0x0015:
        r2 = r0.readable;	 Catch:{ all -> 0x0050 }
        if (r2 != 0) goto L_0x001a;
    L_0x0019:
        goto L_0x004e;
    L_0x001a:
        r0 = r0.snapshot();	 Catch:{ all -> 0x0050 }
        if (r0 != 0) goto L_0x0022;
    L_0x0020:
        monitor-exit(r3);
        return r1;
    L_0x0022:
        r1 = r3.redundantOpCount;	 Catch:{ all -> 0x0050 }
        r1 = r1 + 1;
        r3.redundantOpCount = r1;	 Catch:{ all -> 0x0050 }
        r1 = r3.journalWriter;	 Catch:{ all -> 0x0050 }
        r2 = "READ";
        r1 = r1.writeUtf8(r2);	 Catch:{ all -> 0x0050 }
        r2 = 32;
        r1 = r1.writeByte(r2);	 Catch:{ all -> 0x0050 }
        r4 = r1.writeUtf8(r4);	 Catch:{ all -> 0x0050 }
        r1 = 10;
        r4.writeByte(r1);	 Catch:{ all -> 0x0050 }
        r4 = r3.journalRebuildRequired();	 Catch:{ all -> 0x0050 }
        if (r4 == 0) goto L_0x004c;
    L_0x0045:
        r4 = r3.executor;	 Catch:{ all -> 0x0050 }
        r1 = r3.cleanupRunnable;	 Catch:{ all -> 0x0050 }
        r4.execute(r1);	 Catch:{ all -> 0x0050 }
    L_0x004c:
        monitor-exit(r3);
        return r0;
    L_0x004e:
        monitor-exit(r3);
        return r1;
    L_0x0050:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.get(java.lang.String):okhttp3.internal.cache.DiskLruCache$Snapshot");
    }

    @Nullable
    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    synchronized okhttp3.internal.cache.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r5.initialize();	 Catch:{ all -> 0x0074 }
        r5.checkNotClosed();	 Catch:{ all -> 0x0074 }
        r5.validateKey(r6);	 Catch:{ all -> 0x0074 }
        r0 = r5.lruEntries;	 Catch:{ all -> 0x0074 }
        r0 = r0.get(r6);	 Catch:{ all -> 0x0074 }
        r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0;	 Catch:{ all -> 0x0074 }
        r1 = -1;
        r3 = 0;
        r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1));
        if (r4 == 0) goto L_0x0023;
    L_0x0019:
        if (r0 == 0) goto L_0x0021;
    L_0x001b:
        r1 = r0.sequenceNumber;	 Catch:{ all -> 0x0074 }
        r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1));
        if (r4 == 0) goto L_0x0023;
    L_0x0021:
        monitor-exit(r5);
        return r3;
    L_0x0023:
        if (r0 == 0) goto L_0x002b;
    L_0x0025:
        r7 = r0.currentEditor;	 Catch:{ all -> 0x0074 }
        if (r7 == 0) goto L_0x002b;
    L_0x0029:
        monitor-exit(r5);
        return r3;
    L_0x002b:
        r7 = r5.mostRecentTrimFailed;	 Catch:{ all -> 0x0074 }
        if (r7 != 0) goto L_0x006b;
    L_0x002f:
        r7 = r5.mostRecentRebuildFailed;	 Catch:{ all -> 0x0074 }
        if (r7 == 0) goto L_0x0034;
    L_0x0033:
        goto L_0x006b;
    L_0x0034:
        r7 = r5.journalWriter;	 Catch:{ all -> 0x0074 }
        r8 = "DIRTY";
        r7 = r7.writeUtf8(r8);	 Catch:{ all -> 0x0074 }
        r8 = 32;
        r7 = r7.writeByte(r8);	 Catch:{ all -> 0x0074 }
        r7 = r7.writeUtf8(r6);	 Catch:{ all -> 0x0074 }
        r8 = 10;
        r7.writeByte(r8);	 Catch:{ all -> 0x0074 }
        r7 = r5.journalWriter;	 Catch:{ all -> 0x0074 }
        r7.flush();	 Catch:{ all -> 0x0074 }
        r7 = r5.hasJournalErrors;	 Catch:{ all -> 0x0074 }
        if (r7 == 0) goto L_0x0056;
    L_0x0054:
        monitor-exit(r5);
        return r3;
    L_0x0056:
        if (r0 != 0) goto L_0x0062;
    L_0x0058:
        r0 = new okhttp3.internal.cache.DiskLruCache$Entry;	 Catch:{ all -> 0x0074 }
        r0.<init>(r6);	 Catch:{ all -> 0x0074 }
        r7 = r5.lruEntries;	 Catch:{ all -> 0x0074 }
        r7.put(r6, r0);	 Catch:{ all -> 0x0074 }
    L_0x0062:
        r6 = new okhttp3.internal.cache.DiskLruCache$Editor;	 Catch:{ all -> 0x0074 }
        r6.<init>(r0);	 Catch:{ all -> 0x0074 }
        r0.currentEditor = r6;	 Catch:{ all -> 0x0074 }
        monitor-exit(r5);
        return r6;
    L_0x006b:
        r6 = r5.executor;	 Catch:{ all -> 0x0074 }
        r7 = r5.cleanupRunnable;	 Catch:{ all -> 0x0074 }
        r6.execute(r7);	 Catch:{ all -> 0x0074 }
        monitor-exit(r5);
        return r3;
    L_0x0074:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.edit(java.lang.String, long):okhttp3.internal.cache.DiskLruCache$Editor");
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized != null) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r7) throws java.io.IOException {
        /*
        r6 = this;
        monitor-enter(r6);
        r6.initialize();	 Catch:{ all -> 0x0029 }
        r6.checkNotClosed();	 Catch:{ all -> 0x0029 }
        r6.validateKey(r7);	 Catch:{ all -> 0x0029 }
        r0 = r6.lruEntries;	 Catch:{ all -> 0x0029 }
        r7 = r0.get(r7);	 Catch:{ all -> 0x0029 }
        r7 = (okhttp3.internal.cache.DiskLruCache.Entry) r7;	 Catch:{ all -> 0x0029 }
        r0 = 0;
        if (r7 != 0) goto L_0x0017;
    L_0x0015:
        monitor-exit(r6);
        return r0;
    L_0x0017:
        r7 = r6.removeEntry(r7);	 Catch:{ all -> 0x0029 }
        if (r7 == 0) goto L_0x0027;
    L_0x001d:
        r1 = r6.size;	 Catch:{ all -> 0x0029 }
        r3 = r6.maxSize;	 Catch:{ all -> 0x0029 }
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 > 0) goto L_0x0027;
    L_0x0025:
        r6.mostRecentTrimFailed = r0;	 Catch:{ all -> 0x0029 }
    L_0x0027:
        monitor-exit(r6);
        return r7;
    L_0x0029:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.remove(java.lang.String):boolean");
    }

    boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.detach();
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8(REMOVE).writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired() != null) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry((Entry) this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("keys must match regex [a-z0-9_-]{1,120}: \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new C04383();
    }
}
