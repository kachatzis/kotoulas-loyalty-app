package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap();

    private static final class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent intent, ArrayList<ReceiverRecord> arrayList) {
            this.intent = intent;
            this.receivers = arrayList;
        }
    }

    private static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.filter = intentFilter;
            this.receiver = broadcastReceiver;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("Receiver{");
            stringBuilder.append(this.receiver);
            stringBuilder.append(" filter=");
            stringBuilder.append(this.filter);
            if (this.dead) {
                stringBuilder.append(" DEAD");
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    void executePendingBroadcasts() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0048 in {6, 16, 17, 18, 22} preds:[]
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
    L_0x0000:
        r0 = r9.mReceivers;
        monitor-enter(r0);
        r1 = r9.mPendingBroadcasts;	 Catch:{ all -> 0x0045 }
        r1 = r1.size();	 Catch:{ all -> 0x0045 }
        if (r1 > 0) goto L_0x000d;	 Catch:{ all -> 0x0045 }
    L_0x000b:
        monitor-exit(r0);	 Catch:{ all -> 0x0045 }
        return;	 Catch:{ all -> 0x0045 }
    L_0x000d:
        r1 = new android.support.v4.content.LocalBroadcastManager.BroadcastRecord[r1];	 Catch:{ all -> 0x0045 }
        r2 = r9.mPendingBroadcasts;	 Catch:{ all -> 0x0045 }
        r2.toArray(r1);	 Catch:{ all -> 0x0045 }
        r2 = r9.mPendingBroadcasts;	 Catch:{ all -> 0x0045 }
        r2.clear();	 Catch:{ all -> 0x0045 }
        monitor-exit(r0);	 Catch:{ all -> 0x0045 }
        r0 = 0;
        r2 = 0;
    L_0x001c:
        r3 = r1.length;
        if (r2 >= r3) goto L_0x0000;
    L_0x001f:
        r3 = r1[r2];
        r4 = r3.receivers;
        r4 = r4.size();
        r5 = 0;
    L_0x0028:
        if (r5 >= r4) goto L_0x0042;
    L_0x002a:
        r6 = r3.receivers;
        r6 = r6.get(r5);
        r6 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r6;
        r7 = r6.dead;
        if (r7 != 0) goto L_0x003f;
    L_0x0036:
        r6 = r6.receiver;
        r7 = r9.mAppContext;
        r8 = r3.intent;
        r6.onReceive(r7, r8);
    L_0x003f:
        r5 = r5 + 1;
        goto L_0x0028;
    L_0x0042:
        r2 = r2 + 1;
        goto L_0x001c;
    L_0x0045:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0045 }
        throw r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.executePendingBroadcasts():void");
    }

    public void registerReceiver(@android.support.annotation.NonNull android.content.BroadcastReceiver r7, @android.support.annotation.NonNull android.content.IntentFilter r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:18:0x004a in {5, 11, 12, 14, 17} preds:[]
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
        r0 = r6.mReceivers;
        monitor-enter(r0);
        r1 = new android.support.v4.content.LocalBroadcastManager$ReceiverRecord;	 Catch:{ all -> 0x0047 }
        r1.<init>(r8, r7);	 Catch:{ all -> 0x0047 }
        r2 = r6.mReceivers;	 Catch:{ all -> 0x0047 }
        r2 = r2.get(r7);	 Catch:{ all -> 0x0047 }
        r2 = (java.util.ArrayList) r2;	 Catch:{ all -> 0x0047 }
        r3 = 1;	 Catch:{ all -> 0x0047 }
        if (r2 != 0) goto L_0x001d;	 Catch:{ all -> 0x0047 }
    L_0x0013:
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0047 }
        r2.<init>(r3);	 Catch:{ all -> 0x0047 }
        r4 = r6.mReceivers;	 Catch:{ all -> 0x0047 }
        r4.put(r7, r2);	 Catch:{ all -> 0x0047 }
    L_0x001d:
        r2.add(r1);	 Catch:{ all -> 0x0047 }
        r7 = 0;	 Catch:{ all -> 0x0047 }
    L_0x0021:
        r2 = r8.countActions();	 Catch:{ all -> 0x0047 }
        if (r7 >= r2) goto L_0x0045;	 Catch:{ all -> 0x0047 }
    L_0x0027:
        r2 = r8.getAction(r7);	 Catch:{ all -> 0x0047 }
        r4 = r6.mActions;	 Catch:{ all -> 0x0047 }
        r4 = r4.get(r2);	 Catch:{ all -> 0x0047 }
        r4 = (java.util.ArrayList) r4;	 Catch:{ all -> 0x0047 }
        if (r4 != 0) goto L_0x003f;	 Catch:{ all -> 0x0047 }
    L_0x0035:
        r4 = new java.util.ArrayList;	 Catch:{ all -> 0x0047 }
        r4.<init>(r3);	 Catch:{ all -> 0x0047 }
        r5 = r6.mActions;	 Catch:{ all -> 0x0047 }
        r5.put(r2, r4);	 Catch:{ all -> 0x0047 }
    L_0x003f:
        r4.add(r1);	 Catch:{ all -> 0x0047 }
        r7 = r7 + 1;	 Catch:{ all -> 0x0047 }
        goto L_0x0021;	 Catch:{ all -> 0x0047 }
    L_0x0045:
        monitor-exit(r0);	 Catch:{ all -> 0x0047 }
        return;	 Catch:{ all -> 0x0047 }
    L_0x0047:
        r7 = move-exception;	 Catch:{ all -> 0x0047 }
        monitor-exit(r0);	 Catch:{ all -> 0x0047 }
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.registerReceiver(android.content.BroadcastReceiver, android.content.IntentFilter):void");
    }

    public boolean sendBroadcast(@android.support.annotation.NonNull android.content.Intent r22) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:59:0x017b in {5, 6, 8, 12, 18, 22, 23, 27, 29, 30, 31, 34, 35, 36, 37, 38, 39, 40, 41, 47, 50, 52, 55, 58} preds:[]
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
        r21 = this;
        r1 = r21;
        r0 = r22;
        r2 = r1.mReceivers;
        monitor-enter(r2);
        r10 = r22.getAction();	 Catch:{ all -> 0x0178 }
        r3 = r1.mAppContext;	 Catch:{ all -> 0x0178 }
        r3 = r3.getContentResolver();	 Catch:{ all -> 0x0178 }
        r11 = r0.resolveTypeIfNeeded(r3);	 Catch:{ all -> 0x0178 }
        r12 = r22.getData();	 Catch:{ all -> 0x0178 }
        r13 = r22.getScheme();	 Catch:{ all -> 0x0178 }
        r14 = r22.getCategories();	 Catch:{ all -> 0x0178 }
        r3 = r22.getFlags();	 Catch:{ all -> 0x0178 }
        r3 = r3 & 8;	 Catch:{ all -> 0x0178 }
        if (r3 == 0) goto L_0x002c;	 Catch:{ all -> 0x0178 }
    L_0x0029:
        r16 = 1;	 Catch:{ all -> 0x0178 }
        goto L_0x002e;	 Catch:{ all -> 0x0178 }
    L_0x002c:
        r16 = 0;	 Catch:{ all -> 0x0178 }
    L_0x002e:
        if (r16 == 0) goto L_0x0056;	 Catch:{ all -> 0x0178 }
    L_0x0030:
        r3 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r4.<init>();	 Catch:{ all -> 0x0178 }
        r5 = "Resolving type ";	 Catch:{ all -> 0x0178 }
        r4.append(r5);	 Catch:{ all -> 0x0178 }
        r4.append(r11);	 Catch:{ all -> 0x0178 }
        r5 = " scheme ";	 Catch:{ all -> 0x0178 }
        r4.append(r5);	 Catch:{ all -> 0x0178 }
        r4.append(r13);	 Catch:{ all -> 0x0178 }
        r5 = " of intent ";	 Catch:{ all -> 0x0178 }
        r4.append(r5);	 Catch:{ all -> 0x0178 }
        r4.append(r0);	 Catch:{ all -> 0x0178 }
        r4 = r4.toString();	 Catch:{ all -> 0x0178 }
        android.util.Log.v(r3, r4);	 Catch:{ all -> 0x0178 }
    L_0x0056:
        r3 = r1.mActions;	 Catch:{ all -> 0x0178 }
        r4 = r22.getAction();	 Catch:{ all -> 0x0178 }
        r3 = r3.get(r4);	 Catch:{ all -> 0x0178 }
        r8 = r3;	 Catch:{ all -> 0x0178 }
        r8 = (java.util.ArrayList) r8;	 Catch:{ all -> 0x0178 }
        if (r8 == 0) goto L_0x0175;	 Catch:{ all -> 0x0178 }
    L_0x0065:
        if (r16 == 0) goto L_0x007d;	 Catch:{ all -> 0x0178 }
    L_0x0067:
        r3 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r4.<init>();	 Catch:{ all -> 0x0178 }
        r5 = "Action list: ";	 Catch:{ all -> 0x0178 }
        r4.append(r5);	 Catch:{ all -> 0x0178 }
        r4.append(r8);	 Catch:{ all -> 0x0178 }
        r4 = r4.toString();	 Catch:{ all -> 0x0178 }
        android.util.Log.v(r3, r4);	 Catch:{ all -> 0x0178 }
    L_0x007d:
        r3 = 0;	 Catch:{ all -> 0x0178 }
        r6 = r3;	 Catch:{ all -> 0x0178 }
        r7 = 0;	 Catch:{ all -> 0x0178 }
    L_0x0080:
        r3 = r8.size();	 Catch:{ all -> 0x0178 }
        if (r7 >= r3) goto L_0x0145;	 Catch:{ all -> 0x0178 }
    L_0x0086:
        r3 = r8.get(r7);	 Catch:{ all -> 0x0178 }
        r5 = r3;	 Catch:{ all -> 0x0178 }
        r5 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r5;	 Catch:{ all -> 0x0178 }
        if (r16 == 0) goto L_0x00a7;	 Catch:{ all -> 0x0178 }
    L_0x008f:
        r3 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r4.<init>();	 Catch:{ all -> 0x0178 }
        r9 = "Matching against filter ";	 Catch:{ all -> 0x0178 }
        r4.append(r9);	 Catch:{ all -> 0x0178 }
        r9 = r5.filter;	 Catch:{ all -> 0x0178 }
        r4.append(r9);	 Catch:{ all -> 0x0178 }
        r4 = r4.toString();	 Catch:{ all -> 0x0178 }
        android.util.Log.v(r3, r4);	 Catch:{ all -> 0x0178 }
    L_0x00a7:
        r3 = r5.broadcasting;	 Catch:{ all -> 0x0178 }
        if (r3 == 0) goto L_0x00cc;	 Catch:{ all -> 0x0178 }
    L_0x00ab:
        if (r16 == 0) goto L_0x00c0;	 Catch:{ all -> 0x0178 }
    L_0x00ad:
        r3 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r4 = "  Filter's target already added";	 Catch:{ all -> 0x0178 }
        android.util.Log.v(r3, r4);	 Catch:{ all -> 0x0178 }
        r18 = r7;	 Catch:{ all -> 0x0178 }
        r19 = r8;	 Catch:{ all -> 0x0178 }
        r17 = r10;	 Catch:{ all -> 0x0178 }
        r20 = r11;	 Catch:{ all -> 0x0178 }
        r11 = 1;	 Catch:{ all -> 0x0178 }
        r10 = r6;	 Catch:{ all -> 0x0178 }
        goto L_0x013a;	 Catch:{ all -> 0x0178 }
    L_0x00c0:
        r18 = r7;	 Catch:{ all -> 0x0178 }
        r19 = r8;	 Catch:{ all -> 0x0178 }
        r17 = r10;	 Catch:{ all -> 0x0178 }
        r20 = r11;	 Catch:{ all -> 0x0178 }
        r11 = 1;	 Catch:{ all -> 0x0178 }
        r10 = r6;	 Catch:{ all -> 0x0178 }
        goto L_0x013a;	 Catch:{ all -> 0x0178 }
    L_0x00cc:
        r3 = r5.filter;	 Catch:{ all -> 0x0178 }
        r9 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r4 = r10;	 Catch:{ all -> 0x0178 }
        r15 = r5;	 Catch:{ all -> 0x0178 }
        r5 = r11;	 Catch:{ all -> 0x0178 }
        r17 = r10;	 Catch:{ all -> 0x0178 }
        r10 = r6;	 Catch:{ all -> 0x0178 }
        r6 = r13;	 Catch:{ all -> 0x0178 }
        r18 = r7;	 Catch:{ all -> 0x0178 }
        r7 = r12;	 Catch:{ all -> 0x0178 }
        r19 = r8;	 Catch:{ all -> 0x0178 }
        r8 = r14;	 Catch:{ all -> 0x0178 }
        r20 = r11;	 Catch:{ all -> 0x0178 }
        r11 = 1;	 Catch:{ all -> 0x0178 }
        r3 = r3.match(r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x0178 }
        if (r3 < 0) goto L_0x0111;	 Catch:{ all -> 0x0178 }
    L_0x00e6:
        if (r16 == 0) goto L_0x0102;	 Catch:{ all -> 0x0178 }
    L_0x00e8:
        r4 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r5.<init>();	 Catch:{ all -> 0x0178 }
        r6 = "  Filter matched!  match=0x";	 Catch:{ all -> 0x0178 }
        r5.append(r6);	 Catch:{ all -> 0x0178 }
        r3 = java.lang.Integer.toHexString(r3);	 Catch:{ all -> 0x0178 }
        r5.append(r3);	 Catch:{ all -> 0x0178 }
        r3 = r5.toString();	 Catch:{ all -> 0x0178 }
        android.util.Log.v(r4, r3);	 Catch:{ all -> 0x0178 }
    L_0x0102:
        if (r10 != 0) goto L_0x010a;	 Catch:{ all -> 0x0178 }
    L_0x0104:
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0178 }
        r6.<init>();	 Catch:{ all -> 0x0178 }
        goto L_0x010b;	 Catch:{ all -> 0x0178 }
    L_0x010a:
        r6 = r10;	 Catch:{ all -> 0x0178 }
    L_0x010b:
        r6.add(r15);	 Catch:{ all -> 0x0178 }
        r15.broadcasting = r11;	 Catch:{ all -> 0x0178 }
        goto L_0x013b;	 Catch:{ all -> 0x0178 }
    L_0x0111:
        if (r16 == 0) goto L_0x013a;	 Catch:{ all -> 0x0178 }
    L_0x0113:
        switch(r3) {
            case -4: goto L_0x0122;
            case -3: goto L_0x011f;
            case -2: goto L_0x011c;
            case -1: goto L_0x0119;
            default: goto L_0x0116;
        };	 Catch:{ all -> 0x0178 }
    L_0x0116:
        r3 = "unknown reason";	 Catch:{ all -> 0x0178 }
        goto L_0x0124;	 Catch:{ all -> 0x0178 }
    L_0x0119:
        r3 = "type";	 Catch:{ all -> 0x0178 }
        goto L_0x0124;	 Catch:{ all -> 0x0178 }
    L_0x011c:
        r3 = "data";	 Catch:{ all -> 0x0178 }
        goto L_0x0124;	 Catch:{ all -> 0x0178 }
    L_0x011f:
        r3 = "action";	 Catch:{ all -> 0x0178 }
        goto L_0x0124;	 Catch:{ all -> 0x0178 }
    L_0x0122:
        r3 = "category";	 Catch:{ all -> 0x0178 }
    L_0x0124:
        r4 = "LocalBroadcastManager";	 Catch:{ all -> 0x0178 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r5.<init>();	 Catch:{ all -> 0x0178 }
        r6 = "  Filter did not match: ";	 Catch:{ all -> 0x0178 }
        r5.append(r6);	 Catch:{ all -> 0x0178 }
        r5.append(r3);	 Catch:{ all -> 0x0178 }
        r3 = r5.toString();	 Catch:{ all -> 0x0178 }
        android.util.Log.v(r4, r3);	 Catch:{ all -> 0x0178 }
    L_0x013a:
        r6 = r10;	 Catch:{ all -> 0x0178 }
    L_0x013b:
        r7 = r18 + 1;	 Catch:{ all -> 0x0178 }
        r10 = r17;	 Catch:{ all -> 0x0178 }
        r8 = r19;	 Catch:{ all -> 0x0178 }
        r11 = r20;	 Catch:{ all -> 0x0178 }
        goto L_0x0080;	 Catch:{ all -> 0x0178 }
    L_0x0145:
        r10 = r6;	 Catch:{ all -> 0x0178 }
        r11 = 1;	 Catch:{ all -> 0x0178 }
        if (r10 == 0) goto L_0x0175;	 Catch:{ all -> 0x0178 }
    L_0x0149:
        r3 = 0;	 Catch:{ all -> 0x0178 }
    L_0x014a:
        r4 = r10.size();	 Catch:{ all -> 0x0178 }
        if (r3 >= r4) goto L_0x015c;	 Catch:{ all -> 0x0178 }
    L_0x0150:
        r4 = r10.get(r3);	 Catch:{ all -> 0x0178 }
        r4 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r4;	 Catch:{ all -> 0x0178 }
        r5 = 0;	 Catch:{ all -> 0x0178 }
        r4.broadcasting = r5;	 Catch:{ all -> 0x0178 }
        r3 = r3 + 1;	 Catch:{ all -> 0x0178 }
        goto L_0x014a;	 Catch:{ all -> 0x0178 }
    L_0x015c:
        r3 = r1.mPendingBroadcasts;	 Catch:{ all -> 0x0178 }
        r4 = new android.support.v4.content.LocalBroadcastManager$BroadcastRecord;	 Catch:{ all -> 0x0178 }
        r4.<init>(r0, r10);	 Catch:{ all -> 0x0178 }
        r3.add(r4);	 Catch:{ all -> 0x0178 }
        r0 = r1.mHandler;	 Catch:{ all -> 0x0178 }
        r0 = r0.hasMessages(r11);	 Catch:{ all -> 0x0178 }
        if (r0 != 0) goto L_0x0173;	 Catch:{ all -> 0x0178 }
    L_0x016e:
        r0 = r1.mHandler;	 Catch:{ all -> 0x0178 }
        r0.sendEmptyMessage(r11);	 Catch:{ all -> 0x0178 }
    L_0x0173:
        monitor-exit(r2);	 Catch:{ all -> 0x0178 }
        return r11;	 Catch:{ all -> 0x0178 }
    L_0x0175:
        monitor-exit(r2);	 Catch:{ all -> 0x0178 }
        r0 = 0;	 Catch:{ all -> 0x0178 }
        return r0;	 Catch:{ all -> 0x0178 }
    L_0x0178:
        r0 = move-exception;	 Catch:{ all -> 0x0178 }
        monitor-exit(r2);	 Catch:{ all -> 0x0178 }
        throw r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.sendBroadcast(android.content.Intent):boolean");
    }

    public void unregisterReceiver(@android.support.annotation.NonNull android.content.BroadcastReceiver r12) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:30:0x0067 in {6, 18, 19, 22, 23, 24, 26, 29} preds:[]
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
        r0 = r11.mReceivers;
        monitor-enter(r0);
        r1 = r11.mReceivers;	 Catch:{ all -> 0x0064 }
        r1 = r1.remove(r12);	 Catch:{ all -> 0x0064 }
        r1 = (java.util.ArrayList) r1;	 Catch:{ all -> 0x0064 }
        if (r1 != 0) goto L_0x000f;	 Catch:{ all -> 0x0064 }
    L_0x000d:
        monitor-exit(r0);	 Catch:{ all -> 0x0064 }
        return;	 Catch:{ all -> 0x0064 }
    L_0x000f:
        r2 = r1.size();	 Catch:{ all -> 0x0064 }
        r3 = 1;	 Catch:{ all -> 0x0064 }
        r2 = r2 - r3;	 Catch:{ all -> 0x0064 }
    L_0x0015:
        if (r2 < 0) goto L_0x0062;	 Catch:{ all -> 0x0064 }
    L_0x0017:
        r4 = r1.get(r2);	 Catch:{ all -> 0x0064 }
        r4 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r4;	 Catch:{ all -> 0x0064 }
        r4.dead = r3;	 Catch:{ all -> 0x0064 }
        r5 = 0;	 Catch:{ all -> 0x0064 }
    L_0x0020:
        r6 = r4.filter;	 Catch:{ all -> 0x0064 }
        r6 = r6.countActions();	 Catch:{ all -> 0x0064 }
        if (r5 >= r6) goto L_0x005f;	 Catch:{ all -> 0x0064 }
    L_0x0028:
        r6 = r4.filter;	 Catch:{ all -> 0x0064 }
        r6 = r6.getAction(r5);	 Catch:{ all -> 0x0064 }
        r7 = r11.mActions;	 Catch:{ all -> 0x0064 }
        r7 = r7.get(r6);	 Catch:{ all -> 0x0064 }
        r7 = (java.util.ArrayList) r7;	 Catch:{ all -> 0x0064 }
        if (r7 == 0) goto L_0x005c;	 Catch:{ all -> 0x0064 }
    L_0x0038:
        r8 = r7.size();	 Catch:{ all -> 0x0064 }
        r8 = r8 - r3;	 Catch:{ all -> 0x0064 }
    L_0x003d:
        if (r8 < 0) goto L_0x0051;	 Catch:{ all -> 0x0064 }
    L_0x003f:
        r9 = r7.get(r8);	 Catch:{ all -> 0x0064 }
        r9 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r9;	 Catch:{ all -> 0x0064 }
        r10 = r9.receiver;	 Catch:{ all -> 0x0064 }
        if (r10 != r12) goto L_0x004e;	 Catch:{ all -> 0x0064 }
    L_0x0049:
        r9.dead = r3;	 Catch:{ all -> 0x0064 }
        r7.remove(r8);	 Catch:{ all -> 0x0064 }
    L_0x004e:
        r8 = r8 + -1;	 Catch:{ all -> 0x0064 }
        goto L_0x003d;	 Catch:{ all -> 0x0064 }
    L_0x0051:
        r7 = r7.size();	 Catch:{ all -> 0x0064 }
        if (r7 > 0) goto L_0x005c;	 Catch:{ all -> 0x0064 }
    L_0x0057:
        r7 = r11.mActions;	 Catch:{ all -> 0x0064 }
        r7.remove(r6);	 Catch:{ all -> 0x0064 }
    L_0x005c:
        r5 = r5 + 1;	 Catch:{ all -> 0x0064 }
        goto L_0x0020;	 Catch:{ all -> 0x0064 }
    L_0x005f:
        r2 = r2 + -1;	 Catch:{ all -> 0x0064 }
        goto L_0x0015;	 Catch:{ all -> 0x0064 }
    L_0x0062:
        monitor-exit(r0);	 Catch:{ all -> 0x0064 }
        return;	 Catch:{ all -> 0x0064 }
    L_0x0064:
        r12 = move-exception;	 Catch:{ all -> 0x0064 }
        monitor-exit(r0);	 Catch:{ all -> 0x0064 }
        throw r12;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.unregisterReceiver(android.content.BroadcastReceiver):void");
    }

    @NonNull
    public static LocalBroadcastManager getInstance(@NonNull Context context) {
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            context = mInstance;
        }
        return context;
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    LocalBroadcastManager.this.executePendingBroadcasts();
                }
            }
        };
    }

    public void sendBroadcastSync(@NonNull Intent intent) {
        if (sendBroadcast(intent) != null) {
            executePendingBroadcasts();
        }
    }
}
