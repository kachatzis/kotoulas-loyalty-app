package com.google.zxing.oned;

final class UPCEANExtensionSupport {
    private static final int[] EXTENSION_START_PATTERN = new int[]{1, 1, 2};
    private final UPCEANExtension5Support fiveSupport = new UPCEANExtension5Support();
    private final UPCEANExtension2Support twoSupport = new UPCEANExtension2Support();

    UPCEANExtensionSupport() {
    }

    com.google.zxing.Result decodeRow(int r3, com.google.zxing.common.BitArray r4, int r5) throws com.google.zxing.NotFoundException {
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
        r0 = EXTENSION_START_PATTERN;
        r1 = 0;
        r5 = com.google.zxing.oned.UPCEANReader.findGuardPattern(r4, r5, r1, r0);
        r0 = r2.fiveSupport;	 Catch:{ ReaderException -> 0x000e }
        r3 = r0.decodeRow(r3, r4, r5);	 Catch:{ ReaderException -> 0x000e }
        return r3;
    L_0x000e:
        r0 = r2.twoSupport;
        r3 = r0.decodeRow(r3, r4, r5);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANExtensionSupport.decodeRow(int, com.google.zxing.common.BitArray, int):com.google.zxing.Result");
    }
}
