package com.whdev.garagem.activits;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.whdev.garagem.ConfigActivity;
import com.whdev.garagem.R;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;
    private Handler handler;
    public static SharedPreferences pref;
    private Button btnConfigurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(getResources().getString(R.string.CONSUMER_KEY),
                getResources().getString(R.string.CONSUMER_SECRET));
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_login);
        pref = getApplicationContext().getSharedPreferences("Mentos", 0);

        btnConfigurar = (Button) findViewById(R.id.btn_cnfigure);
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ConfigActivity.class));
            }
        });

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(LoginActivity.this, "Bem Vindo " + result.data.getUserName() + " !!!", Toast.LENGTH_SHORT).show();
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this, MapsActivity.class));
                    }
                }, 1000);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}