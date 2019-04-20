package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteGroup;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.support.v7.mediarouter.C0303R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MediaRouteDevicePickerDialog extends AppCompatDialog {
    private static final int ITEM_TYPE_HEADER = 1;
    private static final int ITEM_TYPE_NONE = 0;
    private static final int ITEM_TYPE_ROUTE = 2;
    static final int MSG_UPDATE_ROUTES = 1;
    private static final String TAG = "MediaRouteDevicePickerDialog";
    private RecyclerAdapter mAdapter;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    private final Handler mHandler;
    private long mLastUpdateTime;
    private RecyclerView mRecyclerView;
    private final MediaRouter mRouter;
    List<RouteInfo> mRoutes;
    private MediaRouteSelector mSelector;
    private long mUpdateRoutesDelayMs;

    /* renamed from: android.support.v7.app.MediaRouteDevicePickerDialog$1 */
    class C02861 extends Handler {
        C02861() {
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                MediaRouteDevicePickerDialog.this.updateRoutes((List) message.obj);
            }
        }
    }

    /* renamed from: android.support.v7.app.MediaRouteDevicePickerDialog$2 */
    class C02872 implements OnClickListener {
        C02872() {
        }

        public void onClick(View view) {
            MediaRouteDevicePickerDialog.this.dismiss();
        }
    }

    static final class RouteComparator implements Comparator<RouteInfo> {
        public static final RouteComparator sInstance = new RouteComparator();

        RouteComparator() {
        }

        public int compare(RouteInfo routeInfo, RouteInfo routeInfo2) {
            return routeInfo.getName().compareToIgnoreCase(routeInfo2.getName());
        }
    }

    private final class MediaRouterCallback extends Callback {
        MediaRouterCallback() {
        }

        public void onRouteAdded(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteDevicePickerDialog.this.refreshRoutes();
        }

        public void onRouteRemoved(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteDevicePickerDialog.this.refreshRoutes();
        }

        public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteDevicePickerDialog.this.refreshRoutes();
        }

        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteDevicePickerDialog.this.dismiss();
        }
    }

    private final class RecyclerAdapter extends Adapter<ViewHolder> {
        private static final String TAG = "RecyclerAdapter";
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        ArrayList<Item> mItems;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        private class Item {
            private final Object mData;
            private final int mType;

            Item(Object obj) {
                this.mData = obj;
                if ((obj instanceof String) != null) {
                    this.mType = 1;
                } else if ((obj instanceof RouteInfo) != null) {
                    this.mType = 2;
                } else {
                    this.mType = null;
                    Log.w(RecyclerAdapter.TAG, "Wrong type of data passed to Item constructor");
                }
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        private class HeaderViewHolder extends ViewHolder {
            TextView mTextView;

            HeaderViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(C0303R.id.mr_dialog_header_name);
            }

            public void binHeaderView(Item item) {
                this.mTextView.setText(item.getData().toString());
            }
        }

        private class RouteViewHolder extends ViewHolder {
            ImageView mImageView;
            View mItemView;
            TextView mTextView;

            RouteViewHolder(View view) {
                super(view);
                this.mItemView = view;
                this.mTextView = (TextView) view.findViewById(C0303R.id.mr_picker_route_name);
                this.mImageView = (ImageView) view.findViewById(C0303R.id.mr_picker_route_icon);
            }

            public void bindRouteView(Item item) {
                final RouteInfo routeInfo = (RouteInfo) item.getData();
                this.mItemView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        routeInfo.select();
                    }
                });
                this.mTextView.setText(routeInfo.getName());
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
            }
        }

        RecyclerAdapter() {
            this.mInflater = LayoutInflater.from(MediaRouteDevicePickerDialog.this.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(MediaRouteDevicePickerDialog.this.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(MediaRouteDevicePickerDialog.this.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(MediaRouteDevicePickerDialog.this.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGropuIcon(MediaRouteDevicePickerDialog.this.mContext);
            setItems();
        }

        void setItems() {
            this.mItems = new ArrayList();
            ArrayList arrayList = new ArrayList();
            for (int size = MediaRouteDevicePickerDialog.this.mRoutes.size() - 1; size >= 0; size--) {
                RouteInfo routeInfo = (RouteInfo) MediaRouteDevicePickerDialog.this.mRoutes.get(size);
                if (routeInfo instanceof RouteGroup) {
                    arrayList.add(routeInfo);
                    MediaRouteDevicePickerDialog.this.mRoutes.remove(size);
                }
            }
            this.mItems.add(new Item(MediaRouteDevicePickerDialog.this.mContext.getString(C0303R.string.mr_dialog_device_header)));
            for (RouteInfo routeInfo2 : MediaRouteDevicePickerDialog.this.mRoutes) {
                this.mItems.add(new Item(routeInfo2));
            }
            this.mItems.add(new Item(MediaRouteDevicePickerDialog.this.mContext.getString(C0303R.string.mr_dialog_route_header)));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mItems.add(new Item((RouteInfo) it.next()));
            }
            notifyDataSetChanged();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            switch (i) {
                case 1:
                    return new HeaderViewHolder(this.mInflater.inflate(C0303R.layout.mr_dialog_header_item, viewGroup, false));
                case 2:
                    return new RouteViewHolder(this.mInflater.inflate(C0303R.layout.mr_picker_route_item, viewGroup, false));
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
                    ((HeaderViewHolder) viewHolder).binHeaderView(i);
                    return;
                case 2:
                    ((RouteViewHolder) viewHolder).bindRouteView(i);
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
                    iconUri = Drawable.createFromStream(MediaRouteDevicePickerDialog.this.mContext.getContentResolver().openInputStream(iconUri), null);
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

    public MediaRouteDevicePickerDialog(Context context) {
        this(context, 0);
    }

    public MediaRouteDevicePickerDialog(Context context, int i) {
        context = MediaRouterThemeHelper.createThemedDialogContext(context, i, false);
        super(context, MediaRouterThemeHelper.createThemedDialogStyle(context));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mHandler = new C02861();
        context = getContext();
        this.mRouter = MediaRouter.getInstance(context);
        this.mCallback = new MediaRouterCallback();
        this.mContext = context;
        this.mUpdateRoutesDelayMs = (long) context.getResources().getInteger(C0303R.integer.mr_update_routes_delay_ms);
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
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (size > 0) {
                if (!onFilterRoute((RouteInfo) list.get(i))) {
                    list.remove(i);
                }
                size = i;
            } else {
                return;
            }
        }
    }

    public boolean onFilterRoute(@NonNull RouteInfo routeInfo) {
        return (routeInfo.isDefaultOrBluetooth() || !routeInfo.isEnabled() || routeInfo.matchesSelector(this.mSelector) == null) ? null : true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0303R.layout.mr_picker_dialog);
        this.mRoutes = new ArrayList();
        this.mCloseButton = (ImageButton) findViewById(C0303R.id.mr_picker_close_button);
        this.mCloseButton.setOnClickListener(new C02872());
        this.mAdapter = new RecyclerAdapter();
        this.mRecyclerView = (RecyclerView) findViewById(C0303R.id.mr_picker_list);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        updateLayout();
    }

    void updateLayout() {
        getWindow().setLayout(-1, -1);
    }

    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        refreshRoutes();
    }

    @CallSuper
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
    }

    public void refreshRoutes() {
        if (this.mAttachedToWindow) {
            List arrayList = new ArrayList(this.mRouter.getRoutes());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, RouteComparator.sInstance);
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= this.mUpdateRoutesDelayMs) {
                updateRoutes(arrayList);
                return;
            }
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + this.mUpdateRoutesDelayMs);
        }
    }

    void updateRoutes(List<RouteInfo> list) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(list);
        this.mAdapter.setItems();
    }
}
