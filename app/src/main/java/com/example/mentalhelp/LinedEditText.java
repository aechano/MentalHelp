package com.example.mentalhelp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Rect mRect;
    private Paint mPaint;

    // Spacing between lines in pixels
    private int mLineSpacing;

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);

        // Set the spacing between lines
        mLineSpacing = (int) getTextSize() + 10; // Adjust as needed
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = (int) Math.ceil((float) (getBottom() - getTop()) / mLineSpacing);
        Rect r = mRect;
        Paint paint = mPaint;

        // Adjust the position to start from the baseline of the first line
        int baseline = getLineBounds(0, r);

        for (int i = 0; i < count; i++) {
            canvas.drawLine(r.left, baseline + i * mLineSpacing + 1, r.right, baseline + i * mLineSpacing + 1, paint);
        }

        super.onDraw(canvas);
    }
}
