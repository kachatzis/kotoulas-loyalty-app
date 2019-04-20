package android.support.v7.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class MediaRouteProviderDescriptor {
    private static final String KEY_ROUTES = "routes";
    final Bundle mBundle;
    List<MediaRouteDescriptor> mRoutes;

    public static final class Builder {
        private final Bundle mBundle;
        private ArrayList<MediaRouteDescriptor> mRoutes;

        public android.support.v7.media.MediaRouteProviderDescriptor.Builder addRoutes(java.util.Collection<android.support.v7.media.MediaRouteDescriptor> r2) {
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
            r0 = (android.support.v7.media.MediaRouteDescriptor) r0;
            r1.addRoute(r0);
            goto L_0x000c;
        L_0x001c:
            return r1;
        L_0x001d:
            r2 = new java.lang.IllegalArgumentException;
            r0 = "routes must not be null";
            r2.<init>(r0);
            throw r2;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.media.MediaRouteProviderDescriptor.Builder.addRoutes(java.util.Collection):android.support.v7.media.MediaRouteProviderDescriptor$Builder");
        }

        public Builder() {
            this.mBundle = new Bundle();
        }

        public Builder(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            if (mediaRouteProviderDescriptor != null) {
                this.mBundle = new Bundle(mediaRouteProviderDescriptor.mBundle);
                mediaRouteProviderDescriptor.ensureRoutes();
                if (!mediaRouteProviderDescriptor.mRoutes.isEmpty()) {
                    this.mRoutes = new ArrayList(mediaRouteProviderDescriptor.mRoutes);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("descriptor must not be null");
        }

        public Builder addRoute(MediaRouteDescriptor mediaRouteDescriptor) {
            if (mediaRouteDescriptor != null) {
                ArrayList arrayList = this.mRoutes;
                if (arrayList == null) {
                    this.mRoutes = new ArrayList();
                } else if (arrayList.contains(mediaRouteDescriptor)) {
                    throw new IllegalArgumentException("route descriptor already added");
                }
                this.mRoutes.add(mediaRouteDescriptor);
                return this;
            }
            throw new IllegalArgumentException("route must not be null");
        }

        Builder setRoutes(Collection<MediaRouteDescriptor> collection) {
            if (collection != null) {
                if (!collection.isEmpty()) {
                    this.mRoutes = new ArrayList(collection);
                    return this;
                }
            }
            this.mRoutes = null;
            this.mBundle.remove(MediaRouteProviderDescriptor.KEY_ROUTES);
            return this;
        }

        public MediaRouteProviderDescriptor build() {
            ArrayList arrayList = this.mRoutes;
            if (arrayList != null) {
                int size = arrayList.size();
                ArrayList arrayList2 = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    arrayList2.add(((MediaRouteDescriptor) this.mRoutes.get(i)).asBundle());
                }
                this.mBundle.putParcelableArrayList(MediaRouteProviderDescriptor.KEY_ROUTES, arrayList2);
            }
            return new MediaRouteProviderDescriptor(this.mBundle, this.mRoutes);
        }
    }

    MediaRouteProviderDescriptor(Bundle bundle, List<MediaRouteDescriptor> list) {
        this.mBundle = bundle;
        this.mRoutes = list;
    }

    public List<MediaRouteDescriptor> getRoutes() {
        ensureRoutes();
        return this.mRoutes;
    }

    void ensureRoutes() {
        if (this.mRoutes == null) {
            ArrayList parcelableArrayList = this.mBundle.getParcelableArrayList(KEY_ROUTES);
            if (parcelableArrayList != null) {
                if (!parcelableArrayList.isEmpty()) {
                    int size = parcelableArrayList.size();
                    this.mRoutes = new ArrayList(size);
                    for (int i = 0; i < size; i++) {
                        this.mRoutes.add(MediaRouteDescriptor.fromBundle((Bundle) parcelableArrayList.get(i)));
                    }
                    return;
                }
            }
            this.mRoutes = Collections.emptyList();
        }
    }

    public boolean isValid() {
        ensureRoutes();
        int size = this.mRoutes.size();
        int i = 0;
        while (i < size) {
            MediaRouteDescriptor mediaRouteDescriptor = (MediaRouteDescriptor) this.mRoutes.get(i);
            if (mediaRouteDescriptor != null) {
                if (mediaRouteDescriptor.isValid()) {
                    i++;
                }
            }
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MediaRouteProviderDescriptor{ ");
        stringBuilder.append("routes=");
        stringBuilder.append(Arrays.toString(getRoutes().toArray()));
        stringBuilder.append(", isValid=");
        stringBuilder.append(isValid());
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public static MediaRouteProviderDescriptor fromBundle(Bundle bundle) {
        return bundle != null ? new MediaRouteProviderDescriptor(bundle, null) : null;
    }
}
