package com.example.loginnex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class staff_home extends AppCompatActivity {
    private Button idbut;
    private Button schbut;
    private Button chatbut;
    private Button linkbut;
    private TextView wel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_home);
        wel = findViewById(R.id.welcome_stu);
        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String fname = perferences.getString("fname", "");
        String lname = perferences.getString("lname", "");
        String welcome = "Welcome,\n" + fname + " " + lname;
        wel.setText(welcome);//chanes the welcome to have the users name


    }

    public void staffidbut(View view) {
        startActivity(new Intent(staff_home.this, staff_id.class));
    }

    public void hallsbut(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        if (connected) {
            startActivity(new Intent(staff_home.this, halls_act.class));
        } else {
            Toast.makeText((getApplicationContext()), "Internet connection needed", Toast.LENGTH_SHORT).show();
        }
    }

    public void errorbut(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        if (connected) {
            startActivity(new Intent(staff_home.this, error_act.class));
        } else {
            Toast.makeText((getApplicationContext()), "Internet connection needed", Toast.LENGTH_SHORT).show();
        }
    }

    public void attenbut(View view) {
        startActivity(new Intent(staff_home.this, attend_act.class));
    }

    public void logoutbut(View view) {
        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        SharedPreferences.Editor editor = perferences.edit();
        editor.putString("remember", "false");//removes value in the variables saves in the file
        editor.putString("email", "");
        editor.putString("ID", "");
        editor.putString("lname", "");
        editor.putString("fname", "");
        editor.putString("faculty", "");
        editor.putString("year", "");
        editor.putString("phonenum", "");
        editor.putString("Occ", "");
        editor.putString("sec", "");
        editor.putString("pp", "");
        editor.apply();
        startActivity(new Intent(staff_home.this, main_activity.class));
    }
    public void setbut(View view) {
        startActivity(new Intent(staff_home.this , set_act.class));
    }

    public void qabut(View view) {
        startActivity(new Intent(staff_home.this , doc_qa.class));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

