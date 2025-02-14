package com.example.loginnex;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class user_request extends AppCompatActivity {

    private ImageView back;
    private ImageView accept;
    private ImageView deny;
    private Button next;
    private Button pervious;
    private TextView name;
    private TextView ID;
    private TextView email;
    private TextView occ;

    int renum = 1;
    int max = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests);
        back = findViewById(R.id.backbutton);
        accept = findViewById(R.id.button);
        deny = findViewById(R.id.button2);
        next = findViewById(R.id.next);
        pervious = findViewById(R.id.per);
        name = findViewById(R.id.stuname);
        ID = findViewById(R.id.studid);
        email = findViewById(R.id.stuem);
        occ = findViewById(R.id.stuocc);
        Loading_dialog loadingdialog = new Loading_dialog(user_request.this);


        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                loadingdialog.StartLoadingDialog();
                String[] field = new String[1];
                field[0] = "renum";
                String[] data = new String[1];
                data[0] = String.valueOf(renum);//equal to 1 to get the first row in table
                PutData putata = new PutData("http://192.168.1.35/loginRegister/reqrows.php", "POST", field, data);//gets number of row in the table
                if (putata.startPut()) {
                    if (putata.onComplete()) {
                        String top = putata.getResult();
                        max = Integer.parseInt(top);
                    }
                }
                PutData ptData = new PutData("http://192.168.1.35/loginRegister/reqname.php", "POST", field, data);//gets the name of the user
                if (ptData.startPut()) {
                    if (ptData.onComplete()) {
                        String reult = ptData.getResult();
                        if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Request submited");
                        }
                    }
                }
                PutData putData = new PutData("http://192.168.1.35/loginRegister/reqID.php", "POST", field, data);//gets the ID of the  user
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String reuslt = putData.getResult();
                        ID.setText(reuslt);
                    }
                }
                PutData puData = new PutData("http://192.168.1.35/loginRegister/reqemail.php", "POST", field, data);//gets the email of the user
                if (puData.startPut()) {
                    if (puData.onComplete()) {
                        String reslt = puData.getResult();
                        email.setText(reslt);
                    }
                }
                PutData utData = new PutData("http://192.168.1.35/loginRegister/reqocc.php", "POST", field, data);//gets the occupation  of the user
                if (utData.startPut()) {
                    if (utData.onComplete()) {
                        String reslt = utData.getResult();
                        occ.setText(reslt);
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
                        String[] field = new String[1];
                        field[0] = "renum";
                        String[] data = new String[1];
                        data[0] = String.valueOf(renum);
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/reqname.php", "POST", field, data);
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Request submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/reqID.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                ID.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/reqemail.php", "POST", field, data);
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                String reslt = puData.getResult();
                                email.setText(reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/reqocc.php", "POST", field, data);
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                occ.setText(reslt);
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
                        String[] field = new String[1];
                        field[0] = "renum";
                        String[] data = new String[1];
                        data[0] = String.valueOf(renum);
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/reqname.php", "POST", field, data);
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Request submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/reqID.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                ID.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/reqemail.php", "POST", field, data);
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                String reslt = puData.getResult();
                                email.setText(reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/reqocc.php", "POST", field, data);
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                occ.setText(reslt);
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
                        String[] field = new String[1];
                        field[0] = "renum";
                        String[] data = new String[1];
                        data[0] = String.valueOf(renum);
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/reqacc.php", "POST", field, data);//updates the user permission to 1 to be able to access the app and deletes him from the table
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(reult.equals("accepted")) {
                                    Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                            }
                        }
                        PutData putata = new PutData("http://192.168.1.35/loginRegister/reqrows.php", "POST", field, data);//gets the new count of the rows
                        if (putata.startPut()) {
                            if (putata.onComplete()) {
                                String top = putata.getResult();
                                max = Integer.parseInt(top);
                            }
                        }
                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/requp.php", "POST", field, data);//reorganize the table
                        if (putDta.startPut()) {
                            if (putDta.onComplete()) {
                                String reut = putDta.getResult();

                            }
                        }
                        PutData ptDta = new PutData("http://192.168.1.35/loginRegister/reqname.php", "POST", field, data);//gets a new users info like the above
                        if (ptDta.startPut()) {
                            if (ptDta.onComplete()) {
                                String reult = ptDta.getResult();
                                if(!reult.equals("")) {
                            if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Request submited");
                        }
                        }
                        else {
                            name.setText("No Request submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/reqID.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                ID.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/reqemail.php", "POST", field, data);
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                String reslt = puData.getResult();
                                email.setText(reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/reqocc.php", "POST", field, data);
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                occ.setText(reslt);
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
                        String[] field = new String[1];
                        field[0] = "renum";
                        String[] data = new String[1];
                        data[0] = String.valueOf(renum);
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/reqdeny.php", "POST", field, data);//denies the users access to the app and deletes him from the table
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String reult = ptData.getResult();
                                if(reult.equals("denied")) {
                                    Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText((getApplicationContext()), reult, Toast.LENGTH_SHORT).show();
                            }
                        }
                        PutData putata = new PutData("http://192.168.1.35/loginRegister/reqrows.php", "POST", field, data);//counts the rows
                        if (putata.startPut()) {
                            if (putata.onComplete()) {
                                String top = putata.getResult();
                                max = Integer.parseInt(top);
                            }
                        }
                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/requp.php", "POST", field, data);//reorganize the table
                        if (putDta.startPut()) {
                            if (putDta.onComplete()) {
                                String reut = putDta.getResult();
                            }
                        }
                        PutData ptDta = new PutData("http://192.168.1.35/loginRegister/reqname.php", "POST", field, data);//gets a new user info
                        if (ptDta.startPut()) {
                            if (ptDta.onComplete()) {
                                String reult = ptDta.getResult();
                                if(!reult.equals("")) {
                            name.setText(reult);
                        }
                        else {
                            name.setText("No Request submited");
                        }
                            }
                        }
                        PutData putData = new PutData("http://192.168.1.35/loginRegister/reqID.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String reuslt = putData.getResult();
                                ID.setText(reuslt);
                            }
                        }
                        PutData puData = new PutData("http://192.168.1.35/loginRegister/reqemail.php", "POST", field, data);
                        if (puData.startPut()) {
                            if (puData.onComplete()) {
                                String reslt = puData.getResult();
                                email.setText(reslt);
                            }
                        }
                        PutData utData = new PutData("http://192.168.1.35/loginRegister/reqocc.php", "POST", field, data);
                        if (utData.startPut()) {
                            if (utData.onComplete()) {
                                String reslt = utData.getResult();
                                occ.setText(reslt);
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

