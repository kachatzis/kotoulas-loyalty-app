package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(21)
class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";
    static Method sAudioAttributesToLegacyStreamType;
    AudioAttributes mAudioAttributes;
    int mLegacyStreamType;

    AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.mLegacyStreamType = -1;
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = i;
    }

    static java.lang.reflect.Method getAudioAttributesToLegacyStreamTypeMethod() {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r0 = sAudioAttributesToLegacyStreamType;	 Catch:{ NoSuchMethodException -> 0x0019 }
        if (r0 != 0) goto L_0x0016;	 Catch:{ NoSuchMethodException -> 0x0019 }
    L_0x0004:
        r0 = android.media.AudioAttributes.class;	 Catch:{ NoSuchMethodException -> 0x0019 }
        r1 = "toLegacyStreamType";	 Catch:{ NoSuchMethodException -> 0x0019 }
        r2 = 1;	 Catch:{ NoSuchMethodException -> 0x0019 }
        r2 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x0019 }
        r3 = 0;	 Catch:{ NoSuchMethodException -> 0x0019 }
        r4 = android.media.AudioAttributes.class;	 Catch:{ NoSuchMethodException -> 0x0019 }
        r2[r3] = r4;	 Catch:{ NoSuchMethodException -> 0x0019 }
        r0 = r0.getMethod(r1, r2);	 Catch:{ NoSuchMethodException -> 0x0019 }
        sAudioAttributesToLegacyStreamType = r0;	 Catch:{ NoSuchMethodException -> 0x0019 }
    L_0x0016:
        r0 = sAudioAttributesToLegacyStreamType;
        return r0;
    L_0x0019:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.AudioAttributesImplApi21.getAudioAttributesToLegacyStreamTypeMethod():java.lang.reflect.Method");
    }

    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public int getVolumeControlStream() {
        if (VERSION.SDK_INT >= 26) {
            return this.mAudioAttributes.getVolumeControlStream();
        }
        return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int getLegacyStreamType() {
        Throwable e;
        String str;
        StringBuilder stringBuilder;
        int i = this.mLegacyStreamType;
        if (i != -1) {
            return i;
        }
        Method audioAttributesToLegacyStreamTypeMethod = getAudioAttributesToLegacyStreamTypeMethod();
        if (audioAttributesToLegacyStreamTypeMethod == null) {
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("No AudioAttributes#toLegacyStreamType() on API: ");
            stringBuilder2.append(VERSION.SDK_INT);
            Log.w(str2, stringBuilder2.toString());
            return -1;
        }
        try {
            return ((Integer) audioAttributesToLegacyStreamTypeMethod.invoke(null, new Object[]{this.mAudioAttributes})).intValue();
        } catch (InvocationTargetException e2) {
            e = e2;
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getLegacyStreamType() failed on API: ");
            stringBuilder.append(VERSION.SDK_INT);
            Log.w(str, stringBuilder.toString(), e);
            return -1;
        } catch (IllegalAccessException e3) {
            e = e3;
            str = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getLegacyStreamType() failed on API: ");
            stringBuilder.append(VERSION.SDK_INT);
            Log.w(str, stringBuilder.toString(), e);
            return -1;
        }
    }

    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS", this.mAudioAttributes);
        int i = this.mLegacyStreamType;
        if (i != -1) {
            bundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return null;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioAttributesCompat: audioattributes=");
        stringBuilder.append(this.mAudioAttributes);
        return stringBuilder.toString();
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        AudioAttributes audioAttributes = (AudioAttributes) bundle.getParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS");
        if (audioAttributes == null) {
            return null;
        }
        return new AudioAttributesImplApi21(audioAttributes, bundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }
}
