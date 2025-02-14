package com.example.loginnex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Locale;

public class stu_sch extends AppCompatActivity {
    private ImageView btnback;
    private Button sch;
    private TextView session;
    private TextView time;

    private ImageView next;

    private ImageView prev;

    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_schedule);

        btnback = findViewById(R.id.backbutton);
        sch = findViewById(R.id.down);
        session = findViewById(R.id.session);
        time = findViewById(R.id.time);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        Loading_dialog loadingdialog = new Loading_dialog(stu_sch.this);
        loadingdialog.StartLoadingDialog();
        SharedPreferences perferences = getSharedPreferences("checkbox" ,MODE_PRIVATE);
        String faculty = perferences.getString("faculty","");
        String year = perferences.getString("year","");
        String sec = perferences.getString("sec","");
        String facyear = faculty.toLowerCase(Locale.ROOT)+year+"_schedule";
        String[] times = {"9:00_9:50","9:50_10:40","10:50_11:40","11:40_12:30","1:00_1:50","1:50_2:40","2:50_3:40","3:40_4:30","4:30_5:20"};
        time.setText(times[0]);
        String[] field = new String[3];
        field[0] = "table";
        field[1] = "sec";
        field[2] = "time";
        String[] data = new String[3];
        data[0] = facyear;
        data[1] = sec;
        data[2] =times[0];
        PutData putata = new PutData("http://192.168.1.35/loginRegister/resch.php", "POST", field, data);//gets number of row in the table

        if (putata.startPut()) {
            if (putata.onComplete()) {
                loadingdialog.CancelLoadingDialog();
                String result = putata.getResult();
                session.setText(result);
            }
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();
                if(count<8)
                {
                    count++;
                    time.setText(times[count]);
                    String[] field = new String[3];
                    field[0] = "table";
                    field[1] = "sec";
                    field[2] = "time";
                    String[] data = new String[3];
                    data[0] = facyear;
                    data[1] = sec;
                    data[2] =times[count];
                    PutData putata = new PutData("http://192.168.1.35/loginRegister/resch.php", "POST", field, data);//gets number of row in the table
                    if (putata.startPut()) {
                        if (putata.onComplete()) {
                            String result = putata.getResult();
                            session.setText(result);
                        }
                    }
                }
                loadingdialog.CancelLoadingDialog();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();
                if(count>0)
                {
                    count--;
                    time.setText(times[count]);
                    String[] field = new String[3];
                    field[0] = "table";
                    field[1] = "sec";
                    field[2] = "time";
                    String[] data = new String[3];
                    data[0] = facyear;
                    data[1] = sec;
                    data[2] =times[count];
                    PutData putata = new PutData("http://192.168.1.35/loginRegister/resch.php", "POST", field, data);//gets number of row in the table
                    if (putata.startPut()) {
                        if (putata.onComplete()) {
                            String result = putata.getResult();
                            session.setText(result);
                        }
                    }
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

        sch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.StartLoadingDialog();

                switch (faculty) {
                    case "IT":
                        Uri uri = Uri.parse("https://drive.google.com/file/d/1GbRcD4rbiAG3LxlIocEVSA5WWSi4khFQ/view?usp=sharing");
                        loadingdialog.CancelLoadingDialog();
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case "MECH":
                        Uri urmech = Uri.parse("https://drive.google.com/file/d/1JOJ8wCzOtAvvSyhsT3IzTcFL93GZ2Qzq/view?usp=sharing");
                        loadingdialog.CancelLoadingDialog();
                        startActivity(new Intent(Intent.ACTION_VIEW, urmech));
                        break;
                    case "RE":
                        Uri urre = Uri.parse("https://drive.google.com/file/d/1LpgvoMmHZPC1v20zMdqF_Wzr6vniSNPP/view?usp=sharing");
                        loadingdialog.CancelLoadingDialog();
                        startActivity(new Intent(Intent.ACTION_VIEW, urre));
                        break;
                    case "AUTO":
                        Uri urauto = Uri.parse("https://drive.google.com/file/d/1ufy_O980Ku5-aqknPtgT8dA4p7dhpfdy/view?usp=sharing");
                        loadingdialog.CancelLoadingDialog();
                        startActivity(new Intent(Intent.ACTION_VIEW, urauto));
                        break;
                    case "PETRO":
                        Uri urpetro = Uri.parse("https://drive.google.com/file/d/1VyJ0Re1iCcZlZ6ojLvryRzoQsZLX0oLV/view?usp=sharing");
                        loadingdialog.CancelLoadingDialog();
                        startActivity(new Intent(Intent.ACTION_VIEW, urpetro));
                        break;
                    case "OTH":
                        Uri uroth = Uri.parse("https://drive.google.com/file/d/1OLAl2_L3ttIaTsFGNSo4vqfkCKohvNbV/view?usp=sharing");
                        loadingdialog.CancelLoadingDialog();
                        startActivity(new Intent(Intent.ACTION_VIEW, uroth));
                        break;
                }

            }
        });

    }
}