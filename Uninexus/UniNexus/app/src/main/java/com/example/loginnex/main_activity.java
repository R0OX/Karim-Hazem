package com.example.loginnex;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class main_activity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences perferences = getSharedPreferences("checkbox" ,MODE_PRIVATE);
        String checkbox = perferences.getString("remember","");
        String user = perferences.getString("Occ","");

        if(checkbox.equals("true"))//checks if user was logged in before
        {
            Intent intent;
            if(user.equals("Student")) {
                intent = new Intent(getApplicationContext(), stu_home.class);
                startActivity(intent);
            }
            else if(user.equals("Staff")) {
                intent = new Intent(getApplicationContext(), it_home.class);
                startActivity(intent);
            }
            else if(user.equals("TA")||user.equals("Doctor")) {
                intent = new Intent(getApplicationContext(), staff_home.class);
                startActivity(intent);
            }

        }

    }

    public void login(View view) {
            startActivity(new Intent(main_activity.this , login_activity.class));
    }
    public void reg(View view) {
        startActivity(new Intent(main_activity.this , reg_activity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}


