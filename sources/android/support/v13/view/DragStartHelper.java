package android.support.v13.view;

import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class DragStartHelper {
    private boolean mDragging;
    private int mLastTouchX;
    private int mLastTouchY;
    private final OnDragStartListener mListener;
    private final OnLongClickListener mLongClickListener = new C01231();
    private final OnTouchListener mTouchListener = new C01242();
    private final View mView;

    /* renamed from: android.support.v13.view.DragStartHelper$1 */
    class C01231 implements OnLongClickListener {
        C01231() {
        }

        public boolean onLongClick(View view) {
            return DragStartHelper.this.onLongClick(view);
        }
    }

    /* renamed from: android.support.v13.view.DragStartHelper$2 */
    class C01242 implements OnTouchListener {
        C01242() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return DragStartHelper.this.onTouch(view, motionEvent);
        }
    }

    public interface OnDragStartListener {
        boolean onDragStart(View view, DragStartHelper dragStartHelper);
    }

    public DragStartHelper(View view, OnDragStartListener onDragStartListener) {
        this.mView = view;
        this.mListener = onDragStartListener;
    }

    public void attach() {
        this.mView.setOnLongClickListener(this.mLongClickListener);
        this.mView.setOnTouchListener(this.mTouchListener);
    }

    public void detach() {
        this.mView.setOnLongClickListener(null);
        this.mView.setOnTouchListener(null);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.mLastTouchX = x;
                this.mLastTouchY = y;
                break;
            case 1:
            case 3:
                this.mDragging = false;
                break;
            case 2:
                if (MotionEventCompat.isFromSource(motionEvent, 8194)) {
                    if ((motionEvent.getButtonState() & 1) != null) {
                        if (this.mDragging == null) {
                            if (this.mLastTouchX == x && this.mLastTouchY == y) {
                                break;
                            }
                            this.mLastTouchX = x;
                            this.mLastTouchY = y;
                            this.mDragging = this.mListener.onDragStart(view, this);
                            return this.mDragging;
                        }
                        break;
                    }
                    break;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public boolean onLongClick(View view) {
        return this.mListener.onDragStart(view, this);
    }

    public void getTouchPosition(Point point) {
        point.set(this.mLastTouchX, this.mLastTouchY);
    }
}
