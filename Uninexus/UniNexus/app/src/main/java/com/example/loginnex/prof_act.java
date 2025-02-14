package com.example.loginnex;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class prof_act extends AppCompatActivity{
    private ImageView back;
    private ImageView pp;
    private TextView name;
    private TextView mail;
    private TextView phone;
    private TextView occ;
    private TextView fac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_act);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        back = findViewById(R.id.backbutton);
        occ = findViewById(R.id.occ);
        fac = findViewById(R.id.fac);
        pp = findViewById(R.id.profile);


        SharedPreferences perferences = getSharedPreferences("checkbox" ,MODE_PRIVATE);
        String fname = perferences.getString("fname","");
        String lname = perferences.getString("lname","");
        String email_ = perferences.getString("email","");
        String phone_ = perferences.getString("phonenum","");
        String occ_ = perferences.getString("Occ","");
        String fac_ = perferences.getString("faculty","");
        String id_ = perferences.getString("ID","");
        String photo = perferences.getString("pp","");
        String name_ = fname +" "+ lname;
        String case_ = fac_ +" - "+ id_;
        if (!photo.equals("")) {
            byte[] imageBytes = Base64.decode(photo, Base64.DEFAULT);
            InputStream prop = new ByteArrayInputStream(imageBytes);
            Bitmap image = BitmapFactory.decodeStream(prop);
            pp.setImageBitmap(image);
        }
        name.setText(name_);
        mail.setText(email_);
        phone.setText(phone_);
        occ.setText(occ_);

        if (occ_.equals("Student")){
            fac.setText(case_);
        }else {
            fac.setText(id_);
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}

