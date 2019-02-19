package com.example.gpsk1.simple_vote;

import android.content.res.Resources;
import android.text.Editable;

public class ExampleItem {
    private String item_context;
    private String item_iamgeUri;

    public ExampleItem(){

    }
    public ExampleItem(String context, String image){
        this.item_context = context;
        this.item_iamgeUri = image;
    }

    public void setItemContext(String context){
        this.item_context = context;
    }
    public void setItemImageUri(String image){
        this.item_iamgeUri = image;
    }
    public String getItemContext(){
        return this.item_context;
    }
    public String getItemImageUri(){
        return this.item_iamgeUri;
    }
}
