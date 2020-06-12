/*
 * BrandAdapter.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import net.tajriy.R;
import net.tajriy.activity.BarangActivity;
import net.tajriy.presenter.BarangPresenter;

import java.util.ArrayList;

import static net.tajriy.activity.BarangActivity.objMenu;

public class BrandAdapter extends ArrayAdapter<BrandModel> {

    private ArrayList<BrandModel> dataSet;
    private Context mContext;
    private int lastPosition = -1;

    // View lookup cache
    private static class ViewHolder {
        CheckedTextView checkedTextView;
        TextView namaBarang;
        TextView exp;
        TextView stok;
        TextView hargaJual;
    }

    public BrandAdapter(ArrayList<BrandModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    private boolean visibleMenu = false;
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static Snackbar a;

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        BrandModel brandModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            System.out.println("init convert");
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.checkedTextView = (CheckedTextView) convertView.findViewById(R.id.checkedtextview);
            viewHolder.namaBarang = (TextView) convertView.findViewById(R.id.namaBarang);
            viewHolder.exp = (TextView) convertView.findViewById(R.id.exp);
            viewHolder.hargaJual = (TextView) convertView.findViewById(R.id.hargaJual);
            viewHolder.stok = (TextView) convertView.findViewById(R.id.stok);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            System.out.println("result");
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(
                mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        assert brandModel != null;
        viewHolder.namaBarang.setText(brandModel.getNamaBarang());
        viewHolder.exp.setText(brandModel.getExp());
        viewHolder.hargaJual.setText(brandModel.getHargaJual());
        viewHolder.stok.setText(brandModel.getStok());
        viewHolder.checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Object object = getItem(position);
                BrandModel brandModel = (BrandModel) object;
                assert brandModel != null;

                if (!viewHolder.checkedTextView.isChecked()) {
                    System.out.println("checked");
                    viewHolder.checkedTextView.setChecked(true);
                    arrayList.add(brandModel.getIdBarang());
                    System.out.println("item terpilih :: " + v.getTag() + " " +
                            "\njumlah :: " + arrayList.size());

                } else {
                    System.out.println("Unchecked");
                    viewHolder.checkedTextView.setChecked(false);
                    arrayList.remove(brandModel.getIdBarang());
                    System.out.println("item terpilih :: " + v.getTag() + " " +
                            "\njumlah :: " + arrayList.size());
                }

                if (arrayList.size() > 0 & !visibleMenu) {
                    System.out.println("test :: " + arrayList.size());
                    visibleMenu = true;
                    objMenu.getItem(0).setVisible(false);
                    objMenu.getItem(1).setVisible(false);
                    objMenu.getItem(2).setVisible(true);
                } else if (arrayList.size() == 0) {
                    visibleMenu = false;
                    objMenu.getItem(0).setVisible(true);
                    objMenu.getItem(1).setVisible(true);
                    objMenu.getItem(2).setVisible(false);
                }

                a = Snackbar.make(v, arrayList.size() + " item terpilih",
                        arrayList.size() > 0 ? Snackbar.LENGTH_INDEFINITE :
                                Snackbar.LENGTH_SHORT);
                a.setAction("Hapus", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder
                                (BarangActivity.mainActivity);
                        builder.setTitle("Apakah anda yakin untuk menghapus " +
                                arrayList.size()
                                + " item ini ?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.out.println("hapus :: " + arrayList.size() + " item " + arrayList.get(0) + " terpilih");

                                        for (int i = 0; i < arrayList.size(); i++) {
                                            BarangPresenter.barangPresenter.delete(arrayList.get(i));
                                        }

                                        visibleMenu = false;
                                        objMenu.getItem(0).setVisible(true);
                                        objMenu.getItem(1).setVisible(true);
                                        objMenu.getItem(2).setVisible(false);
                                        BarangActivity.mainActivity.RefreshList();
                                        arrayList = new ArrayList<String>();
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        a.show();
                                    }
                                }).create().show();
                    }
                });
                a.show();

            }
        });
        viewHolder.checkedTextView.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


}
