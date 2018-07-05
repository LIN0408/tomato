package com.customappbar.tomatoclock;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class TomatoView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint timePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.parseColor("#D1D1D1");
    private int centerX;
    private int centerY;
    private int radius;
    private RectF mRectF = new RectF();
    public static final float START_ANGLE = -90;
    private float sweepVelocity = 0;
    private String textTime = "00:00";

    public TomatoView(Context context) {
        super(context);
    }

    public TomatoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TomatoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//控制
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        centerX = width / 2;//值2為置中
        centerY = height / 3;//影響圓的高度,值越大越偏高
        radius = (int) dpToPixel(120);//影響圓的大小
        setMeasuredDimension(width, height);
    }
    @Override
    protected void onDraw(Canvas canvas) {//畫圓
        super.onDraw(canvas);
        mRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        //黑圆
        canvas.save();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dpToPixel(5));
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        canvas.restore();
        //灰圆
        canvas.save();
        mPaint.setColor(mColor);
        canvas.drawArc(mRectF, START_ANGLE, 360 * sweepVelocity, false, mPaint);
        canvas.restore();
        //时间
        canvas.save();
        timePaint.setColor(Color.BLACK);
        timePaint.setStyle(Paint.Style.FILL);
        timePaint.setTextSize(dpToPixel(40));
        canvas.drawText(textTime, centerX - timePaint.measureText(textTime) / 2,
                centerY - (timePaint.ascent() + timePaint.descent()) / 2, timePaint);
        canvas.restore();
    }
}
