package com.c.taskmaster.listviewtasks;

public class TaskListItem {
    private boolean checked = false;
    private String id = "";
    private String itemText = "";
    private String itemDesc = "";
    private String itemExp = "";
    private String itemGold = "";
    private String itemImportance = "";

    public TaskListItem(boolean checked, String id, String itemText, String itemDesc, String itemExp, String itemGold, String itemImportance) {
        this.checked = checked;
        this.id = id;
        this.itemText = itemText;
        this.itemDesc = itemDesc;
        this.itemExp = itemExp;
        this.itemGold = itemGold;
        this.itemImportance = itemImportance;
    }

    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getItemText() {
        return itemText;
    }
    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public String getItemDesc() {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemExp() {
        return itemExp;
    }
    public void setItemExp(String itemExp) {
        this.itemExp = itemExp;
    }

    public String getItemGold() {
        return itemGold;
    }
    public void setItemGold(String itemGold) {
        this.itemGold = itemGold;
    }

    public String getItemImportance() {
        return itemImportance;
    }
    public void setItemImportance(String itemImportance) {
        this.itemImportance = itemImportance;
    }
}
