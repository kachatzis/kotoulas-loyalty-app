package com.google.zxing.client.android.camera;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.journeyapps.barcodescanner.camera.CameraSettings.FocusMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class CameraConfigurationUtils {
    private static final int AREA_PER_1000 = 400;
    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;
    private static final int MAX_FPS = 20;
    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;
    private static final int MIN_FPS = 10;
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final String TAG = "CameraConfiguration";

    private CameraConfigurationUtils() {
    }

    public static void setFocus(Parameters parameters, FocusMode focusMode, boolean z) {
        Collection supportedFocusModes = parameters.getSupportedFocusModes();
        if (!z) {
            if (focusMode != FocusMode.AUTO) {
                if (focusMode == FocusMode.CONTINUOUS) {
                    focusMode = findSettableValue("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto");
                } else if (focusMode == FocusMode.INFINITY) {
                    focusMode = findSettableValue("focus mode", supportedFocusModes, "infinity");
                } else if (focusMode == FocusMode.MACRO) {
                    focusMode = findSettableValue("focus mode", supportedFocusModes, "macro");
                } else {
                    focusMode = null;
                }
                if (!z && r7 == null) {
                    focusMode = findSettableValue("focus mode", supportedFocusModes, new String[]{"macro", "edof"});
                }
                if (focusMode == null) {
                }
                if (focusMode.equals(parameters.getFocusMode())) {
                    parameters.setFocusMode(focusMode);
                    return;
                }
                parameters = TAG;
                z = new StringBuilder();
                z.append("Focus mode already set to ");
                z.append(focusMode);
                Log.i(parameters, z.toString());
            }
        }
        focusMode = findSettableValue("focus mode", supportedFocusModes, "auto");
        focusMode = findSettableValue("focus mode", supportedFocusModes, new String[]{"macro", "edof"});
        if (focusMode == null) {
            if (focusMode.equals(parameters.getFocusMode())) {
                parameters.setFocusMode(focusMode);
                return;
            }
            parameters = TAG;
            z = new StringBuilder();
            z.append("Focus mode already set to ");
            z.append(focusMode);
            Log.i(parameters, z.toString());
        }
    }

    public static void setTorch(Parameters parameters, boolean z) {
        Collection supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            z = findSettableValue("flash mode", supportedFlashModes, "torch", "on");
        } else {
            z = findSettableValue("flash mode", supportedFlashModes, "off");
        }
        if (!z) {
            return;
        }
        if (z.equals(parameters.getFlashMode())) {
            parameters = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Flash mode already set to ");
            stringBuilder.append(z);
            Log.i(parameters, stringBuilder.toString());
            return;
        }
        String str = TAG;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Setting flash mode to ");
        stringBuilder2.append(z);
        Log.i(str, stringBuilder2.toString());
        parameters.setFlashMode(z);
    }

    public static void setBestExposure(Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (!(minExposureCompensation == 0 && maxExposureCompensation == 0)) {
            float f = 0.0f;
            if (exposureCompensationStep > 0.0f) {
                if (!z) {
                    f = MAX_EXPOSURE_COMPENSATION;
                }
                z = Math.round(f / exposureCompensationStep);
                exposureCompensationStep *= (float) z;
                z = Math.max(Math.min(z, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == z) {
                    parameters = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exposure compensation already set to ");
                    stringBuilder.append(z);
                    stringBuilder.append(" / ");
                    stringBuilder.append(exposureCompensationStep);
                    Log.i(parameters, stringBuilder.toString());
                    return;
                }
                String str = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Setting exposure compensation to ");
                stringBuilder2.append(z);
                stringBuilder2.append(" / ");
                stringBuilder2.append(exposureCompensationStep);
                Log.i(str, stringBuilder2.toString());
                parameters.setExposureCompensation(z);
                return;
            }
        }
        Log.i(TAG, "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Parameters parameters, int i, int i2) {
        Collection<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Supported FPS ranges: ");
        stringBuilder.append(toString((Collection) supportedPreviewFpsRange));
        Log.i(str, stringBuilder.toString());
        if (supportedPreviewFpsRange != null && !supportedPreviewFpsRange.isEmpty()) {
            int[] iArr = null;
            for (int[] iArr2 : supportedPreviewFpsRange) {
                int i3 = iArr2[0];
                int i4 = iArr2[1];
                if (i3 >= i * 1000 && i4 <= i2 * 1000) {
                    iArr = iArr2;
                    break;
                }
            }
            if (iArr == null) {
                Log.i(TAG, "No suitable FPS range?");
                return;
            }
            i = new int[2];
            parameters.getPreviewFpsRange(i);
            if (Arrays.equals(i, iArr) != 0) {
                parameters = TAG;
                i = new StringBuilder();
                i.append("FPS range already set to ");
                i.append(Arrays.toString(iArr));
                Log.i(parameters, i.toString());
                return;
            }
            i = TAG;
            i2 = new StringBuilder();
            i2.append("Setting FPS range to ");
            i2.append(Arrays.toString(iArr));
            Log.i(i, i2.toString());
            parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        }
    }

    @TargetApi(15)
    public static void setFocusArea(Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Old focus areas: ");
            stringBuilder.append(toString(parameters.getFocusAreas()));
            Log.i(str, stringBuilder.toString());
            Iterable buildMiddleArea = buildMiddleArea(AREA_PER_1000);
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Setting focus area to : ");
            stringBuilder2.append(toString(buildMiddleArea));
            Log.i(str2, stringBuilder2.toString());
            parameters.setFocusAreas(buildMiddleArea);
            return;
        }
        Log.i(TAG, "Device does not support focus areas");
    }

    @TargetApi(15)
    public static void setMetering(Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Old metering areas: ");
            stringBuilder.append(parameters.getMeteringAreas());
            Log.i(str, stringBuilder.toString());
            Iterable buildMiddleArea = buildMiddleArea(AREA_PER_1000);
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Setting metering area to : ");
            stringBuilder2.append(toString(buildMiddleArea));
            Log.i(str2, stringBuilder2.toString());
            parameters.setMeteringAreas(buildMiddleArea);
            return;
        }
        Log.i(TAG, "Device does not support metering areas");
    }

    @TargetApi(15)
    private static List<Area> buildMiddleArea(int i) {
        int i2 = -i;
        return Collections.singletonList(new Area(new Rect(i2, i2, i, i), 1));
    }

    @TargetApi(15)
    public static void setVideoStabilization(Parameters parameters) {
        if (!parameters.isVideoStabilizationSupported()) {
            Log.i(TAG, "This device does not support video stabilization");
        } else if (parameters.getVideoStabilization()) {
            Log.i(TAG, "Video stabilization already enabled");
        } else {
            Log.i(TAG, "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
        }
    }

    public static void setBarcodeSceneMode(Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i(TAG, "Barcode scene mode already set");
            return;
        }
        String findSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (findSettableValue != null) {
            parameters.setSceneMode(findSettableValue);
        }
    }

    public static void setZoom(Parameters parameters, double d) {
        if (parameters.isZoomSupported()) {
            d = indexOfClosestZoom(parameters, d);
            if (d != null) {
                if (parameters.getZoom() == d.intValue()) {
                    parameters = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Zoom is already set to ");
                    stringBuilder.append(d);
                    Log.i(parameters, stringBuilder.toString());
                } else {
                    String str = TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Setting zoom to ");
                    stringBuilder2.append(d);
                    Log.i(str, stringBuilder2.toString());
                    parameters.setZoom(d.intValue());
                }
            } else {
                return;
            }
        }
        Log.i(TAG, "Zoom is not supported");
    }

    private static Integer indexOfClosestZoom(Parameters parameters, double d) {
        List zoomRatios = parameters.getZoomRatios();
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Zoom ratios: ");
        stringBuilder.append(zoomRatios);
        Log.i(str, stringBuilder.toString());
        parameters = parameters.getMaxZoom();
        if (!(zoomRatios == null || zoomRatios.isEmpty())) {
            if (zoomRatios.size() == parameters + 1) {
                d *= 100.0d;
                double d2 = Double.POSITIVE_INFINITY;
                int i = 0;
                for (parameters = null; parameters < zoomRatios.size(); parameters++) {
                    double intValue = (double) ((Integer) zoomRatios.get(parameters)).intValue();
                    Double.isNaN(intValue);
                    intValue = Math.abs(intValue - d);
                    if (intValue < d2) {
                        i = parameters;
                        d2 = intValue;
                    }
                }
                parameters = TAG;
                d = new StringBuilder();
                d.append("Chose zoom ratio of ");
                d2 = (double) ((Integer) zoomRatios.get(i)).intValue();
                Double.isNaN(d2);
                d.append(d2 / 100.0d);
                Log.i(parameters, d.toString());
                return Integer.valueOf(i);
            }
        }
        Log.w(TAG, "Invalid zoom ratios!");
        return null;
    }

    public static void setInvertColor(Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i(TAG, "Negative effect already set");
            return;
        }
        String findSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), "negative");
        if (findSettableValue != null) {
            parameters.setColorEffect(findSettableValue);
        }
    }

    private static String findSettableValue(String str, Collection<String> collection, String... strArr) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requesting ");
        stringBuilder.append(str);
        stringBuilder.append(" value from among: ");
        stringBuilder.append(Arrays.toString(strArr));
        Log.i(str2, stringBuilder.toString());
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Supported ");
        stringBuilder.append(str);
        stringBuilder.append(" values: ");
        stringBuilder.append(collection);
        Log.i(str2, stringBuilder.toString());
        if (collection != null) {
            for (String str3 : strArr) {
                if (collection.contains(str3)) {
                    collection = TAG;
                    strArr = new StringBuilder();
                    strArr.append("Can set ");
                    strArr.append(str);
                    strArr.append(" to: ");
                    strArr.append(str3);
                    Log.i(collection, strArr.toString());
                    return str3;
                }
            }
        }
        Log.i(TAG, "No supported values match");
        return null;
    }

    private static String toString(Collection<int[]> collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                collection = collection.iterator();
                while (collection.hasNext()) {
                    stringBuilder.append(Arrays.toString((int[]) collection.next()));
                    if (collection.hasNext()) {
                        stringBuilder.append(", ");
                    }
                }
                stringBuilder.append(']');
                return stringBuilder.toString();
            }
        }
        return "[]";
    }

    @TargetApi(15)
    private static String toString(Iterable<Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Area area : iterable) {
            stringBuilder.append(area.rect);
            stringBuilder.append(':');
            stringBuilder.append(area.weight);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    public static String collectStats(Parameters parameters) {
        return collectStats(parameters.flatten());
    }

    public static String collectStats(CharSequence charSequence) {
        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append("BOARD=");
        stringBuilder.append(Build.BOARD);
        stringBuilder.append('\n');
        stringBuilder.append("BRAND=");
        stringBuilder.append(Build.BRAND);
        stringBuilder.append('\n');
        stringBuilder.append("CPU_ABI=");
        stringBuilder.append(Build.CPU_ABI);
        stringBuilder.append('\n');
        stringBuilder.append("DEVICE=");
        stringBuilder.append(Build.DEVICE);
        stringBuilder.append('\n');
        stringBuilder.append("DISPLAY=");
        stringBuilder.append(Build.DISPLAY);
        stringBuilder.append('\n');
        stringBuilder.append("FINGERPRINT=");
        stringBuilder.append(Build.FINGERPRINT);
        stringBuilder.append('\n');
        stringBuilder.append("HOST=");
        stringBuilder.append(Build.HOST);
        stringBuilder.append('\n');
        stringBuilder.append("ID=");
        stringBuilder.append(Build.ID);
        stringBuilder.append('\n');
        stringBuilder.append("MANUFACTURER=");
        stringBuilder.append(Build.MANUFACTURER);
        stringBuilder.append('\n');
        stringBuilder.append("MODEL=");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append('\n');
        stringBuilder.append("PRODUCT=");
        stringBuilder.append(Build.PRODUCT);
        stringBuilder.append('\n');
        stringBuilder.append("TAGS=");
        stringBuilder.append(Build.TAGS);
        stringBuilder.append('\n');
        stringBuilder.append("TIME=");
        stringBuilder.append(Build.TIME);
        stringBuilder.append('\n');
        stringBuilder.append("TYPE=");
        stringBuilder.append(Build.TYPE);
        stringBuilder.append('\n');
        stringBuilder.append("USER=");
        stringBuilder.append(Build.USER);
        stringBuilder.append('\n');
        stringBuilder.append("VERSION.CODENAME=");
        stringBuilder.append(VERSION.CODENAME);
        stringBuilder.append('\n');
        stringBuilder.append("VERSION.INCREMENTAL=");
        stringBuilder.append(VERSION.INCREMENTAL);
        stringBuilder.append('\n');
        stringBuilder.append("VERSION.RELEASE=");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append('\n');
        stringBuilder.append("VERSION.SDK_INT=");
        stringBuilder.append(VERSION.SDK_INT);
        stringBuilder.append('\n');
        if (charSequence != null) {
            charSequence = SEMICOLON.split(charSequence);
            Arrays.sort(charSequence);
            for (String append : charSequence) {
                stringBuilder.append(append);
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }
}
