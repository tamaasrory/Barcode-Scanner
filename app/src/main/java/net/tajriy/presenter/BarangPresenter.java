/*
 * BarangPresenter.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.presenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import net.tajriy.R;
import net.tajriy.activity.BarangActivity;
import net.tajriy.model.SqlBaseModel;

public class BarangPresenter extends AppCompatActivity {
    protected Cursor cursor;
    SqlBaseModel sqlBaseModel;
    SQLiteDatabase dbWriteAccess, dbReadAccess;
    EditText idBarang, barcode, namaBarang, description, exp, stok, hargaBeli, hargaJual, kategori;
    String action;
    int menu;
    public static BarangPresenter barangPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlBaseModel = new SqlBaseModel(this);
        dbWriteAccess = sqlBaseModel.getWritableDatabase();
        dbReadAccess = sqlBaseModel.getReadableDatabase();
        action = getIntent().getExtras().getString("action");
        setupActionBar(action);
        switch (action) {
            case "Tambah Barang":
                add();
                menu = R.menu.menu_tambah_barang;
                break;
            case "Update Barang":
                update();
                menu = R.menu.menu_update_barang;
                break;
            case "Detail":
                detail();
                menu = R.menu.menu_detail_barang;
                break;
            case "Hapus":
                menu = R.menu.menu_hapus_barang;
                delete();
                break;
            default:
                detail();
        }
        barangPresenter = this;
    }

    public void detail() {
        //idBarang,barcode,namaBarang,description,exp,stok,hargaBeli,hargaJual,Kategori
        TextView idBarang, barcode, namaBarang, description, exp, stok, hargaBeli, hargaJual, kategori;
        setContentView(R.layout.activity_detail_barang);
        idBarang = (TextView) findViewById(R.id.idBarang);
        barcode = (TextView) findViewById(R.id.barcode);
        namaBarang = (TextView) findViewById(R.id.namaBarang);
        description = (TextView) findViewById(R.id.description);
        exp = (TextView) findViewById(R.id.exp);
        stok = (TextView) findViewById(R.id.stok);
        hargaBeli = (TextView) findViewById(R.id.hargaBeli);
        hargaJual = (TextView) findViewById(R.id.hargaJual);
        kategori = (TextView) findViewById(R.id.kategori);
        cursor = dbReadAccess.rawQuery("SELECT * FROM barang WHERE idBarang = '" +
                getIntent().getStringExtra("idBarang") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            idBarang.setText(cursor.getString(0));
            barcode.setText(cursor.getString(1));
            namaBarang.setText(cursor.getString(2));
            description.setText(cursor.getString(3));
            exp.setText(cursor.getString(4));
            stok.setText(cursor.getString(5));
            hargaBeli.setText(cursor.getString(6));
            hargaJual.setText(cursor.getString(7));
            kategori.setText(cursor.getString(8));
        }

    }

    public void add() {
        setContentView(R.layout.activity_add_barang);
        initContentView();
    }


    public void update() {
        setContentView(R.layout.activity_update_barang);
        initContentView();
        cursor = dbReadAccess.rawQuery("SELECT * FROM barang WHERE idBarang = '" +
                getIntent().getStringExtra("idBarang") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            idBarang.setText(cursor.getString(0));
            barcode.setText(cursor.getString(1));
            namaBarang.setText(cursor.getString(2));
            description.setText(cursor.getString(3));
            exp.setText(cursor.getString(4));
            stok.setText(cursor.getString(5));
            hargaBeli.setText(cursor.getString(6));
            hargaJual.setText(cursor.getString(7));
            kategori.setText(cursor.getString(8));
        }
    }

    public void initContentView() {
        idBarang = (EditText) findViewById(R.id.idBarang);
        barcode = (EditText) findViewById(R.id.barcode);
        namaBarang = (EditText) findViewById(R.id.namaBarang);
        description = (EditText) findViewById(R.id.description);
        exp = (EditText) findViewById(R.id.exp);
        stok = (EditText) findViewById(R.id.stok);
        hargaBeli = (EditText) findViewById(R.id.hargaBeli);
        hargaJual = (EditText) findViewById(R.id.hargaJual);
        kategori = (EditText) findViewById(R.id.kategori);
    }

    public void delete() {
        String id = getIntent().getStringExtra("idBarang");
        dbWriteAccess.execSQL("delete from barang where idBarang = '" + id + "'");
        Log.d("Delete Data Barang", "Sukses Id = " + id);
        finish();
        BarangActivity.mainActivity.RefreshList();
    }

    public void delete(String key) {
        dbWriteAccess.execSQL("delete from barang where idBarang = '" + key + "'");
        Log.d("Delete Data Barang", "Sukses Id = " + key);
    }

    private void setupActionBar(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(this.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_add) {
            dbWriteAccess.execSQL("insert into barang (idBarang,barcode,namaBarang,description," +
                    "exp,stok,hargaBeli,hargaJual,Kategori) values('" +
                    idBarang.getText().toString() + "','" +
                    barcode.getText().toString() + "','" +
                    namaBarang.getText().toString() + "','" +
                    description.getText().toString() + "','" +
                    exp.getText().toString() + "','" +
                    stok.getText().toString() + "','" +
                    hargaBeli.getText().toString() + "','" +
                    hargaJual.getText().toString() + "','" +
                    kategori.getText().toString() + "')");
        } else if (id == R.id.action_update_save) {
            dbWriteAccess.execSQL("update barang set barcode='" +
                    barcode.getText().toString() + "', namaBarang='" +
                    namaBarang.getText().toString() + "', description='" +
                    description.getText().toString() + "', exp='" +
                    exp.getText().toString() + "', stok='" +
                    stok.getText().toString() + "', hargaBeli='" +
                    hargaBeli.getText().toString() + "', hargaJual='" +
                    hargaJual.getText().toString() + "', Kategori='" +
                    kategori.getText().toString() + "' where idBarang='" +
                    idBarang.getText().toString() + "'");
        } else if (id == R.id.action_update || id == R.id.action_delete) {
            Intent add;
            Bundle send = new Bundle();

            send.putString("idBarang", getIntent().getStringExtra("idBarang"));
            action = (id == R.id.action_update) ? "Update Barang" : "Hapus";
            send.putString("action", action);

            add = new Intent(this, BarangPresenter.class);
            add.putExtras(send);
            final Intent pass = add;

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder
                    (BarangPresenter.this);
            builder.setTitle("Apakah anda yakin untuk menghapus item ini ?")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(pass);
                            BarangActivity.refresh = true;
                            finish();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
            if (action == "Hapus") {
                builder.show();
            } else {
                startActivity(pass);
            }

            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }

        Toast.makeText(getApplicationContext(), "Berhasil " + action, Toast.LENGTH_LONG).show();
        BarangActivity.mainActivity.RefreshList();
        finish();
        return super.onOptionsItemSelected(item);
    }


}