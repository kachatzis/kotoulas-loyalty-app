package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    static void flush(@NonNull Resources resources) {
        if (VERSION.SDK_INT < 28) {
            if (VERSION.SDK_INT >= 24) {
                flushNougats(resources);
            } else if (VERSION.SDK_INT >= 23) {
                flushMarshmallows(resources);
            } else if (VERSION.SDK_INT >= 21) {
                flushLollipops(resources);
            }
        }
    }

    @RequiresApi(21)
    private static void flushLollipops(@NonNull Resources resources) {
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (Throwable e) {
                Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        Field field = sDrawableCacheField;
        if (field != null) {
            try {
                resources = (Map) field.get(resources);
            } catch (Resources resources2) {
                Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", resources2);
                resources2 = null;
            }
            if (resources2 != null) {
                resources2.clear();
            }
        }
    }

    @RequiresApi(23)
    private static void flushMarshmallows(@NonNull Resources resources) {
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (Throwable e) {
                Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        Object obj = null;
        Field field = sDrawableCacheField;
        if (field != null) {
            try {
                obj = field.get(resources);
            } catch (Resources resources2) {
                Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", resources2);
            }
        }
        if (obj != null) {
            flushThemedResourcesCache(obj);
        }
    }

    @RequiresApi(24)
    private static void flushNougats(@NonNull Resources resources) {
        if (!sResourcesImplFieldFetched) {
            try {
                sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
                sResourcesImplField.setAccessible(true);
            } catch (Throwable e) {
                Log.e(TAG, "Could not retrieve Resources#mResourcesImpl field", e);
            }
            sResourcesImplFieldFetched = true;
        }
        Field field = sResourcesImplField;
        if (field != null) {
            Object obj = null;
            try {
                resources = field.get(resources);
            } catch (Resources resources2) {
                Log.e(TAG, "Could not retrieve value from Resources#mResourcesImpl", resources2);
                resources2 = null;
            }
            if (resources2 != null) {
                if (!sDrawableCacheFieldFetched) {
                    try {
                        sDrawableCacheField = resources2.getClass().getDeclaredField("mDrawableCache");
                        sDrawableCacheField.setAccessible(true);
                    } catch (Throwable e2) {
                        Log.e(TAG, "Could not retrieve ResourcesImpl#mDrawableCache field", e2);
                    }
                    sDrawableCacheFieldFetched = true;
                }
                field = sDrawableCacheField;
                if (field != null) {
                    try {
                        obj = field.get(resources2);
                    } catch (Resources resources22) {
                        Log.e(TAG, "Could not retrieve value from ResourcesImpl#mDrawableCache", resources22);
                    }
                }
                if (obj != null) {
                    flushThemedResourcesCache(obj);
                }
            }
        }
    }

    @RequiresApi(16)
    private static void flushThemedResourcesCache(@NonNull Object obj) {
        if (!sThemedResourceCacheClazzFetched) {
            try {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            } catch (Throwable e) {
                Log.e(TAG, "Could not find ThemedResourceCache class", e);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        Class cls = sThemedResourceCacheClazz;
        if (cls != null) {
            if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
                try {
                    sThemedResourceCache_mUnthemedEntriesField = cls.getDeclaredField("mUnthemedEntries");
                    sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
                } catch (Throwable e2) {
                    Log.e(TAG, "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e2);
                }
                sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
            }
            Field field = sThemedResourceCache_mUnthemedEntriesField;
            if (field != null) {
                try {
                    obj = (LongSparseArray) field.get(obj);
                } catch (Object obj2) {
                    Log.e(TAG, "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", obj2);
                    obj2 = null;
                }
                if (obj2 != null) {
                    obj2.clear();
                }
            }
        }
    }

    private ResourcesFlusher() {
    }
}
