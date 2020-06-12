/*
 * BrandSalesAdapter.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import net.tajriy.R;

import java.util.ArrayList;

//import android.util.Log;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class BrandSalesAdapter extends ArrayAdapter<BrandSalesModel> {//implements View.OnClickListener {

    private ArrayList<BrandSalesModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView description;
        TextView qty;
        TextView harga;
        TextView subtotal;
//        ImageView info;
    }

    public BrandSalesAdapter(ArrayList<BrandSalesModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

//    @Override
//    public void onClick(View v) {
//        int position = (Integer) v.getTag();
//        Object object = getItem(position);
//        BrandSalesModel dataModel = (BrandSalesModel) object;
//        switch (v.getId()) {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " + dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
//    }

//    private int lastPosition = -1;

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        DataModel dataModel = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        ViewHolder viewHolder; // view lookup cache stored in tag
//
//        final View result;
//
//        if (convertView == null) {
//
//
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.row_item, parent, false);
//            viewHolder.txtName = (TextView) convertView.findViewById(R.id.barcode);
//            viewHolder.txtType = (TextView) convertView.findViewById(R.id.qty);
//            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.hargaJual);
//            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);
//
//            result = convertView;
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result = convertView;
//        }
//
//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;
//
//
//        viewHolder.txtName.setText(dataModel.getBarcode());
//        viewHolder.txtType.setText(dataModel.getQty());
//        viewHolder.txtVersion.setText(dataModel.getHargaJual());
//        viewHolder.info.setOnClickListener(this);
//        viewHolder.info.setTag(position);
//        // Return the completed view to render on screen
//        return convertView;
//    }


}
