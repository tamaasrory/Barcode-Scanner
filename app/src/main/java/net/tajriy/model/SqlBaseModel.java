/*
 * SqlBaseModel.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class SqlBaseModel extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tajriy_accounting.db";
    private static final int DATABASE_VERSION = 1;
    public static final String dir = Environment.getExternalStorageDirectory()
            + File.separator + "/DataBase/" + File.separator;
    private SQLiteDatabase db_read_mode = getReadableDatabase();
    private SQLiteDatabase db_write_mode = getWritableDatabase();

    public SqlBaseModel(Context context) {
        super(context, dirCreator(), null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    public static String dirCreator() {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("membuat direktori");
        }
        return directory.getPath() + File.separator + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sqli[] = {
                "CREATE TABLE `penjualan` (" +
                        "`id_penjualan`INTEGER NOT NULL," +
                        "`tgl_penjualan`TEXT NOT NULL," +
                        "PRIMARY KEY(id_penjualan)" +
                        ");",
                "CREATE TABLE `kategori` (" +
                        "`idKategori`INTEGER NOT NULL," +
                        "`namaKategori`TEXT NOT NULL," +
                        "PRIMARY KEY(idKategori)" +
                        ");",
                "CREATE TABLE `detail_penjualan` (" +
                        "`id_penjualan`INTEGER NOT NULL," +
                        "`barang`INTEGER NOT NULL," +
                        "`harga_barang`NUMERIC NOT NULL," +
                        "`qty`INTEGER NOT NULL" +
                        ");",
                "CREATE TABLE `barang` (" +
                        "`idBarang`TEXT NOT NULL," +
                        "`barcode`TEXT NOT NULL," +
                        "`namaBarang`TEXT NOT NULL," +
                        "`description`TEXT NOT NULL," +
                        "`exp`TEXT NOT NULL," +
                        "`stok`INTEGER NOT NULL DEFAULT 0," +
                        "`hargaBeli`NUMERIC NOT NULL DEFAULT 0," +
                        "`hargaJual`NUMERIC NOT NULL DEFAULT 0," +
                        "`kategori`INTEGER NOT NULL," +
                        "PRIMARY KEY(idBarang)" +
                        ");"};

        Log.d("Membuat tabel", "membuat tabel penjualan : " + sqli[0]);
        db.execSQL(sqli[0]);
        Log.d("Membuat tabel", "membuat tabel kategori: " + sqli[1]);
        db.execSQL(sqli[1]);
        Log.d("Membuat tabel", "membuat tabel detail penjualan: " + sqli[2]);
        db.execSQL(sqli[2]);
        Log.d("Membuat tabel", "membuat tabel barang: " + sqli[3]);
        db.execSQL(sqli[3]);
        db.execSQL("insert into barang(idBarang,barcode,namaBarang,description,exp,stok,hargaBeli,hargaJual,Kategori)" +
                "VALUES('BRG00001','AXS4GB','Axis 4G 4GB 24JAM','bla bla bla...','02-07-2018','10','25000','50000'," +
                "'0')");
    }

    public Cursor select(String sql) {
        return db_read_mode.rawQuery(sql, null);
    }

    public Cursor select(String sql, String[] args) {
        return db_read_mode.rawQuery(sql, args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}