package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import java.util.Map;

public class BarcodeResult {
    private static final float PREVIEW_DOT_WIDTH = 10.0f;
    private static final float PREVIEW_LINE_WIDTH = 4.0f;
    protected Result mResult;
    private final int mScaleFactor = 2;
    protected SourceData sourceData;

    public int getBitmapScaleFactor() {
        return 2;
    }

    public BarcodeResult(Result result, SourceData sourceData) {
        this.mResult = result;
        this.sourceData = sourceData;
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        if (resultPoint != null && resultPoint2 != null) {
            i = (float) i;
            canvas.drawLine(resultPoint.getX() / i, resultPoint.getY() / i, resultPoint2.getX() / i, resultPoint2.getY() / i, paint);
        }
    }

    public Result getResult() {
        return this.mResult;
    }

    public Bitmap getBitmap() {
        return this.sourceData.getBitmap(2);
    }

    public Bitmap getBitmapWithResultPoints(int i) {
        Bitmap bitmap = getBitmap();
        ResultPoint[] resultPoints = this.mResult.getResultPoints();
        if (resultPoints == null || resultPoints.length <= 0 || bitmap == null) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
        Paint paint = new Paint();
        paint.setColor(i);
        int i2 = 0;
        if (resultPoints.length == 2) {
            paint.setStrokeWidth(1082130432);
            drawLine(canvas, paint, resultPoints[0], resultPoints[1], 2);
        } else if (resultPoints.length == 4 && (this.mResult.getBarcodeFormat() == BarcodeFormat.UPC_A || this.mResult.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
            drawLine(canvas, paint, resultPoints[0], resultPoints[1], 2);
            drawLine(canvas, paint, resultPoints[2], resultPoints[3], 2);
        } else {
            paint.setStrokeWidth(1092616192);
            i = resultPoints.length;
            while (i2 < i) {
                ResultPoint resultPoint = resultPoints[i2];
                if (resultPoint != null) {
                    canvas.drawPoint(resultPoint.getX() / 2.0f, resultPoint.getY() / 2.0f, paint);
                }
                i2++;
            }
        }
        return createBitmap;
    }

    public String getText() {
        return this.mResult.getText();
    }

    public byte[] getRawBytes() {
        return this.mResult.getRawBytes();
    }

    public ResultPoint[] getResultPoints() {
        return this.mResult.getResultPoints();
    }

    public BarcodeFormat getBarcodeFormat() {
        return this.mResult.getBarcodeFormat();
    }

    public Map<ResultMetadataType, Object> getResultMetadata() {
        return this.mResult.getResultMetadata();
    }

    public long getTimestamp() {
        return this.mResult.getTimestamp();
    }

    public String toString() {
        return this.mResult.getText();
    }
}
