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
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.vote_title) EditText title;
    @BindView(R.id.vote_finish) Button finish;
    @BindView(R.id.select_vote_type) RadioGroup group;
    @BindView(R.id.vote_text) RadioButton text;
    @BindView(R.id.vote_date) RadioButton date;
    @BindView(R.id.add_context) Button addList;
    @BindView(R.id.vote_time) CheckedTextView time;
    @BindView(R.id.vote_multi) CheckedTextView multi;
    @BindView(R.id.vote_anonymous) CheckedTextView anonymous;
    @BindView(R.id.vote_permitAdd) CheckedTextView permitadd;

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter; //브릿지 역할 (뷰 <-> 데이터)
    private RecyclerView.Adapter mVoteAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int id;
    private boolean check[];
    private int checkDrawable[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
        ButterKnife.bind(this);

        check = new boolean[4];
        checkDrawable = new int[2];
        checkDrawable[0] = R.drawable.selectoption;
        checkDrawable[1] = R.drawable.selectedoption;

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

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView view = (CheckedTextView)v;
                view.toggle();
                if(check[0]==false)
                    check[0] = true;
                else
                    check[0] = false;
                if(view.isChecked() && check[0]==true)
                    time.setCheckMarkDrawable(checkDrawable[1]);
                else if(!view.isChecked() && check[0]==false)
                    time.setCheckMarkDrawable(checkDrawable[0]);
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView view = (CheckedTextView)v;
                view.toggle();
                if(check[1]==false)
                    check[1] = true;
                else
                    check[1] = false;
                if(view.isChecked() && check[1]==true)
                    multi.setCheckMarkDrawable(checkDrawable[1]);
                else if(!view.isChecked() && check[1]==false)
                    multi.setCheckMarkDrawable(checkDrawable[0]);
            }
        });
        anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView view = (CheckedTextView)v;
                view.toggle();
                if(check[2]==false)
                    check[2] = true;
                else
                    check[2] = false;
                if(view.isChecked() && check[2]==true)
                    anonymous.setCheckMarkDrawable(checkDrawable[1]);
                else if(!view.isChecked() && check[2]==false)
                    anonymous.setCheckMarkDrawable(checkDrawable[0]);
            }
        });
        permitadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView view = (CheckedTextView)v;
                view.toggle();
                if(check[3]==false)
                    check[3] = true;
                else
                    check[3] = false;
                if(view.isChecked() && check[3]==true)
                    permitadd.setCheckMarkDrawable(checkDrawable[1]);
                else if(!view.isChecked() && check[3]==false)
                    permitadd.setCheckMarkDrawable(checkDrawable[0]);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        mVoteAdapter.notifyDataSetChanged();
    }
}
