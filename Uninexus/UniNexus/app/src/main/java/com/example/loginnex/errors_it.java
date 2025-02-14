package com.example.loginnex;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.Arrays;

public class errors_it extends AppCompatActivity {

    private ImageView back;
    private Button go;
    private Spinner hall;
    private TextView halls;
    private TextView errortype;
    private TextView desc;
    private TextView build;
    private TextView done;


    int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error2);

        back = findViewById(R.id.backbutton);
        hall = findViewById(R.id.hal);
        halls = findViewById(R.id.haller);
        go = findViewById(R.id.go);
        errortype = findViewById(R.id.errortype);
        desc = findViewById(R.id.desc);
        build = findViewById(R.id.buliding);
        done = findViewById(R.id.done);

        ArrayList<CharSequence> list = new ArrayList<>();//creates a list to be used a the spinners item
        list.add("");
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "renum";
                String[] data = new String[1];
                data[0] = "item";
                PutData putata = new PutData("http://192.168.1.35/loginRegister/errows.php", "POST", field, data);//counts the rows in the table
                if (putata.startPut()) {
                    if (putata.onComplete()) {
                        String top = putata.getResult();
                        max = Integer.parseInt(top);
                    }
                }
                String[] hals = new String[max];

                for (int i = 1; i <= max; i++) {
                    int row = i;
                    String[] fields = new String[1];
                    fields[0] = "ID";
                    String[] datas = new String[1];
                    datas[0] = String.valueOf(row);
                    PutData putdata = new PutData("http://192.168.1.35/loginRegister/errhall.php", "POST", fields, datas);//gets the halls with errors
                    if (putdata.startPut()) {
                        if (putdata.onComplete()) {
                            String item = putdata.getResult();
                            if(!Arrays.asList(hals).contains(item))//checks if this hall was added to the spinner before
                            {
                                hals[i-1] = item;
                                list.add(hals[i-1]);
                            }
                        }
                    }
                }
            }
        });
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);//adds the halls to the spinner
        hall.setAdapter(adapter);





        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hal = hall.getSelectedItem().toString();//gets the selected hall and converts it to string
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        halls.setText("Hall "+hal);
                        String[] field = new String[1];
                        field[0] = "hall";
                        String[] data = new String[1];
                        data[0] = hal;
                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/errortype.php", "POST",field,data);//gets the error type
                        if (putDta.startPut()) {
                            if (putDta.onComplete()) {
                                String reslt = putDta.getResult();
                                errortype.setText("Error type: " + reslt);

                            }
                        }
                        PutData ptDta = new PutData("http://192.168.1.35/loginRegister/errordes.php", "POST",field,data);//gets the error description
                        if (ptDta.startPut()) {
                            if (ptDta.onComplete()) {
                                String reslt = ptDta.getResult();
                                desc.setText("Description:\n" + reslt);

                            }
                        }
                        PutData putata = new PutData("http://192.168.1.35/loginRegister/errbuild.php", "POST",field,data);//gets the halls building
                        if (putata.startPut()) {
                            if (putata.onComplete()) {
                                String reslt = putata.getResult();
                                build.setText("Building " + reslt);
                            }
                        }
                    }
                });
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hal = hall.getSelectedItem().toString();
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[1];
                        field[0] = "hall";
                        String[] data = new String[1];
                        data[0] = hal;
                        PutData putDta = new PutData("http://192.168.1.35/loginRegister/errdone.php", "POST",field,data);//delete selected hall from the table
                        if (putDta.startPut()) {
                            if (putDta.onComplete()) {
                                String reslt = putDta.getResult();
                                Toast.makeText((getApplicationContext()), reslt, Toast.LENGTH_SHORT).show();
                            }
                        }
                        PutData ptDta = new PutData("http://192.168.1.35/loginRegister/errup.php", "POST", field, data);//reorganizes the table
                        if (ptDta.startPut()) {
                            if (ptDta.onComplete()) {
                                String reut = ptDta.getResult();
                            }
                        }
                    }
                });
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
