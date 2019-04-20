package okio;

import java.io.IOException;
import java.util.zip.Inflater;

public final class InflaterSource implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;

    public long read(okio.Buffer r5, long r6) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:34:0x008b in {6, 12, 17, 19, 21, 24, 26, 29, 31, 33} preds:[]
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
        r4 = this;
        r0 = 0;
        r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x0074;
    L_0x0006:
        r2 = r4.closed;
        if (r2 != 0) goto L_0x006c;
    L_0x000a:
        r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x000f;
    L_0x000e:
        return r0;
    L_0x000f:
        r6 = r4.refill();
        r7 = 1;
        r7 = r5.writableSegment(r7);	 Catch:{ DataFormatException -> 0x0065 }
        r0 = r4.inflater;	 Catch:{ DataFormatException -> 0x0065 }
        r1 = r7.data;	 Catch:{ DataFormatException -> 0x0065 }
        r2 = r7.limit;	 Catch:{ DataFormatException -> 0x0065 }
        r3 = r7.limit;	 Catch:{ DataFormatException -> 0x0065 }
        r3 = 8192 - r3;	 Catch:{ DataFormatException -> 0x0065 }
        r0 = r0.inflate(r1, r2, r3);	 Catch:{ DataFormatException -> 0x0065 }
        if (r0 <= 0) goto L_0x0034;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0028:
        r6 = r7.limit;	 Catch:{ DataFormatException -> 0x0065 }
        r6 = r6 + r0;	 Catch:{ DataFormatException -> 0x0065 }
        r7.limit = r6;	 Catch:{ DataFormatException -> 0x0065 }
        r6 = r5.size;	 Catch:{ DataFormatException -> 0x0065 }
        r0 = (long) r0;	 Catch:{ DataFormatException -> 0x0065 }
        r6 = r6 + r0;	 Catch:{ DataFormatException -> 0x0065 }
        r5.size = r6;	 Catch:{ DataFormatException -> 0x0065 }
        return r0;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0034:
        r0 = r4.inflater;	 Catch:{ DataFormatException -> 0x0065 }
        r0 = r0.finished();	 Catch:{ DataFormatException -> 0x0065 }
        if (r0 != 0) goto L_0x0050;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x003c:
        r0 = r4.inflater;	 Catch:{ DataFormatException -> 0x0065 }
        r0 = r0.needsDictionary();	 Catch:{ DataFormatException -> 0x0065 }
        if (r0 == 0) goto L_0x0045;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0044:
        goto L_0x0050;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0045:
        if (r6 != 0) goto L_0x0048;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0047:
        goto L_0x000f;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0048:
        r5 = new java.io.EOFException;	 Catch:{ DataFormatException -> 0x0065 }
        r6 = "source exhausted prematurely";	 Catch:{ DataFormatException -> 0x0065 }
        r5.<init>(r6);	 Catch:{ DataFormatException -> 0x0065 }
        throw r5;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0050:
        r4.releaseInflatedBytes();	 Catch:{ DataFormatException -> 0x0065 }
        r6 = r7.pos;	 Catch:{ DataFormatException -> 0x0065 }
        r0 = r7.limit;	 Catch:{ DataFormatException -> 0x0065 }
        if (r6 != r0) goto L_0x0062;	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0059:
        r6 = r7.pop();	 Catch:{ DataFormatException -> 0x0065 }
        r5.head = r6;	 Catch:{ DataFormatException -> 0x0065 }
        okio.SegmentPool.recycle(r7);	 Catch:{ DataFormatException -> 0x0065 }
    L_0x0062:
        r5 = -1;
        return r5;
    L_0x0065:
        r5 = move-exception;
        r6 = new java.io.IOException;
        r6.<init>(r5);
        throw r6;
    L_0x006c:
        r5 = new java.lang.IllegalStateException;
        r6 = "closed";
        r5.<init>(r6);
        throw r5;
    L_0x0074:
        r5 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "byteCount < 0: ";
        r0.append(r1);
        r0.append(r6);
        r6 = r0.toString();
        r5.<init>(r6);
        throw r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.InflaterSource.read(okio.Buffer, long):long");
    }

    public InflaterSource(Source source, Inflater inflater) {
        this(Okio.buffer(source), inflater);
    }

    InflaterSource(BufferedSource bufferedSource, Inflater inflater) {
        if (bufferedSource == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater != null) {
            this.source = bufferedSource;
            this.inflater = inflater;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    public boolean refill() throws IOException {
        if (!this.inflater.needsInput()) {
            return false;
        }
        releaseInflatedBytes();
        if (this.inflater.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.source.exhausted()) {
            return true;
        } else {
            Segment segment = this.source.buffer().head;
            this.bufferBytesHeldByInflater = segment.limit - segment.pos;
            this.inflater.setInput(segment.data, segment.pos, this.bufferBytesHeldByInflater);
            return false;
        }
    }

    private void releaseInflatedBytes() throws IOException {
        int i = this.bufferBytesHeldByInflater;
        if (i != 0) {
            i -= this.inflater.getRemaining();
            this.bufferBytesHeldByInflater -= i;
            this.source.skip((long) i);
        }
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.inflater.end();
            this.closed = true;
            this.source.close();
        }
    }
}
