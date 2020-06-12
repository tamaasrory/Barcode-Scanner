/*
 * BarangActivity.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import net.tajriy.R;
import net.tajriy.model.BrandAdapter;
import net.tajriy.model.BrandModel;
import net.tajriy.model.SqlBaseModel;
import net.tajriy.presenter.BarangPresenter;
import net.tajriy.view.BarangView;

import java.util.ArrayList;

public class BarangActivity extends AppCompatActivity implements BarangView {
    public String[] daftar;
    public ListView listViewBarang;
    protected Cursor cursor;
    public SqlBaseModel dbcenter;
    public static BarangActivity mainActivity;
    public Intent intentActivity;
    public Bundle shareValue = new Bundle();
    int menu;
    public static boolean refresh = false;
    public static Menu objMenu;
    public ArrayList<BrandModel> brandModels;
    public BrandAdapter brandAdapter;
    public static Bundle tmpBarangToDelete = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_barang);
        intentActivity = new Intent(this, BarangPresenter.class);
        menu = R.menu.menu_barang;
        mainActivity = this;
        dbcenter = new SqlBaseModel(this);
        RefreshList();
    }

    public void RefreshList() {
        brandModels = new ArrayList<BrandModel>();
        cursor = dbcenter.select("SELECT * FROM barang");
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int index = 0; index < cursor.getCount(); index++) {
            cursor.moveToPosition(index);
            brandModels.add(new BrandModel(
                    cursor.getString(cursor.getColumnIndex("idBarang")),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8)
            ));
            Log.d("", cursor.getString(1));
        }

        listViewBarang = (ListView) findViewById(R.id.listViewBarang);
        brandAdapter = new BrandAdapter(brandModels, getApplicationContext());
        listViewBarang.setAdapter(brandAdapter);
        ((ArrayAdapter) listViewBarang.getAdapter()).notifyDataSetInvalidated();

        listViewBarang.setOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        shareValue.putString("action", "Detail");
                        shareValue.putString("idBarang", brandModels.get(position).getIdBarang());
                        intentActivity.putExtras(shareValue);
                        startActivity(intentActivity);
                    }
                }
        );

        listViewBarang.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        final String selection = brandModels.get(position).getIdBarang();
                        final CharSequence[] dialogitem = {"Ubah", "Hapus"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(BarangActivity.this);
                        builder.setTitle("Pilihan");
                        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        shareValue.putString("action", "Update Barang");
                                        break;
                                    case 1:
                                        shareValue.putString("action", "Hapus");
                                        break;
                                }
                                shareValue.putString("idBarang", selection);
                                intentActivity.putExtras(shareValue);
                                startActivity(intentActivity);
                            }
                        });
                        builder.create().show();
                        return true;
                    }
                }
        );
    }


    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        objMenu = menu;
        getMenuInflater().inflate(this.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            shareValue.putString("action", "Tambah Barang");
        } else if (id == R.id.account_search) {
            shareValue.putString("action", "search");
        } else if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_cancel) {
            BarangActivity.mainActivity.RefreshList();

            BrandAdapter.a.dismiss();
            BrandAdapter.arrayList = new ArrayList<>();
            return true;
        }

        intentActivity.putExtras(shareValue);
        startActivity(intentActivity);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (refresh) {
            BarangActivity.mainActivity.RefreshList();
            refresh = false;
        }

        Log.d("On Resume RefreshList","BarangActivity.mainActivity.RefreshList();");
    }

    @Override
    public void onSaveSuccess(String string) {

    }

    @Override
    public void onSaveError(String string) {

    }

    @Override
    public void runActivity(Intent intent) {
        startActivity(intent);
    }
}