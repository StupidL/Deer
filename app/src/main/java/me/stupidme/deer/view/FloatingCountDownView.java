package me.stupidme.deer.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import me.stupidme.deer.R;
import me.stupidme.deer.util.UnitUtil;

/**
 * Created by allen on 17-8-12.
 */

public class FloatingCountDownView extends View {

    private Context mContext;

    private Paint mBackgroundPaint;
    private Paint mArcPaint;
    private Paint mTextPaint;

    private float mArcWidth;
    private float mBackgroundRadius;

    private int mArcColor;
    private int mBackgroundColor;
    private int mTextColor;
    private float mTextSize;

    private float mStartAngle;
    private float mEndAngle;
    private float mSweepAngle;

    private String mText = "0s";

    private int mWidth;
    private int mHeight;

    private int mCountDownTime = 10;

    private OnCountDownFinishListener mCountDownFinishListener;

    public FloatingCountDownView(Context context) {
        this(context, null, 0);
    }

    public FloatingCountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingCountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.FloatingCountDownView);
        mText = array.getString(R.styleable.FloatingCountDownView_text);
        mTextColor = array.getInt(R.styleable.FloatingCountDownView_textColor, Color.WHITE);
        mTextSize = array.getDimension(R.styleable.FloatingCountDownView_textSize, UnitUtil.dp2px(mContext, 28));
        mBackgroundColor = array.getColor(R.styleable.FloatingCountDownView_backgroundColor, Color.parseColor("#55B2E5"));
        mBackgroundRadius = array.getDimension(R.styleable.FloatingCountDownView_backgroundRadius, UnitUtil.dp2px(mContext, 25));
        Log.d("mBackgroundRadius", mBackgroundRadius + "");
        mArcWidth = array.getDimension(R.styleable.FloatingCountDownView_arcWidth, UnitUtil.dp2px(mContext, 3));
        mArcColor = array.getColor(R.styleable.FloatingCountDownView_arcColor, Color.CYAN);
        array.recycle();
        init();
    }

    private void init() {
        setBackground(ContextCompat.getDrawable(mContext, android.R.color.transparent));

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBackgroundColor);

        mArcPaint = new Paint();
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(mArcColor);
        mArcPaint.setStrokeWidth(mArcWidth);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

        mStartAngle = 0;
        mSweepAngle = 0;
        mEndAngle = 360;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) mBackgroundRadius * 2, (int) mBackgroundRadius * 2);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawArc(canvas);
        drawText(canvas);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - mArcWidth, mBackgroundPaint);
    }

    private void drawArc(Canvas canvas) {
        RectF rectf = new RectF(mArcWidth / 2, mArcWidth / 2, mWidth - mArcWidth / 2, mHeight - mArcWidth / 2);
        canvas.drawArc(rectf, -90, mSweepAngle, false, mArcPaint);
    }

    private void drawText(Canvas canvas) {
        float textWidth = mTextPaint.measureText(mText, 0, mText.length());
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        float dx = mWidth / 2 - textWidth / 2;
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseLine = mHeight / 2 + dy;
        canvas.drawText(mText, dx, baseLine, mTextPaint);
    }

    private void updateText(int seconds) {
        mText = "";
        long minute = seconds / 60;
        long second = seconds % 60;
        if (minute > 0)
            mText = mText + minute + "min";
        mText = mText + second + "s";
    }

    public void setCountDownTimeInSeconds(int seconds) {
        mCountDownTime = seconds;
    }

    public void setOnCountDownFinishListener(OnCountDownFinishListener listener) {
        mCountDownFinishListener = listener;
    }

    public void setCircleBackgroundColor(int color) {
        mBackgroundColor = color;
        mBackgroundPaint.setColor(mBackgroundColor);
    }

    public void setArcColor(int color) {
        mArcColor = color;
        mArcPaint.setColor(mArcColor);
    }

    public void start() {
        ValueAnimator angle = ValueAnimator.ofFloat(mStartAngle, mEndAngle);
        angle.setInterpolator(new LinearInterpolator());
        angle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSweepAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        ValueAnimator time = ValueAnimator.ofInt(mCountDownTime, 0);
        time.setInterpolator(new LinearInterpolator());
        time.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int time = (int) valueAnimator.getAnimatedValue();
                updateText(time);
            }
        });

        final AnimatorSet set = new AnimatorSet();
        set.playTogether(angle, time);
        set.setInterpolator(new LinearInterpolator());
        set.setDuration(mCountDownTime * 1000);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mCountDownFinishListener != null)
                    mCountDownFinishListener.onFinished();
            }
        });
        set.start();
    }

    public interface OnCountDownFinishListener {

        void onFinished();
    }
}

