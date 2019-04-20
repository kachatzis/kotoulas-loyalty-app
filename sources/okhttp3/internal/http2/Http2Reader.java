package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Http2Reader implements Closeable {
    static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private final ContinuationSource continuation = new ContinuationSource(this.source);
    final Reader hpackReader = new Reader(4096, this.continuation);
    private final BufferedSource source;

    interface Handler {
        void ackSettings();

        void alternateService(int i, String str, ByteString byteString, String str2, int i2, long j);

        void data(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException;

        void goAway(int i, ErrorCode errorCode, ByteString byteString);

        void headers(boolean z, int i, int i2, List<Header> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<Header> list) throws IOException;

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    static final class ContinuationSource implements Source {
        byte flags;
        int left;
        int length;
        short padding;
        private final BufferedSource source;
        int streamId;

        public void close() throws IOException {
        }

        ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }

        public long read(Buffer buffer, long j) throws IOException {
            int i;
            while (true) {
                i = this.left;
                if (i != 0) {
                    break;
                }
                this.source.skip((long) this.padding);
                this.padding = (short) 0;
                if ((this.flags & 4) != 0) {
                    return -1;
                }
                readContinuationHeader();
            }
            buffer = this.source.read(buffer, Math.min(j, (long) i));
            if (buffer == -1) {
                return -1;
            }
            this.left = (int) (((long) this.left) - buffer);
            return buffer;
        }

        public Timeout timeout() {
            return this.source.timeout();
        }

        private void readContinuationHeader() throws IOException {
            int i = this.streamId;
            int readMedium = Http2Reader.readMedium(this.source);
            this.left = readMedium;
            this.length = readMedium;
            byte readByte = (byte) (this.source.readByte() & 255);
            this.flags = (byte) (this.source.readByte() & 255);
            if (Http2Reader.logger.isLoggable(Level.FINE)) {
                Http2Reader.logger.fine(Http2.frameLog(true, this.streamId, this.length, readByte, this.flags));
            }
            this.streamId = this.source.readInt() & ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (readByte != (byte) 9) {
                throw Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(readByte));
            } else if (this.streamId != i) {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    private void readSettings(okhttp3.internal.http2.Http2Reader.Handler r6, int r7, byte r8, int r9) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:41:0x008a in {6, 8, 15, 20, 22, 25, 27, 28, 31, 33, 34, 36, 38, 40} preds:[]
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
        if (r9 != 0) goto L_0x0081;
    L_0x0003:
        r9 = 1;
        r8 = r8 & r9;
        if (r8 == 0) goto L_0x0016;
    L_0x0007:
        if (r7 != 0) goto L_0x000d;
    L_0x0009:
        r6.ackSettings();
        return;
    L_0x000d:
        r6 = new java.lang.Object[r0];
        r7 = "FRAME_SIZE_ERROR ack frame should be empty!";
        r6 = okhttp3.internal.http2.Http2.ioException(r7, r6);
        throw r6;
    L_0x0016:
        r8 = r7 % 6;
        if (r8 != 0) goto L_0x0072;
    L_0x001a:
        r8 = new okhttp3.internal.http2.Settings;
        r8.<init>();
        r1 = 0;
    L_0x0020:
        if (r1 >= r7) goto L_0x006e;
    L_0x0022:
        r2 = r5.source;
        r2 = r2.readShort();
        r3 = r5.source;
        r3 = r3.readInt();
        switch(r2) {
            case 1: goto L_0x0068;
            case 2: goto L_0x005a;
            case 3: goto L_0x0058;
            case 4: goto L_0x004b;
            case 5: goto L_0x0032;
            case 6: goto L_0x0068;
            default: goto L_0x0031;
        };
    L_0x0031:
        goto L_0x0068;
    L_0x0032:
        r4 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        if (r3 < r4) goto L_0x003c;
    L_0x0036:
        r4 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        if (r3 > r4) goto L_0x003c;
    L_0x003b:
        goto L_0x0068;
    L_0x003c:
        r6 = new java.lang.Object[r9];
        r7 = java.lang.Integer.valueOf(r3);
        r6[r0] = r7;
        r7 = "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s";
        r6 = okhttp3.internal.http2.Http2.ioException(r7, r6);
        throw r6;
    L_0x004b:
        r2 = 7;
        if (r3 < 0) goto L_0x004f;
    L_0x004e:
        goto L_0x0068;
    L_0x004f:
        r6 = new java.lang.Object[r0];
        r7 = "PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1";
        r6 = okhttp3.internal.http2.Http2.ioException(r7, r6);
        throw r6;
    L_0x0058:
        r2 = 4;
        goto L_0x0068;
    L_0x005a:
        if (r3 == 0) goto L_0x0068;
    L_0x005c:
        if (r3 != r9) goto L_0x005f;
    L_0x005e:
        goto L_0x0068;
    L_0x005f:
        r6 = new java.lang.Object[r0];
        r7 = "PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1";
        r6 = okhttp3.internal.http2.Http2.ioException(r7, r6);
        throw r6;
    L_0x0068:
        r8.set(r2, r3);
        r1 = r1 + 6;
        goto L_0x0020;
    L_0x006e:
        r6.settings(r0, r8);
        return;
    L_0x0072:
        r6 = new java.lang.Object[r9];
        r7 = java.lang.Integer.valueOf(r7);
        r6[r0] = r7;
        r7 = "TYPE_SETTINGS length %% 6 != 0: %s";
        r6 = okhttp3.internal.http2.Http2.ioException(r7, r6);
        throw r6;
    L_0x0081:
        r6 = new java.lang.Object[r0];
        r7 = "TYPE_SETTINGS streamId != 0";
        r6 = okhttp3.internal.http2.Http2.ioException(r7, r6);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Reader.readSettings(okhttp3.internal.http2.Http2Reader$Handler, int, byte, int):void");
    }

    Http2Reader(BufferedSource bufferedSource, boolean z) {
        this.source = bufferedSource;
        this.client = z;
    }

    public void readConnectionPreface(Handler handler) throws IOException {
        if (!this.client) {
            handler = this.source.readByteString((long) Http2.CONNECTION_PREFACE.size());
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format("<< CONNECTION %s", handler.hex()));
            }
            if (!Http2.CONNECTION_PREFACE.equals(handler)) {
                throw Http2.ioException("Expected a connection header but was %s", handler.utf8());
            }
        } else if (nextFrame(true, handler) == null) {
            throw Http2.ioException("Required SETTINGS preface not received", new Object[0]);
        }
    }

    public boolean nextFrame(boolean r7, okhttp3.internal.http2.Http2Reader.Handler r8) throws java.io.IOException {
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
        r6 = this;
        r0 = 0;
        r1 = r6.source;	 Catch:{ IOException -> 0x0096 }
        r2 = 9;	 Catch:{ IOException -> 0x0096 }
        r1.require(r2);	 Catch:{ IOException -> 0x0096 }
        r1 = r6.source;
        r1 = readMedium(r1);
        r2 = 1;
        if (r1 < 0) goto L_0x0087;
    L_0x0011:
        r3 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        if (r1 > r3) goto L_0x0087;
    L_0x0015:
        r3 = r6.source;
        r3 = r3.readByte();
        r3 = r3 & 255;
        r3 = (byte) r3;
        if (r7 == 0) goto L_0x0033;
    L_0x0020:
        r7 = 4;
        if (r3 != r7) goto L_0x0024;
    L_0x0023:
        goto L_0x0033;
    L_0x0024:
        r7 = new java.lang.Object[r2];
        r8 = java.lang.Byte.valueOf(r3);
        r7[r0] = r8;
        r8 = "Expected a SETTINGS frame but was %s";
        r7 = okhttp3.internal.http2.Http2.ioException(r8, r7);
        throw r7;
    L_0x0033:
        r7 = r6.source;
        r7 = r7.readByte();
        r7 = r7 & 255;
        r7 = (byte) r7;
        r0 = r6.source;
        r0 = r0.readInt();
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r0 = r0 & r4;
        r4 = logger;
        r5 = java.util.logging.Level.FINE;
        r4 = r4.isLoggable(r5);
        if (r4 == 0) goto L_0x0059;
    L_0x0050:
        r4 = logger;
        r5 = okhttp3.internal.http2.Http2.frameLog(r2, r0, r1, r3, r7);
        r4.fine(r5);
    L_0x0059:
        switch(r3) {
            case 0: goto L_0x0083;
            case 1: goto L_0x007f;
            case 2: goto L_0x007b;
            case 3: goto L_0x0077;
            case 4: goto L_0x0073;
            case 5: goto L_0x006f;
            case 6: goto L_0x006b;
            case 7: goto L_0x0067;
            case 8: goto L_0x0063;
            default: goto L_0x005c;
        };
    L_0x005c:
        r7 = r6.source;
        r0 = (long) r1;
        r7.skip(r0);
        goto L_0x0086;
    L_0x0063:
        r6.readWindowUpdate(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x0067:
        r6.readGoAway(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x006b:
        r6.readPing(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x006f:
        r6.readPushPromise(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x0073:
        r6.readSettings(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x0077:
        r6.readRstStream(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x007b:
        r6.readPriority(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x007f:
        r6.readHeaders(r8, r1, r7, r0);
        goto L_0x0086;
    L_0x0083:
        r6.readData(r8, r1, r7, r0);
    L_0x0086:
        return r2;
    L_0x0087:
        r7 = new java.lang.Object[r2];
        r8 = java.lang.Integer.valueOf(r1);
        r7[r0] = r8;
        r8 = "FRAME_SIZE_ERROR: %s";
        r7 = okhttp3.internal.http2.Http2.ioException(r8, r7);
        throw r7;
    L_0x0096:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Reader.nextFrame(boolean, okhttp3.internal.http2.Http2Reader$Handler):boolean");
    }

    private void readHeaders(Handler handler, int i, byte b, int i2) throws IOException {
        short s = (short) 0;
        if (i2 != 0) {
            boolean z = (b & 1) != 0;
            if ((b & 8) != 0) {
                s = (short) (this.source.readByte() & 255);
            }
            if ((b & 32) != 0) {
                readPriority(handler, i2);
                i -= 5;
            }
            handler.headers(z, i2, (byte) -1, readHeaderBlock(lengthWithoutPadding(i, b, s), s, b, i2));
            return;
        }
        throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
    }

    private List<Header> readHeaderBlock(int i, short s, byte b, int i2) throws IOException {
        ContinuationSource continuationSource = this.continuation;
        continuationSource.left = i;
        continuationSource.length = i;
        continuationSource.padding = s;
        continuationSource.flags = b;
        continuationSource.streamId = i2;
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private void readData(Handler handler, int i, byte b, int i2) throws IOException {
        short s = (short) 0;
        if (i2 != 0) {
            Object obj = 1;
            boolean z = (b & 1) != 0;
            if ((b & 32) == 0) {
                obj = null;
            }
            if (obj == null) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                handler.data(z, i2, this.source, lengthWithoutPadding(i, b, s));
                this.source.skip((long) s);
                return;
            }
            throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        throw Http2.ioException("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
    }

    private void readPriority(Handler handler, int i, byte b, int i2) throws IOException {
        if (i != 5) {
            throw Http2.ioException("TYPE_PRIORITY length: %d != 5", new Object[]{Integer.valueOf(i)});
        } else if (i2 != 0) {
            readPriority(handler, i2);
        } else {
            throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
    }

    private void readPriority(Handler handler, int i) throws IOException {
        int readInt = this.source.readInt();
        handler.priority(i, readInt & ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, (this.source.readByte() & 255) + 1, (Integer.MIN_VALUE & readInt) != 0);
    }

    private void readRstStream(Handler handler, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", new Object[]{Integer.valueOf(i)});
        } else if (i2 != 0) {
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.source.readInt());
            if (fromHttp2 != null) {
                handler.rstStream(i2, fromHttp2);
            } else {
                throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[]{Integer.valueOf(i)});
            }
        } else {
            throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
    }

    private void readPushPromise(Handler handler, int i, byte b, int i2) throws IOException {
        short s = (short) 0;
        if (i2 != 0) {
            if ((b & 8) != 0) {
                s = (short) (this.source.readByte() & 255);
            }
            handler.pushPromise(i2, this.source.readInt() & ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, readHeaderBlock(lengthWithoutPadding(i - 4, b, s), s, b, i2));
            return;
        }
        throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
    }

    private void readPing(Handler handler, int i, byte b, int i2) throws IOException {
        boolean z = false;
        if (i != 8) {
            throw Http2.ioException("TYPE_PING length != 8: %s", new Object[]{Integer.valueOf(i)});
        } else if (i2 == 0) {
            i = this.source.readInt();
            i2 = this.source.readInt();
            if ((b & (byte) 1) != (byte) 0) {
                z = true;
            }
            handler.ping(z, i, i2);
        } else {
            throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
        }
    }

    private void readGoAway(Handler handler, int i, byte b, int i2) throws IOException {
        if (i < 8) {
            throw Http2.ioException("TYPE_GOAWAY length < 8: %s", new Object[]{Integer.valueOf(i)});
        } else if (i2 == 0) {
            i2 = this.source.readInt();
            i -= 8;
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.source.readInt());
            if (fromHttp2 != null) {
                b = ByteString.EMPTY;
                if (i > 0) {
                    b = this.source.readByteString((long) i);
                }
                handler.goAway(i2, fromHttp2, b);
                return;
            }
            throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", new Object[]{Integer.valueOf(r2)});
        } else {
            throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
    }

    private void readWindowUpdate(Handler handler, int i, byte b, int i2) throws IOException {
        if (i == 4) {
            long readInt = ((long) this.source.readInt()) & 2147483647L;
            if (readInt != 0) {
                handler.windowUpdate(i2, readInt);
                return;
            } else {
                throw Http2.ioException("windowSizeIncrement was 0", new Object[]{Long.valueOf(readInt)});
            }
        }
        throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", new Object[]{Integer.valueOf(i)});
    }

    public void close() throws IOException {
        this.source.close();
    }

    static int readMedium(BufferedSource bufferedSource) throws IOException {
        return (bufferedSource.readByte() & 255) | (((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8));
    }

    static int lengthWithoutPadding(int i, byte b, short s) throws IOException {
        if ((b & 8) != (byte) 0) {
            short s2 = i - 1;
        }
        if (s <= s2) {
            return (short) (s2 - s);
        }
        throw Http2.ioException("PROTOCOL_ERROR padding %s > remaining length %s", new Object[]{Short.valueOf(s), Integer.valueOf(s2)});
    }
}
