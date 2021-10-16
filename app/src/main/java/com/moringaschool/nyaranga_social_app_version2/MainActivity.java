package com.moringaschool.nyaranga_social_app_version2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        loginButton = (TwitterLoginButton)findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken=session.getAuthToken();

                loginMethod(session);
            }



            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void loginMethod(TwitterSession twitterSession){
        String userName = twitterSession.getUserName();
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }
    @Override
    protected void onActityResult(int requestCode, int resultCode, intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}