package android.support.v7.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaControllerCompat.Callback;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.util.ObjectsCompat;
import android.support.v7.app.OverlayListView.OverlayObject;
import android.support.v7.app.OverlayListView.OverlayObject.OnAnimationEndListener;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteGroup;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.support.v7.mediarouter.C0303R;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MediaRouteControllerDialog extends AlertDialog {
    static final int BUTTON_DISCONNECT_RES_ID = 16908314;
    private static final int BUTTON_NEUTRAL_RES_ID = 16908315;
    static final int BUTTON_STOP_RES_ID = 16908313;
    static final int CONNECTION_TIMEOUT_MILLIS = ((int) TimeUnit.SECONDS.toMillis(30));
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MediaRouteCtrlDialog";
    static final int VOLUME_UPDATE_DELAY_MILLIS = 500;
    private Interpolator mAccelerateDecelerateInterpolator;
    final AccessibilityManager mAccessibilityManager;
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
    private FrameLayout mCustomControlLayout;
    private View mCustomControlView;
    FrameLayout mDefaultControlLayout;
    MediaDescriptionCompat mDescription;
    private LinearLayout mDialogAreaLayout;
    private int mDialogContentWidth;
    private Button mDisconnectButton;
    private View mDividerView;
    private FrameLayout mExpandableAreaLayout;
    private Interpolator mFastOutSlowInInterpolator;
    FetchArtTask mFetchArtTask;
    private MediaRouteExpandCollapseButton mGroupExpandCollapseButton;
    int mGroupListAnimationDurationMs;
    Runnable mGroupListFadeInAnimation;
    private int mGroupListFadeInDurationMs;
    private int mGroupListFadeOutDurationMs;
    private List<RouteInfo> mGroupMemberRoutes;
    Set<RouteInfo> mGroupMemberRoutesAdded;
    Set<RouteInfo> mGroupMemberRoutesAnimatingWithBitmap;
    private Set<RouteInfo> mGroupMemberRoutesRemoved;
    boolean mHasPendingUpdate;
    private Interpolator mInterpolator;
    boolean mIsGroupExpanded;
    boolean mIsGroupListAnimating;
    boolean mIsGroupListAnimationPending;
    private Interpolator mLinearOutSlowInInterpolator;
    MediaControllerCompat mMediaController;
    private LinearLayout mMediaMainControlLayout;
    boolean mPendingUpdateAnimationNeeded;
    private ImageButton mPlaybackControlButton;
    private RelativeLayout mPlaybackControlLayout;
    final RouteInfo mRoute;
    RouteInfo mRouteInVolumeSliderTouched;
    private TextView mRouteNameTextView;
    final MediaRouter mRouter;
    PlaybackStateCompat mState;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private TextView mTitleView;
    VolumeChangeListener mVolumeChangeListener;
    private boolean mVolumeControlEnabled;
    private LinearLayout mVolumeControlLayout;
    VolumeGroupAdapter mVolumeGroupAdapter;
    OverlayListView mVolumeGroupList;
    private int mVolumeGroupListItemHeight;
    private int mVolumeGroupListItemIconSize;
    private int mVolumeGroupListMaxHeight;
    private final int mVolumeGroupListPaddingTop;
    SeekBar mVolumeSlider;
    Map<RouteInfo, SeekBar> mVolumeSliderMap;

    /* renamed from: android.support.v7.app.MediaRouteControllerDialog$1 */
    class C02761 implements Runnable {
        C02761() {
        }

        public void run() {
            MediaRouteControllerDialog.this.startGroupListFadeInAnimation();
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteControllerDialog$2 */
    class C02772 implements OnClickListener {
        C02772() {
        }

        public void onClick(View view) {
            MediaRouteControllerDialog.this.dismiss();
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteControllerDialog$3 */
    class C02783 implements OnClickListener {
        public void onClick(View view) {
        }

        C02783() {
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteControllerDialog$4 */
    class C02794 implements OnClickListener {
        C02794() {
        }

        public void onClick(android.view.View r3) {
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
            r2 = this;
            r3 = android.support.v7.app.MediaRouteControllerDialog.this;
            r3 = r3.mMediaController;
            if (r3 == 0) goto L_0x002f;
        L_0x0006:
            r3 = android.support.v7.app.MediaRouteControllerDialog.this;
            r3 = r3.mMediaController;
            r3 = r3.getSessionActivity();
            if (r3 == 0) goto L_0x002f;
        L_0x0010:
            r3.send();	 Catch:{ CanceledException -> 0x0019 }
            r0 = android.support.v7.app.MediaRouteControllerDialog.this;	 Catch:{ CanceledException -> 0x0019 }
            r0.dismiss();	 Catch:{ CanceledException -> 0x0019 }
            goto L_0x002f;
        L_0x0019:
            r0 = "MediaRouteCtrlDialog";
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r1.append(r3);
            r3 = " was not sent, it had been canceled.";
            r1.append(r3);
            r3 = r1.toString();
            android.util.Log.e(r0, r3);
        L_0x002f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.MediaRouteControllerDialog.4.onClick(android.view.View):void");
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteControllerDialog$5 */
    class C02805 implements OnClickListener {
        C02805() {
        }

        public void onClick(View view) {
            view = MediaRouteControllerDialog.this;
            view.mIsGroupExpanded ^= true;
            if (MediaRouteControllerDialog.this.mIsGroupExpanded != null) {
                MediaRouteControllerDialog.this.mVolumeGroupList.setVisibility(0);
            }
            MediaRouteControllerDialog.this.loadInterpolator();
            MediaRouteControllerDialog.this.updateLayoutHeight(true);
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteControllerDialog$9 */
    class C02849 implements AnimationListener {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        C02849() {
        }

        public void onAnimationStart(Animation animation) {
            MediaRouteControllerDialog.this.mVolumeGroupList.startAnimationAll();
            MediaRouteControllerDialog.this.mVolumeGroupList.postDelayed(MediaRouteControllerDialog.this.mGroupListFadeInAnimation, (long) MediaRouteControllerDialog.this.mGroupListAnimationDurationMs);
        }
    }

    private final class ClickListener implements OnClickListener {
        ClickListener() {
        }

        public void onClick(View view) {
            view = view.getId();
            int i = 1;
            if (view != 16908313) {
                if (view != MediaRouteControllerDialog.BUTTON_DISCONNECT_RES_ID) {
                    if (view == C0303R.id.mr_control_playback_ctrl) {
                        if (MediaRouteControllerDialog.this.mMediaController != null && MediaRouteControllerDialog.this.mState != null) {
                            int i2 = 0;
                            if (MediaRouteControllerDialog.this.mState.getState() != 3) {
                                i = 0;
                            }
                            if (i != 0 && MediaRouteControllerDialog.this.isPauseActionSupported() != null) {
                                MediaRouteControllerDialog.this.mMediaController.getTransportControls().pause();
                                i2 = C0303R.string.mr_controller_pause;
                            } else if (i != 0 && MediaRouteControllerDialog.this.isStopActionSupported() != null) {
                                MediaRouteControllerDialog.this.mMediaController.getTransportControls().stop();
                                i2 = C0303R.string.mr_controller_stop;
                            } else if (i == 0 && MediaRouteControllerDialog.this.isPlayActionSupported() != null) {
                                MediaRouteControllerDialog.this.mMediaController.getTransportControls().play();
                                i2 = C0303R.string.mr_controller_play;
                            }
                            if (MediaRouteControllerDialog.this.mAccessibilityManager != null && MediaRouteControllerDialog.this.mAccessibilityManager.isEnabled() != null && i2 != 0) {
                                view = AccessibilityEvent.obtain(16384);
                                view.setPackageName(MediaRouteControllerDialog.this.mContext.getPackageName());
                                view.setClassName(getClass().getName());
                                view.getText().add(MediaRouteControllerDialog.this.mContext.getString(i2));
                                MediaRouteControllerDialog.this.mAccessibilityManager.sendAccessibilityEvent(view);
                                return;
                            }
                            return;
                        }
                        return;
                    } else if (view == C0303R.id.mr_close) {
                        MediaRouteControllerDialog.this.dismiss();
                        return;
                    } else {
                        return;
                    }
                }
            }
            if (MediaRouteControllerDialog.this.mRoute.isSelected()) {
                MediaRouter mediaRouter = MediaRouteControllerDialog.this.mRouter;
                if (view == 16908313) {
                    i = 2;
                }
                mediaRouter.unselect(i);
            }
            MediaRouteControllerDialog.this.dismiss();
        }
    }

    private class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private static final long SHOW_ANIM_TIME_THRESHOLD_MILLIS = 120;
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;
        private long mStartTimeMillis;

        FetchArtTask() {
            Uri uri = null;
            Bitmap iconBitmap = MediaRouteControllerDialog.this.mDescription == null ? null : MediaRouteControllerDialog.this.mDescription.getIconBitmap();
            if (MediaRouteControllerDialog.isBitmapRecycled(iconBitmap)) {
                Log.w(MediaRouteControllerDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
                iconBitmap = null;
            }
            this.mIconBitmap = iconBitmap;
            if (MediaRouteControllerDialog.this.mDescription != null) {
                uri = MediaRouteControllerDialog.this.mDescription.getIconUri();
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
            this.mStartTimeMillis = SystemClock.uptimeMillis();
            MediaRouteControllerDialog.this.clearLoadedBitmap();
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
            r3 = "MediaRouteCtrlDialog";	 Catch:{ IOException -> 0x00a7 }
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
            r3 = "MediaRouteCtrlDialog";	 Catch:{ IOException -> 0x00a7 }
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
            r4 = android.support.v7.app.MediaRouteControllerDialog.this;	 Catch:{ IOException -> 0x00a7 }
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
            r4 = "MediaRouteCtrlDialog";	 Catch:{ all -> 0x00cc }
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
            r3 = android.support.v7.app.MediaRouteControllerDialog.isBitmapRecycled(r8);
            if (r3 == 0) goto L_0x00f1;
        L_0x00da:
            r0 = "MediaRouteCtrlDialog";
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
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.MediaRouteControllerDialog.FetchArtTask.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
        }

        protected void onPostExecute(Bitmap bitmap) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.mFetchArtTask = null;
            if (!ObjectsCompat.equals(mediaRouteControllerDialog.mArtIconBitmap, this.mIconBitmap) || !ObjectsCompat.equals(MediaRouteControllerDialog.this.mArtIconUri, this.mIconUri)) {
                mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                mediaRouteControllerDialog.mArtIconBitmap = this.mIconBitmap;
                mediaRouteControllerDialog.mArtIconLoadedBitmap = bitmap;
                mediaRouteControllerDialog.mArtIconUri = this.mIconUri;
                mediaRouteControllerDialog.mArtIconBackgroundColor = this.mBackgroundColor;
                bitmap = true;
                mediaRouteControllerDialog.mArtIconIsLoaded = true;
                long uptimeMillis = SystemClock.uptimeMillis() - this.mStartTimeMillis;
                MediaRouteControllerDialog mediaRouteControllerDialog2 = MediaRouteControllerDialog.this;
                if (uptimeMillis <= SHOW_ANIM_TIME_THRESHOLD_MILLIS) {
                    bitmap = null;
                }
                mediaRouteControllerDialog2.update(bitmap);
            }
        }

        private InputStream openInputStreamByScheme(Uri uri) throws IOException {
            String toLowerCase = uri.getScheme().toLowerCase();
            if (!("android.resource".equals(toLowerCase) || "content".equals(toLowerCase))) {
                if (!"file".equals(toLowerCase)) {
                    uri = new URL(uri.toString()).openConnection();
                    uri.setConnectTimeout(MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS);
                    uri.setReadTimeout(MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS);
                    uri = uri.getInputStream();
                    if (uri != null) {
                        return null;
                    }
                    return new BufferedInputStream(uri);
                }
            }
            uri = MediaRouteControllerDialog.this.mContext.getContentResolver().openInputStream(uri);
            if (uri != null) {
                return new BufferedInputStream(uri);
            }
            return null;
        }
    }

    private class VolumeChangeListener implements OnSeekBarChangeListener {
        private final Runnable mStopTrackingTouch = new C02851();

        /* renamed from: android.support.v7.app.MediaRouteControllerDialog$VolumeChangeListener$1 */
        class C02851 implements Runnable {
            C02851() {
            }

            public void run() {
                if (MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != null) {
                    MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched = null;
                    if (MediaRouteControllerDialog.this.mHasPendingUpdate) {
                        MediaRouteControllerDialog.this.update(MediaRouteControllerDialog.this.mPendingUpdateAnimationNeeded);
                    }
                }
            }
        }

        VolumeChangeListener() {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            if (MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != null) {
                MediaRouteControllerDialog.this.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch);
            }
            MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched = (RouteInfo) seekBar.getTag();
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            MediaRouteControllerDialog.this.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 500);
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                RouteInfo routeInfo = (RouteInfo) seekBar.getTag();
                if (MediaRouteControllerDialog.DEBUG) {
                    z = MediaRouteControllerDialog.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(");
                    stringBuilder.append(i);
                    stringBuilder.append(")");
                    Log.d(z, stringBuilder.toString());
                }
                routeInfo.requestSetVolume(i);
            }
        }
    }

    private class VolumeGroupAdapter extends ArrayAdapter<RouteInfo> {
        final float mDisabledAlpha;

        public boolean isEnabled(int i) {
            return false;
        }

        public VolumeGroupAdapter(Context context, List<RouteInfo> list) {
            super(context, null, list);
            this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 0;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(C0303R.layout.mr_controller_volume_item, viewGroup, false);
            } else {
                MediaRouteControllerDialog.this.updateVolumeGroupItemHeight(view);
            }
            RouteInfo routeInfo = (RouteInfo) getItem(i);
            if (routeInfo != null) {
                int i3;
                boolean isEnabled = routeInfo.isEnabled();
                TextView textView = (TextView) view.findViewById(C0303R.id.mr_name);
                textView.setEnabled(isEnabled);
                textView.setText(routeInfo.getName());
                MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(C0303R.id.mr_volume_slider);
                MediaRouterThemeHelper.setVolumeSliderColor(viewGroup.getContext(), mediaRouteVolumeSlider, MediaRouteControllerDialog.this.mVolumeGroupList);
                mediaRouteVolumeSlider.setTag(routeInfo);
                MediaRouteControllerDialog.this.mVolumeSliderMap.put(routeInfo, mediaRouteVolumeSlider);
                mediaRouteVolumeSlider.setHideThumb(isEnabled ^ 1);
                mediaRouteVolumeSlider.setEnabled(isEnabled);
                if (isEnabled) {
                    if (MediaRouteControllerDialog.this.isVolumeControlAvailable(routeInfo) != null) {
                        mediaRouteVolumeSlider.setMax(routeInfo.getVolumeMax());
                        mediaRouteVolumeSlider.setProgress(routeInfo.getVolume());
                        mediaRouteVolumeSlider.setOnSeekBarChangeListener(MediaRouteControllerDialog.this.mVolumeChangeListener);
                    } else {
                        mediaRouteVolumeSlider.setMax(100);
                        mediaRouteVolumeSlider.setProgress(100);
                        mediaRouteVolumeSlider.setEnabled(false);
                    }
                }
                ImageView imageView = (ImageView) view.findViewById(C0303R.id.mr_volume_item_icon);
                if (isEnabled) {
                    i3 = 255;
                } else {
                    i3 = (int) (this.mDisabledAlpha * 255.0f);
                }
                imageView.setAlpha(i3);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C0303R.id.volume_item_container);
                if (MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.contains(routeInfo)) {
                    i2 = 4;
                }
                linearLayout.setVisibility(i2);
                if (!(MediaRouteControllerDialog.this.mGroupMemberRoutesAdded == null || MediaRouteControllerDialog.this.mGroupMemberRoutesAdded.contains(routeInfo) == 0)) {
                    i = new AlphaAnimation(0.0f, 0.0f);
                    i.setDuration(0);
                    i.setFillEnabled(true);
                    i.setFillAfter(true);
                    view.clearAnimation();
                    view.startAnimation(i);
                }
            }
            return view;
        }
    }

    private final class MediaControllerCallback extends Callback {
        MediaControllerCallback() {
        }

        public void onSessionDestroyed() {
            if (MediaRouteControllerDialog.this.mMediaController != null) {
                MediaRouteControllerDialog.this.mMediaController.unregisterCallback(MediaRouteControllerDialog.this.mControllerCallback);
                MediaRouteControllerDialog.this.mMediaController = null;
            }
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.mState = playbackStateCompat;
            mediaRouteControllerDialog.update(null);
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaRouteControllerDialog.this.mDescription = mediaMetadataCompat == null ? null : mediaMetadataCompat.getDescription();
            MediaRouteControllerDialog.this.updateArtIconIfNeeded();
            MediaRouteControllerDialog.this.update(false);
        }
    }

    private final class MediaRouterCallback extends MediaRouter.Callback {
        MediaRouterCallback() {
        }

        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteControllerDialog.this.update(null);
        }

        public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteControllerDialog.this.update(true);
        }

        public void onRouteVolumeChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
            SeekBar seekBar = (SeekBar) MediaRouteControllerDialog.this.mVolumeSliderMap.get(routeInfo);
            int volume = routeInfo.getVolume();
            if (MediaRouteControllerDialog.DEBUG) {
                String str = MediaRouteControllerDialog.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onRouteVolumeChanged(), route.getVolume:");
                stringBuilder.append(volume);
                Log.d(str, stringBuilder.toString());
            }
            if (seekBar != null && MediaRouteControllerDialog.this.mRouteInVolumeSliderTouched != routeInfo) {
                seekBar.setProgress(volume);
            }
        }
    }

    public View onCreateMediaControlView(Bundle bundle) {
        return null;
    }

    public MediaRouteControllerDialog(Context context) {
        this(context, 0);
    }

    public MediaRouteControllerDialog(Context context, int i) {
        context = MediaRouterThemeHelper.createThemedDialogContext(context, i, true);
        super(context, MediaRouterThemeHelper.createThemedDialogStyle(context));
        this.mVolumeControlEnabled = true;
        this.mGroupListFadeInAnimation = new C02761();
        this.mContext = getContext();
        this.mControllerCallback = new MediaControllerCallback();
        this.mRouter = MediaRouter.getInstance(this.mContext);
        this.mCallback = new MediaRouterCallback();
        this.mRoute = this.mRouter.getSelectedRoute();
        setMediaSession(this.mRouter.getMediaSessionToken());
        this.mVolumeGroupListPaddingTop = this.mContext.getResources().getDimensionPixelSize(C0303R.dimen.mr_controller_volume_group_list_padding_top);
        this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        if (VERSION.SDK_INT >= 21) {
            this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, C0303R.interpolator.mr_linear_out_slow_in);
            this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(context, C0303R.interpolator.mr_fast_out_slow_in);
        }
        this.mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    }

    public RouteInfo getRoute() {
        return this.mRoute;
    }

    private RouteGroup getGroup() {
        RouteInfo routeInfo = this.mRoute;
        return routeInfo instanceof RouteGroup ? (RouteGroup) routeInfo : null;
    }

    public View getMediaControlView() {
        return this.mCustomControlView;
    }

    public void setVolumeControlEnabled(boolean z) {
        if (this.mVolumeControlEnabled != z) {
            this.mVolumeControlEnabled = z;
            if (this.mCreated) {
                update(false);
            }
        }
    }

    public boolean isVolumeControlEnabled() {
        return this.mVolumeControlEnabled;
    }

    private void setMediaSession(Token token) {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        PlaybackStateCompat playbackStateCompat = null;
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
            if (token2 == null) {
                token2 = null;
            } else {
                token2 = token2.getDescription();
            }
            this.mDescription = token2;
            token2 = this.mMediaController;
            if (token2 != null) {
                playbackStateCompat = token2.getPlaybackState();
            }
            this.mState = playbackStateCompat;
            updateArtIconIfNeeded();
            update(null);
        }
    }

    public Token getMediaSession() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        return mediaControllerCompat == null ? null : mediaControllerCompat.getSessionToken();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
        setContentView(C0303R.layout.mr_controller_material_dialog_b);
        findViewById(BUTTON_NEUTRAL_RES_ID).setVisibility(8);
        OnClickListener clickListener = new ClickListener();
        this.mExpandableAreaLayout = (FrameLayout) findViewById(C0303R.id.mr_expandable_area);
        this.mExpandableAreaLayout.setOnClickListener(new C02772());
        this.mDialogAreaLayout = (LinearLayout) findViewById(C0303R.id.mr_dialog_area);
        this.mDialogAreaLayout.setOnClickListener(new C02783());
        int buttonTextColor = MediaRouterThemeHelper.getButtonTextColor(this.mContext);
        this.mDisconnectButton = (Button) findViewById(BUTTON_DISCONNECT_RES_ID);
        this.mDisconnectButton.setText(C0303R.string.mr_controller_disconnect);
        this.mDisconnectButton.setTextColor(buttonTextColor);
        this.mDisconnectButton.setOnClickListener(clickListener);
        this.mStopCastingButton = (Button) findViewById(BUTTON_STOP_RES_ID);
        this.mStopCastingButton.setText(C0303R.string.mr_controller_stop_casting);
        this.mStopCastingButton.setTextColor(buttonTextColor);
        this.mStopCastingButton.setOnClickListener(clickListener);
        this.mRouteNameTextView = (TextView) findViewById(C0303R.id.mr_name);
        this.mCloseButton = (ImageButton) findViewById(C0303R.id.mr_close);
        this.mCloseButton.setOnClickListener(clickListener);
        this.mCustomControlLayout = (FrameLayout) findViewById(C0303R.id.mr_custom_control);
        this.mDefaultControlLayout = (FrameLayout) findViewById(C0303R.id.mr_default_control);
        OnClickListener c02794 = new C02794();
        this.mArtView = (ImageView) findViewById(C0303R.id.mr_art);
        this.mArtView.setOnClickListener(c02794);
        findViewById(C0303R.id.mr_control_title_container).setOnClickListener(c02794);
        this.mMediaMainControlLayout = (LinearLayout) findViewById(C0303R.id.mr_media_main_control);
        this.mDividerView = findViewById(C0303R.id.mr_control_divider);
        this.mPlaybackControlLayout = (RelativeLayout) findViewById(C0303R.id.mr_playback_control);
        this.mTitleView = (TextView) findViewById(C0303R.id.mr_control_title);
        this.mSubtitleView = (TextView) findViewById(C0303R.id.mr_control_subtitle);
        this.mPlaybackControlButton = (ImageButton) findViewById(C0303R.id.mr_control_playback_ctrl);
        this.mPlaybackControlButton.setOnClickListener(clickListener);
        this.mVolumeControlLayout = (LinearLayout) findViewById(C0303R.id.mr_volume_control);
        this.mVolumeControlLayout.setVisibility(8);
        this.mVolumeSlider = (SeekBar) findViewById(C0303R.id.mr_volume_slider);
        this.mVolumeSlider.setTag(this.mRoute);
        this.mVolumeChangeListener = new VolumeChangeListener();
        this.mVolumeSlider.setOnSeekBarChangeListener(this.mVolumeChangeListener);
        this.mVolumeGroupList = (OverlayListView) findViewById(C0303R.id.mr_volume_group_list);
        this.mGroupMemberRoutes = new ArrayList();
        this.mVolumeGroupAdapter = new VolumeGroupAdapter(this.mVolumeGroupList.getContext(), this.mGroupMemberRoutes);
        this.mVolumeGroupList.setAdapter(this.mVolumeGroupAdapter);
        this.mGroupMemberRoutesAnimatingWithBitmap = new HashSet();
        MediaRouterThemeHelper.setMediaControlsBackgroundColor(this.mContext, this.mMediaMainControlLayout, this.mVolumeGroupList, getGroup() != null);
        MediaRouterThemeHelper.setVolumeSliderColor(this.mContext, (MediaRouteVolumeSlider) this.mVolumeSlider, this.mMediaMainControlLayout);
        this.mVolumeSliderMap = new HashMap();
        this.mVolumeSliderMap.put(this.mRoute, this.mVolumeSlider);
        this.mGroupExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(C0303R.id.mr_group_expand_collapse);
        this.mGroupExpandCollapseButton.setOnClickListener(new C02805());
        loadInterpolator();
        this.mGroupListAnimationDurationMs = this.mContext.getResources().getInteger(C0303R.integer.mr_controller_volume_group_list_animation_duration_ms);
        this.mGroupListFadeInDurationMs = this.mContext.getResources().getInteger(C0303R.integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.mGroupListFadeOutDurationMs = this.mContext.getResources().getInteger(C0303R.integer.mr_controller_volume_group_list_fade_out_duration_ms);
        this.mCustomControlView = onCreateMediaControlView(bundle);
        bundle = this.mCustomControlView;
        if (bundle != null) {
            this.mCustomControlLayout.addView(bundle);
            this.mCustomControlLayout.setVisibility(0);
        }
        this.mCreated = true;
        updateLayout();
    }

    void updateLayout() {
        int dialogWidth = MediaRouteDialogHelper.getDialogWidth(this.mContext);
        getWindow().setLayout(dialogWidth, -2);
        View decorView = getWindow().getDecorView();
        this.mDialogContentWidth = (dialogWidth - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = this.mContext.getResources();
        this.mVolumeGroupListItemIconSize = resources.getDimensionPixelSize(C0303R.dimen.mr_controller_volume_group_list_item_icon_size);
        this.mVolumeGroupListItemHeight = resources.getDimensionPixelSize(C0303R.dimen.mr_controller_volume_group_list_item_height);
        this.mVolumeGroupListMaxHeight = resources.getDimensionPixelSize(C0303R.dimen.mr_controller_volume_group_list_max_height);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        updateArtIconIfNeeded();
        update(false);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(MediaRouteSelector.EMPTY, this.mCallback, 2);
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    public void onDetachedFromWindow() {
        this.mRouter.removeCallback(this.mCallback);
        setMediaSession(null);
        this.mAttachedToWindow = false;
        super.onDetachedFromWindow();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 25) {
            if (i != 24) {
                return super.onKeyDown(i, keyEvent);
            }
        }
        this.mRoute.requestUpdateVolume(i == 25 ? -1 : 1);
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 25) {
            if (i != 24) {
                return super.onKeyUp(i, keyEvent);
            }
        }
        return true;
    }

    void update(boolean z) {
        if (this.mRouteInVolumeSliderTouched != null) {
            this.mHasPendingUpdate = true;
            this.mPendingUpdateAnimationNeeded = z | this.mPendingUpdateAnimationNeeded;
            return;
        }
        int i = 0;
        this.mHasPendingUpdate = false;
        this.mPendingUpdateAnimationNeeded = false;
        if (this.mRoute.isSelected()) {
            if (!this.mRoute.isDefaultOrBluetooth()) {
                if (this.mCreated) {
                    this.mRouteNameTextView.setText(this.mRoute.getName());
                    Button button = this.mDisconnectButton;
                    if (!this.mRoute.canDisconnect()) {
                        i = 8;
                    }
                    button.setVisibility(i);
                    if (this.mCustomControlView == null && this.mArtIconIsLoaded) {
                        if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                            String str = TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Can't set artwork image with recycled bitmap: ");
                            stringBuilder.append(this.mArtIconLoadedBitmap);
                            Log.w(str, stringBuilder.toString());
                        } else {
                            this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
                            this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
                        }
                        clearLoadedBitmap();
                    }
                    updateVolumeControlLayout();
                    updatePlaybackControlLayout();
                    updateLayoutHeight(z);
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

    private boolean canShowPlaybackControlLayout() {
        return this.mCustomControlView == null && !(this.mDescription == null && this.mState == null);
    }

    private int getMainControllerHeight(boolean z) {
        if (!z && this.mVolumeControlLayout.getVisibility() != 0) {
            return 0;
        }
        int paddingTop = 0 + (this.mMediaMainControlLayout.getPaddingTop() + this.mMediaMainControlLayout.getPaddingBottom());
        if (z) {
            paddingTop += this.mPlaybackControlLayout.getMeasuredHeight();
        }
        if (this.mVolumeControlLayout.getVisibility() == 0) {
            paddingTop += this.mVolumeControlLayout.getMeasuredHeight();
        }
        return (!z || this.mVolumeControlLayout.getVisibility()) ? paddingTop : paddingTop + this.mDividerView.getMeasuredHeight();
    }

    private void updateMediaControlVisibility(boolean z) {
        View view = this.mDividerView;
        int i = 0;
        int i2 = (this.mVolumeControlLayout.getVisibility() == 0 && z) ? 0 : 8;
        view.setVisibility(i2);
        LinearLayout linearLayout = this.mMediaMainControlLayout;
        if (this.mVolumeControlLayout.getVisibility() == 8 && !z) {
            i = 8;
        }
        linearLayout.setVisibility(i);
    }

    void updateLayoutHeight(final boolean z) {
        this.mDefaultControlLayout.requestLayout();
        this.mDefaultControlLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                MediaRouteControllerDialog.this.mDefaultControlLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (MediaRouteControllerDialog.this.mIsGroupListAnimating) {
                    MediaRouteControllerDialog.this.mIsGroupListAnimationPending = true;
                } else {
                    MediaRouteControllerDialog.this.updateLayoutHeightInternal(z);
                }
            }
        });
    }

    void updateLayoutHeightInternal(boolean z) {
        int desiredArtHeight;
        int size;
        int i;
        Rect rect;
        int height;
        boolean z2;
        int max;
        int layoutHeight = getLayoutHeight(this.mMediaMainControlLayout);
        setLayoutHeight(this.mMediaMainControlLayout, -1);
        updateMediaControlVisibility(canShowPlaybackControlLayout());
        View decorView = getWindow().getDecorView();
        decorView.measure(MeasureSpec.makeMeasureSpec(getWindow().getAttributes().width, 1073741824), 0);
        setLayoutHeight(this.mMediaMainControlLayout, layoutHeight);
        if (this.mCustomControlView == null && (this.mArtView.getDrawable() instanceof BitmapDrawable)) {
            Bitmap bitmap = ((BitmapDrawable) this.mArtView.getDrawable()).getBitmap();
            if (bitmap != null) {
                desiredArtHeight = getDesiredArtHeight(bitmap.getWidth(), bitmap.getHeight());
                this.mArtView.setScaleType(bitmap.getWidth() >= bitmap.getHeight() ? ScaleType.FIT_XY : ScaleType.FIT_CENTER);
                layoutHeight = getMainControllerHeight(canShowPlaybackControlLayout());
                size = this.mGroupMemberRoutes.size();
                if (getGroup() != null) {
                    i = 0;
                } else {
                    i = this.mVolumeGroupListItemHeight * getGroup().getRoutes().size();
                }
                if (size > 0) {
                    i += this.mVolumeGroupListPaddingTop;
                }
                size = Math.min(i, this.mVolumeGroupListMaxHeight);
                if (this.mIsGroupExpanded) {
                    size = 0;
                }
                i = Math.max(desiredArtHeight, size) + layoutHeight;
                rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                height = rect.height() - (this.mDialogAreaLayout.getMeasuredHeight() - this.mDefaultControlLayout.getMeasuredHeight());
                if (this.mCustomControlView == null || desiredArtHeight <= 0 || i > height) {
                    if (getLayoutHeight(this.mVolumeGroupList) + this.mMediaMainControlLayout.getMeasuredHeight() >= this.mDefaultControlLayout.getMeasuredHeight()) {
                        this.mArtView.setVisibility(8);
                    }
                    i = size + layoutHeight;
                    desiredArtHeight = 0;
                } else {
                    this.mArtView.setVisibility(0);
                    setLayoutHeight(this.mArtView, desiredArtHeight);
                }
                if (canShowPlaybackControlLayout() || r5 > height) {
                    this.mPlaybackControlLayout.setVisibility(8);
                } else {
                    this.mPlaybackControlLayout.setVisibility(0);
                }
                z2 = true;
                updateMediaControlVisibility(this.mPlaybackControlLayout.getVisibility() != 0);
                if (this.mPlaybackControlLayout.getVisibility() == 0) {
                    z2 = false;
                }
                layoutHeight = getMainControllerHeight(z2);
                max = Math.max(desiredArtHeight, size) + layoutHeight;
                if (max > height) {
                    size -= max - height;
                    max = height;
                }
                this.mMediaMainControlLayout.clearAnimation();
                this.mVolumeGroupList.clearAnimation();
                this.mDefaultControlLayout.clearAnimation();
                if (z) {
                    setLayoutHeight(this.mMediaMainControlLayout, layoutHeight);
                    setLayoutHeight(this.mVolumeGroupList, size);
                    setLayoutHeight(this.mDefaultControlLayout, max);
                } else {
                    animateLayoutHeight(this.mMediaMainControlLayout, layoutHeight);
                    animateLayoutHeight(this.mVolumeGroupList, size);
                    animateLayoutHeight(this.mDefaultControlLayout, max);
                }
                setLayoutHeight(this.mExpandableAreaLayout, rect.height());
                rebuildVolumeGroupList(z);
            }
        }
        desiredArtHeight = 0;
        layoutHeight = getMainControllerHeight(canShowPlaybackControlLayout());
        size = this.mGroupMemberRoutes.size();
        if (getGroup() != null) {
            i = this.mVolumeGroupListItemHeight * getGroup().getRoutes().size();
        } else {
            i = 0;
        }
        if (size > 0) {
            i += this.mVolumeGroupListPaddingTop;
        }
        size = Math.min(i, this.mVolumeGroupListMaxHeight);
        if (this.mIsGroupExpanded) {
            size = 0;
        }
        i = Math.max(desiredArtHeight, size) + layoutHeight;
        rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        height = rect.height() - (this.mDialogAreaLayout.getMeasuredHeight() - this.mDefaultControlLayout.getMeasuredHeight());
        if (this.mCustomControlView == null) {
        }
        if (getLayoutHeight(this.mVolumeGroupList) + this.mMediaMainControlLayout.getMeasuredHeight() >= this.mDefaultControlLayout.getMeasuredHeight()) {
            this.mArtView.setVisibility(8);
        }
        i = size + layoutHeight;
        desiredArtHeight = 0;
        if (canShowPlaybackControlLayout()) {
        }
        this.mPlaybackControlLayout.setVisibility(8);
        z2 = true;
        if (this.mPlaybackControlLayout.getVisibility() != 0) {
        }
        updateMediaControlVisibility(this.mPlaybackControlLayout.getVisibility() != 0);
        if (this.mPlaybackControlLayout.getVisibility() == 0) {
            z2 = false;
        }
        layoutHeight = getMainControllerHeight(z2);
        max = Math.max(desiredArtHeight, size) + layoutHeight;
        if (max > height) {
            size -= max - height;
            max = height;
        }
        this.mMediaMainControlLayout.clearAnimation();
        this.mVolumeGroupList.clearAnimation();
        this.mDefaultControlLayout.clearAnimation();
        if (z) {
            setLayoutHeight(this.mMediaMainControlLayout, layoutHeight);
            setLayoutHeight(this.mVolumeGroupList, size);
            setLayoutHeight(this.mDefaultControlLayout, max);
        } else {
            animateLayoutHeight(this.mMediaMainControlLayout, layoutHeight);
            animateLayoutHeight(this.mVolumeGroupList, size);
            animateLayoutHeight(this.mDefaultControlLayout, max);
        }
        setLayoutHeight(this.mExpandableAreaLayout, rect.height());
        rebuildVolumeGroupList(z);
    }

    void updateVolumeGroupItemHeight(View view) {
        setLayoutHeight((LinearLayout) view.findViewById(C0303R.id.volume_item_container), this.mVolumeGroupListItemHeight);
        view = view.findViewById(C0303R.id.mr_volume_item_icon);
        LayoutParams layoutParams = view.getLayoutParams();
        int i = this.mVolumeGroupListItemIconSize;
        layoutParams.width = i;
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    private void animateLayoutHeight(final View view, final int i) {
        final int layoutHeight = getLayoutHeight(view);
        Animation c02827 = new Animation() {
            protected void applyTransformation(float f, Transformation transformation) {
                transformation = layoutHeight;
                MediaRouteControllerDialog.setLayoutHeight(view, transformation - ((int) (((float) (transformation - i)) * f)));
            }
        };
        c02827.setDuration((long) this.mGroupListAnimationDurationMs);
        if (VERSION.SDK_INT >= 21) {
            c02827.setInterpolator(this.mInterpolator);
        }
        view.startAnimation(c02827);
    }

    void loadInterpolator() {
        if (VERSION.SDK_INT >= 21) {
            this.mInterpolator = this.mIsGroupExpanded ? this.mLinearOutSlowInInterpolator : this.mFastOutSlowInInterpolator;
        } else {
            this.mInterpolator = this.mAccelerateDecelerateInterpolator;
        }
    }

    private void updateVolumeControlLayout() {
        int i = 8;
        if (!isVolumeControlAvailable(this.mRoute)) {
            this.mVolumeControlLayout.setVisibility(8);
        } else if (this.mVolumeControlLayout.getVisibility() == 8) {
            this.mVolumeControlLayout.setVisibility(0);
            this.mVolumeSlider.setMax(this.mRoute.getVolumeMax());
            this.mVolumeSlider.setProgress(this.mRoute.getVolume());
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = this.mGroupExpandCollapseButton;
            if (getGroup() != null) {
                i = 0;
            }
            mediaRouteExpandCollapseButton.setVisibility(i);
        }
    }

    private void rebuildVolumeGroupList(boolean z) {
        List routes = getGroup() == null ? null : getGroup().getRoutes();
        if (routes == null) {
            this.mGroupMemberRoutes.clear();
            this.mVolumeGroupAdapter.notifyDataSetChanged();
        } else if (MediaRouteDialogHelper.listUnorderedEquals(this.mGroupMemberRoutes, routes)) {
            this.mVolumeGroupAdapter.notifyDataSetChanged();
        } else {
            Map itemBoundMap = z ? MediaRouteDialogHelper.getItemBoundMap(this.mVolumeGroupList, this.mVolumeGroupAdapter) : null;
            Map itemBitmapMap = z ? MediaRouteDialogHelper.getItemBitmapMap(this.mContext, this.mVolumeGroupList, this.mVolumeGroupAdapter) : null;
            this.mGroupMemberRoutesAdded = MediaRouteDialogHelper.getItemsAdded(this.mGroupMemberRoutes, routes);
            this.mGroupMemberRoutesRemoved = MediaRouteDialogHelper.getItemsRemoved(this.mGroupMemberRoutes, routes);
            this.mGroupMemberRoutes.addAll(0, this.mGroupMemberRoutesAdded);
            this.mGroupMemberRoutes.removeAll(this.mGroupMemberRoutesRemoved);
            this.mVolumeGroupAdapter.notifyDataSetChanged();
            if (z && this.mIsGroupExpanded && this.mGroupMemberRoutesAdded.size() + this.mGroupMemberRoutesRemoved.size() <= false) {
                animateGroupListItems(itemBoundMap, itemBitmapMap);
                return;
            }
            this.mGroupMemberRoutesAdded = null;
            this.mGroupMemberRoutesRemoved = null;
        }
    }

    private void animateGroupListItems(final Map<RouteInfo, Rect> map, final Map<RouteInfo, BitmapDrawable> map2) {
        this.mVolumeGroupList.setEnabled(false);
        this.mVolumeGroupList.requestLayout();
        this.mIsGroupListAnimating = true;
        this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                MediaRouteControllerDialog.this.animateGroupListItemsInternal(map, map2);
            }
        });
    }

    void animateGroupListItemsInternal(Map<RouteInfo, Rect> map, Map<RouteInfo, BitmapDrawable> map2) {
        Map<RouteInfo, Rect> map3 = map;
        Set set = this.mGroupMemberRoutesAdded;
        if (set != null) {
            if (r0.mGroupMemberRoutesRemoved != null) {
                int size = set.size() - r0.mGroupMemberRoutesRemoved.size();
                AnimationListener c02849 = new C02849();
                int firstVisiblePosition = r0.mVolumeGroupList.getFirstVisiblePosition();
                Object obj = null;
                for (int i = 0; i < r0.mVolumeGroupList.getChildCount(); i++) {
                    View childAt = r0.mVolumeGroupList.getChildAt(i);
                    RouteInfo routeInfo = (RouteInfo) r0.mVolumeGroupAdapter.getItem(firstVisiblePosition + i);
                    Rect rect = (Rect) map3.get(routeInfo);
                    int top = childAt.getTop();
                    int i2 = rect != null ? rect.top : (r0.mVolumeGroupListItemHeight * size) + top;
                    Animation animationSet = new AnimationSet(true);
                    Set set2 = r0.mGroupMemberRoutesAdded;
                    if (set2 != null && set2.contains(routeInfo)) {
                        Animation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                        alphaAnimation.setDuration((long) r0.mGroupListFadeInDurationMs);
                        animationSet.addAnimation(alphaAnimation);
                        i2 = top;
                    }
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) (i2 - top), 0.0f);
                    translateAnimation.setDuration((long) r0.mGroupListAnimationDurationMs);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.setFillAfter(true);
                    animationSet.setFillEnabled(true);
                    animationSet.setInterpolator(r0.mInterpolator);
                    if (obj == null) {
                        animationSet.setAnimationListener(c02849);
                        obj = 1;
                    }
                    childAt.clearAnimation();
                    childAt.startAnimation(animationSet);
                    map3.remove(routeInfo);
                    map2.remove(routeInfo);
                }
                Map<RouteInfo, BitmapDrawable> map4 = map2;
                for (Entry entry : map2.entrySet()) {
                    OverlayObject interpolator;
                    final RouteInfo routeInfo2 = (RouteInfo) entry.getKey();
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) entry.getValue();
                    Rect rect2 = (Rect) map3.get(routeInfo2);
                    if (r0.mGroupMemberRoutesRemoved.contains(routeInfo2)) {
                        interpolator = new OverlayObject(bitmapDrawable, rect2).setAlphaAnimation(1.0f, 0.0f).setDuration((long) r0.mGroupListFadeOutDurationMs).setInterpolator(r0.mInterpolator);
                    } else {
                        interpolator = new OverlayObject(bitmapDrawable, rect2).setTranslateYAnimation(r0.mVolumeGroupListItemHeight * size).setDuration((long) r0.mGroupListAnimationDurationMs).setInterpolator(r0.mInterpolator).setAnimationEndListener(new OnAnimationEndListener() {
                            public void onAnimationEnd() {
                                MediaRouteControllerDialog.this.mGroupMemberRoutesAnimatingWithBitmap.remove(routeInfo2);
                                MediaRouteControllerDialog.this.mVolumeGroupAdapter.notifyDataSetChanged();
                            }
                        });
                        r0.mGroupMemberRoutesAnimatingWithBitmap.add(routeInfo2);
                    }
                    r0.mVolumeGroupList.addOverlayObject(interpolator);
                }
            }
        }
    }

    void startGroupListFadeInAnimation() {
        clearGroupListAnimation(true);
        this.mVolumeGroupList.requestLayout();
        this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                MediaRouteControllerDialog.this.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                MediaRouteControllerDialog.this.startGroupListFadeInAnimationInternal();
            }
        });
    }

    void startGroupListFadeInAnimationInternal() {
        Set set = this.mGroupMemberRoutesAdded;
        if (set == null || set.size() == 0) {
            finishAnimation(true);
        } else {
            fadeInAddedRoutes();
        }
    }

    void finishAnimation(boolean z) {
        this.mGroupMemberRoutesAdded = null;
        this.mGroupMemberRoutesRemoved = null;
        this.mIsGroupListAnimating = false;
        if (this.mIsGroupListAnimationPending) {
            this.mIsGroupListAnimationPending = false;
            updateLayoutHeight(z);
        }
        this.mVolumeGroupList.setEnabled(true);
    }

    private void fadeInAddedRoutes() {
        AnimationListener anonymousClass12 = new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                MediaRouteControllerDialog.this.finishAnimation(true);
            }
        };
        int firstVisiblePosition = this.mVolumeGroupList.getFirstVisiblePosition();
        Object obj = null;
        for (int i = 0; i < this.mVolumeGroupList.getChildCount(); i++) {
            View childAt = this.mVolumeGroupList.getChildAt(i);
            if (this.mGroupMemberRoutesAdded.contains((RouteInfo) this.mVolumeGroupAdapter.getItem(firstVisiblePosition + i))) {
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration((long) this.mGroupListFadeInDurationMs);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (obj == null) {
                    alphaAnimation.setAnimationListener(anonymousClass12);
                    obj = 1;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }

    void clearGroupListAnimation(boolean z) {
        int firstVisiblePosition = this.mVolumeGroupList.getFirstVisiblePosition();
        for (int i = 0; i < this.mVolumeGroupList.getChildCount(); i++) {
            View childAt = this.mVolumeGroupList.getChildAt(i);
            RouteInfo routeInfo = (RouteInfo) this.mVolumeGroupAdapter.getItem(firstVisiblePosition + i);
            if (z) {
                Set set = this.mGroupMemberRoutesAdded;
                if (set != null && set.contains(routeInfo)) {
                }
            }
            ((LinearLayout) childAt.findViewById(C0303R.id.volume_item_container)).setVisibility(0);
            Animation animationSet = new AnimationSet(true);
            Animation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
            alphaAnimation.setDuration(0);
            animationSet.addAnimation(alphaAnimation);
            new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            childAt.clearAnimation();
            childAt.startAnimation(animationSet);
        }
        this.mVolumeGroupList.stopAnimationAll();
        if (!z) {
            finishAnimation(false);
        }
    }

    private void updatePlaybackControlLayout() {
        if (canShowPlaybackControlLayout()) {
            Object obj;
            Object obj2;
            MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
            CharSequence charSequence = null;
            CharSequence title = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getTitle();
            int i = 1;
            int isEmpty = TextUtils.isEmpty(title) ^ 1;
            MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
            if (mediaDescriptionCompat2 != null) {
                charSequence = mediaDescriptionCompat2.getSubtitle();
            }
            int isEmpty2 = TextUtils.isEmpty(charSequence) ^ 1;
            if (this.mRoute.getPresentationDisplayId() != -1) {
                this.mTitleView.setText(C0303R.string.mr_controller_casting_screen);
                obj = 1;
                obj2 = null;
            } else {
                PlaybackStateCompat playbackStateCompat = this.mState;
                if (playbackStateCompat != null) {
                    if (playbackStateCompat.getState() != 0) {
                        if (isEmpty == 0 && isEmpty2 == 0) {
                            this.mTitleView.setText(C0303R.string.mr_controller_no_info_available);
                            obj = 1;
                            obj2 = null;
                        } else {
                            if (isEmpty != 0) {
                                this.mTitleView.setText(title);
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            if (isEmpty2 != 0) {
                                this.mSubtitleView.setText(charSequence);
                                obj2 = 1;
                            } else {
                                obj2 = null;
                            }
                        }
                    }
                }
                this.mTitleView.setText(C0303R.string.mr_controller_no_media_selected);
                obj = 1;
                obj2 = null;
            }
            isEmpty2 = 8;
            this.mTitleView.setVisibility(obj != null ? 0 : 8);
            this.mSubtitleView.setVisibility(obj2 != null ? 0 : 8);
            PlaybackStateCompat playbackStateCompat2 = this.mState;
            if (playbackStateCompat2 != null) {
                Context context;
                int i2;
                ImageButton imageButton;
                if (playbackStateCompat2.getState() != 6) {
                    if (this.mState.getState() != 3) {
                        obj = null;
                        context = this.mPlaybackControlButton.getContext();
                        if (obj == null && isPauseActionSupported()) {
                            i2 = C0303R.attr.mediaRoutePauseDrawable;
                            isEmpty = C0303R.string.mr_controller_pause;
                        } else if (obj == null && isStopActionSupported()) {
                            i2 = C0303R.attr.mediaRouteStopDrawable;
                            isEmpty = C0303R.string.mr_controller_stop;
                        } else if (obj == null || !isPlayActionSupported()) {
                            i2 = 0;
                            isEmpty = 0;
                            i = 0;
                        } else {
                            i2 = C0303R.attr.mediaRoutePlayDrawable;
                            isEmpty = C0303R.string.mr_controller_play;
                        }
                        imageButton = this.mPlaybackControlButton;
                        if (i != 0) {
                            isEmpty2 = 0;
                        }
                        imageButton.setVisibility(isEmpty2);
                        if (i != 0) {
                            this.mPlaybackControlButton.setImageResource(MediaRouterThemeHelper.getThemeResource(context, i2));
                            this.mPlaybackControlButton.setContentDescription(context.getResources().getText(isEmpty));
                        }
                    }
                }
                obj = 1;
                context = this.mPlaybackControlButton.getContext();
                if (obj == null) {
                }
                if (obj == null) {
                }
                if (obj == null) {
                }
                i2 = 0;
                isEmpty = 0;
                i = 0;
                imageButton = this.mPlaybackControlButton;
                if (i != 0) {
                    isEmpty2 = 0;
                }
                imageButton.setVisibility(isEmpty2);
                if (i != 0) {
                    this.mPlaybackControlButton.setImageResource(MediaRouterThemeHelper.getThemeResource(context, i2));
                    this.mPlaybackControlButton.setContentDescription(context.getResources().getText(isEmpty));
                }
            }
        }
    }

    boolean isPlayActionSupported() {
        return (this.mState.getActions() & 516) != 0;
    }

    boolean isPauseActionSupported() {
        return (this.mState.getActions() & 514) != 0;
    }

    boolean isStopActionSupported() {
        return (this.mState.getActions() & 1) != 0;
    }

    boolean isVolumeControlAvailable(RouteInfo routeInfo) {
        return this.mVolumeControlEnabled && routeInfo.getVolumeHandling() == 1;
    }

    private static int getLayoutHeight(View view) {
        return view.getLayoutParams().height;
    }

    static void setLayoutHeight(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    private static boolean uriEquals(Uri uri, Uri uri2) {
        if (uri == null || !uri.equals(uri2)) {
            return (uri == null && uri2 == null) ? true : null;
        } else {
            return true;
        }
    }

    int getDesiredArtHeight(int i, int i2) {
        if (i >= i2) {
            return (int) (((((float) this.mDialogContentWidth) * ((float) i2)) / ((float) i)) + 0.5f);
        }
        return (int) (((((float) this.mDialogContentWidth) * 1091567616) / 1098907648) + 1056964608);
    }

    void updateArtIconIfNeeded() {
        if (this.mCustomControlView == null) {
            if (isIconChanged()) {
                FetchArtTask fetchArtTask = this.mFetchArtTask;
                if (fetchArtTask != null) {
                    fetchArtTask.cancel(true);
                }
                this.mFetchArtTask = new FetchArtTask();
                this.mFetchArtTask.execute(new Void[0]);
            }
        }
    }

    void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    private boolean isIconChanged() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        Uri uri = null;
        Bitmap iconBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        if (mediaDescriptionCompat2 != null) {
            uri = mediaDescriptionCompat2.getIconUri();
        }
        FetchArtTask fetchArtTask = this.mFetchArtTask;
        Bitmap iconBitmap2 = fetchArtTask == null ? this.mArtIconBitmap : fetchArtTask.getIconBitmap();
        FetchArtTask fetchArtTask2 = this.mFetchArtTask;
        Uri iconUri = fetchArtTask2 == null ? this.mArtIconUri : fetchArtTask2.getIconUri();
        if (iconBitmap2 != iconBitmap) {
            return true;
        }
        if (iconBitmap2 != null || uriEquals(iconUri, r1)) {
            return false;
        }
        return true;
    }
}
