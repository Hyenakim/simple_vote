package com.example.gpsk1.simple_vote;

import android.content.res.Resources;
import android.text.Editable;

public class ExampleItem {
    private String item_context;
    private String item_iamgeUri;
    private int id;

    public ExampleItem(){

    }
    public ExampleItem(int id){
        this.item_context = null;
        this.item_iamgeUri = null;
        this.id = id;
    }
    public ExampleItem(String context, String image, int id){
        this.item_context = context;
        this.item_iamgeUri = image;
        this.id = id;
    }

    public void setItemContext(String context){
        this.item_context = context;
    }
    public void setItemImageUri(String image){
        this.item_iamgeUri = image;
    }
    public void setId(int id){this.id = id;}
    public String getItemContext(){
        if(this.item_context == null)
            return null;
        return this.item_context;
    }
    public String getItemImageUri(){
        if(this.item_iamgeUri == null)
            return null;
        return this.item_iamgeUri;
    }
    public int getId() {return this.id;}
}
