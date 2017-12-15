package com.klyashtorny.graduation.model;

import java.util.Date;

public class Restaurant extends AbstractNamedEntity {

    private String Address;

    private Date registered = new Date();

    public Restaurant() {
    }

    public Restaurant(String address, Date registered) {
        Address = address;
        this.registered = registered;
    }

    public Restaurant(Integer id, String name, String address, Date registered) {
        super(id, name);
        Address = address;
        this.registered = registered;
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
