/*
 * BarangView.java dibuat pada 6/27/18 11:59 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.view;

import android.content.Intent;

/**
 * Created by asrory on 27/06/18.
 */

public interface BarangView {

    void onSaveSuccess(String string);

    /**
     *
     * @param string
     */
    void onSaveError(String string);

    /**
     *
     * @param intent
     */
    void runActivity(Intent intent);
}
