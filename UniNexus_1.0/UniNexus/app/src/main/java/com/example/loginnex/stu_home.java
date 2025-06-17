package com.example.loginnex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class stu_home extends AppCompatActivity{

    private TextView wel;

    private ImageView pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_home);
        wel = findViewById(R.id.welcome_stu);
        pp = findViewById(R.id.profile);


        SharedPreferences perferences = getSharedPreferences("checkbox" ,MODE_PRIVATE);
        String fname = perferences.getString("fname","");
        String lname = perferences.getString("lname","");
        String photo = perferences.getString("pp","");
        String welcome = "Welcome,\n" + fname +" "+ lname;
        if (!photo.equals("")) {
            byte[] imageBytes = Base64.decode(photo, Base64.DEFAULT);
            InputStream prop = new ByteArrayInputStream(imageBytes);
            Bitmap image = BitmapFactory.decodeStream(prop);
            pp.setImageBitmap(image);
        }
        wel.setText(welcome);
    }


    public void idbut(View view) {
        startActivity(new Intent(stu_home.this , stu_id.class));
    }
    public void schbut(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        if (connected) {
            startActivity(new Intent(stu_home.this, stu_sch.class));
        } else {
            Toast.makeText((getApplicationContext()), "Internet connection needed", Toast.LENGTH_SHORT).show();
        }
    }
    public void linkbut(View view) {
        startActivity(new Intent(stu_home.this , stu_link.class));
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
        startActivity(new Intent(stu_home.this, main_activity.class));
    }

    public void setbut(View view) {
        startActivity(new Intent(stu_home.this , set_act.class));
    }

    public void qabut(View view) {
        startActivity(new Intent(stu_home.this , stu_qa.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

