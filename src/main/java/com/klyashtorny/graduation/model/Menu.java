package com.klyashtorny.graduation.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "menu")
public class Menu extends AbstractNamedEntity {

    private Date registered = new Date();

    public Menu() {
    }

    public Menu(Integer id, String name, int price) {
        super(id, name);
    }

}
