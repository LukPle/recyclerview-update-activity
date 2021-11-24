package com.example.update_activity;

/**
 * The class represents a item.
 * There are also methods for changing the item and the quantity values.
 */
public class Item {

    private String item;
    private String quantity;

    public Item(String item, String quantity) {

        this.item = item;
        this.quantity = quantity;
    }

    public void changeItem(String editedItem) {

        item = editedItem;
    }

    public void changeQuantity(String editedQuantity) {

        quantity = editedQuantity;
    }

    public String getItem() {

        return item;
    }

    public String getQuantity() {

        return quantity;
    }
}
