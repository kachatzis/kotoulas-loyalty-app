package android.support.v7.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaControllerCompat.Callback;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.util.ObjectsCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteGroup;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.support.v7.mediarouter.C0303R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MediaRouteCastDialog extends AppCompatDialog {
    static final int CONNECTION_TIMEOUT_MILLIS = ((int) TimeUnit.SECONDS.toMillis(30));
    static final int MSG_UPDATE_ROUTES = 1;
    static final String TAG = "MediaRouteCastDialog";
    private static final long UPDATE_ROUTES_DELAY_MS = 300;
    private RecyclerAdapter mAdapter;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    private ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    MediaDescriptionCompat mDescription;
    FetchArtTask mFetchArtTask;
    private final Handler mHandler;
    private long mLastUpdateTime;
    MediaControllerCompat mMediaController;
    private RelativeLayout mMetadataLayout;
    private RecyclerView mRecyclerView;
    final RouteInfo mRoute;
    final MediaRouter mRouter;
    final List<RouteInfo> mRoutes;
    private MediaRouteSelector mSelector;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private String mTitlePlaceholder;
    private TextView mTitleView;
    VolumeChangeListener mVolumeChangeListener;
    int mVolumeSliderColor;

    /* renamed from: android.support.v7.app.MediaRouteCastDialog$1 */
    class C02721 extends Handler {
        C02721() {
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                MediaRouteCastDialog.this.updateRoutes((List) message.obj);
            }
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteCastDialog$2 */
    class C02732 implements OnClickListener {
        C02732() {
        }

        public void onClick(View view) {
            MediaRouteCastDialog.this.dismiss();
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteCastDialog$3 */
    class C02743 implements OnClickListener {
        C02743() {
        }

        public void onClick(View view) {
            if (MediaRouteCastDialog.this.mRoute.isSelected() != null) {
                MediaRouteCastDialog.this.mRouter.unselect(2);
            }
            MediaRouteCastDialog.this.dismiss();
        }
    }

    private class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;

        FetchArtTask() {
            Uri uri = null;
            Bitmap iconBitmap = MediaRouteCastDialog.this.mDescription == null ? null : MediaRouteCastDialog.this.mDescription.getIconBitmap();
            if (MediaRouteCastDialog.isBitmapRecycled(iconBitmap)) {
                Log.w(MediaRouteCastDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
                iconBitmap = null;
            }
            this.mIconBitmap = iconBitmap;
            if (MediaRouteCastDialog.this.mDescription != null) {
                uri = MediaRouteCastDialog.this.mDescription.getIconUri();
            }
            this.mIconUri = uri;
        }

        public Bitmap getIconBitmap() {
            return this.mIconBitmap;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        protected void onPreExecute() {
            MediaRouteCastDialog.this.clearLoadedBitmap();
        }

        protected android.graphics.Bitmap doInBackground(java.lang.Void... r8) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
            r7 = this;
            r8 = r7.mIconBitmap;
            r0 = 0;
            r1 = 1;
            r2 = 0;
            if (r8 == 0) goto L_0x0009;
        L_0x0007:
            goto L_0x00d4;
        L_0x0009:
            r8 = r7.mIconUri;
            if (r8 == 0) goto L_0x00d3;
        L_0x000d:
            r8 = r7.openInputStreamByScheme(r8);	 Catch:{ IOException -> 0x00ac, all -> 0x00a9 }
            if (r8 != 0) goto L_0x0031;
        L_0x0013:
            r3 = "MediaRouteCastDialog";	 Catch:{ IOException -> 0x00a7 }
            r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00a7 }
            r4.<init>();	 Catch:{ IOException -> 0x00a7 }
            r5 = "Unable to open: ";	 Catch:{ IOException -> 0x00a7 }
            r4.append(r5);	 Catch:{ IOException -> 0x00a7 }
            r5 = r7.mIconUri;	 Catch:{ IOException -> 0x00a7 }
            r4.append(r5);	 Catch:{ IOException -> 0x00a7 }
            r4 = r4.toString();	 Catch:{ IOException -> 0x00a7 }
            android.util.Log.w(r3, r4);	 Catch:{ IOException -> 0x00a7 }
            if (r8 == 0) goto L_0x0030;
        L_0x002d:
            r8.close();	 Catch:{ IOException -> 0x0030 }
        L_0x0030:
            return r2;
        L_0x0031:
            r3 = new android.graphics.BitmapFactory$Options;	 Catch:{ IOException -> 0x00a7 }
            r3.<init>();	 Catch:{ IOException -> 0x00a7 }
            r3.inJustDecodeBounds = r1;	 Catch:{ IOException -> 0x00a7 }
            android.graphics.BitmapFactory.decodeStream(r8, r2, r3);	 Catch:{ IOException -> 0x00a7 }
            r4 = r3.outWidth;	 Catch:{ IOException -> 0x00a7 }
            if (r4 == 0) goto L_0x00a1;	 Catch:{ IOException -> 0x00a7 }
        L_0x003f:
            r4 = r3.outHeight;	 Catch:{ IOException -> 0x00a7 }
            if (r4 != 0) goto L_0x0044;
        L_0x0043:
            goto L_0x00a1;
        L_0x0044:
            r8.reset();	 Catch:{ IOException -> 0x0048 }
            goto L_0x0071;
        L_0x0048:
            r8.close();	 Catch:{ IOException -> 0x00a7 }
            r4 = r7.mIconUri;	 Catch:{ IOException -> 0x00a7 }
            r8 = r7.openInputStreamByScheme(r4);	 Catch:{ IOException -> 0x00a7 }
            if (r8 != 0) goto L_0x0071;	 Catch:{ IOException -> 0x00a7 }
        L_0x0053:
            r3 = "MediaRouteCastDialog";	 Catch:{ IOException -> 0x00a7 }
            r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00a7 }
            r4.<init>();	 Catch:{ IOException -> 0x00a7 }
            r5 = "Unable to open: ";	 Catch:{ IOException -> 0x00a7 }
            r4.append(r5);	 Catch:{ IOException -> 0x00a7 }
            r5 = r7.mIconUri;	 Catch:{ IOException -> 0x00a7 }
            r4.append(r5);	 Catch:{ IOException -> 0x00a7 }
            r4 = r4.toString();	 Catch:{ IOException -> 0x00a7 }
            android.util.Log.w(r3, r4);	 Catch:{ IOException -> 0x00a7 }
            if (r8 == 0) goto L_0x0070;
        L_0x006d:
            r8.close();	 Catch:{ IOException -> 0x0070 }
        L_0x0070:
            return r2;
        L_0x0071:
            r3.inJustDecodeBounds = r0;	 Catch:{ IOException -> 0x00a7 }
            r4 = android.support.v7.app.MediaRouteCastDialog.this;	 Catch:{ IOException -> 0x00a7 }
            r5 = r3.outWidth;	 Catch:{ IOException -> 0x00a7 }
            r6 = r3.outHeight;	 Catch:{ IOException -> 0x00a7 }
            r4 = r4.getDesiredArtHeight(r5, r6);	 Catch:{ IOException -> 0x00a7 }
            r5 = r3.outHeight;	 Catch:{ IOException -> 0x00a7 }
            r5 = r5 / r4;	 Catch:{ IOException -> 0x00a7 }
            r4 = java.lang.Integer.highestOneBit(r5);	 Catch:{ IOException -> 0x00a7 }
            r4 = java.lang.Math.max(r1, r4);	 Catch:{ IOException -> 0x00a7 }
            r3.inSampleSize = r4;	 Catch:{ IOException -> 0x00a7 }
            r4 = r7.isCancelled();	 Catch:{ IOException -> 0x00a7 }
            if (r4 == 0) goto L_0x0096;
        L_0x0090:
            if (r8 == 0) goto L_0x0095;
        L_0x0092:
            r8.close();	 Catch:{ IOException -> 0x0095 }
        L_0x0095:
            return r2;
        L_0x0096:
            r3 = android.graphics.BitmapFactory.decodeStream(r8, r2, r3);	 Catch:{ IOException -> 0x00a7 }
            if (r8 == 0) goto L_0x009f;
        L_0x009c:
            r8.close();	 Catch:{ IOException -> 0x009f }
        L_0x009f:
            r8 = r3;
            goto L_0x00d4;
        L_0x00a1:
            if (r8 == 0) goto L_0x00a6;
        L_0x00a3:
            r8.close();	 Catch:{ IOException -> 0x00a6 }
        L_0x00a6:
            return r2;
        L_0x00a7:
            r3 = move-exception;
            goto L_0x00ae;
        L_0x00a9:
            r0 = move-exception;
            r8 = r2;
            goto L_0x00cd;
        L_0x00ac:
            r3 = move-exception;
            r8 = r2;
        L_0x00ae:
            r4 = "MediaRouteCastDialog";	 Catch:{ all -> 0x00cc }
            r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cc }
            r5.<init>();	 Catch:{ all -> 0x00cc }
            r6 = "Unable to open: ";	 Catch:{ all -> 0x00cc }
            r5.append(r6);	 Catch:{ all -> 0x00cc }
            r6 = r7.mIconUri;	 Catch:{ all -> 0x00cc }
            r5.append(r6);	 Catch:{ all -> 0x00cc }
            r5 = r5.toString();	 Catch:{ all -> 0x00cc }
            android.util.Log.w(r4, r5, r3);	 Catch:{ all -> 0x00cc }
            if (r8 == 0) goto L_0x00d3;
        L_0x00c8:
            r8.close();	 Catch:{ IOException -> 0x00d3 }
            goto L_0x00d3;
        L_0x00cc:
            r0 = move-exception;
        L_0x00cd:
            if (r8 == 0) goto L_0x00d2;
        L_0x00cf:
            r8.close();	 Catch:{ IOException -> 0x00d2 }
        L_0x00d2:
            throw r0;
        L_0x00d3:
            r8 = r2;
        L_0x00d4:
            r3 = android.support.v7.app.MediaRouteCastDialog.isBitmapRecycled(r8);
            if (r3 == 0) goto L_0x00f1;
        L_0x00da:
            r0 = "MediaRouteCastDialog";
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r3 = "Can't use recycled bitmap: ";
            r1.append(r3);
            r1.append(r8);
            r8 = r1.toString();
            android.util.Log.w(r0, r8);
            return r2;
        L_0x00f1:
            if (r8 == 0) goto L_0x0125;
        L_0x00f3:
            r2 = r8.getWidth();
            r3 = r8.getHeight();
            if (r2 >= r3) goto L_0x0125;
        L_0x00fd:
            r2 = new android.support.v7.graphics.Palette$Builder;
            r2.<init>(r8);
            r1 = r2.maximumColorCount(r1);
            r1 = r1.generate();
            r2 = r1.getSwatches();
            r2 = r2.isEmpty();
            if (r2 == 0) goto L_0x0115;
        L_0x0114:
            goto L_0x0123;
        L_0x0115:
            r1 = r1.getSwatches();
            r0 = r1.get(r0);
            r0 = (android.support.v7.graphics.Palette.Swatch) r0;
            r0 = r0.getRgb();
        L_0x0123:
            r7.mBackgroundColor = r0;
        L_0x0125:
            return r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.MediaRouteCastDialog.FetchArtTask.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
        }

        protected void onPostExecute(Bitmap bitmap) {
            MediaRouteCastDialog mediaRouteCastDialog = MediaRouteCastDialog.this;
            mediaRouteCastDialog.mFetchArtTask = null;
            if (!ObjectsCompat.equals(mediaRouteCastDialog.mArtIconBitmap, this.mIconBitmap) || !ObjectsCompat.equals(MediaRouteCastDialog.this.mArtIconUri, this.mIconUri)) {
                mediaRouteCastDialog = MediaRouteCastDialog.this;
                mediaRouteCastDialog.mArtIconBitmap = this.mIconBitmap;
                mediaRouteCastDialog.mArtIconLoadedBitmap = bitmap;
                mediaRouteCastDialog.mArtIconUri = this.mIconUri;
                mediaRouteCastDialog.mArtIconBackgroundColor = this.mBackgroundColor;
                mediaRouteCastDialog.mArtIconIsLoaded = true;
                mediaRouteCastDialog.update();
            }
        }

        private InputStream openInputStreamByScheme(Uri uri) throws IOException {
            String toLowerCase = uri.getScheme().toLowerCase();
            if (!("android.resource".equals(toLowerCase) || "content".equals(toLowerCase))) {
                if (!"file".equals(toLowerCase)) {
                    uri = new URL(uri.toString()).openConnection();
                    uri.setConnectTimeout(MediaRouteCastDialog.CONNECTION_TIMEOUT_MILLIS);
                    uri.setReadTimeout(MediaRouteCastDialog.CONNECTION_TIMEOUT_MILLIS);
                    uri = uri.getInputStream();
                    if (uri != null) {
                        return null;
                    }
                    return new BufferedInputStream(uri);
                }
            }
            uri = MediaRouteCastDialog.this.mContext.getContentResolver().openInputStream(uri);
            if (uri != null) {
                return new BufferedInputStream(uri);
            }
            return null;
        }
    }

    private class VolumeChangeListener implements OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        VolumeChangeListener() {
        }
    }

    private final class MediaControllerCallback extends Callback {
        MediaControllerCallback() {
        }

        public void onSessionDestroyed() {
            if (MediaRouteCastDialog.this.mMediaController != null) {
                MediaRouteCastDialog.this.mMediaController.unregisterCallback(MediaRouteCastDialog.this.mControllerCallback);
                MediaRouteCastDialog.this.mMediaController = null;
            }
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaRouteCastDialog.this.mDescription = mediaMetadataCompat == null ? null : mediaMetadataCompat.getDescription();
            MediaRouteCastDialog.this.updateArtIconIfNeeded();
            MediaRouteCastDialog.this.update();
        }
    }

    private final class MediaRouterCallback extends MediaRouter.Callback {
        MediaRouterCallback() {
        }

        public void onRouteAdded(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteCastDialog.this.refreshRoutes();
        }

        public void onRouteRemoved(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteCastDialog.this.refreshRoutes();
        }

        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteCastDialog.this.update();
        }

        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteCastDialog.this.update();
        }

        public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteCastDialog.this.refreshRoutes();
            MediaRouteCastDialog.this.update();
        }
    }

    private final class RecyclerAdapter extends Adapter<ViewHolder> {
        private static final int ITEM_TYPE_GROUP = 4;
        private static final int ITEM_TYPE_GROUP_VOLUME = 1;
        private static final int ITEM_TYPE_HEADER = 2;
        private static final int ITEM_TYPE_ROUTE = 3;
        private static final String TAG = "RecyclerAdapter";
        private final ArrayList<RouteInfo> mAvailableGroups = new ArrayList();
        private final ArrayList<RouteInfo> mAvailableRoutes = new ArrayList();
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        private final ArrayList<Item> mItems = new ArrayList();
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        private class Item {
            private final Object mData;
            private final int mType;

            Item(Object obj, int i) {
                this.mData = obj;
                this.mType = i;
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        private class GroupViewHolder extends ViewHolder {
            ImageView mImageView;
            TextView mTextView;

            GroupViewHolder(View view) {
                super(view);
                this.mImageView = (ImageView) view.findViewById(C0303R.id.mr_cast_group_icon);
                this.mTextView = (TextView) view.findViewById(C0303R.id.mr_cast_group_name);
            }

            public void bindGroupViewHolder(Item item) {
                RouteInfo routeInfo = (RouteInfo) item.getData();
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
                this.mTextView.setText(routeInfo.getName());
            }
        }

        private class GroupVolumeViewHolder extends ViewHolder {
            MediaRouteVolumeSlider mGroupVolumeSlider;
            TextView mTextView;

            GroupVolumeViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(C0303R.id.mr_group_volume_route_name);
                this.mGroupVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(C0303R.id.mr_group_volume_slider);
            }

            public void bindGroupVolumeView(Item item) {
                RouteInfo routeInfo = (RouteInfo) item.getData();
                this.mTextView.setText(routeInfo.getName().toUpperCase());
                this.mGroupVolumeSlider.setColor(MediaRouteCastDialog.this.mVolumeSliderColor);
                this.mGroupVolumeSlider.setTag(routeInfo);
                this.mGroupVolumeSlider.setProgress(MediaRouteCastDialog.this.mRoute.getVolume());
                this.mGroupVolumeSlider.setOnSeekBarChangeListener(MediaRouteCastDialog.this.mVolumeChangeListener);
            }
        }

        private class HeaderViewHolder extends ViewHolder {
            TextView mTextView;

            HeaderViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(C0303R.id.mr_dialog_header_name);
            }

            public void bindHeaderViewHolder(Item item) {
                this.mTextView.setText(item.getData().toString().toUpperCase());
            }
        }

        private class RouteViewHolder extends ViewHolder {
            CheckBox mCheckBox;
            ImageView mImageView;
            TextView mTextView;
            MediaRouteVolumeSlider mVolumeSlider;

            RouteViewHolder(View view) {
                super(view);
                this.mImageView = (ImageView) view.findViewById(C0303R.id.mr_cast_route_icon);
                this.mTextView = (TextView) view.findViewById(C0303R.id.mr_cast_route_name);
                this.mCheckBox = (CheckBox) view.findViewById(C0303R.id.mr_cast_checkbox);
                this.mVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(C0303R.id.mr_cast_volume_slider);
            }

            public void bindRouteViewHolder(Item item) {
                RouteInfo routeInfo = (RouteInfo) item.getData();
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
                this.mTextView.setText(routeInfo.getName());
                this.mCheckBox.setChecked(RecyclerAdapter.this.isSelectedRoute(routeInfo));
                this.mVolumeSlider.setColor(MediaRouteCastDialog.this.mVolumeSliderColor);
                this.mVolumeSlider.setTag(routeInfo);
                this.mVolumeSlider.setProgress(routeInfo.getVolume());
                this.mVolumeSlider.setOnSeekBarChangeListener(MediaRouteCastDialog.this.mVolumeChangeListener);
            }
        }

        RecyclerAdapter() {
            this.mInflater = LayoutInflater.from(MediaRouteCastDialog.this.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(MediaRouteCastDialog.this.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(MediaRouteCastDialog.this.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(MediaRouteCastDialog.this.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGropuIcon(MediaRouteCastDialog.this.mContext);
            setItems();
        }

        boolean isSelectedRoute(RouteInfo routeInfo) {
            if (routeInfo.isSelected()) {
                return true;
            }
            if (MediaRouteCastDialog.this.mRoute instanceof RouteGroup) {
                for (RouteInfo id : ((RouteGroup) MediaRouteCastDialog.this.mRoute).getRoutes()) {
                    if (id.getId().equals(routeInfo.getId())) {
                        return true;
                    }
                }
            }
            return null;
        }

        void setItems() {
            Iterator it;
            this.mItems.clear();
            if (MediaRouteCastDialog.this.mRoute instanceof RouteGroup) {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mRoute, 1));
                for (RouteInfo item : ((RouteGroup) MediaRouteCastDialog.this.mRoute).getRoutes()) {
                    this.mItems.add(new Item(item, 3));
                }
            } else {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mRoute, 3));
            }
            this.mAvailableRoutes.clear();
            this.mAvailableGroups.clear();
            for (RouteInfo item2 : MediaRouteCastDialog.this.mRoutes) {
                if (!isSelectedRoute(item2)) {
                    if (item2 instanceof RouteGroup) {
                        this.mAvailableGroups.add(item2);
                    } else {
                        this.mAvailableRoutes.add(item2);
                    }
                }
            }
            if (this.mAvailableRoutes.size() > 0) {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mContext.getString(C0303R.string.mr_dialog_device_header), 2));
                it = this.mAvailableRoutes.iterator();
                while (it.hasNext()) {
                    this.mItems.add(new Item((RouteInfo) it.next(), 3));
                }
            }
            if (this.mAvailableGroups.size() > 0) {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mContext.getString(C0303R.string.mr_dialog_route_header), 2));
                it = this.mAvailableGroups.iterator();
                while (it.hasNext()) {
                    this.mItems.add(new Item((RouteInfo) it.next(), 4));
                }
            }
            notifyDataSetChanged();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            switch (i) {
                case 1:
                    return new GroupVolumeViewHolder(this.mInflater.inflate(C0303R.layout.mr_cast_group_volume_item, viewGroup, false));
                case 2:
                    return new HeaderViewHolder(this.mInflater.inflate(C0303R.layout.mr_dialog_header_item, viewGroup, false));
                case 3:
                    return new RouteViewHolder(this.mInflater.inflate(C0303R.layout.mr_cast_route_item, viewGroup, false));
                case 4:
                    return new GroupViewHolder(this.mInflater.inflate(C0303R.layout.mr_cast_group_item, viewGroup, false));
                default:
                    Log.w(TAG, "Cannot create ViewHolder because of wrong view type");
                    return null;
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            i = getItem(i);
            switch (itemViewType) {
                case 1:
                    ((GroupVolumeViewHolder) viewHolder).bindGroupVolumeView(i);
                    return;
                case 2:
                    ((HeaderViewHolder) viewHolder).bindHeaderViewHolder(i);
                    return;
                case 3:
                    ((RouteViewHolder) viewHolder).bindRouteViewHolder(i);
                    return;
                case 4:
                    ((GroupViewHolder) viewHolder).bindGroupViewHolder(i);
                    return;
                default:
                    Log.w(TAG, "Cannot bind item to ViewHolder because of wrong view type");
                    return;
            }
        }

        public int getItemCount() {
            return this.mItems.size();
        }

        Drawable getIconDrawable(RouteInfo routeInfo) {
            Object iconUri = routeInfo.getIconUri();
            if (iconUri != null) {
                try {
                    iconUri = Drawable.createFromStream(MediaRouteCastDialog.this.mContext.getContentResolver().openInputStream(iconUri), null);
                    if (iconUri != null) {
                        return iconUri;
                    }
                } catch (Throwable e) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to load ");
                    stringBuilder.append(iconUri);
                    Log.w(str, stringBuilder.toString(), e);
                }
            }
            return getDefaultIconDrawable(routeInfo);
        }

        private Drawable getDefaultIconDrawable(RouteInfo routeInfo) {
            switch (routeInfo.getDeviceType()) {
                case 1:
                    return this.mTvIcon;
                case 2:
                    return this.mSpeakerIcon;
                default:
                    if ((routeInfo instanceof RouteGroup) != null) {
                        return this.mSpeakerGroupIcon;
                    }
                    return this.mDefaultIcon;
            }
        }

        public int getItemViewType(int i) {
            return ((Item) this.mItems.get(i)).getType();
        }

        public Item getItem(int i) {
            return (Item) this.mItems.get(i);
        }
    }

    public MediaRouteCastDialog(Context context) {
        this(context, 0);
    }

    public MediaRouteCastDialog(Context context, int i) {
        context = MediaRouterThemeHelper.createThemedDialogContext(context, i, false);
        super(context, MediaRouterThemeHelper.createThemedDialogStyle(context));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mRoutes = new ArrayList();
        this.mHandler = new C02721();
        this.mContext = getContext();
        this.mRouter = MediaRouter.getInstance(this.mContext);
        this.mCallback = new MediaRouterCallback();
        this.mRoute = this.mRouter.getSelectedRoute();
        this.mControllerCallback = new MediaControllerCallback();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    private void setMediaSession(Token token) {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        MediaDescriptionCompat mediaDescriptionCompat = null;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.mControllerCallback);
            this.mMediaController = null;
        }
        if (token != null && this.mAttachedToWindow) {
            try {
                this.mMediaController = new MediaControllerCompat(this.mContext, token);
            } catch (Token token2) {
                Log.e(TAG, "Error creating media controller in setMediaSession.", token2);
            }
            token2 = this.mMediaController;
            if (token2 != null) {
                token2.registerCallback(this.mControllerCallback);
            }
            token2 = this.mMediaController;
            if (token2 == null) {
                token2 = null;
            } else {
                token2 = token2.getMetadata();
            }
            if (token2 != null) {
                mediaDescriptionCompat = token2.getDescription();
            }
            this.mDescription = mediaDescriptionCompat;
            updateArtIconIfNeeded();
            update();
        }
    }

    public Token getMediaSession() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        return mediaControllerCompat == null ? null : mediaControllerCompat.getSessionToken();
    }

    @NonNull
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    public void setRouteSelector(@NonNull MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.mSelector.equals(mediaRouteSelector)) {
            this.mSelector = mediaRouteSelector;
            if (this.mAttachedToWindow) {
                this.mRouter.removeCallback(this.mCallback);
                this.mRouter.addCallback(mediaRouteSelector, this.mCallback, 1);
            }
            refreshRoutes();
        }
    }

    public void onFilterRoutes(@NonNull List<RouteInfo> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!onFilterRoute((RouteInfo) list.get(size))) {
                list.remove(size);
            }
        }
    }

    public boolean onFilterRoute(@NonNull RouteInfo routeInfo) {
        return (routeInfo.isDefaultOrBluetooth() || !routeInfo.isEnabled() || routeInfo.matchesSelector(this.mSelector) == null) ? null : true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0303R.layout.mr_cast_dialog);
        this.mCloseButton = (ImageButton) findViewById(C0303R.id.mr_cast_close_button);
        this.mCloseButton.setOnClickListener(new C02732());
        this.mStopCastingButton = (Button) findViewById(C0303R.id.mr_cast_stop_button);
        this.mStopCastingButton.setOnClickListener(new C02743());
        this.mAdapter = new RecyclerAdapter();
        this.mRecyclerView = (RecyclerView) findViewById(C0303R.id.mr_cast_list);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mVolumeChangeListener = new VolumeChangeListener();
        this.mVolumeSliderColor = MediaRouterThemeHelper.getControllerColor(this.mContext, 0);
        this.mMetadataLayout = (RelativeLayout) findViewById(C0303R.id.mr_cast_meta);
        this.mArtView = (ImageView) findViewById(C0303R.id.mr_cast_meta_art);
        this.mTitleView = (TextView) findViewById(C0303R.id.mr_cast_meta_title);
        this.mSubtitleView = (TextView) findViewById(C0303R.id.mr_cast_meta_subtitle);
        this.mTitlePlaceholder = this.mContext.getResources().getString(C0303R.string.mr_cast_dialog_title_view_placeholder);
        this.mCreated = true;
        updateLayout();
    }

    void updateLayout() {
        getWindow().setLayout(-1, -1);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        updateArtIconIfNeeded();
        update();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        refreshRoutes();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
        setMediaSession(null);
    }

    void update() {
        if (this.mRoute.isSelected()) {
            if (!this.mRoute.isDefaultOrBluetooth()) {
                if (this.mCreated) {
                    if (this.mArtIconIsLoaded) {
                        if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                            this.mArtView.setVisibility(8);
                            String str = TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Can't set artwork image with recycled bitmap: ");
                            stringBuilder.append(this.mArtIconLoadedBitmap);
                            Log.w(str, stringBuilder.toString());
                        } else {
                            this.mArtView.setVisibility(0);
                            this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
                            this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
                            this.mMetadataLayout.setBackgroundDrawable(new BitmapDrawable(this.mArtIconLoadedBitmap));
                        }
                        clearLoadedBitmap();
                    } else {
                        this.mArtView.setVisibility(8);
                    }
                    updateMetadataLayout();
                    return;
                }
                return;
            }
        }
        dismiss();
    }

    static boolean isBitmapRecycled(Bitmap bitmap) {
        return (bitmap == null || bitmap.isRecycled() == null) ? null : true;
    }

    int getDesiredArtHeight(int i, int i2) {
        return this.mArtView.getHeight();
    }

    void updateArtIconIfNeeded() {
        if (isIconChanged()) {
            FetchArtTask fetchArtTask = this.mFetchArtTask;
            if (fetchArtTask != null) {
                fetchArtTask.cancel(true);
            }
            this.mFetchArtTask = new FetchArtTask();
            this.mFetchArtTask.execute(new Void[0]);
        }
    }

    void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    private boolean isIconChanged() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        Object obj = null;
        Bitmap iconBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        if (mediaDescriptionCompat2 != null) {
            obj = mediaDescriptionCompat2.getIconUri();
        }
        FetchArtTask fetchArtTask = this.mFetchArtTask;
        Bitmap iconBitmap2 = fetchArtTask == null ? this.mArtIconBitmap : fetchArtTask.getIconBitmap();
        FetchArtTask fetchArtTask2 = this.mFetchArtTask;
        Object iconUri = fetchArtTask2 == null ? this.mArtIconUri : fetchArtTask2.getIconUri();
        if (iconBitmap2 != iconBitmap) {
            return true;
        }
        if (iconBitmap2 == null && ObjectsCompat.equals(iconUri, r1)) {
            return true;
        }
        return false;
    }

    private void updateMetadataLayout() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        CharSequence charSequence = null;
        CharSequence title = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getTitle();
        int isEmpty = TextUtils.isEmpty(title) ^ 1;
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        if (mediaDescriptionCompat2 != null) {
            charSequence = mediaDescriptionCompat2.getSubtitle();
        }
        int isEmpty2 = TextUtils.isEmpty(charSequence) ^ 1;
        if (isEmpty != 0) {
            this.mTitleView.setText(title);
        } else {
            this.mTitleView.setText(this.mTitlePlaceholder);
        }
        if (isEmpty2 != 0) {
            this.mSubtitleView.setText(charSequence);
            this.mSubtitleView.setVisibility(0);
            return;
        }
        this.mSubtitleView.setVisibility(8);
    }

    public void refreshRoutes() {
        if (this.mAttachedToWindow) {
            List arrayList = new ArrayList(this.mRouter.getRoutes());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, RouteComparator.sInstance);
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= UPDATE_ROUTES_DELAY_MS) {
                updateRoutes(arrayList);
                return;
            }
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + UPDATE_ROUTES_DELAY_MS);
        }
    }

    void updateRoutes(List<RouteInfo> list) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(list);
        this.mAdapter.setItems();
    }
}
