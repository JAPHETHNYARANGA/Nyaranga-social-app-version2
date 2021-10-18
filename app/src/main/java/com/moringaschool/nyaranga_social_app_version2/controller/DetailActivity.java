package com.moringaschool.nyaranga_social_app_version2.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringaschool.nyaranga_social_app_version2.R;

public class DetailActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button btn = (Button)findViewById(R.id.search_repo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}