package com.google.zxing.datamatrix.encoder;

import android.support.v4.view.InputDeviceCompat;

final class Base256Encoder implements Encoder {
    public void encode(com.google.zxing.datamatrix.encoder.EncoderContext r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:26:0x00a4 in {5, 8, 9, 12, 15, 19, 22, 23, 25} preds:[]
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
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = 0;
        r0.append(r1);
    L_0x0009:
        r2 = r7.hasMoreCharacters();
        r3 = 1;
        if (r2 == 0) goto L_0x0033;
    L_0x0010:
        r2 = r7.getCurrentChar();
        r0.append(r2);
        r2 = r7.pos;
        r2 = r2 + r3;
        r7.pos = r2;
        r2 = r7.getMessage();
        r4 = r7.pos;
        r5 = r6.getEncodingMode();
        r2 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.lookAheadTest(r2, r4, r5);
        r4 = r6.getEncodingMode();
        if (r2 == r4) goto L_0x0009;
    L_0x0030:
        r7.signalEncoderChange(r2);
    L_0x0033:
        r2 = r0.length();
        r2 = r2 - r3;
        r4 = r7.getCodewordCount();
        r4 = r4 + r2;
        r4 = r4 + r3;
        r7.updateSymbolInfo(r4);
        r5 = r7.getSymbolInfo();
        r5 = r5.getDataCapacity();
        r5 = r5 - r4;
        if (r5 <= 0) goto L_0x004e;
    L_0x004c:
        r4 = 1;
        goto L_0x004f;
    L_0x004e:
        r4 = 0;
    L_0x004f:
        r5 = r7.hasMoreCharacters();
        if (r5 != 0) goto L_0x0057;
    L_0x0055:
        if (r4 == 0) goto L_0x0073;
    L_0x0057:
        r4 = 249; // 0xf9 float:3.49E-43 double:1.23E-321;
        if (r2 > r4) goto L_0x0060;
    L_0x005b:
        r2 = (char) r2;
        r0.setCharAt(r1, r2);
        goto L_0x0073;
    L_0x0060:
        if (r2 <= r4) goto L_0x008d;
    L_0x0062:
        r5 = 1555; // 0x613 float:2.179E-42 double:7.683E-321;
        if (r2 > r5) goto L_0x008d;
    L_0x0066:
        r5 = r2 / 250;
        r5 = r5 + r4;
        r4 = (char) r5;
        r0.setCharAt(r1, r4);
        r2 = r2 % 250;
        r2 = (char) r2;
        r0.insert(r3, r2);
    L_0x0073:
        r2 = r0.length();
    L_0x0077:
        if (r1 >= r2) goto L_0x008c;
    L_0x0079:
        r4 = r0.charAt(r1);
        r5 = r7.getCodewordCount();
        r5 = r5 + r3;
        r4 = randomize255State(r4, r5);
        r7.writeCodeword(r4);
        r1 = r1 + 1;
        goto L_0x0077;
    L_0x008c:
        return;
    L_0x008d:
        r7 = new java.lang.IllegalStateException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Message length not in valid ranges: ";
        r0.append(r1);
        r0.append(r2);
        r0 = r0.toString();
        r7.<init>(r0);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.Base256Encoder.encode(com.google.zxing.datamatrix.encoder.EncoderContext):void");
    }

    public int getEncodingMode() {
        return 5;
    }

    Base256Encoder() {
    }

    private static char randomize255State(char c, int i) {
        c += ((i * 149) % 255) + 1;
        return c <= 'ÿ' ? (char) c : (char) (c + InputDeviceCompat.SOURCE_ANY);
    }
}
