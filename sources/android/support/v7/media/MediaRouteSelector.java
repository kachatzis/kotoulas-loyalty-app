package android.support.v7.media;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MediaRouteSelector {
    public static final MediaRouteSelector EMPTY = new MediaRouteSelector(new Bundle(), null);
    static final String KEY_CONTROL_CATEGORIES = "controlCategories";
    private final Bundle mBundle;
    List<String> mControlCategories;

    public static final class Builder {
        private ArrayList<String> mControlCategories;

        @android.support.annotation.NonNull
        public android.support.v7.media.MediaRouteSelector.Builder addControlCategories(@android.support.annotation.NonNull java.util.Collection<java.lang.String> r2) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
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
            r1.addControlCategory(r0);
            goto L_0x000c;
        L_0x001c:
            return r1;
        L_0x001d:
            r2 = new java.lang.IllegalArgumentException;
            r0 = "categories must not be null";
            r2.<init>(r0);
            throw r2;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.media.MediaRouteSelector.Builder.addControlCategories(java.util.Collection):android.support.v7.media.MediaRouteSelector$Builder");
        }

        public Builder(@NonNull MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector != null) {
                mediaRouteSelector.ensureControlCategories();
                if (!mediaRouteSelector.mControlCategories.isEmpty()) {
                    this.mControlCategories = new ArrayList(mediaRouteSelector.mControlCategories);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("selector must not be null");
        }

        @NonNull
        public Builder addControlCategory(@NonNull String str) {
            if (str != null) {
                if (this.mControlCategories == null) {
                    this.mControlCategories = new ArrayList();
                }
                if (!this.mControlCategories.contains(str)) {
                    this.mControlCategories.add(str);
                }
                return this;
            }
            throw new IllegalArgumentException("category must not be null");
        }

        @NonNull
        public Builder addSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector != null) {
                addControlCategories(mediaRouteSelector.getControlCategories());
                return this;
            }
            throw new IllegalArgumentException("selector must not be null");
        }

        @NonNull
        public MediaRouteSelector build() {
            if (this.mControlCategories == null) {
                return MediaRouteSelector.EMPTY;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(MediaRouteSelector.KEY_CONTROL_CATEGORIES, this.mControlCategories);
            return new MediaRouteSelector(bundle, this.mControlCategories);
        }
    }

    MediaRouteSelector(Bundle bundle, List<String> list) {
        this.mBundle = bundle;
        this.mControlCategories = list;
    }

    public List<String> getControlCategories() {
        ensureControlCategories();
        return this.mControlCategories;
    }

    void ensureControlCategories() {
        if (this.mControlCategories == null) {
            this.mControlCategories = this.mBundle.getStringArrayList(KEY_CONTROL_CATEGORIES);
            List list = this.mControlCategories;
            if (list == null || list.isEmpty()) {
                this.mControlCategories = Collections.emptyList();
            }
        }
    }

    public boolean hasControlCategory(String str) {
        if (str != null) {
            ensureControlCategories();
            int size = this.mControlCategories.size();
            for (int i = 0; i < size; i++) {
                if (((String) this.mControlCategories.get(i)).equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean matchesControlFilters(List<IntentFilter> list) {
        if (list != null) {
            ensureControlCategories();
            int size = this.mControlCategories.size();
            if (size != 0) {
                int size2 = list.size();
                for (int i = 0; i < size2; i++) {
                    IntentFilter intentFilter = (IntentFilter) list.get(i);
                    if (intentFilter != null) {
                        for (int i2 = 0; i2 < size; i2++) {
                            if (intentFilter.hasCategory((String) this.mControlCategories.get(i2))) {
                                return true;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return false;
    }

    public boolean contains(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            return null;
        }
        ensureControlCategories();
        mediaRouteSelector.ensureControlCategories();
        return this.mControlCategories.containsAll(mediaRouteSelector.mControlCategories);
    }

    public boolean isEmpty() {
        ensureControlCategories();
        return this.mControlCategories.isEmpty();
    }

    public boolean isValid() {
        ensureControlCategories();
        return !this.mControlCategories.contains(null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaRouteSelector)) {
            return null;
        }
        MediaRouteSelector mediaRouteSelector = (MediaRouteSelector) obj;
        ensureControlCategories();
        mediaRouteSelector.ensureControlCategories();
        return this.mControlCategories.equals(mediaRouteSelector.mControlCategories);
    }

    public int hashCode() {
        ensureControlCategories();
        return this.mControlCategories.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MediaRouteSelector{ ");
        stringBuilder.append("controlCategories=");
        stringBuilder.append(Arrays.toString(getControlCategories().toArray()));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public static MediaRouteSelector fromBundle(@Nullable Bundle bundle) {
        return bundle != null ? new MediaRouteSelector(bundle, null) : null;
    }
}
