/*
 * MenuModel.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;

import android.view.View;

public class MenuModel {
    private String menuTitle;
    private String counter;
    private int color;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    View.OnClickListener onClickListener;

    public MenuModel() {
    }

    public MenuModel(String menuTitles, String counter, int colors, View.OnClickListener onClickListener) {
        this.menuTitle = menuTitles;
        this.counter = counter;
        this.color = colors;
        this.onClickListener = onClickListener;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
