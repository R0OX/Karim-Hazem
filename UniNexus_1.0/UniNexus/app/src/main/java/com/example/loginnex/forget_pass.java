package com.example.loginnex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class forget_pass extends AppCompatActivity {
    private EditText idtext;
    private EditText email;
    private Button subbut;
    private ImageView btnback;
    private Spinner Occ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_act);
        idtext = findViewById(R.id.IDText);
        email = findViewById(R.id.emailtext);
        Occ = findViewById(R.id.hal);
        subbut = findViewById(R.id.sub);
        btnback = findViewById(R.id.backbutton);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        Occ.setAdapter(adapter);

        subbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = Occ.getSelectedItem().toString();
                if (idtext.length() != 0 && email.length() != 0) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] check = new String[1];
                            check[0] = "ID";
                            String[] checkd = new String[1];
                            checkd[0] = String.valueOf(idtext.getText());
                            if(item.equals("Student")){
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/check.php", "POST", check, checkd);//checks if student ID is in the table
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[2];
                                            field[0] = "Email";
                                            field[1] = "ID";
                                            String[] data = new String[2];
                                            data[0] = String.valueOf(email.getText());
                                            data[1] = String.valueOf(idtext.getText());
                                            PutData ptData = new PutData("http://192.168.1.4/loginRegister/forget_password.php","POST",field,data);//send user info to the table
                                            if(ptData.startPut()) {
                                                if(ptData.onComplete()){
                                                    String reult = ptData.getResult();
                                                    if(reult.equals("Request Success")){
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(),req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                    else{
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                        }
                                    }
                            }
                            if(item.equals("Doctor")){
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/check2.php", "POST", check, checkd);//checks if dr ID is in the table
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[2];
                                            field[0] = "Email";
                                            field[1] = "ID";
                                            String[] data = new String[2];
                                            data[0] = String.valueOf(email.getText());
                                            data[1] = String.valueOf(idtext.getText());
                                            PutData ptData = new PutData("http://192.168.1.4/loginRegister/forget_password.php","POST",field,data);
                                            if(ptData.startPut()) {
                                                if(ptData.onComplete()){
                                                    String reult = ptData.getResult();
                                                    if(reult.equals("Request Success")){
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(),req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                    else{
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                            if(item.equals("TA")){
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/check3.php", "POST", check, checkd);//checks if TA ID is in the table
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[2];
                                            field[0] = "Email";
                                            field[1] = "ID";
                                            String[] data = new String[2];
                                            data[0] = String.valueOf(email.getText());
                                            data[1] = String.valueOf(idtext.getText());
                                            PutData ptData = new PutData("http://192.168.1.4/loginRegister/forget_password.php","POST",field,data);
                                            if(ptData.startPut()) {
                                                if(ptData.onComplete()){
                                                    String reult = ptData.getResult();
                                                    if(reult.equals("Request Success")){
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(),req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                    else{
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                            if(item.equals("Stuff")){
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/check4.php", "POST", check, checkd); //checks if IT ID is in the table
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[2];
                                            field[0] = "Email";
                                            field[1] = "ID";
                                            String[] data = new String[2];
                                            data[0] = String.valueOf(email.getText());
                                            data[1] = String.valueOf(idtext.getText());
                                            PutData ptData = new PutData("http://192.168.1.4/loginRegister/forget_password.php","POST",field,data);
                                            if(ptData.startPut()) {
                                                if(ptData.onComplete()){
                                                    String reult = ptData.getResult();
                                                    if(reult.equals("Request Success")){
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(),req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                    else{
                                                        Toast.makeText((getApplicationContext()),reult,Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText((getApplicationContext()), "ALL FIELDS REQUIRED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}