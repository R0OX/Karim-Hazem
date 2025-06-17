package com.example.loginnex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class set_act extends AppCompatActivity {

    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        btnback = findViewById(R.id.backbutton);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    public void logoutbut(View view) {
        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor = perferences.edit();
        editor.putString("remember", "false");
        editor.apply();
        startActivity(new Intent(set_act.this, main_activity.class));
    }

    public void contbut(View view) {
        startActivity(new Intent(set_act.this, cont_act.class));
    }

    public void credtbut(View view) {
        startActivity(new Intent(set_act.this, cred_act.class));
    }

    public void probut(View view) {
        startActivity(new Intent(set_act.this, prof_act.class));
    }

    public void fedbut(View view) {
        startActivity(new Intent(set_act.this, feedback.class));
    }
}