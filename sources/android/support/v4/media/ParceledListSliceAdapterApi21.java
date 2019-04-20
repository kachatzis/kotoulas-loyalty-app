package android.support.v4.media;

import android.media.browse.MediaBrowser.MediaItem;
import android.support.annotation.RequiresApi;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiresApi(21)
class ParceledListSliceAdapterApi21 {
    private static Constructor sConstructor;

    static {
        ReflectiveOperationException e;
        try {
            sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(new Class[]{List.class});
        } catch (ClassNotFoundException e2) {
            e = e2;
            e.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e = e3;
            e.printStackTrace();
        }
    }

    static Object newInstance(List<MediaItem> list) {
        try {
            return sConstructor.newInstance(new Object[]{list});
        } catch (InstantiationException e) {
            list = e;
            list.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            list = e2;
            list.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            list = e3;
            list.printStackTrace();
            return null;
        }
    }

    private ParceledListSliceAdapterApi21() {
    }
}
