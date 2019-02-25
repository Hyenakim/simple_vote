package com.example.gpsk1.simple_vote;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText title;

    private Button finish;
    private RadioButton text;
    private RadioButton date;

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter; //브릿지 역할 (뷰 <-> 데이터)
    private RecyclerView.Adapter mVoteAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button addList;

    private CheckedTextView time;
    private CheckedTextView multi;
    private CheckedTextView anonymous;
    private CheckedTextView permitadd;

    private int id;

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
                intent.putExtra("title",title.getText().toString());
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
        id = mExampleList.size();
        mExampleList.add(position, new ExampleItem(id));
        mAdapter.notifyItemInserted(position);
        mVoteAdapter.notifyItemInserted(position);
    }
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(0));
        mExampleList.add(new ExampleItem(1));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.vote_context);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mVoteAdapter = new VoteAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            //이미지 로드 취소
            return;
        }
        //이미지 저장
        Log.i("main",String.valueOf(requestCode));
        mExampleList.get(requestCode).setItemImageUri(data.getData().toString());
        //이미지 받아서 띄우기

        //mAdapter.notifyDataSetChanged();
        //mVoteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        mVoteAdapter.notifyDataSetChanged();
    }
}
