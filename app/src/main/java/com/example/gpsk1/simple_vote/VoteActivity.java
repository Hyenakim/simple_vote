package com.example.gpsk1.simple_vote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VoteActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        tv = findViewById(R.id.result);
        for(int i=0;i<ExampleAdapter.mExampleList.size();i++){
            tv.setText(tv.getText()+" "+ExampleAdapter.mExampleList.get(i).getItemContext());
        }
    }
}
