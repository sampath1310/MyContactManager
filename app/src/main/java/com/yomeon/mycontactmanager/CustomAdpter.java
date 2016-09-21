package com.yomeon.mycontactmanager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yomeon.mycontactmanager.datamodel.Datamodel;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sampa on 20-09-2016.
 */

public class CustomAdpter extends BaseAdapter {
    public ArrayList<Object> list;
    Activity activity;
    CustomAdpter(ArrayList<Object> list,Activity activity){
        super();
        this.activity=activity;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
     public static class ViewHolder {
         TextView Name;
         TextView number;
     }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater inflater=activity.getLayoutInflater();
        if(view==null){
        view=inflater.inflate(R.layout.items,null);
            viewHolder = new ViewHolder();
            viewHolder.Name=(TextView)view.findViewById(R.id.item_name);
            viewHolder.number=(TextView)view.findViewById(R.id.item_number);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)view.getTag();
        }
        ListItem bean=(ListItem) list.get(i);
        viewHolder.Name.setText(bean.getName());
        viewHolder.number.setText(bean.getNumber());

        return view;
    }

}
