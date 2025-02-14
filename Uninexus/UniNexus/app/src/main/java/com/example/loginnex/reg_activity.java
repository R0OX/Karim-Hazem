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

public class reg_activity extends AppCompatActivity {
    private EditText email;
    private EditText fname;
    private Spinner Occ;
    private EditText ID;
    private Button regbut;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        email = findViewById(R.id.emailText);
        fname = findViewById(R.id.Fname);
        Occ = findViewById(R.id.hal);
        ID = findViewById(R.id.phonenum);
        regbut = findViewById(R.id.logbutton);
        btnback = findViewById(R.id.backbutton);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        Occ.setAdapter(adapter);

        regbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = Occ.getSelectedItem().toString();
                if (email.length() != 0 && fname.length() != 0 && ID.length() != 0 ) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] check = new String[1];
                            check[0] = "ID";
                            String[] checkd = new String[1];
                            checkd[0] = String.valueOf(ID.getText());
                            if(item.equals("Student")) {
                                PutData putData = new PutData("http://192.168.1.35/loginRegister/check.php", "POST", check, checkd);//checks if users id exists
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[4];
                                            field[0] = "FName";
                                            field[1] = "ID";
                                            field[2] = "Email";
                                            field[3] = "Occ";
                                            String[] data = new String[4];
                                            data[0] = String.valueOf(fname.getText());
                                            data[1] = String.valueOf(ID.getText());
                                            data[2] = String.valueOf(email.getText());
                                            data[3] = item;
                                            PutData ptData = new PutData("http://192.168.1.35/loginRegister/signup.php", "POST", field, data);//send users data
                                            if (ptData.startPut()) {
                                                if (ptData.onComplete()) {
                                                    String reult = ptData.getResult();
                                                    if (reult.equals("Sign Up Success")) {
                                                        String[] fields = new String[1];
                                                        fields[0] = "renum";
                                                        String[] datas = new String[1];
                                                        datas[0] = "renum";
                                                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/requp.php", "POST", fields, datas);//reorganizes table
                                                        if (putDta.startPut()) {
                                                            if (putDta.onComplete()) {
                                                                String reut = putDta.getResult();
                                                            }
                                                        }
                                                        Intent intent = new Intent(getApplicationContext(), req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        } else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                                else if(item.equals("Doctor")) {//the same as the above
                                    PutData puData = new PutData("http://192.168.1.35/loginRegister/check2.php", "POST", check, checkd);
                                    if (puData.startPut()) {
                                        if (puData.onComplete()) {
                                            String result = puData.getResult();
                                            if (result.equals("Sign up Success")) {
                                                String[] field = new String[4];
                                                field[0] = "FName";
                                                field[1] = "ID";
                                                field[2] = "Email";
                                                field[3] = "Occ";
                                                String[] data = new String[4];
                                                data[0] = String.valueOf(fname.getText());
                                                data[1] = String.valueOf(ID.getText());
                                                data[2] = String.valueOf(email.getText());
                                                data[3] = item;
                                                PutData ptData = new PutData("http://192.168.1.35/loginRegister/signup.php", "POST", field, data);
                                                if (ptData.startPut()) {
                                                    if (ptData.onComplete()) {
                                                        String reult = ptData.getResult();
                                                        if (reult.equals("Sign Up Success")) {
                                                            String[] fields = new String[1];
                                                            fields[0] = "renum";
                                                            String[] datas = new String[1];
                                                            datas[0] = "renum";
                                                            PutData putDta = new PutData("http://192.168.1.35/loginRegister/requp.php", "POST", fields, datas);
                                                            if (putDta.startPut()) {
                                                                if (putDta.onComplete()) {
                                                                    String reut = putDta.getResult();
                                                                }
                                                            }
                                                            Intent intent = new Intent(getApplicationContext(), req_act.class);
                                                            startActivity(intent);
                                                            finish();
                                                        } else {
                                                            Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
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
                            else if(item.equals("TA")) {
                                PutData puData = new PutData("http://192.168.1.35/loginRegister/check3.php", "POST", check, checkd);
                                if (puData.startPut()) {
                                    if (puData.onComplete()) {
                                        String result = puData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[4];
                                            field[0] = "FName";
                                            field[1] = "ID";
                                            field[2] = "Email";
                                            field[3] = "Occ";
                                            String[] data = new String[4];
                                            data[0] = String.valueOf(fname.getText());
                                            data[1] = String.valueOf(ID.getText());
                                            data[2] = String.valueOf(email.getText());
                                            data[3] = item;
                                            PutData ptData = new PutData("http://192.168.1.35/loginRegister/signup.php", "POST", field, data);
                                            if (ptData.startPut()) {
                                                if (ptData.onComplete()) {
                                                    String reult = ptData.getResult();
                                                    if (reult.equals("Sign Up Success")) {
                                                        String[] fields = new String[1];
                                                        fields[0] = "renum";
                                                        String[] datas = new String[1];
                                                        datas[0] = "renum";
                                                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/requp.php", "POST", fields, datas);
                                                        if (putDta.startPut()) {
                                                            if (putDta.onComplete()) {
                                                                String reut = putDta.getResult();
                                                            }
                                                        }
                                                        Intent intent = new Intent(getApplicationContext(), req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
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
                            else if(item.equals("Stuff")) {
                                PutData puData = new PutData("http://192.168.1.35/loginRegister/check4.php", "POST", check, checkd);
                                if (puData.startPut()) {
                                    if (puData.onComplete()) {
                                        String result = puData.getResult();
                                        if (result.equals("Sign up Success")) {
                                            String[] field = new String[4];
                                            field[0] = "FName";
                                            field[1] = "ID";
                                            field[2] = "Email";
                                            field[3] = "Occ";
                                            String[] data = new String[4];
                                            data[0] = String.valueOf(fname.getText());
                                            data[1] = String.valueOf(ID.getText());
                                            data[2] = String.valueOf(email.getText());
                                            data[3] = item;
                                            PutData ptData = new PutData("http://192.168.1.35/loginRegister/signup.php", "POST", field, data);
                                            if (ptData.startPut()) {
                                                if (ptData.onComplete()) {
                                                    String reult = ptData.getResult();
                                                    if (reult.equals("Sign Up Success")) {
                                                        String[] fields = new String[1];
                                                        fields[0] = "renum";
                                                        String[] datas = new String[1];
                                                        datas[0] = "renum";
                                                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/requp.php", "POST", fields, datas);
                                                        if (putDta.startPut()) {
                                                            if (putDta.onComplete()) {
                                                                String reut = putDta.getResult();
                                                            }
                                                        }
                                                        Intent intent = new Intent(getApplicationContext(), req_act.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
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
                } else {
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
