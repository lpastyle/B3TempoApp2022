package com.example.b3tempoapp2022;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

/**
 * TODO: document your custom view class.
 */
public class DayColorView extends View {

    private static final float CIRCLE_SCALE = 0.9f; // circle will occupy 90% of room's view
    // Custom attributes data model
    private String captionText;
    private int captionColor = Color.BLACK;
    private float captionTextSize = 0;
    private int dayCircleColor = Color.GRAY;

    private Context context;

    private TextPaint textPaint;
    private Paint circlePaint;

    private float mTextWidth;
    private float mTextHeight;

    public DayColorView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DayColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DayColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.context = context;

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DayColorView, defStyle, 0);
        try {
            captionText = a.getString(R.styleable.DayColorView_captionText);
            captionColor = a.getColor(R.styleable.DayColorView_captionColor, captionColor);
            captionTextSize = a.getDimension(R.styleable.DayColorView_captionTextSize, getResources().getDimension(R.dimen.tempo_color_text_size));
            dayCircleColor = a.getColor(R.styleable.DayColorView_dayCircleColor, ContextCompat.getColor(context, R.color.tempo_undecided_day_bg));
        } finally {
            a.recycle();
        }

        // Set up a default TextPaint object
        textPaint = new TextPaint();
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(captionTextSize);
        textPaint.setColor(captionColor);
        mTextWidth = textPaint.measureText(captionText);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;

        // set up a default paint object
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(dayCircleColor);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(captionText,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                textPaint);

        // Draw circle
        float radius = Math.min(contentHeight, contentWidth) * 0.5f * CIRCLE_SCALE;
        canvas.drawCircle(contentWidth * 0.5f, contentHeight * 0.5f, radius, circlePaint);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return captionText;
    }

    /**
     * Sets the view"s example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        captionText = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return captionColor;
    }

    /**
     * Sets the view"s example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        captionColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return captionTextSize;
    }

    /**
     * Sets the view"s example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        captionTextSize = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view"s example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}