package com.c.taskmaster.listviewprofs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.c.taskmaster.R;
import com.c.taskmaster.level.LevelCalculator;

import java.util.List;

public class ProfListViewAdapter extends BaseAdapter {

    private List<ProfListItem> itemList =  null;
    private Context context = null;

    public ProfListViewAdapter(List<ProfListItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        int cnt = 0;
        if (itemList != null) {
            cnt = itemList.size();
        }
        return cnt;
    }

    @Override
    public Object getItem(int position) {
        Object ret = null;
        if (itemList != null) {
            ret = itemList.get(position);
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProfListViewItemViewHolder viewHolder = null;

        if (convertView != null) {
            viewHolder = (ProfListViewItemViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.list_view_prof_item, null);

            ImageView listItemImg = convertView.findViewById(R.id.profPic);
            TextView listItemName = convertView.findViewById(R.id.profName);
            TextView listItemExp = convertView.findViewById(R.id.profExp);
            TextView listItemGold = convertView.findViewById(R.id.profGold);

            viewHolder = new ProfListViewItemViewHolder(convertView);
            viewHolder.setName(listItemName);
            viewHolder.setImg(listItemImg);
            viewHolder.setExp(listItemExp);
            viewHolder.setGold(listItemGold);

            convertView.setTag(viewHolder);
        }

        ProfListItem item = itemList.get(position);

        viewHolder.getName().setText(item.getItemName());
        viewHolder.getImg().setImageResource(getImageResource(Integer.parseInt(item.getItemPic())));
        viewHolder.getExp().setText("Level " + LevelCalculator.calculateLevel(Integer.parseInt(item.getItemExp())).toString());
        viewHolder.getGold().setText(item.getItemGold());

        return convertView;
    }

    private int getImageResource(int id) {
        switch (id) {
            case 1:
                return R.drawable.char1;
            case 2:
                return R.drawable.char2;
            case 3:
                return R.drawable.char3;
            case 4:
                return R.drawable.char4;
            case 5:
                return R.drawable.char5;
            case 6:
                return R.drawable.char6;
            case 7:
                return R.drawable.char7;
            case 8:
                return R.drawable.char8;
            case 9:
                return R.drawable.char9;
            case 10:
                return R.drawable.char10;
            case 11:
                return R.drawable.char11;
            case 12:
                return R.drawable.char12;
            case 13:
                return R.drawable.char13;
            case 14:
                return R.drawable.char14;
            default:
                return -1;
        }
    }
}
