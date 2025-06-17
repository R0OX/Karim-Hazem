package com.example.loginnex;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class login_activity extends AppCompatActivity {

    private EditText Email;
    private EditText Pass;
    private Button logbut;
    private ImageView btnback;
    private TextView forget;
    private Spinner Occ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.emailText);
        Pass = findViewById(R.id.passText);
        logbut = findViewById(R.id.logbutton);
        btnback = findViewById(R.id.backbutton);
        forget = findViewById(R.id.click);
        Occ = findViewById(R.id.hal);
        Loading_dialog loadingdialog = new Loading_dialog(login_activity.this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        Occ.setAdapter(adapter);


        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email, password;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Pass.getText());
                String item = Occ.getSelectedItem().toString();
                loadingdialog.StartLoadingDialog();
                if (!email.equals("") && !password.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;
                            if (item.equals("Student")) {
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/login.php", "POST", field, data);//sees if the info is in the table
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Login Success")) {
                                            String[] redata = new String[1];
                                            redata[0] = "email";
                                            String[] redata2 = new String[1];
                                            redata2[0] = email;
                                            PutData reID = new PutData("http://192.168.1.4/loginRegister/reID.php", "POST", redata, redata2);
                                            if (reID.startPut()) {
                                                if (reID.onComplete()) {
                                                    String ID = reID.getResult();
                                                    PutData refname = new PutData("http://192.168.1.4/loginRegister/refname.php", "POST", redata, redata2);
                                                    if (refname.startPut()) {
                                                        if (refname.onComplete()) {
                                                            String fname = refname.getResult();
                                                            PutData relname = new PutData("http://192.168.1.4/loginRegister/relname.php", "POST", redata, redata2);
                                                            if (relname.startPut()) {
                                                                if (relname.onComplete()) {
                                                                    String lname = relname.getResult();
                                                                    PutData refaculty = new PutData("http://192.168.1.4/loginRegister/refaculty.php", "POST", redata, redata2);
                                                                    if (refaculty.startPut()) {
                                                                        if (refaculty.onComplete()) {
                                                                            String faculty = refaculty.getResult();
                                                                            PutData reyear = new PutData("http://192.168.1.4/loginRegister/reyear.php", "POST", redata, redata2);
                                                                            if (reyear.startPut()) {
                                                                                if (reyear.onComplete()) {
                                                                                    String year = reyear.getResult();
                                                                                    PutData rephone = new PutData("http://192.168.1.4/loginRegister/rephone.php", "POST", redata, redata2);
                                                                                    if (rephone.startPut()) {
                                                                                        if (rephone.onComplete()) {
                                                                                            String phone = rephone.getResult();
                                                                                            PutData resec = new PutData("http://192.168.1.4/loginRegister/resec.php", "POST", redata, redata2);
                                                                                            if (resec.startPut()) {
                                                                                                if (resec.onComplete()) {
                                                                                                    String sec = resec.getResult();
                                                                                                    PutData repp = new PutData("http://192.168.1.4/loginRegister/rephoto.php", "POST", redata, redata2);
                                                                                                    if (repp.startPut()) {
                                                                                                        if (repp.onComplete()) {
                                                                                                            String photo = repp.getResult();
                                                                                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                                                                                            SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);//private file
                                                                                                            SharedPreferences.Editor editor = perferences.edit();
                                                                                                            editor.putString("remember", "true");//puts value in the variables saves in the file
                                                                                                            editor.putString("email", email);
                                                                                                            editor.putString("ID", ID);
                                                                                                            editor.putString("lname", lname);
                                                                                                            editor.putString("fname", fname);
                                                                                                            editor.putString("faculty", faculty);
                                                                                                            editor.putString("year", year);
                                                                                                            editor.putString("phonenum", phone);
                                                                                                            editor.putString("Occ", item);
                                                                                                            editor.putString("sec", sec);
                                                                                                            editor.putString("pp", photo);
                                                                                                            editor.apply();
                                                                                                            loadingdialog.CancelLoadingDialog();
                                                                                                            Intent intent = new Intent(getApplicationContext(), stu_home.class);
                                                                                                            startActivity(intent);
                                                                                                            finish();
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else if (item.equals("Doctor")) {//the same as the above
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/login2.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Login Success")) {
                                            String[] redata = new String[1];
                                            redata[0] = "email";
                                            String[] redata2 = new String[1];
                                            redata2[0] = email;
                                            PutData reID = new PutData("http://192.168.1.4/loginRegister/reID2.php", "POST", redata, redata2);
                                            if (reID.startPut()) {
                                                if (reID.onComplete()) {
                                                    String ID = reID.getResult();
                                                    PutData refname = new PutData("http://192.168.1.4/loginRegister/refname2.php", "POST", redata, redata2);
                                                    if (refname.startPut()) {
                                                        if (refname.onComplete()) {
                                                            String fname = refname.getResult();
                                                            PutData relname = new PutData("http://192.168.1.4/loginRegister/relname2.php", "POST", redata, redata2);
                                                            if (relname.startPut()) {
                                                                if (relname.onComplete()) {
                                                                    String lname = relname.getResult();
                                                                    PutData rephone = new PutData("http://192.168.1.4/loginRegister/rephone2.php", "POST", redata, redata2);
                                                                    if (rephone.startPut()) {
                                                                        if (rephone.onComplete()) {
                                                                            String phone = rephone.getResult();
                                                                            PutData resub = new PutData("http://192.168.1.4/loginRegister/resub.php", "POST", redata, redata2);
                                                                            if (resub.startPut()) {
                                                                                if (resub.onComplete()) {
                                                                                    String resb = resub.getResult();
                                                                                    Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                                                                    SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                                                                    SharedPreferences.Editor editor = perferences.edit();
                                                                                    editor.putString("remember", "true");
                                                                                    editor.putString("email", email);
                                                                                    editor.putString("ID", ID);
                                                                                    editor.putString("lname", lname);
                                                                                    editor.putString("fname", fname);
                                                                                    editor.putString("phonenum", phone);
                                                                                    editor.putString("sub", resb);
                                                                                    editor.putString("Occ", item);
                                                                                    editor.apply();
                                                                                    loadingdialog.CancelLoadingDialog();
                                                                                    Intent intent = new Intent(getApplicationContext(), staff_home.class);
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else if (item.equals("TA")) {
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/login3.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Login Success")) {
                                            String[] redata = new String[1];
                                            redata[0] = "email";
                                            String[] redata2 = new String[1];
                                            redata2[0] = email;
                                            PutData reID = new PutData("http://192.168.1.4/loginRegister/reID3.php", "POST", redata, redata2);
                                            if (reID.startPut()) {
                                                if (reID.onComplete()) {
                                                    String ID = reID.getResult();
                                                    PutData refname = new PutData("http://192.168.1.4/loginRegister/refname3.php", "POST", redata, redata2);
                                                    if (refname.startPut()) {
                                                        if (refname.onComplete()) {
                                                            String fname = refname.getResult();
                                                            PutData relname = new PutData("http://192.168.1.4/loginRegister/relname3.php", "POST", redata, redata2);
                                                            if (relname.startPut()) {
                                                                if (relname.onComplete()) {
                                                                    String lname = relname.getResult();
                                                                    PutData rephone = new PutData("http://192.168.1.4/loginRegister/rephone2.php", "POST", redata, redata2);
                                                                    if (rephone.startPut()) {
                                                                        if (rephone.onComplete()) {
                                                                            String phone = rephone.getResult();
                                                                            PutData resub = new PutData("http://192.168.1.4/loginRegister/resub2.php", "POST", redata, redata2);
                                                                            if (resub.startPut()) {
                                                                                if (resub.onComplete()) {
                                                                                    String resb = resub.getResult();
                                                                                    Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                                                                    SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                                                                    SharedPreferences.Editor editor = perferences.edit();
                                                                                    editor.putString("remember", "true");
                                                                                    editor.putString("email", email);
                                                                                    editor.putString("ID", ID);
                                                                                    editor.putString("lname", lname);
                                                                                    editor.putString("fname", fname);
                                                                                    editor.putString("phonenum", phone);
                                                                                    editor.putString("sub", resb);
                                                                                    editor.putString("Occ", item);
                                                                                    editor.apply();
                                                                                    loadingdialog.CancelLoadingDialog();
                                                                                    Intent intent = new Intent(getApplicationContext(), staff_home.class);
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else if (item.equals("Staff")) {
                                PutData putData = new PutData("http://192.168.1.4/loginRegister/login4.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Login Success")) {
                                            String[] redata = new String[1];
                                            redata[0] = "email";
                                            String[] redata2 = new String[1];
                                            redata2[0] = email;
                                            PutData reID = new PutData("http://192.168.1.4/loginRegister/reID4.php", "POST", redata, redata2);
                                            if (reID.startPut()) {
                                                if (reID.onComplete()) {
                                                    String ID = reID.getResult();
                                                    PutData refname = new PutData("http://192.168.1.4/loginRegister/refname4.php", "POST", redata, redata2);
                                                    if (refname.startPut()) {
                                                        if (refname.onComplete()) {
                                                            String fname = refname.getResult();
                                                            PutData relname = new PutData("http://192.168.1.4/loginRegister/relname4.php", "POST", redata, redata2);
                                                            if (relname.startPut()) {
                                                                if (relname.onComplete()) {
                                                                    String lname = relname.getResult();
                                                                    PutData rephone = new PutData("http://192.168.1.4/loginRegister/rephone2.php", "POST", redata, redata2);
                                                                    if (rephone.startPut()) {
                                                                        if (rephone.onComplete()) {
                                                                            String phone = rephone.getResult();
                                                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                                                            SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                                                            SharedPreferences.Editor editor = perferences.edit();
                                                                            editor.putString("remember", "true");
                                                                            editor.putString("email", email);
                                                                            editor.putString("ID", ID);
                                                                            editor.putString("lname", lname);
                                                                            editor.putString("fname", fname);
                                                                            editor.putString("phonenum", phone);
                                                                            editor.putString("Occ", item);
                                                                            editor.apply();
                                                                            loadingdialog.CancelLoadingDialog();
                                                                            Intent intent = new Intent(getApplicationContext(), it_home.class);
                                                                            startActivity(intent);
                                                                            finish();
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            loadingdialog.CancelLoadingDialog();
                                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    });
                } else {
                    loadingdialog.CancelLoadingDialog();
                    Toast.makeText((getApplicationContext()), "ALL fields required", Toast.LENGTH_SHORT).show();
                }
                loadingdialog.CancelLoadingDialog();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_activity.this, forget_pass.class));
            }
        });

        Pass.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                } else {
                    Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.show, 0);
                }
                isPasswordVisible = !isPasswordVisible;
                Pass.setSelection(Pass.getText().length());
            }
        });
    }
}