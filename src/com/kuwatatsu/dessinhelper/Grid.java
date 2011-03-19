package com.kuwatatsu.dessinhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

public class Grid extends View {
	private Context context = null;
	private int width = 0;
	private int height = 0;

	public Grid(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public Grid(Context context) {
		super(context);
		this.context = context;
	}

    /**
     * @see android.view.View#measure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
        	width = specSize;
            result = specSize;
        } else {
            // Measure the text
            result = specSize;
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
        	height = specSize;
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = specSize;
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

	@Override
	protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int pref_width = -1;
        try {
        	pref_width = Integer.parseInt(pref.getString(getResources().getString(R.string.pref_key_grid_width), "" + getResources().getInteger(R.integer.grid_default_width)));
        } catch (Exception e) {
        	pref_width = getResources().getInteger(R.integer.grid_default_height);
        }
        int pref_height = -1;
        try {
        	pref_height = Integer.parseInt(pref.getString(getResources().getString(R.string.pref_key_grid_height), "" + getResources().getInteger(R.integer.grid_default_height)));
        } catch (Exception e) {
        	pref_height = getResources().getInteger(R.integer.grid_default_height);
        }
        int pref_color = -1;
        try {
        	pref_color = Color.parseColor(pref.getString(getResources().getString(R.string.pref_key_grid_color), "" + getResources().getColor(R.color.grid)));
        } catch (Exception e1) {
    		pref_color = getResources().getColor(R.color.grid);
        }
        Paint paint = new Paint();
        paint.setColor(pref_color);
        paint.setAntiAlias(true);
        for (int i = 0;i < width;i += pref_width) {
        	canvas.drawLine(i, 0, i, height, paint);
        }
    	for (int j = 0;j < height;j += pref_height) {
        	canvas.drawLine(0, j, width, j, paint);
    	}
	}	
}
