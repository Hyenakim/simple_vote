package com.example.gpsk1.simple_vote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.gpsk1.simple_vote.ExampleAdapter.mExampleList;

public class VoteActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        intent = getIntent();

        buildRecyclerView();
        textView = findViewById(R.id.title);
        textView.setText(intent.getStringExtra("title"));
    }


    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.vote);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new VoteAdapter(mExampleList);
        Log.i("VoteActivity",String.valueOf(mExampleList.size()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
