package com.c.taskmaster.listviewtasks;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListViewItemViewHolder extends RecyclerView.ViewHolder {

    private CheckBox checkbox;
    private TextView text;
    private TextView desc;
    private TextView exp;
    private TextView gold;
    private ImageView imgImportance;
    private TextView txtImportance;

    public TaskListViewItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }
    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public TextView getText() {
        return text;
    }
    public void setText(TextView text) {
        this.text = text;
    }

    public TextView getDesc() {
        return desc;
    }
    public void setDesc(TextView desc) {
        this.desc = desc;
    }

    public TextView getExp() {
        return exp;
    }
    public void setExp(TextView exp) {
        this.exp = exp;
    }

    public TextView getGold() {
        return gold;
    }
    public void setGold(TextView gold) {
        this.gold = gold;
    }

    public ImageView getImgImportance() {
        return imgImportance;
    }
    public void setImgImportance(ImageView imgImportance) {
        this.imgImportance = imgImportance;
    }

    public TextView getTxtImportance() {
        return txtImportance;
    }
    public void setTxtImportance(TextView txtImportance) {
        this.txtImportance = txtImportance;
    }
}
