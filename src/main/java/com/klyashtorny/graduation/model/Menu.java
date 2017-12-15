package com.klyashtorny.graduation.model;

import java.util.Date;

public class Menu extends AbstractNamedEntity {

    private int price;

    private Date registered = new Date();

    public Menu() {
    }

    public Menu(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
