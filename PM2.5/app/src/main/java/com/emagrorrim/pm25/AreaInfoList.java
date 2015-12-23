package com.emagrorrim.pm25;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.emagrorrim.pm25.Area.AreaInfo;
import com.emagrorrim.pm25.PollutionColor.PollutionColor;

/**
 * Created by apple on 15/12/22.
 */
public class AreaInfoList extends BaseAdapter {
    private View[] itemViews;
    private Activity containerView;

    public AreaInfoList(AreaInfo[] areasInfos,Activity containerView) {
        this.containerView = containerView;
        this.itemViews = new View[areasInfos.length];
        for (int i = 0; i < itemViews.length; i++) {
            itemViews[i] = makeItemsView(areasInfos[i].getPositionName(),areasInfos[i].getQuality(),areasInfos[i].getPm25());
        }
    }

    private View makeItemsView(String positionName, String quality, int pm25) {
        LayoutInflater inflater = (LayoutInflater)containerView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.listview_item,null);

        TextView positionTextView = (TextView)item.findViewById(R.id.item_position_name);
        positionTextView.setText(positionName);

        TextView qualityTextView = (TextView)item.findViewById(R.id.item_quality);
        qualityTextView.setText(quality);

        TextView pm25TextView = (TextView)item.findViewById(R.id.item_pm_25);
        pm25TextView.setText(String.valueOf(pm25));

        View view = item.findViewById(R.id.pollution_color_view);
        if (pm25 <= 50)
            view.setBackgroundColor(PollutionColor.pollution0);
        else if (pm25 <= 100)
            view.setBackgroundColor(PollutionColor.pollution1);
        else if (pm25 <= 150)
            view.setBackgroundColor(PollutionColor.pollution2);
        else if (pm25 <= 200)
            view.setBackgroundColor(PollutionColor.pollution3);
        else if (pm25 <= 300)
            view.setBackgroundColor(PollutionColor.pollution4);
        else
            view.setBackgroundColor(PollutionColor.pollution5);
        return item;
    }

    @Override
    public int getCount() {
        return itemViews.length;
    }

    @Override
    public Object getItem(int position) {
        return itemViews[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            return itemViews[position];
        return convertView;
    }
}
