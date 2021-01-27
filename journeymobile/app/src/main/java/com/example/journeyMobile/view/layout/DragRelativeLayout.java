package com.example.journeyMobile.view.layout;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.HashSet;


public class DragRelativeLayout extends RelativeLayout {
    private ViewDragHelper mHelper;
    private boolean isDrag = true;//child view can be dragged. default: true
    private AbsorbType isAdsorb = AbsorbType.nothing;//adsorb when near the edge default: false
    private int oldOrientation = Configuration.ORIENTATION_PORTRAIT;
    private HashSet<Integer> mSet = new HashSet<>(4);
    private SparseArray<Point> mViewsPoint = new SparseArray<>(4);
    private boolean canDragHorizontal = true;
    private boolean canDragVertical = true;
    private  double hideFact = 0.2; //for the size shoule be hided




    public DragRelativeLayout(Context context) {
        this(context, null);
    }

    public DragRelativeLayout(Context context,AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragRelativeLayout(Context context,AttributeSet attrs,int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DragRelativeLayout(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();

    }

    private void init() {
        if (!isDrag) {
            return;
        }
        setClickable(true);
        initViewDragHelper();
    }

    public void initial(int viewID, boolean canDragHorizontal, boolean canDragVertical,
                        AbsorbType adsorb, double hideFact) {

        addDragView(viewID);
        setCanDragHorizontal(canDragHorizontal);
        setCanDragVertical(canDragVertical);
        setAdsorb(adsorb);
        setHideFact(hideFact);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return isDrag && mHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isDrag) {
            mHelper.processTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (isDrag && mHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int orientation = this.getResources().getConfiguration().orientation;
        boolean screenRotate = false;// whether the screen can be rotated
        if (this.getResources().getConfiguration().orientation != oldOrientation) {
            oldOrientation = orientation;
            // move the target to the place when rotation happened
            screenRotate = true;
        }
        a:
        for (Integer id : mSet) {
            int count = getChildCount();

            for (int i = 0; i < count; i++) {
                View view = getChildAt(i);
                if (view.getId() != id) {
                    continue;
                }
                Point point = mViewsPoint.get(id);
                if (point == null) {
                    continue a;
                }
                if (screenRotate) {
                    Point adsorb = adsorb(point, view);
                    if (adsorb != null) {
                        point.set(adsorb.x, adsorb.y);
                    }
                }

                view.layout(point.x, point.y, point.x + view.getMeasuredWidth(), point.y + view.getMeasuredHeight());
            }
        }
    }

    private View searchView(Integer id) {
        if (id == null) return null;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view.getId() == id){
                return view;
            }
        }

        return null;
    }

    private void initViewDragHelper() {
        mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {



            @Override
            public boolean tryCaptureView(View child, int pointerId) {


                for (Integer childId : mSet) {
                    if (child.getId() == childId) {
//                        dragView = searchView(mSet.get(childId));

                        child.bringToFront();

                        return true;
                    }
                }
                return false;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (!canDragHorizontal) return child.getLeft();

                int width = child.getMeasuredWidth();

                switch (isAdsorb) {
                    case right:
                        if (left < getMeasuredWidth() - width) {
                            return child.getLeft();
                        } else if (left > getMeasuredWidth() - width * hideFact) {
                            return child.getLeft();
                        } else {
                            return left;
                        }

                    case left:
                        if (left > getMeasuredWidth() - width) {
                            return child.getLeft();
                        } else if (left < getMeasuredWidth() - width * hideFact) {
                            return child.getLeft();
                        } else {
                            return left;
                        }

                    default:
                        if (left < 0) {
                            return 0;
                        } else if (left > getMeasuredWidth() - width) {
                            return child.getLeft();
                        } else {
                            return left;
                        }
                }



            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (!canDragVertical) return child.getTop();

                int height = child.getMeasuredHeight();

                switch (isAdsorb) {
                    case bottom:
                        if (top < getMeasuredHeight() - height) {
                            return getMeasuredHeight() - height;
                        } else if (top > getMeasuredHeight() - height * hideFact) {
                            return (int) (getMeasuredHeight() - height * hideFact);
                        } else {
                            return top;
                        }

                    case top:
                        if (top > getMeasuredHeight() - height) {
                            return child.getTop();
                        } else if (top < getMeasuredHeight() - height * hideFact) {
                            return child.getTop();
                        } else {
                            return top;
                        }

                    default:
                        if (top < 0) {
                            return 0;
                        } else if (top > getMeasuredHeight() - height) {
                            return child.getTop();
                        } else {
                            return top;
                        }
                }
            }

            @Override
            public int getViewHorizontalDragRange(View child) {

                for (Integer childId : mSet) {
                    if (child.getId() == childId) {
                        return getMeasuredWidth() - child.getMeasuredWidth();
                    }
                }
                return 0;
            }

            @Override
            public int getViewVerticalDragRange(View child) {

                for (Integer childId : mSet) {
                    if (child.getId() == childId) {
                        return getMeasuredHeight() - child.getMeasuredHeight();
                    }
                }
                return 0;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                resetPoint(releasedChild.getId(), releasedChild.getLeft(), releasedChild.getTop());

//                if (dragView != null) {
//                    dragLeft = dragView.getLeft();
//                    dragRight = dragView.getRight();
//                    dragBottom = dragView.getBottom();
//                    dragTop = dragView.getTop();
//                }
                //Adsorb the edge
//                if (isAdsorb == null) {
//                    return;
//                }
//
//                Point end = adsorb(null, releasedChild);
//                if (end == null) {
//                    return;
//                }
//                mHelper.settleCapturedViewAt(end.x, end.y);
//                invalidate();
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                resetPoint(changedView.getId(), left, top);

            }

            private void resetPoint(int viewId, int x, int y) {
                Point point = mViewsPoint.get(viewId);
                if (point == null) {
                    mViewsPoint.put(viewId, new Point(x, y));
                } else {
                    point.set(x, y);
                }
            }

        });
        mHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }

