package com.duma.ld.zhilianlift.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * When this layout is in the Horizontal orientation and one and only one child is a TextView with a
 * non-null android:ellipsize, this layout will reduce android:maxWidth of that TextView to ensure
 * the siblings are not truncated. This class is useful when that ellipsize-text-view "starts"
 * before other children of this view group. This layout has no effect if:
 * <ul>
 * <li>the orientation is not horizontal</li>
 * <li>any child has weights.</li>
 * <li>more than one child has a non-null android:ellipsize.</li>
 * </ul>
 * <p>
 * <p>The purpose of this horizontal-linear-layout is to ensure that when the sum of widths of the
 * children are greater than this parent, the maximum width of the ellipsize-text-view, is reduced
 * so that no siblings are truncated.</p>
 * <p>
 * <p>For example: Given Text1 has android:ellipsize="end" and Text2 has android:ellipsize="none",
 * as Text1 and/or Text2 grow in width, both will consume more width until Text2 hits the end
 * margin, then Text1 will cease to grow and instead shrink to accommodate any further growth in
 * Text2.</p>
 * <ul>
 * <li>|[text1]|[text2]              |</li>
 * <li>|[text1 text1]|[text2 text2]  |</li>
 * <li>|[text...]|[text2 text2 text2]|</li>
 * </ul>
 */
public class EllipsizeLayout extends LinearLayout {

    public EllipsizeLayout(Context context) {
        this(context, null);
    }

    public EllipsizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getOrientation() == HORIZONTAL
                && (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY)) {
            int totalLength = 0;
            boolean outOfSpec = false;
            TextView ellipView = null;
            final int count = getChildCount();

            for (int ii = 0; ii < count && !outOfSpec; ++ii) {
                final View child = getChildAt(ii);
                if (child != null && child.getVisibility() != GONE) {
                    if (child instanceof TextView) {
                        final TextView tv = (TextView) child;
                        if (tv.getEllipsize() != null) {
                            if (ellipView == null) {
                                ellipView = tv;
                                // clear maxWidth on mEllipView before measure
                                ellipView.setMaxWidth(Integer.MAX_VALUE);
                            } else {
                                // TODO: support multiple android:ellipsize
                                outOfSpec = true;
                            }
                        }
                    }
                    final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) child
                            .getLayoutParams();
                    outOfSpec |= (lp.weight > 0f);
                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                    totalLength += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                }
            }
            outOfSpec |= (ellipView == null) || (totalLength == 0);
            final int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

            if (!outOfSpec && totalLength > parentWidth) {
                int maxWidth = ellipView.getMeasuredWidth() - (totalLength - parentWidth);
                // TODO: Respect android:minWidth (easy with @TargetApi(16))
                int minWidth = 0;
                if (maxWidth < minWidth) {
                    maxWidth = minWidth;
                }
                ellipView.setMaxWidth(maxWidth);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}