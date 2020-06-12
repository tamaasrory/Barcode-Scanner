/*
 * BrandSalesModel.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class BrandSalesModel {
    String barcode;
    String description;
    String qty;
    String harga;
    String subtotal;

    public BrandSalesModel(String barcode, String description, String qty, String harga, String total) {
        this.barcode = barcode;
        this.description = description;
        this.qty = qty;
        this.harga = harga;
        this.subtotal = total;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public String getQty() {
        return qty;
    }

    public String getHarga() {
        return harga;
    }

    public String getSubtotal() {
        return subtotal;
    }

}
