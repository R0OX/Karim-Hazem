package com.example.loginnex;

import android.content.Context;
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

public class error_act extends AppCompatActivity {

    private ImageView back;

    private Spinner hall;

    private Spinner type;

    private Spinner build;

    private EditText des;

    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
        back = findViewById(R.id.backbutton);
        hall = findViewById(R.id.halltext);
        type = findViewById(R.id.errortext);
        build = findViewById(R.id.buildtext);
        des = findViewById(R.id.destext);
        sub = findViewById(R.id.submit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Halls, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        hall.setAdapter(adapter);
        ArrayAdapter<CharSequence> adpter = ArrayAdapter.createFromResource(this, R.array.error, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        type.setAdapter(adpter);
        ArrayAdapter<CharSequence> adaptr = ArrayAdapter.createFromResource(this, R.array.Build, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        build.setAdapter(adaptr);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
                if (connected) {
                    String name = hall.getSelectedItem().toString();//takes the data from the edittext , spinners and conerts it to string
                    String error = type.getSelectedItem().toString();
                    String bild = build.getSelectedItem().toString();
                    String desc = des.getText().toString();
                    String[] field = new String[4];
                    field[0] = "hall";
                    field[1] = "error";
                    field[2] = "build";
                    field[3] = "desc";
                    String[] data = new String[4];
                    data[0] = name;
                    data[1] = error;
                    data[2] = bild;
                    data[3] = desc;
                    PutData ptData = new PutData("http://192.168.1.4/loginRegister/errorsub.php", "POST", field, data);//send info as a new error to the error table
                    if (ptData.startPut()) {
                        if (ptData.onComplete()) {
                            String result = ptData.getResult();
                            Toast.makeText((getApplicationContext()), result, Toast.LENGTH_SHORT).show();
                        }
                    }
                    PutData ptDta = new PutData("http://192.168.1.4/loginRegister/errup.php", "POST", field, data);//reorganizes the errors table
                    if (ptDta.startPut()) {
                        if (ptDta.onComplete()) {
                            String reut = ptDta.getResult();
                        }
                    }
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

