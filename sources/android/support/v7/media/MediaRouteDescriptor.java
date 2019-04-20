package android.support.v7.media;

import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MediaRouteDescriptor {
    static final String KEY_CAN_DISCONNECT = "canDisconnect";
    static final String KEY_CONNECTING = "connecting";
    static final String KEY_CONNECTION_STATE = "connectionState";
    static final String KEY_CONTROL_FILTERS = "controlFilters";
    static final String KEY_DESCRIPTION = "status";
    static final String KEY_DEVICE_TYPE = "deviceType";
    static final String KEY_ENABLED = "enabled";
    static final String KEY_EXTRAS = "extras";
    static final String KEY_GROUP_MEMBER_IDS = "groupMemberIds";
    static final String KEY_ICON_URI = "iconUri";
    static final String KEY_ID = "id";
    static final String KEY_MAX_CLIENT_VERSION = "maxClientVersion";
    static final String KEY_MIN_CLIENT_VERSION = "minClientVersion";
    static final String KEY_NAME = "name";
    static final String KEY_PLAYBACK_STREAM = "playbackStream";
    static final String KEY_PLAYBACK_TYPE = "playbackType";
    static final String KEY_PRESENTATION_DISPLAY_ID = "presentationDisplayId";
    static final String KEY_SETTINGS_INTENT = "settingsIntent";
    static final String KEY_VOLUME = "volume";
    static final String KEY_VOLUME_HANDLING = "volumeHandling";
    static final String KEY_VOLUME_MAX = "volumeMax";
    final Bundle mBundle;
    List<IntentFilter> mControlFilters;

    public static final class Builder {
        private final Bundle mBundle;
        private ArrayList<IntentFilter> mControlFilters;
        private ArrayList<String> mGroupMemberIds;

        public android.support.v7.media.MediaRouteDescriptor.Builder addControlFilters(java.util.Collection<android.content.IntentFilter> r2) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x0025 in {6, 7, 9} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r1 = this;
            if (r2 == 0) goto L_0x001d;
        L_0x0002:
            r0 = r2.isEmpty();
            if (r0 != 0) goto L_0x001c;
        L_0x0008:
            r2 = r2.iterator();
        L_0x000c:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x001c;
        L_0x0012:
            r0 = r2.next();
            r0 = (android.content.IntentFilter) r0;
            r1.addControlFilter(r0);
            goto L_0x000c;
        L_0x001c:
            return r1;
        L_0x001d:
            r2 = new java.lang.IllegalArgumentException;
            r0 = "filters must not be null";
            r2.<init>(r0);
            throw r2;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.media.MediaRouteDescriptor.Builder.addControlFilters(java.util.Collection):android.support.v7.media.MediaRouteDescriptor$Builder");
        }

        @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
        public android.support.v7.media.MediaRouteDescriptor.Builder addGroupMemberIds(java.util.Collection<java.lang.String> r2) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:10:0x0025 in {6, 7, 9} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r1 = this;
            if (r2 == 0) goto L_0x001d;
        L_0x0002:
            r0 = r2.isEmpty();
            if (r0 != 0) goto L_0x001c;
        L_0x0008:
            r2 = r2.iterator();
        L_0x000c:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x001c;
        L_0x0012:
            r0 = r2.next();
            r0 = (java.lang.String) r0;
            r1.addGroupMemberId(r0);
            goto L_0x000c;
        L_0x001c:
            return r1;
        L_0x001d:
            r2 = new java.lang.IllegalArgumentException;
            r0 = "groupMemberIds must not be null";
            r2.<init>(r0);
            throw r2;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.media.MediaRouteDescriptor.Builder.addGroupMemberIds(java.util.Collection):android.support.v7.media.MediaRouteDescriptor$Builder");
        }

        public Builder(String str, String str2) {
            this.mBundle = new Bundle();
            setId(str);
            setName(str2);
        }

        public Builder(MediaRouteDescriptor mediaRouteDescriptor) {
            if (mediaRouteDescriptor != null) {
                this.mBundle = new Bundle(mediaRouteDescriptor.mBundle);
                mediaRouteDescriptor.ensureControlFilters();
                if (!mediaRouteDescriptor.mControlFilters.isEmpty()) {
                    this.mControlFilters = new ArrayList(mediaRouteDescriptor.mControlFilters);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("descriptor must not be null");
        }

        public Builder setId(String str) {
            this.mBundle.putString(MediaRouteDescriptor.KEY_ID, str);
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Builder addGroupMemberId(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("groupMemberId must not be empty");
            }
            if (this.mGroupMemberIds == null) {
                this.mGroupMemberIds = new ArrayList();
            }
            if (!this.mGroupMemberIds.contains(str)) {
                this.mGroupMemberIds.add(str);
            }
            return this;
        }

        public Builder setName(String str) {
            this.mBundle.putString(MediaRouteDescriptor.KEY_NAME, str);
            return this;
        }

        public Builder setDescription(String str) {
            this.mBundle.putString("status", str);
            return this;
        }

        public Builder setIconUri(Uri uri) {
            if (uri != null) {
                this.mBundle.putString(MediaRouteDescriptor.KEY_ICON_URI, uri.toString());
                return this;
            }
            throw new IllegalArgumentException("iconUri must not be null");
        }

        public Builder setEnabled(boolean z) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_ENABLED, z);
            return this;
        }

        @Deprecated
        public Builder setConnecting(boolean z) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_CONNECTING, z);
            return this;
        }

        public Builder setConnectionState(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_CONNECTION_STATE, i);
            return this;
        }

        public Builder setCanDisconnect(boolean z) {
            this.mBundle.putBoolean(MediaRouteDescriptor.KEY_CAN_DISCONNECT, z);
            return this;
        }

        public Builder setSettingsActivity(IntentSender intentSender) {
            this.mBundle.putParcelable(MediaRouteDescriptor.KEY_SETTINGS_INTENT, intentSender);
            return this;
        }

        public Builder addControlFilter(IntentFilter intentFilter) {
            if (intentFilter != null) {
                if (this.mControlFilters == null) {
                    this.mControlFilters = new ArrayList();
                }
                if (!this.mControlFilters.contains(intentFilter)) {
                    this.mControlFilters.add(intentFilter);
                }
                return this;
            }
            throw new IllegalArgumentException("filter must not be null");
        }

        public Builder setPlaybackType(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_PLAYBACK_TYPE, i);
            return this;
        }

        public Builder setPlaybackStream(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_PLAYBACK_STREAM, i);
            return this;
        }

        public Builder setDeviceType(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_DEVICE_TYPE, i);
            return this;
        }

        public Builder setVolume(int i) {
            this.mBundle.putInt("volume", i);
            return this;
        }

        public Builder setVolumeMax(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_VOLUME_MAX, i);
            return this;
        }

        public Builder setVolumeHandling(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_VOLUME_HANDLING, i);
            return this;
        }

        public Builder setPresentationDisplayId(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_PRESENTATION_DISPLAY_ID, i);
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mBundle.putBundle(MediaRouteDescriptor.KEY_EXTRAS, bundle);
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Builder setMinClientVersion(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_MIN_CLIENT_VERSION, i);
            return this;
        }

        @RestrictTo({Scope.LIBRARY_GROUP})
        public Builder setMaxClientVersion(int i) {
            this.mBundle.putInt(MediaRouteDescriptor.KEY_MAX_CLIENT_VERSION, i);
            return this;
        }

        public MediaRouteDescriptor build() {
            ArrayList arrayList = this.mControlFilters;
            if (arrayList != null) {
                this.mBundle.putParcelableArrayList(MediaRouteDescriptor.KEY_CONTROL_FILTERS, arrayList);
            }
            arrayList = this.mGroupMemberIds;
            if (arrayList != null) {
                this.mBundle.putStringArrayList(MediaRouteDescriptor.KEY_GROUP_MEMBER_IDS, arrayList);
            }
            return new MediaRouteDescriptor(this.mBundle, this.mControlFilters);
        }
    }

    MediaRouteDescriptor(Bundle bundle, List<IntentFilter> list) {
        this.mBundle = bundle;
        this.mControlFilters = list;
    }

    public String getId() {
        return this.mBundle.getString(KEY_ID);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public List<String> getGroupMemberIds() {
        return this.mBundle.getStringArrayList(KEY_GROUP_MEMBER_IDS);
    }

    public String getName() {
        return this.mBundle.getString(KEY_NAME);
    }

    public String getDescription() {
        return this.mBundle.getString("status");
    }

    public Uri getIconUri() {
        String string = this.mBundle.getString(KEY_ICON_URI);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public boolean isEnabled() {
        return this.mBundle.getBoolean(KEY_ENABLED, true);
    }

    @Deprecated
    public boolean isConnecting() {
        return this.mBundle.getBoolean(KEY_CONNECTING, false);
    }

    public int getConnectionState() {
        return this.mBundle.getInt(KEY_CONNECTION_STATE, 0);
    }

    public boolean canDisconnectAndKeepPlaying() {
        return this.mBundle.getBoolean(KEY_CAN_DISCONNECT, false);
    }

    public IntentSender getSettingsActivity() {
        return (IntentSender) this.mBundle.getParcelable(KEY_SETTINGS_INTENT);
    }

    public List<IntentFilter> getControlFilters() {
        ensureControlFilters();
        return this.mControlFilters;
    }

    void ensureControlFilters() {
        if (this.mControlFilters == null) {
            this.mControlFilters = this.mBundle.getParcelableArrayList(KEY_CONTROL_FILTERS);
            if (this.mControlFilters == null) {
                this.mControlFilters = Collections.emptyList();
            }
        }
    }

    public int getPlaybackType() {
        return this.mBundle.getInt(KEY_PLAYBACK_TYPE, 1);
    }

    public int getPlaybackStream() {
        return this.mBundle.getInt(KEY_PLAYBACK_STREAM, -1);
    }

    public int getDeviceType() {
        return this.mBundle.getInt(KEY_DEVICE_TYPE);
    }

    public int getVolume() {
        return this.mBundle.getInt("volume");
    }

    public int getVolumeMax() {
        return this.mBundle.getInt(KEY_VOLUME_MAX);
    }

    public int getVolumeHandling() {
        return this.mBundle.getInt(KEY_VOLUME_HANDLING, 0);
    }

    public int getPresentationDisplayId() {
        return this.mBundle.getInt(KEY_PRESENTATION_DISPLAY_ID, -1);
    }

    public Bundle getExtras() {
        return this.mBundle.getBundle(KEY_EXTRAS);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getMinClientVersion() {
        return this.mBundle.getInt(KEY_MIN_CLIENT_VERSION, 1);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getMaxClientVersion() {
        return this.mBundle.getInt(KEY_MAX_CLIENT_VERSION, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public boolean isValid() {
        ensureControlFilters();
        if (!(TextUtils.isEmpty(getId()) || TextUtils.isEmpty(getName()))) {
            if (!this.mControlFilters.contains(null)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MediaRouteDescriptor{ ");
        stringBuilder.append("id=");
        stringBuilder.append(getId());
        stringBuilder.append(", groupMemberIds=");
        stringBuilder.append(getGroupMemberIds());
        stringBuilder.append(", name=");
        stringBuilder.append(getName());
        stringBuilder.append(", description=");
        stringBuilder.append(getDescription());
        stringBuilder.append(", iconUri=");
        stringBuilder.append(getIconUri());
        stringBuilder.append(", isEnabled=");
        stringBuilder.append(isEnabled());
        stringBuilder.append(", isConnecting=");
        stringBuilder.append(isConnecting());
        stringBuilder.append(", connectionState=");
        stringBuilder.append(getConnectionState());
        stringBuilder.append(", controlFilters=");
        stringBuilder.append(Arrays.toString(getControlFilters().toArray()));
        stringBuilder.append(", playbackType=");
        stringBuilder.append(getPlaybackType());
        stringBuilder.append(", playbackStream=");
        stringBuilder.append(getPlaybackStream());
        stringBuilder.append(", deviceType=");
        stringBuilder.append(getDeviceType());
        stringBuilder.append(", volume=");
        stringBuilder.append(getVolume());
        stringBuilder.append(", volumeMax=");
        stringBuilder.append(getVolumeMax());
        stringBuilder.append(", volumeHandling=");
        stringBuilder.append(getVolumeHandling());
        stringBuilder.append(", presentationDisplayId=");
        stringBuilder.append(getPresentationDisplayId());
        stringBuilder.append(", extras=");
        stringBuilder.append(getExtras());
        stringBuilder.append(", isValid=");
        stringBuilder.append(isValid());
        stringBuilder.append(", minClientVersion=");
        stringBuilder.append(getMinClientVersion());
        stringBuilder.append(", maxClientVersion=");
        stringBuilder.append(getMaxClientVersion());
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public static MediaRouteDescriptor fromBundle(Bundle bundle) {
        return bundle != null ? new MediaRouteDescriptor(bundle, null) : null;
    }
}
