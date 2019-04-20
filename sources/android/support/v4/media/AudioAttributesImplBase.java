package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;

class AudioAttributesImplBase implements AudioAttributesImpl {
    int mContentType = 0;
    int mFlags = 0;
    int mLegacyStream = -1;
    int mUsage = 0;

    public Object getAudioAttributes() {
        return null;
    }

    AudioAttributesImplBase() {
    }

    AudioAttributesImplBase(int i, int i2, int i3, int i4) {
        this.mContentType = i;
        this.mFlags = i2;
        this.mUsage = i3;
        this.mLegacyStream = i4;
    }

    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
    }

    public int getLegacyStreamType() {
        int i = this.mLegacyStream;
        if (i != -1) {
            return i;
        }
        return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    public int getRawLegacyStreamType() {
        return this.mLegacyStream;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public int getUsage() {
        return this.mUsage;
    }

    public int getFlags() {
        int i = this.mFlags;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i |= 4;
        } else if (legacyStreamType == 7) {
            i |= 1;
        }
        return i & 273;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("android.support.v4.media.audio_attrs.USAGE", this.mUsage);
        bundle.putInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", this.mContentType);
        bundle.putInt("android.support.v4.media.audio_attrs.FLAGS", this.mFlags);
        int i = this.mLegacyStream;
        if (i != -1) {
            bundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.mContentType == audioAttributesImplBase.getContentType() && this.mFlags == audioAttributesImplBase.getFlags() && this.mUsage == audioAttributesImplBase.getUsage() && this.mLegacyStream == audioAttributesImplBase.mLegacyStream) {
            z = true;
        }
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream != -1) {
            stringBuilder.append(" stream=");
            stringBuilder.append(this.mLegacyStream);
            stringBuilder.append(" derived");
        }
        stringBuilder.append(" usage=");
        stringBuilder.append(AudioAttributesCompat.usageToString(this.mUsage));
        stringBuilder.append(" content=");
        stringBuilder.append(this.mContentType);
        stringBuilder.append(" flags=0x");
        stringBuilder.append(Integer.toHexString(this.mFlags).toUpperCase());
        return stringBuilder.toString();
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new AudioAttributesImplBase(bundle.getInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", 0), bundle.getInt("android.support.v4.media.audio_attrs.FLAGS", 0), bundle.getInt("android.support.v4.media.audio_attrs.USAGE", 0), bundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }
}
