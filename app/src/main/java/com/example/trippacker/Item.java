package com.example.trippacker;

import java.io.Serializable;

public class Item  {
    String itemName;
    boolean packed;
    int bag;

    public Item(String name, boolean packed, int bag) {
        itemName=name;
        this.packed=packed;
        this.bag=bag;
    }

    @Override
    public String toString() {
        return this.itemName;
    }
}
