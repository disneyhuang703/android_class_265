package com.example.accupass.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by accupass on 16/4/25.
 */
public class OrderAdapter extends BaseAdapter{

    ArrayList<Order> orders;
    LayoutInflater inflater;

    public OrderAdapter(Context context, ArrayList<Order> orders)
    {
        this.inflater = LayoutInflater.from(context);
        this.orders = orders;

    }

    @Override

    //return後方的數量是指顯示的個數
    public int getCount() {
        return orders.size();
    }


    //找出第幾個值再回傳
    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;


        //convertView會變成listView的layout,宣告後要記得轉型別

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_item, null);

            holder = new Holder();

            holder.drinkName = (TextView)convertView.findViewById(R.id.drinkName);
            holder.note = (TextView)convertView.findViewById(R.id.note);

            convertView.setTag(holder);

        }

        else{
            
            holder = (Holder)convertView.getTag();

        }


        holder.drinkName.setText(orders.get(position).drinkName);
        holder.note.setText(orders.get(position).note);

        return convertView;

    }

    class Holder{
        TextView drinkName;
        TextView note;
    }


}
