package com.example.loginnex;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class stu_qa extends AppCompatActivity {

    private ImageView back;

    private Spinner subj;

    private EditText des;
    private EditText title;

    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_qa);
        back = findViewById(R.id.backbutton);
        subj = findViewById(R.id.halltext);
        title = findViewById(R.id.titletext);
        des = findViewById(R.id.destext);
        sub = findViewById(R.id.submit);

        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String fac = perferences.getString("faculty", "");
        String year = perferences.getString("year", "");
        String facyear = fac+year;
        switch(facyear){
            case "IT2":
                ArrayAdapter<CharSequence> adpter = ArrayAdapter.createFromResource(this, R.array.IT2, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                subj.setAdapter(adpter);
                break;
        }



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
                if (connected) {
                    String cat = subj.getSelectedItem().toString(); //takes the data from the edittext , spinners and converts it to string
                    String desc = des.getText().toString();
                    String titlestring = title.getText().toString();
                    String[] fields = new String[1];
                    fields[0] = "renum";
                    String[] datas = new String[1];
                    datas[0] = cat;
                    String num = "";
                    int qanumber=0;
                    PutData putDta = new PutData("http://192.168.1.4/loginRegister/qastart.php", "POST", fields, datas);
                    if (putDta.startPut()) {
                        if (putDta.onComplete()) {
                            num = putDta.getResult();
                            if(num.equals(""))
                            {
                                qanumber=+1;
                            }
                            else {
                                qanumber = Integer.parseInt(num)+1;
                            }
                        }
                    }
                    if (!desc.equals("")) {
                        String[] field = new String[7];
                        field[0] = "Email";
                        field[1] = "phone";
                        field[2] = "name";
                        field[3] = "title";
                        field[4] = "desc";
                        field[5] = "subj";
                        field[6] = "qnum";
                        String[] data = new String[7];
                        data[0] = perferences.getString("email", "");
                        data[1] = perferences.getString("phonenum", "");
                        data[2] = perferences.getString("fname", "")+" "+perferences.getString("lname", "");
                        data[3] = titlestring;
                        data[4] = desc;
                        data[5] = cat;
                        data[6] = String.valueOf(qanumber);
                        PutData ptData = new PutData("http://192.168.1.4/loginRegister/qasubmit.php", "POST", field, data);//send info as a new suggestion to the feedback table
                        if (ptData.startPut()) {
                            if (ptData.onComplete()) {
                                String result = ptData.getResult();
                                Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    Toast.makeText((getApplicationContext()), "Internet connection needed", Toast.LENGTH_SHORT).show();
                }
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
