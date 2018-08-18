package com.example.administrator.testjson;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2018/8/2 0002.
 */

public class myimageview extends ViewGroup {
    private int lastInterceptX;
    private int lastInterceptY;
    private int lastX;
    private int lastY;
    private int currentIndex = 0;
    private int childwidth = 0;
    private Scroller scroller;
    private VelocityTracker tracker;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthmode == MeasureSpec.AT_MOST && heightmode == MeasureSpec.AT_MOST) {
            View childone = getChildAt(0);
            int childwidth = childone.getMeasuredWidth();
            int childheight = childone.getMeasuredHeight();
            setMeasuredDimension(childwidth * getChildCount(), childheight);
        } else if (widthmode == MeasureSpec.AT_MOST) {
            View childone = getChildAt(0);
            int childwidth = childone.getMeasuredWidth();
            setMeasuredDimension(childwidth * getChildCount(), heightsize);
        } else if (heightmode == MeasureSpec.AT_MOST) {
            View childone = getChildAt(0);
            int childheight = childone.getMeasuredHeight();
            setMeasuredDimension(widthsize, childheight);
        }
    }

    public myimageview(Context context) {
        super(context);
        init();
    }

    public myimageview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public myimageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                if (Math.abs(deltaX) - Math.abs(deltaY) > 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childwidth;
                if (Math.abs(distance) > childwidth / 10) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            currentIndex--;
                        } else {
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childwidth, 0);
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    public void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY(), 150);
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                childwidth = width;
                child.layout(left, 0, left + width, height);
                left += width;
            }
        }
    }

    private void init() {
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }
}
