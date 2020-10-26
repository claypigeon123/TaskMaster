package com.c.taskmaster.listviewprofs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfListViewItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView img;
    private TextView name;
    private TextView exp;
    private TextView gold;

    public ProfListViewItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public ImageView getImg() {
        return img;
    }
    public void setImg(ImageView img) {
        this.img = img;
    }

    public TextView getName() {
        return name;
    }
    public void setName(TextView name) {
        this.name = name;
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
}
