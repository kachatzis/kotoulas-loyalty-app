package android.support.v4.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.v4.util.ObjectsCompat;
import android.text.TextUtils;

class MediaSessionManagerImplBase implements MediaSessionManagerImpl {
    private static final boolean DEBUG = MediaSessionManager.DEBUG;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String PERMISSION_MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
    private static final String PERMISSION_STATUS_BAR_SERVICE = "android.permission.STATUS_BAR_SERVICE";
    private static final String TAG = "MediaSessionManager";
    ContentResolver mContentResolver = this.mContext.getContentResolver();
    Context mContext;

    static class RemoteUserInfoImplBase implements RemoteUserInfoImpl {
        private String mPackageName;
        private int mPid;
        private int mUid;

        RemoteUserInfoImplBase(String str, int i, int i2) {
            this.mPackageName = str;
            this.mPid = i;
            this.mUid = i2;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public int getPid() {
            return this.mPid;
        }

        public int getUid() {
            return this.mUid;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfoImplBase)) {
                return false;
            }
            RemoteUserInfoImplBase remoteUserInfoImplBase = (RemoteUserInfoImplBase) obj;
            if (!TextUtils.equals(this.mPackageName, remoteUserInfoImplBase.mPackageName) || this.mPid != remoteUserInfoImplBase.mPid || this.mUid != remoteUserInfoImplBase.mUid) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mPackageName, Integer.valueOf(this.mPid), Integer.valueOf(this.mUid));
        }
    }

    MediaSessionManagerImplBase(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isTrustedForMediaControl(@android.support.annotation.NonNull android.support.v4.media.MediaSessionManager.RemoteUserInfoImpl r5) {
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
        r4 = this;
        r0 = 0;
        r1 = r4.mContext;	 Catch:{ NameNotFoundException -> 0x0062 }
        r1 = r1.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0062 }
        r2 = r5.getPackageName();	 Catch:{ NameNotFoundException -> 0x0062 }
        r1 = r1.getApplicationInfo(r2, r0);	 Catch:{ NameNotFoundException -> 0x0062 }
        r1 = r1.uid;
        r2 = r5.getUid();
        if (r1 == r2) goto L_0x0042;
    L_0x0017:
        r1 = DEBUG;
        if (r1 == 0) goto L_0x0041;
    L_0x001b:
        r1 = "MediaSessionManager";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Package name ";
        r2.append(r3);
        r3 = r5.getPackageName();
        r2.append(r3);
        r3 = " doesn't match with the uid ";
        r2.append(r3);
        r5 = r5.getUid();
        r2.append(r5);
        r5 = r2.toString();
        android.util.Log.d(r1, r5);
    L_0x0041:
        return r0;
    L_0x0042:
        r1 = "android.permission.STATUS_BAR_SERVICE";
        r1 = r4.isPermissionGranted(r5, r1);
        if (r1 != 0) goto L_0x0060;
    L_0x004a:
        r1 = "android.permission.MEDIA_CONTENT_CONTROL";
        r1 = r4.isPermissionGranted(r5, r1);
        if (r1 != 0) goto L_0x0060;
    L_0x0052:
        r1 = r5.getUid();
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r1 == r2) goto L_0x0060;
    L_0x005a:
        r5 = r4.isEnabledNotificationListener(r5);
        if (r5 == 0) goto L_0x0061;
    L_0x0060:
        r0 = 1;
    L_0x0061:
        return r0;
        r1 = DEBUG;
        if (r1 == 0) goto L_0x0086;
    L_0x0067:
        r1 = "MediaSessionManager";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Package ";
        r2.append(r3);
        r5 = r5.getPackageName();
        r2.append(r5);
        r5 = " doesn't exist";
        r2.append(r5);
        r5 = r2.toString();
        android.util.Log.d(r1, r5);
    L_0x0086:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaSessionManagerImplBase.isTrustedForMediaControl(android.support.v4.media.MediaSessionManager$RemoteUserInfoImpl):boolean");
    }

    private boolean isPermissionGranted(RemoteUserInfoImpl remoteUserInfoImpl, String str) {
        boolean z = true;
        if (remoteUserInfoImpl.getPid() < 0) {
            if (this.mContext.getPackageManager().checkPermission(str, remoteUserInfoImpl.getPackageName()) != null) {
                z = false;
            }
            return z;
        }
        if (this.mContext.checkPermission(str, remoteUserInfoImpl.getPid(), remoteUserInfoImpl.getUid()) != null) {
            z = false;
        }
        return z;
    }

    boolean isEnabledNotificationListener(@NonNull RemoteUserInfoImpl remoteUserInfoImpl) {
        String string = Secure.getString(this.mContentResolver, ENABLED_NOTIFICATION_LISTENERS);
        if (string != null) {
            String[] split = string.split(":");
            for (String unflattenFromString : split) {
                ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
                if (unflattenFromString2 != null && unflattenFromString2.getPackageName().equals(remoteUserInfoImpl.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
