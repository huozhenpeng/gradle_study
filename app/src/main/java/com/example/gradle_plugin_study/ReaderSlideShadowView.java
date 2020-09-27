package com.example.gradle_plugin_study;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ReaderSlideShadowView extends View {
    private Paint mPaint;
    private Shader mShader;

    public ReaderSlideShadowView(Context context) {
        super(context);
        init(context);
    }

    public ReaderSlideShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ReaderSlideShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initShader(w);
    }

    private void initShader(int width) {
        if (width == 0) {
            return;
        }
        Shader shader1 = new LinearGradient(0, 0, width, 0, 0x4c000000, 0, Shader.TileMode.CLAMP);
        Shader shader2 = new LinearGradient(0, 0, width, 0, 0xff000000, 0, Shader.TileMode.CLAMP);
        Shader shader3 = new LinearGradient(0, 0, width, 0, 0xff000000, 0, Shader.TileMode.CLAMP);
        Shader shader4 = new ComposeShader(shader1, shader2, PorterDuff.Mode.MULTIPLY);
        mShader = new ComposeShader(shader3, shader4, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShader == null || getMeasuredWidth() == 0) {
            return;
        }
        mPaint.setShader(mShader);
        canvas.drawRect(0,0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setShader(null);
    }
}
