package com.journeyapps.barcodescanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.C0391R;
import com.journeyapps.barcodescanner.CameraPreview.StateListener;
import java.util.ArrayList;
import java.util.List;

public class ViewfinderView extends View {
    protected static final long ANIMATION_DELAY = 80;
    protected static final int CURRENT_POINT_OPACITY = 160;
    protected static final int MAX_RESULT_POINTS = 20;
    protected static final int POINT_SIZE = 6;
    protected static final int[] SCANNER_ALPHA = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
    protected static final String TAG = "ViewfinderView";
    protected CameraPreview cameraPreview;
    protected Rect framingRect;
    protected final int laserColor;
    protected List<ResultPoint> lastPossibleResultPoints;
    protected final int maskColor;
    protected final Paint paint = new Paint(1);
    protected List<ResultPoint> possibleResultPoints;
    protected Rect previewFramingRect;
    protected Bitmap resultBitmap;
    protected final int resultColor;
    protected final int resultPointColor;
    protected int scannerAlpha;

    /* renamed from: com.journeyapps.barcodescanner.ViewfinderView$1 */
    class C06021 implements StateListener {
        public void cameraError(Exception exception) {
        }

        public void previewStarted() {
        }

        public void previewStopped() {
        }

        C06021() {
        }

        public void previewSized() {
            ViewfinderView.this.refreshSizes();
            ViewfinderView.this.invalidate();
        }
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context = getResources();
        attributeSet = getContext().obtainStyledAttributes(attributeSet, C0391R.styleable.zxing_finder);
        this.maskColor = attributeSet.getColor(C0391R.styleable.zxing_finder_zxing_viewfinder_mask, context.getColor(C0391R.color.zxing_viewfinder_mask));
        this.resultColor = attributeSet.getColor(C0391R.styleable.zxing_finder_zxing_result_view, context.getColor(C0391R.color.zxing_result_view));
        this.laserColor = attributeSet.getColor(C0391R.styleable.zxing_finder_zxing_viewfinder_laser, context.getColor(C0391R.color.zxing_viewfinder_laser));
        this.resultPointColor = attributeSet.getColor(C0391R.styleable.zxing_finder_zxing_possible_result_points, context.getColor(C0391R.color.zxing_possible_result_points));
        attributeSet.recycle();
        this.scannerAlpha = null;
        this.possibleResultPoints = new ArrayList(5);
        this.lastPossibleResultPoints = null;
    }

    public void setCameraPreview(CameraPreview cameraPreview) {
        this.cameraPreview = cameraPreview;
        cameraPreview.addStateListener(new C06021());
    }

    protected void refreshSizes() {
        CameraPreview cameraPreview = this.cameraPreview;
        if (cameraPreview != null) {
            Rect framingRect = cameraPreview.getFramingRect();
            Rect previewFramingRect = this.cameraPreview.getPreviewFramingRect();
            if (!(framingRect == null || previewFramingRect == null)) {
                this.framingRect = framingRect;
                this.previewFramingRect = previewFramingRect;
            }
        }
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        refreshSizes();
        Rect rect = this.framingRect;
        if (rect != null) {
            Rect rect2 = this.previewFramingRect;
            if (rect2 != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
                float f = (float) width;
                canvas.drawRect(0.0f, 0.0f, f, (float) rect.top, this.paint);
                canvas.drawRect(0.0f, (float) rect.top, (float) rect.left, (float) (rect.bottom + 1), this.paint);
                float f2 = f;
                canvas.drawRect((float) (rect.right + 1), (float) rect.top, f2, (float) (rect.bottom + 1), this.paint);
                canvas.drawRect(0.0f, (float) (rect.bottom + 1), f2, (float) height, this.paint);
                if (this.resultBitmap != null) {
                    this.paint.setAlpha(CURRENT_POINT_OPACITY);
                    canvas.drawBitmap(this.resultBitmap, null, rect, this.paint);
                } else {
                    this.paint.setColor(this.laserColor);
                    this.paint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
                    this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
                    width = (rect.height() / 2) + rect.top;
                    canvas.drawRect((float) (rect.left + 2), (float) (width - 1), (float) (rect.right - 1), (float) (width + 2), this.paint);
                    float width2 = ((float) rect.width()) / ((float) rect2.width());
                    float height2 = ((float) rect.height()) / ((float) rect2.height());
                    List<ResultPoint> list = this.possibleResultPoints;
                    List<ResultPoint> list2 = this.lastPossibleResultPoints;
                    int i = rect.left;
                    int i2 = rect.top;
                    if (list.isEmpty()) {
                        this.lastPossibleResultPoints = null;
                    } else {
                        this.possibleResultPoints = new ArrayList(5);
                        this.lastPossibleResultPoints = list;
                        this.paint.setAlpha(CURRENT_POINT_OPACITY);
                        this.paint.setColor(this.resultPointColor);
                        for (ResultPoint resultPoint : list) {
                            canvas.drawCircle((float) (((int) (resultPoint.getX() * width2)) + i), (float) (((int) (resultPoint.getY() * height2)) + i2), 6.0f, this.paint);
                        }
                    }
                    if (list2 != null) {
                        this.paint.setAlpha(80);
                        this.paint.setColor(this.resultPointColor);
                        for (ResultPoint resultPoint2 : list2) {
                            canvas.drawCircle((float) (((int) (resultPoint2.getX() * width2)) + i), (float) (((int) (resultPoint2.getY() * height2)) + i2), 3.0f, this.paint);
                        }
                    }
                    postInvalidateDelayed(ANIMATION_DELAY, rect.left - 6, rect.top - 6, rect.right + 6, rect.bottom + 6);
                }
            }
        }
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        List list = this.possibleResultPoints;
        list.add(resultPoint);
        resultPoint = list.size();
        if (resultPoint > 20) {
            list.subList(0, resultPoint - 10).clear();
        }
    }
}
