package com.codepath.wangela.apps.twittah.activities.models;

import java.util.List;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import com.activeandroid.Model;

@Table(name = "Categories")
public class Category extends Model {
    // This is how you avoid duplicates based on a unique ID
    @Column(name = "remote_id", unique = true)
    public int remoteId;
    @Column(name = "Name")
    public String name;

    // Make sure to have a default constructor for every ActiveAndroid model
    public Category(){
       super();
    }

    // Used to return items from another table based on the foreign key
    public List<Item> items() {
        return getMany(Item.class, "Category");
    }
}