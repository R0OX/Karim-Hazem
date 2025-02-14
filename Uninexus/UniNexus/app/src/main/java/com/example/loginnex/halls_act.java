package com.example.loginnex;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class halls_act extends AppCompatActivity {

    private Spinner halls;
    private Button gobut;
    private ImageView btnback;
    private TextView hallname;
    private TextView hallbuilding;
    private TextView hallav;
    private TextView hallerror;
    private TextView errorde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halls);
        btnback = findViewById(R.id.backbutton);
        gobut = findViewById(R.id.Gobut);
        halls = findViewById(R.id.hal);
        hallname = findViewById(R.id.hall);
        hallbuilding = findViewById(R.id.buliding);
        hallav = findViewById(R.id.aval);
        hallerror = findViewById(R.id.ertype);
        errorde = findViewById(R.id.erde);
        Loading_dialog loadingdialog = new Loading_dialog(halls_act.this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Halls, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        halls.setAdapter(adapter);

        gobut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = halls.getSelectedItem().toString();
                loadingdialog.StartLoadingDialog();
                String[] field= new String[1];
                field[0] = "hall";
                String[] data = new String[1];
                data[0] = item;
                hallname.setText("Hall "+item);
                PutData ptDta = new PutData("http://192.168.1.35/loginRegister/hallbuild.php", "POST",field,data);//gets halls buildings
                if (ptDta.startPut()) {
                    if (ptDta.onComplete()) {
                        String reult = ptDta.getResult();
                        hallbuilding.setText("Building "+reult);

                    }
                }
                PutData putData = new PutData("http://192.168.1.35/loginRegister/hallava.php", "POST",field,data);//gets halls availability
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if(result.equals("1"))           //sees if the hall have people in it
                        {
                            hallav.setText("Full");
                            hallav.setTextColor(Color.parseColor("#FF0000"));
                        }
                        if(result.equals("0"))          //sees if the hall doesn't have people in it
                        {
                            hallav.setText("Empty");
                            hallav.setTextColor(Color.parseColor("#00FF38"));
                        }
                    }
                    loadingdialog.CancelLoadingDialog();
                }
                PutData putDta = new PutData("http://192.168.1.35/loginRegister/errortype.php", "POST",field,data);     //gets the error type of the hall if it existed
                if (putDta.startPut()) {
                    if (putDta.onComplete()) {
                        String reslt = putDta.getResult();
                        if(reslt.equals(""))
                        {
                            hallerror.setText("Error type: No Error");
                        }
                        else {
                            hallerror.setText("Error type: " + reslt);
                        }
                    }
                }
                PutData utDta = new PutData("http://192.168.1.35/loginRegister/errordes.php", "POST",field,data);       //gets the error description of the hall if it existed
                if (utDta.startPut()) {
                    if (utDta.onComplete()) {
                        String relt = utDta.getResult();
                        errorde.setText("Description:\n "+relt);
                    }
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