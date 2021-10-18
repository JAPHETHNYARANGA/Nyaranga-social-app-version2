package com.moringaschool.nyaranga_social_app_version2.controller.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moringaschool.nyaranga_social_app_version2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactUs extends AppCompatActivity {

    @BindView(R.id.et_to) EditText etTo;
    @BindView(R.id.et_message) EditText etMessage;
    @BindView(R.id.et_subject) EditText etSubject;
    @BindView(R.id.bt_send) Button btSend;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ButterKnife.bind(this);
    etTo.setEnabled(false);
    btSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("mailto:" + etTo.getText().toString()));
            intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
            startActivity(intent);
        }
    });
    }




}