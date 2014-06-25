package com.codepath.wangela.apps.twittah.models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;

@Table(name = "Items")
public class Item extends Model {
    // This is how you avoid duplicates
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int remoteId;
    @Column(name = "Name")
    public String name;
    @Column(name = "Category", onUpdate = ForeignKeyAction.CASCADE, onDelete = ForeignKeyAction.CASCADE)
    public Category category;
    
    // Make sure to have a default constructor for every ActiveAndroid model
    public Item(){
       super();
    }
    
    public Item(int remoteId, String name, Category category){
        super();
        this.remoteId = remoteId;
        this.name = name;
        this.category = category;
    }
}