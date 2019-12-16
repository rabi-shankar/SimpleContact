package com.rabi.simplecontacts.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rabi.simplecontacts.R;

public class IntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        final Context context = this;

        if(isFirstTime()){
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }else {
            markAsFirstTime();
        }


        findViewById(R.id.action_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean isFirstTime() {
        SharedPreferences prefs = getSharedPreferences("oneTime", MODE_PRIVATE);
        return prefs.getBoolean("oneTime", false);
    }



    private void markAsFirstTime() {
        SharedPreferences.Editor editor = getSharedPreferences("oneTime", MODE_PRIVATE).edit();
        editor.putBoolean("oneTime", true);
        editor.apply();
    }

}
