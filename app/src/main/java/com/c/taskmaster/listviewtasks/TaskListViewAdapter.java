package com.c.taskmaster.listviewtasks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.c.taskmaster.R;

import java.util.List;

public class TaskListViewAdapter extends BaseAdapter {

    private List<TaskListItem> itemList =  null;
    private Context context = null;

    public TaskListViewAdapter(List<TaskListItem> itemList, Context context) {
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
        TaskListViewItemViewHolder viewHolder = null;

        if (convertView != null) {
            viewHolder = (TaskListViewItemViewHolder)convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.list_view_item, null);

            CheckBox listItemCheckbox = convertView.findViewById(R.id.list_view_item_checkbox);
            TextView listItemText = convertView.findViewById(R.id.list_view_item_text);
            TextView listItemDesc = convertView.findViewById(R.id.list_view_item_description);
            TextView listItemExp = convertView.findViewById(R.id.list_view_item_exp);
            TextView listItemGold = convertView.findViewById(R.id.list_view_item_gold);
            ImageView listItemImportanceImg = convertView.findViewById(R.id.list_view_item_importance_img);
            TextView listItemImportanceTxt = convertView.findViewById(R.id.list_view_item_importance_text);

            viewHolder = new TaskListViewItemViewHolder(convertView);
            viewHolder.setCheckbox(listItemCheckbox);
            viewHolder.setText(listItemText);
            viewHolder.setDesc(listItemDesc);
            viewHolder.setExp(listItemExp);
            viewHolder.setGold(listItemGold);
            viewHolder.setImgImportance(listItemImportanceImg);
            viewHolder.setTxtImportance(listItemImportanceTxt);

            convertView.setTag(viewHolder);
        }

        TaskListItem item = itemList.get(position);

        viewHolder.getCheckbox().setChecked(item.isChecked());
        viewHolder.getText().setText(item.getItemText());
        viewHolder.getDesc().setText(item.getItemDesc());
        viewHolder.getExp().setText(item.getItemExp());
        viewHolder.getGold().setText(item.getItemGold());

        viewHolder.getImgImportance().setImageResource(R.drawable.low);
        viewHolder.getTxtImportance().setText("Low");

        if (item.getItemImportance().equals("1")) {
            viewHolder.getImgImportance().setImageResource(R.drawable.med);
            viewHolder.getTxtImportance().setText("Medium");
        } else if (item.getItemImportance().equals("2")) {
            viewHolder.getImgImportance().setImageResource(R.drawable.high);
            viewHolder.getTxtImportance().setText("High");
        }

        return convertView;
    }
}
