/*
 * BarcodeGraphic.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.presenter;

import android.graphics.Canvas;
import android.util.Log;
import com.google.android.gms.vision.barcode.Barcode;
import net.tajriy.view.DetectionListener;

/**
 * Graphic instance for rendering barcode position, size, and ID within an associated graphic
 * overlay view.
 */
public class BarcodeGraphic extends TrackedGraphic<Barcode> {
//    private static final int COLOR_CHOICES[] = {
//            Color.BLUE,
//            Color.CYAN,
//            Color.GREEN
//    };
//    private static int mCurrentColorIndex = 0;
//
//    private Paint mRectPaint;
//    private Paint mTextPaint;
    private volatile Barcode mBarcode;
    private DetectionListener detectionListener;

    BarcodeGraphic(GraphicOverlay overlay, DetectionListener detList) {
        super(overlay);
//
//        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
//        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];
//
        detectionListener = detList;
//
//        mRectPaint = new Paint();
//        mRectPaint.setColor(selectedColor);
//        mRectPaint.setStyle(Paint.Style.STROKE);
//        mRectPaint.setStrokeWidth(4.0f);
//
//        mTextPaint = new Paint();
//        mTextPaint.setColor(selectedColor);
//        mTextPaint.setTextSize(36.0f);
    }

    /**
     * Updates the barcode instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    void updateItem(Barcode barcode) {
        mBarcode = barcode;
        Log.v("TAG", "called update");
        postInvalidate();
    }

    /**
     * Draws the barcode annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        Barcode barcode = mBarcode;
        if (barcode == null) {
            return;
        }
//
//        // Draws the bounding box around the barcode.
//        RectF rect = new RectF(barcode.getBoundingBox());
//        rect.left = translateX(rect.left);
//        rect.top = translateY(rect.top);
//        rect.right = translateX(rect.right);
//        rect.bottom = translateY(rect.bottom);
//        canvas.drawRect(rect, mRectPaint);
//
//        // Draws a label at the bottom of the barcode indicate the barcode value that was detected.
//        canvas.drawText(barcode.rawValue, rect.left, rect.bottom, mTextPaint);

        detectionListener.onDetection(barcode);
    }
}
