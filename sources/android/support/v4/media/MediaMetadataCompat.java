package android.support.v4.media;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

public final class MediaMetadataCompat implements Parcelable {
    public static final Creator<MediaMetadataCompat> CREATOR = new C01951();
    static final ArrayMap<String, Integer> METADATA_KEYS_TYPE = new ArrayMap();
    public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int METADATA_TYPE_BITMAP = 2;
    static final int METADATA_TYPE_LONG = 0;
    static final int METADATA_TYPE_RATING = 3;
    static final int METADATA_TYPE_TEXT = 1;
    private static final String[] PREFERRED_BITMAP_ORDER = new String[]{METADATA_KEY_DISPLAY_ICON, METADATA_KEY_ART, METADATA_KEY_ALBUM_ART};
    private static final String[] PREFERRED_DESCRIPTION_ORDER = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", METADATA_KEY_ALBUM, "android.media.metadata.ALBUM_ARTIST", METADATA_KEY_WRITER, "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
    private static final String[] PREFERRED_URI_ORDER = new String[]{METADATA_KEY_DISPLAY_ICON_URI, METADATA_KEY_ART_URI, METADATA_KEY_ALBUM_ART_URI};
    private static final String TAG = "MediaMetadata";
    final Bundle mBundle;
    private MediaDescriptionCompat mDescription;
    private Object mMetadataObj;

    /* renamed from: android.support.v4.media.MediaMetadataCompat$1 */
    static class C01951 implements Creator<MediaMetadataCompat> {
        C01951() {
        }

        public MediaMetadataCompat createFromParcel(Parcel parcel) {
            return new MediaMetadataCompat(parcel);
        }

