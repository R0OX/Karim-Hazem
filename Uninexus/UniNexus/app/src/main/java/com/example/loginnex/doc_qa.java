package com.example.loginnex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class doc_qa extends AppCompatActivity {

    private ImageView back;
    private TextView accept;
    private TextView deny;
    private Button next;
    private Button pervious;
    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView title;
    private TextView des;

    int renum = 1;
    int max = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_qa);
        back = findViewById(R.id.backbutton);
        accept = findViewById(R.id.respond);
        deny = findViewById(R.id.decline);
        next = findViewById(R.id.next);
        pervious = findViewById(R.id.per);
        name = findViewById(R.id.stuname);
        phone = findViewById(R.id.stuphone);
        email = findViewById(R.id.stuem);
        title = findViewById(R.id.title);
        des = findViewById(R.id.desc);
        Loading_dialog loadingdialog = new Loading_dialog(doc_qa.this);
        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String sub = perferences.getString("sub", "");
        String[] reslt = {""};
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                loadingdialog.StartLoadingDialog();
                String[] field = new String[2];
                field[0] = "renum";
                field[1] = "sub";
                String[] data = new String[2];
                data[0] = String.valueOf(renum);//equal to 1 to get the first row in table for subject
                data[1] = sub;
                PutData putata = new PutData("http://192.168.1.35/loginRegister/qarows.php", "POST", field, data);//gets number of row in the table
                if (putata.startPut()) {
                    if (putata.onComplete()) {
                        String top = putata.getResult();
                        max = Integer.parseInt(top);
                    }
                }
                PutData ptData = new PutData("http://192.168.1.35/loginRegister/qaname.php", "POST", field, data);//gets the name of the user
                if (ptData.startPut()) {
                    if (ptData.onComplete()) {
                        String reult = ptData.getResult();
                        if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Question submited");
                        }
                    }
                }
                PutData putData = new PutData("http://192.168.1.35/loginRegister/qaphone.php", "POST", field, data);//gets the ID of the  user
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String reuslt = putData.getResult();
                        phone.setText(reuslt);
                    }
                }
                PutData puData = new PutData("http://192.168.1.35/loginRegister/qaemail.php", "POST", field, data);//gets the email of the user
                if (puData.startPut()) {
                    if (puData.onComplete()) {
                        reslt[0] = puData.getResult();
                        email.setText(reslt[0]);
                    }
                }
                PutData puDat = new PutData("http://192.168.1.35/loginRegister/qatitle.php", "POST", field, data);//gets the email of the user
                if (puDat.startPut()) {
                    if (puDat.onComplete()) {
                        String reslt = puDat.getResult();
                        title.setText("Title: "+reslt);
                    }
                }
                PutData utData = new PutData("http://192.168.1.35/loginRegister/qades.php", "POST", field, data);//gets the desupation  of the user
                if (utData.startPut()) {
                    if (utData.onComplete()) {
                        String reslt = utData.getResult();
                        des.setText("Description:\n"+ reslt);
                    }
                }
                loadingdialog.CancelLoadingDialog();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();
                if (renum < max) {
                    renum++; // adds 1 to renum if it's not the last row to go to next row in the table aka the next user
                }
                Handler handler = new Handler();//like the above it gets info about the user to update the app
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "renum";
                        field[1] = "sub";
                        String[] data = new String[2];
                        data[0] = String.valueOf(renum);
                        data[1] = sub;
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/qaname.php", "POST", field, data);//gets the name of the user
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Question submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/qaphone.php", "POST", field, data);//gets the ID of the  user
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                phone.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/qaemail.php", "POST", field, data);//gets the email of the user
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                reslt[0] = puData.getResult();
                                email.setText(reslt[0]);
                            }
                        }
                        PutData puDat = new PutData("http://192.168.1.35/loginRegister/qatitle.php", "POST", field, data);//gets the email of the user
                        if (puDat.startPut()) {
                            if (puDat.onComplete()) {
                                String reslt = puDat.getResult();
                                title.setText("Title: "+reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/qades.php", "POST", field, data);//gets the desupation  of the user
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                des.setText("Description:\n"+ reslt);
                            }
                        }
                    }
                });
                loadingdialog.CancelLoadingDialog();
            }
        });

        pervious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();
                if (renum != 1) {
                    renum--;//decreases by 1 if it's not already equal to 1 to go back one user
                }
                Handler handler = new Handler();//like the above it gets info about the user to update the app
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "renum";
                        field[1] = "sub";
                        String[] data = new String[2];
                        data[0] = String.valueOf(renum);
                        data[1] = sub;
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/qaname.php", "POST", field, data);//gets the name of the user
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Question submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/qaphone.php", "POST", field, data);//gets the ID of the  user
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                phone.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/qaemail.php", "POST", field, data);//gets the email of the user
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                reslt[0]  = puData.getResult();
                                email.setText(reslt[0]);
                            }
                        }
                        PutData puDat = new PutData("http://192.168.1.35/loginRegister/qatitle.php", "POST", field, data);//gets the email of the user
                        if (puDat.startPut()) {
                            if (puDat.onComplete()) {
                                String reslt = puDat.getResult();
                                title.setText("Title: "+reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/qades.php", "POST", field, data);//gets the desupation  of the user
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                des.setText("Description:\n"+ reslt);
                            }
                        }
                    }
                });
                loadingdialog.CancelLoadingDialog();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "renum";
                        field[1] = "sub";
                        String[] data = new String[2];
                        data[0] = String.valueOf(renum);
                        data[1] = sub;
                        Intent mail = new Intent(Intent.ACTION_VIEW);
                        Uri datas = Uri.parse("mailto:"+reslt[0]);
                        mail.setData(datas);
                        startActivity(mail);
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/qaact.php", "POST", field, data);//updates the user permission to 1 to be able to access the app and deletes him from the table
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(reult.equals("accepted")) {
                                    Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                            }
                        }
                        PutData putata = new PutData("http://192.168.1.35/loginRegister/qarows.php", "POST", field, data);//gets number of row in the table
                        if (putata.startPut()) {
                            if (putata.onComplete()) {
                                String top = putata.getResult();
                                max = Integer.parseInt(top);
                            }
                        }
                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/qaup.php", "POST", field, data);//reorganize the table
                        if (putDta.startPut()) {
                            if (putDta.onComplete()) {
                                String reut = putDta.getResult();

                            }
                        }
                        PutData pData = new PutData("http://192.168.1.35/loginRegister/qaname.php", "POST", field, data);//gets the name of the user
                        if (pData.startPut()) {
                            if (pData.onComplete()) {
                                String reult = pData.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Question submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/qaphone.php", "POST", field, data);//gets the ID of the  user
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                phone.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/qaemail.php", "POST", field, data);//gets the email of the user
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                reslt[0]  = puData.getResult();
                                email.setText(reslt[0]);
                            }
                        }
                        PutData puDat = new PutData("http://192.168.1.35/loginRegister/qatitle.php", "POST", field, data);//gets the email of the user
                        if (puDat.startPut()) {
                            if (puDat.onComplete()) {
                                String reslt = puDat.getResult();
                                title.setText("Title: "+reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/qades.php", "POST", field, data);//gets the desupation  of the user
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                des.setText("Description:\n"+ reslt);
                            }
                        }
                    }
                });
                loadingdialog.CancelLoadingDialog();
            }
        });

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        field[0] = "renum";
                        field[1] = "sub";
                        String[] data = new String[2];
                        data[0] = String.valueOf(renum);
                        data[1] = sub;
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/qaact.php", "POST", field, data);//updates the user permission to 1 to be able to access the app and deletes him from the table
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(reult.equals("accepted")) {
                                    Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                            }
                        }
                        PutData putata = new PutData("http://192.168.1.35/loginRegister/qarows.php", "POST", field, data);//gets number of row in the table
                        if (putata.startPut()) {
                            if (putata.onComplete()) {
                                String top = putata.getResult();
                                max = Integer.parseInt(top);
                            }
                        }
                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/qaup.php", "POST", field, data);//reorganize the table
                        if (putDta.startPut()) {
                            if (putDta.onComplete()) {
                                String reut = putDta.getResult();

                            }
                        }
                        PutData pData = new PutData("http://192.168.1.35/loginRegister/qaname.php", "POST", field, data);//gets the name of the user
                        if (pData.startPut()) {
                            if (pData.onComplete()) {
                                String reult = pData.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Question submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/qaphone.php", "POST", field, data);//gets the ID of the  user
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                phone.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/qaemail.php", "POST", field, data);//gets the email of the user
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                reslt[0] = puData.getResult();
                                email.setText(reslt[0]);
                            }
                        }
                        PutData puDat = new PutData("http://192.168.1.35/loginRegister/qatitle.php", "POST", field, data);//gets the email of the user
                        if (puDat.startPut()) {
                            if (puDat.onComplete()) {
                                String reslt = puDat.getResult();
                                title.setText("Title: "+reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/qades.php", "POST", field, data);//gets the desupation  of the user
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                des.setText("Description:\n"+ reslt);
                            }
                        }
                    }
                });
                loadingdialog.CancelLoadingDialog();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

