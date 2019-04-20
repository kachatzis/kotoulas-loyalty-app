package okhttp3.internal.ws;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

final class WebSocketReader {
    boolean closed;
    long frameBytesRead;
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    boolean isMasked;
    final byte[] maskBuffer = new byte[8192];
    final byte[] maskKey = new byte[4];
    int opcode;
    final BufferedSource source;

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    private void readControlFrame() throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:31:0x00c1 in {4, 9, 11, 15, 16, 17, 24, 26, 27, 28, 30} preds:[]
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
        r11 = this;
        r0 = new okio.Buffer;
        r0.<init>();
        r1 = r11.frameBytesRead;
        r3 = r11.frameLength;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x0051;
    L_0x000d:
        r1 = r11.isClient;
        if (r1 == 0) goto L_0x0017;
    L_0x0011:
        r1 = r11.source;
        r1.readFully(r0, r3);
        goto L_0x0051;
    L_0x0017:
        r1 = r11.frameBytesRead;
        r3 = r11.frameLength;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 >= 0) goto L_0x0051;
    L_0x001f:
        r3 = r3 - r1;
        r1 = r11.maskBuffer;
        r1 = r1.length;
        r1 = (long) r1;
        r1 = java.lang.Math.min(r3, r1);
        r2 = (int) r1;
        r1 = r11.source;
        r3 = r11.maskBuffer;
        r4 = 0;
        r1 = r1.read(r3, r4, r2);
        r2 = -1;
        if (r1 == r2) goto L_0x004b;
    L_0x0035:
        r5 = r11.maskBuffer;
        r2 = (long) r1;
        r8 = r11.maskKey;
        r9 = r11.frameBytesRead;
        r6 = r2;
        okhttp3.internal.ws.WebSocketProtocol.toggleMask(r5, r6, r8, r9);
        r5 = r11.maskBuffer;
        r0.write(r5, r4, r1);
        r4 = r11.frameBytesRead;
        r4 = r4 + r2;
        r11.frameBytesRead = r4;
        goto L_0x0017;
    L_0x004b:
        r0 = new java.io.EOFException;
        r0.<init>();
        throw r0;
    L_0x0051:
        r1 = r11.opcode;
        switch(r1) {
            case 8: goto L_0x0087;
            case 9: goto L_0x007d;
            case 10: goto L_0x0073;
            default: goto L_0x0056;
        };
    L_0x0056:
        r0 = new java.net.ProtocolException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unknown control opcode: ";
        r1.append(r2);
        r2 = r11.opcode;
        r2 = java.lang.Integer.toHexString(r2);
        r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0073:
        r1 = r11.frameCallback;
        r0 = r0.readByteString();
        r1.onReadPong(r0);
        goto L_0x00b8;
    L_0x007d:
        r1 = r11.frameCallback;
        r0 = r0.readByteString();
        r1.onReadPing(r0);
        goto L_0x00b8;
    L_0x0087:
        r1 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;
        r2 = "";
        r3 = r0.size();
        r5 = 1;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x00b9;
    L_0x0095:
        r5 = 0;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x00b0;
    L_0x009b:
        r1 = r0.readShort();
        r2 = r0.readUtf8();
        r0 = okhttp3.internal.ws.WebSocketProtocol.closeCodeExceptionMessage(r1);
        if (r0 != 0) goto L_0x00aa;
    L_0x00a9:
        goto L_0x00b0;
    L_0x00aa:
        r1 = new java.net.ProtocolException;
        r1.<init>(r0);
        throw r1;
    L_0x00b0:
        r0 = r11.frameCallback;
        r0.onReadClose(r1, r2);
        r0 = 1;
        r11.closed = r0;
    L_0x00b8:
        return;
    L_0x00b9:
        r0 = new java.net.ProtocolException;
        r1 = "Malformed close payload length of 1.";
        r0.<init>(r1);
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.WebSocketReader.readControlFrame():void");
    }

    private void readMessage(okio.Buffer r12) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:30:0x009a in {6, 13, 15, 20, 22, 25, 27, 29} preds:[]
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
        r11 = this;
    L_0x0000:
        r0 = r11.closed;
        if (r0 != 0) goto L_0x0092;
    L_0x0004:
        r0 = r11.frameBytesRead;
        r2 = r11.frameLength;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x0042;
    L_0x000c:
        r0 = r11.isFinalFrame;
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        return;
    L_0x0011:
        r11.readUntilNonControlFrame();
        r0 = r11.opcode;
        if (r0 != 0) goto L_0x0025;
    L_0x0018:
        r0 = r11.isFinalFrame;
        if (r0 == 0) goto L_0x0042;
    L_0x001c:
        r0 = r11.frameLength;
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x0042;
    L_0x0024:
        return;
    L_0x0025:
        r12 = new java.net.ProtocolException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Expected continuation opcode. Got: ";
        r0.append(r1);
        r1 = r11.opcode;
        r1 = java.lang.Integer.toHexString(r1);
        r0.append(r1);
        r0 = r0.toString();
        r12.<init>(r0);
        throw r12;
    L_0x0042:
        r0 = r11.frameLength;
        r2 = r11.frameBytesRead;
        r0 = r0 - r2;
        r2 = r11.isMasked;
        r3 = -1;
        if (r2 == 0) goto L_0x007b;
    L_0x004d:
        r2 = r11.maskBuffer;
        r2 = r2.length;
        r5 = (long) r2;
        r0 = java.lang.Math.min(r0, r5);
        r2 = r11.source;
        r5 = r11.maskBuffer;
        r1 = (int) r0;
        r0 = 0;
        r1 = r2.read(r5, r0, r1);
        r1 = (long) r1;
        r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r5 == 0) goto L_0x0075;
    L_0x0064:
        r5 = r11.maskBuffer;
        r8 = r11.maskKey;
        r9 = r11.frameBytesRead;
        r6 = r1;
        okhttp3.internal.ws.WebSocketProtocol.toggleMask(r5, r6, r8, r9);
        r3 = r11.maskBuffer;
        r4 = (int) r1;
        r12.write(r3, r0, r4);
        goto L_0x0085;
    L_0x0075:
        r12 = new java.io.EOFException;
        r12.<init>();
        throw r12;
    L_0x007b:
        r2 = r11.source;
        r1 = r2.read(r12, r0);
        r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r0 == 0) goto L_0x008c;
    L_0x0085:
        r3 = r11.frameBytesRead;
        r3 = r3 + r1;
        r11.frameBytesRead = r3;
        goto L_0x0000;
    L_0x008c:
        r12 = new java.io.EOFException;
        r12.<init>();
        throw r12;
    L_0x0092:
        r12 = new java.io.IOException;
        r0 = "closed";
        r12.<init>(r0);
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.WebSocketReader.readMessage(okio.Buffer):void");
    }

    WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback != null) {
            this.isClient = z;
            this.source = bufferedSource;
            this.frameCallback = frameCallback;
        } else {
            throw new NullPointerException("frameCallback == null");
        }
    }

    void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    private void readHeader() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        long timeoutNanos = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int readByte = this.source.readByte() & 255;
            this.isFinalFrame = timeoutNanos != null;
            this.isControlFrame = (readByte & 8) != 0;
            if (this.isControlFrame) {
                if (!this.isFinalFrame) {
                    throw new ProtocolException("Control frames must be final.");
                }
            }
            Object obj = (readByte & 64) != 0 ? 1 : null;
            Object obj2 = (readByte & 32) != 0 ? 1 : null;
            Object obj3 = (readByte & 16) != 0 ? 1 : null;
            if (obj == null && obj2 == null && obj3 == null) {
                int readByte2 = this.source.readByte() & 255;
                if ((readByte2 & 128) != 0) {
                }
                this.isMasked = false;
                boolean z = this.isMasked;
                boolean z2 = this.isClient;
                if (z == z2) {
                    throw new ProtocolException(z2 ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
                }
                this.frameLength = (long) (readByte2 & 127);
                timeoutNanos = this.frameLength;
                if (timeoutNanos == 126) {
                    this.frameLength = ((long) this.source.readShort()) & 65535;
                } else if (timeoutNanos == 127) {
                    this.frameLength = this.source.readLong();
                    if (this.frameLength < 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Frame length 0x");
                        stringBuilder.append(Long.toHexString(this.frameLength));
                        stringBuilder.append(" > 0x7FFFFFFFFFFFFFFF");
                        throw new ProtocolException(stringBuilder.toString());
                    }
                }
                this.frameBytesRead = 0;
                if (this.isControlFrame) {
                    if (this.frameLength > 125) {
                        throw new ProtocolException("Control frame must be less than 125B.");
                    }
                }
                if (this.isMasked) {
                    this.source.readFully(this.maskKey);
                    return;
                }
                return;
            }
            throw new ProtocolException("Reserved flags are unsupported.");
        } finally {
            this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
        }
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i != 1) {
            if (i != 2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown opcode: ");
                stringBuilder.append(Integer.toHexString(i));
                throw new ProtocolException(stringBuilder.toString());
            }
        }
        Buffer buffer = new Buffer();
        readMessage(buffer);
        if (i == 1) {
            this.frameCallback.onReadMessage(buffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(buffer.readByteString());
        }
    }

    void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }
}