    /**
     * absorb the edge
     *
     * @param point         record the last position of the widget
     * @param releasedChild  the target child
     * @return absord to the position, null means do not absord
     */
    @Nullable
    private Point adsorb(Point point, View releasedChild) {
        int width = getMeasuredWidth() - releasedChild.getMeasuredWidth();
        int height = getMeasuredHeight() - releasedChild.getMeasuredHeight();


        // situation: the gap is less than 1/4 of the width of height then will absord
        boolean toTop, toBottom, toLeft, toRight;
        if (point == null) {
            toTop = releasedChild.getTop() < height >> 2;
            toBottom = releasedChild.getTop() > height - (height >> 2);
            toLeft = releasedChild.getLeft() < width >> 2;
            toRight = releasedChild.getLeft() > width - (width >> 2);
        } else {
            toTop = point.y < height >> 2;
            toLeft = point.x < width >> 2;
            toRight = point.x > width - (width >> 2);
            toBottom = point.y > height - (height >> 2);
        }

        if (!toTop && !toBottom && !toLeft && !toRight) {//do not absorb when in the middle
            return null;
        }
        if (toTop && toLeft) {//left top
            return new Point();
        } else if (toTop && toRight) {//right top
            return new Point(width, 0);
        } else if (toBottom && toLeft) {//Left top
            return new Point(0, height);
        } else if (toBottom && toRight) {//right bottom
            return new Point(width, height);
        } else if (toTop) {//middle of the top
            return new Point(releasedChild.getLeft(), 0);
        } else if (toBottom) {//middle of the bottom
            return new Point(releasedChild.getLeft(), height);
        } else if (toLeft) {//middle of the left
            return new Point(0, releasedChild.getTop());
        } else {// middle of the right
            return new Point(width, releasedChild.getTop());
        }
    }

    /**
     * whether child can be dragged
     *
     * @param drag default: ture
     */
    public void setDrag(boolean drag) {
        if (drag && mHelper == null) {
            initViewDragHelper();
        }
        isDrag = drag;
    }

    public boolean isDrag() {
        return isDrag;
    }

    /**
     * whether the widget can be absorded
     *
     * @param adsorb default: false
     */
    public void setAdsorb(AbsorbType adsorb) {
        isAdsorb = adsorb;
    }

    public AbsorbType isAdsorb() {
        return isAdsorb;
    }

    public void addDragView(@IdRes int viewId) {
        mSet.add(viewId);

    }

    public void removeDragView(@IdRes int viewId) {
        mSet.remove(viewId);
    }





    public SparseArray<Point> getViewsPoint() {
        return mViewsPoint;
    }

    public void setViewsPoint(SparseArray<Point> viewsPoint) {
        mViewsPoint = viewsPoint;
    }

    public void setCanDragHorizontal(boolean canDragHorizontal) {
        this.canDragHorizontal = canDragHorizontal;
    }

    public void setCanDragVertical(boolean canDragVertical) {
        canDragVertical = canDragVertical;
    }

    public boolean isCanDragHorizontal() {
        return canDragHorizontal;
    }

    public boolean isCanDragVertical() {
        return canDragVertical;
    }

    public void setHideFact(double fact) {
        this.hideFact = fact;
    }

    public double getHideFact() {
        return hideFact;
    }

    public enum AbsorbType {
        left,right,bottom,top,nothing;
    }
}
