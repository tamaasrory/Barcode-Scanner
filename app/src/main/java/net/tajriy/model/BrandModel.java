/*
 * BrandModel.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;

public class BrandModel {
    String idBarang;
    String barcode;
    String namaBarang;
    String description;
    String exp;
    String stok;
    String hargaBeli;
    String hargaJual;
    String kategori;

    public BrandModel(String idBarang, String barcode, String namaBarang, String description, String exp, String stok,
                      String hargaBeli,
                      String total, String kategori) {
        this.idBarang = idBarang;
        this.barcode = barcode;
        this.namaBarang = namaBarang;
        this.description = description;
        this.exp = exp;
        this.stok = stok;
        this.hargaBeli = hargaBeli;
        this.hargaJual = total;
        this.kategori = kategori;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public String getExp() {
        return exp;
    }

    public String getStok() {
        return stok;
    }

    public String getHargaBeli() {
        return hargaBeli;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public String getKategori() {
        return kategori;
    }

    public boolean insert(BrandModel brangModel) {
        return true;
    }

    public boolean update(BrandModel brangModel) {
        return true;
    }

    public boolean delete(BrandModel brangModel) {
        return true;
    }
}