        public MediaMetadataCompat[] newArray(int i) {
            return new MediaMetadataCompat[i];
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BitmapKey {
    }

    public static final class Builder {
        private final Bundle mBundle;

        public Builder() {
            this.mBundle = new Bundle();
        }

        public Builder(MediaMetadataCompat mediaMetadataCompat) {
            this.mBundle = new Bundle(mediaMetadataCompat.mBundle);
            MediaSessionCompat.ensureClassLoader(this.mBundle);
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Builder(MediaMetadataCompat mediaMetadataCompat, int i) {
            this(mediaMetadataCompat);
            for (String str : this.mBundle.keySet()) {
                Object obj = this.mBundle.get(str);
                if (obj instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) obj;
                    if (bitmap.getHeight() > i || bitmap.getWidth() > i) {
                        putBitmap(str, scaleBitmap(bitmap, i));
                    }
                }
            }
        }

        public Builder putText(String str, CharSequence charSequence) {
            if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(str)) {
                if (((Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str)).intValue() != 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The ");
                    stringBuilder.append(str);
                    stringBuilder.append(" key cannot be used to put a CharSequence");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            this.mBundle.putCharSequence(str, charSequence);
            return this;
        }

        public Builder putString(String str, String str2) {
            if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(str)) {
                if (((Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str)).intValue() != 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The ");
                    stringBuilder.append(str);
                    stringBuilder.append(" key cannot be used to put a String");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            this.mBundle.putCharSequence(str, str2);
            return this;
        }

        public Builder putLong(String str, long j) {
            if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(str)) {
                if (((Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str)).intValue() != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The ");
                    stringBuilder.append(str);
                    stringBuilder.append(" key cannot be used to put a long");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            this.mBundle.putLong(str, j);
            return this;
        }

        public Builder putRating(String str, RatingCompat ratingCompat) {
            if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(str)) {
                if (((Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str)).intValue() != 3) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The ");
                    stringBuilder.append(str);
                    stringBuilder.append(" key cannot be used to put a Rating");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            if (VERSION.SDK_INT >= 19) {
                this.mBundle.putParcelable(str, (Parcelable) ratingCompat.getRating());
            } else {
                this.mBundle.putParcelable(str, ratingCompat);
            }
            return this;
        }

        public Builder putBitmap(String str, Bitmap bitmap) {
            if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(str)) {
                if (((Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str)).intValue() != 2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The ");
                    stringBuilder.append(str);
                    stringBuilder.append(" key cannot be used to put a Bitmap");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            this.mBundle.putParcelable(str, bitmap);
            return this;
        }

        public MediaMetadataCompat build() {
            return new MediaMetadataCompat(this.mBundle);
        }

        private Bitmap scaleBitmap(Bitmap bitmap, int i) {
            i = (float) i;
            i = Math.min(i / ((float) bitmap.getWidth()), i / ((float) bitmap.getHeight()));
            return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * i), (int) (((float) bitmap.getHeight()) * i), true);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LongKey {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RatingKey {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextKey {
    }

    public int describeContents() {
        return 0;
    }

    static {
        METADATA_KEYS_TYPE.put("android.media.metadata.TITLE", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_WRITER, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_COMPILATION, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DATE, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put(METADATA_KEY_GENRE, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put(METADATA_KEY_NUM_TRACKS, Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
        METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_ART, Integer.valueOf(2));
        METADATA_KEYS_TYPE.put(METADATA_KEY_ART_URI, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM_ART, Integer.valueOf(2));
        METADATA_KEYS_TYPE.put(METADATA_KEY_ALBUM_ART_URI, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_USER_RATING, Integer.valueOf(3));
        METADATA_KEYS_TYPE.put(METADATA_KEY_RATING, Integer.valueOf(3));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_TITLE, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_SUBTITLE, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_DESCRIPTION, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_ICON, Integer.valueOf(2));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DISPLAY_ICON_URI, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_MEDIA_ID, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_BT_FOLDER_TYPE, Integer.valueOf(0));
        METADATA_KEYS_TYPE.put(METADATA_KEY_MEDIA_URI, Integer.valueOf(1));
        METADATA_KEYS_TYPE.put(METADATA_KEY_ADVERTISEMENT, Integer.valueOf(0));
        METADATA_KEYS_TYPE.put(METADATA_KEY_DOWNLOAD_STATUS, Integer.valueOf(0));
    }

    MediaMetadataCompat(Bundle bundle) {
        this.mBundle = new Bundle(bundle);
        MediaSessionCompat.ensureClassLoader(this.mBundle);
    }

    MediaMetadataCompat(Parcel parcel) {
        this.mBundle = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public boolean containsKey(String str) {
        return this.mBundle.containsKey(str);
    }

    public CharSequence getText(String str) {
        return this.mBundle.getCharSequence(str);
    }

    public String getString(String str) {
        str = this.mBundle.getCharSequence(str);
        return str != null ? str.toString() : null;
    }

    public long getLong(String str) {
        return this.mBundle.getLong(str, 0);
    }

    public RatingCompat getRating(String str) {
        try {
            if (VERSION.SDK_INT >= 19) {
                return RatingCompat.fromRating(this.mBundle.getParcelable(str));
            }
            return (RatingCompat) this.mBundle.getParcelable(str);
        } catch (String str2) {
            Log.w(TAG, "Failed to retrieve a key as Rating.", str2);
            return null;
        }
    }

    public Bitmap getBitmap(String str) {
        try {
            return (Bitmap) this.mBundle.getParcelable(str);
        } catch (String str2) {
            Log.w(TAG, "Failed to retrieve a key as Bitmap.", str2);
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.v4.media.MediaDescriptionCompat getDescription() {
        /*
        r10 = this;
        r0 = r10.mDescription;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = "android.media.metadata.MEDIA_ID";
        r0 = r10.getString(r0);
        r1 = 3;
        r1 = new java.lang.CharSequence[r1];
        r2 = "android.media.metadata.DISPLAY_TITLE";
        r2 = r10.getText(r2);
        r3 = android.text.TextUtils.isEmpty(r2);
        r4 = 2;
        r5 = 1;
        r6 = 0;
        if (r3 != 0) goto L_0x0030;
    L_0x001d:
        r1[r6] = r2;
        r2 = "android.media.metadata.DISPLAY_SUBTITLE";
        r2 = r10.getText(r2);
        r1[r5] = r2;
        r2 = "android.media.metadata.DISPLAY_DESCRIPTION";
        r2 = r10.getText(r2);
        r1[r4] = r2;
        goto L_0x004f;
    L_0x0030:
        r2 = 0;
        r3 = 0;
    L_0x0032:
        r7 = r1.length;
        if (r2 >= r7) goto L_0x004f;
    L_0x0035:
        r7 = PREFERRED_DESCRIPTION_ORDER;
        r8 = r7.length;
        if (r3 >= r8) goto L_0x004f;
    L_0x003a:
        r8 = r3 + 1;
        r3 = r7[r3];
        r3 = r10.getText(r3);
        r7 = android.text.TextUtils.isEmpty(r3);
        if (r7 != 0) goto L_0x004d;
    L_0x0048:
        r7 = r2 + 1;
        r1[r2] = r3;
        r2 = r7;
    L_0x004d:
        r3 = r8;
        goto L_0x0032;
    L_0x004f:
        r2 = 0;
    L_0x0050:
        r3 = PREFERRED_BITMAP_ORDER;
        r7 = r3.length;
        r8 = 0;
        if (r2 >= r7) goto L_0x0062;
    L_0x0056:
        r3 = r3[r2];
        r3 = r10.getBitmap(r3);
        if (r3 == 0) goto L_0x005f;
    L_0x005e:
        goto L_0x0063;
    L_0x005f:
        r2 = r2 + 1;
        goto L_0x0050;
    L_0x0062:
        r3 = r8;
    L_0x0063:
        r2 = 0;
    L_0x0064:
        r7 = PREFERRED_URI_ORDER;
        r9 = r7.length;
        if (r2 >= r9) goto L_0x007d;
    L_0x0069:
        r7 = r7[r2];
        r7 = r10.getString(r7);
        r9 = android.text.TextUtils.isEmpty(r7);
        if (r9 != 0) goto L_0x007a;
    L_0x0075:
        r2 = android.net.Uri.parse(r7);
        goto L_0x007e;
    L_0x007a:
        r2 = r2 + 1;
        goto L_0x0064;
    L_0x007d:
        r2 = r8;
    L_0x007e:
        r7 = "android.media.metadata.MEDIA_URI";
        r7 = r10.getString(r7);
        r9 = android.text.TextUtils.isEmpty(r7);
        if (r9 != 0) goto L_0x008e;
    L_0x008a:
        r8 = android.net.Uri.parse(r7);
    L_0x008e:
        r7 = new android.support.v4.media.MediaDescriptionCompat$Builder;
        r7.<init>();
        r7.setMediaId(r0);
        r0 = r1[r6];
        r7.setTitle(r0);
        r0 = r1[r5];
        r7.setSubtitle(r0);
        r0 = r1[r4];
        r7.setDescription(r0);
        r7.setIconBitmap(r3);
        r7.setIconUri(r2);
        r7.setMediaUri(r8);
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = r10.mBundle;
        r2 = "android.media.metadata.BT_FOLDER_TYPE";
        r1 = r1.containsKey(r2);
        if (r1 == 0) goto L_0x00c8;
    L_0x00bd:
        r1 = "android.media.extra.BT_FOLDER_TYPE";
        r2 = "android.media.metadata.BT_FOLDER_TYPE";
        r2 = r10.getLong(r2);
        r0.putLong(r1, r2);
    L_0x00c8:
        r1 = r10.mBundle;
        r2 = "android.media.metadata.DOWNLOAD_STATUS";
        r1 = r1.containsKey(r2);
        if (r1 == 0) goto L_0x00dd;
    L_0x00d2:
        r1 = "android.media.extra.DOWNLOAD_STATUS";
        r2 = "android.media.metadata.DOWNLOAD_STATUS";
        r2 = r10.getLong(r2);
        r0.putLong(r1, r2);
    L_0x00dd:
        r1 = r0.isEmpty();
        if (r1 != 0) goto L_0x00e6;
    L_0x00e3:
        r7.setExtras(r0);
    L_0x00e6:
        r0 = r7.build();
        r10.mDescription = r0;
        r0 = r10.mDescription;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaMetadataCompat.getDescription():android.support.v4.media.MediaDescriptionCompat");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mBundle);
    }

    public int size() {
        return this.mBundle.size();
    }

    public Set<String> keySet() {
        return this.mBundle.keySet();
    }

    public Bundle getBundle() {
        return new Bundle(this.mBundle);
    }

    public static MediaMetadataCompat fromMediaMetadata(Object obj) {
        if (obj == null || VERSION.SDK_INT < 21) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        MediaMetadataCompatApi21.writeToParcel(obj, obtain, 0);
        obtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompat = (MediaMetadataCompat) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        mediaMetadataCompat.mMetadataObj = obj;
        return mediaMetadataCompat;
    }

    public Object getMediaMetadata() {
        if (this.mMetadataObj == null && VERSION.SDK_INT >= 21) {
            Parcel obtain = Parcel.obtain();
            writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(obtain);
            obtain.recycle();
        }
        return this.mMetadataObj;
    }
}
