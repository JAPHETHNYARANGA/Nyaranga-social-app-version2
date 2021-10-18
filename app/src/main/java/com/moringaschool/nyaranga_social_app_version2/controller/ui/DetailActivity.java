package com.moringaschool.nyaranga_social_app_version2.controller.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.moringaschool.nyaranga_social_app_version2.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.search_repo) Button btn;
    @BindView(R.id.contact_us) Button contact;



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.image1);

        Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtk570HKSLfdmQRKnVqujHuI63zpyZDnnCJg&usqp=CAU")
                .into(imageView);


        ImageView imageView1 = findViewById(R.id.image2);

        Picasso.get()
                .load("https://cdn.thenewstack.io/media/2021/09/48affeb3-github-new-user-page-1024x562.png")
                .into(imageView1);


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });
    }


}