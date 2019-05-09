package com.example.leo.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txt_timer,txt_result,txt_right_answer;
    Button btn_filter_total,btn_filter_right_answer,btn_filter_wrong_answer,btn_filter_no_answer;
    RecyclerView recycler_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("RESULT");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txt_result = (TextView)findViewById(R.id.txt_result);
        txt_right_answer = (TextView)findViewById(R.id.txt_right_answer);
        txt_timer = (TextView)findViewById(R.id.txt_timer);

        btn_filter_no_answer = (Button)findViewById(R.id.btn_filter_no_answer);
        btn_filter_right_answer = (Button)findViewById(R.id.btn_filter_right_answer);
        btn_filter_wrong_answer = (Button)findViewById(R.id.btn_filter_wrong_answer);
        btn_filter_total = (Button)findViewById(R.id.btn_filter_total);

        recycler_result = (RecyclerView)findViewById(R.id.recycler_result);
        recycler_result.setHasFixedSize(true);
        recycler_result.setLayoutManager(new GridLayoutManager(this,3));

    }
}
