package com.example.accupass.simpleui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by accupass on 16/4/25.
 */
public class OrderAdapter extends BaseAdapter{
//把資料存入inflater裡面，並讓layout長的像我們要的樣子
    List<Order> orders;
    LayoutInflater inflater;

    public OrderAdapter(Context context, List<Order> orders)
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


    //
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;


        //convertView會變成listView的layout,宣告後要記得轉型別

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_item, null);
        //用holder抓住值來避免一直使用findViewbyId（佔資源）
            holder = new Holder();

            holder.drinkName = (TextView)convertView.findViewById(R.id.drinkName);
            holder.note = (TextView)convertView.findViewById(R.id.note);
            holder.storeInfo = (TextView)convertView.findViewById(R.id.store);


            convertView.setTag(holder);

        }

        else{

            holder = (Holder)convertView.getTag();

        }

        //拿到holder後，我們再就holder中的component來做設定
        //從order class中去拿各個呼叫的值，最後再把convertView的內容return回去

        holder.drinkName.setText(orders.get(position).getDrinkName());
        holder.note.setText(orders.get(position).getNote());
        holder.storeInfo.setText(orders.get(position).getStoreInfo());

        return convertView;

    }

    class Holder{
        TextView drinkName;
        TextView note;
        TextView storeInfo;
    }


}
