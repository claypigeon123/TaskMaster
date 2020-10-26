package com.c.taskmaster.listviewprofs;

public class ProfListItem {
    private String id = "";
    private String itemName = "";
    private String itemExp = "";
    private String itemGold = "";
    private String itemPic = "";

    public ProfListItem(String id, String itemName, String itemExp, String itemGold, String itemPic) {
        this.id = id;
        this.itemName = itemName;
        this.itemExp = itemExp;
        this.itemGold = itemGold;
        this.itemPic = itemPic;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getItemPic() {
        return itemPic;
    }
    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }
}
