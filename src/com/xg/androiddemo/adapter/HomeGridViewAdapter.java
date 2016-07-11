package com.xg.androiddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xg.androiddemo.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xugang on 16/7/11.
 */
public class HomeGridViewAdapter extends BaseAdapter {

    private Context context = null;
    private LayoutInflater layoutInflater = null;
    private List<HashMap<String,Object>> dataList = null;

    public HomeGridViewAdapter(Context context,List<HashMap<String,Object>> dataList){
        this.context = context;
        this.dataList = dataList;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return this.dataList != null ? this.dataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return this.dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            convertView = this.layoutInflater.inflate(R.layout.tab_grid_item,null);

        }
        TextView text = (TextView)convertView.findViewById(R.id.tab_grid_item_text);
        ImageView image = (ImageView)convertView.findViewById(R.id.tab_grid_item_iamge);

        HashMap<String,Object> item = dataList.get(position);
        text.setText((String)item.get("menu_text"));
        image.setImageResource((Integer) item.get("menu_image"));




        return convertView;
    }
}
