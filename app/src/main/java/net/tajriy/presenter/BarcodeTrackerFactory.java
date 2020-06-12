/*
 * BarcodeTrackerFactory.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */
package net.tajriy.presenter;

//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;

import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

import net.tajriy.view.DetectionListener;

/**
 * Factory for creating a tracker and associated graphic to be associated with a new barcode.  The
 * multi-processor uses this factory to create barcode trackers as needed -- one for each barcode.
 */
public class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {
    private GraphicOverlay mGraphicOverlay;
    private DetectionListener detList;

    public BarcodeTrackerFactory(GraphicOverlay graphicOverlay, DetectionListener detectionListener) {
        mGraphicOverlay = graphicOverlay;
        detList = detectionListener;
    }

    @Override
    public Tracker<Barcode> create(Barcode barcode) {
        BarcodeGraphic graphic = new BarcodeGraphic(mGraphicOverlay, detList);
        return new GraphicTracker<>(mGraphicOverlay, graphic);
    }
}

