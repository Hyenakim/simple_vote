package com.example.gpsk1.simple_vote;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText title;

    private Button finish;
    private RadioButton text;
    private RadioButton date;

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter; //브릿지 역할 (뷰 <-> 데이터)
    private RecyclerView.LayoutManager mLayoutManager;
    private Button addList;

    private CheckedTextView time;
    private CheckedTextView multi;
    private CheckedTextView anonymous;
    private CheckedTextView permitadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
        title = findViewById(R.id.vote_title);
        finish = findViewById(R.id.vote_finish);
        addList = findViewById(R.id.add_context);

        finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VoteActivity.class);
                startActivity(intent);
            }
        });
        addList.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position = mExampleList.size();
                insertItem(position);
            }
        });

    }

    public void insertItem(int position){
        mExampleList.add(position, new ExampleItem());
        mAdapter.notifyItemInserted(position);
    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem());
        mExampleList.add(new ExampleItem());
        mExampleList.add(new ExampleItem());
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.vote_context);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
