/*
 * MenuAdapter.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.tajriy.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<MenuModel> menuList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, counter;
        public LinearLayout color;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            counter = (TextView) view.findViewById(R.id.count);
            color = (LinearLayout) view.findViewById(R.id.color);
        }
    }


    public MenuAdapter(List<MenuModel> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MenuModel menu = menuList.get(position);
        holder.title.setText(menu.getMenuTitle());
        holder.counter.setText(menu.getCounter());
        holder.color.setBackgroundResource(menu.getColor());
        holder.color.setOnClickListener(menu.getOnClickListener());
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
