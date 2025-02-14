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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class feedback extends AppCompatActivity {

    private ImageView back;

    private Spinner type;

    private EditText des;
    private RatingBar rate;

    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        back = findViewById(R.id.backbutton);
        type = findViewById(R.id.cat);
        rate = findViewById(R.id.rate);
        des = findViewById(R.id.destext);
        sub = findViewById(R.id.feedback_button);

        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        ArrayAdapter<CharSequence> adpter = ArrayAdapter.createFromResource(this, R.array.cat, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        type.setAdapter(adpter);


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
                if (connected) {
                    String cat = type.getSelectedItem().toString(); //takes the data from the edittext , spinners and converts it to string
                    String desc = des.getText().toString();
                    if (!desc.equals("")) {
                        String[] field = new String[5];
                        field[0] = "Email";
                        field[1] = "ID";
                        field[2] = "Rating";
                        field[3] = "cat";
                        field[4] = "feedback";
                        String[] data = new String[5];
                        data[0] = perferences.getString("email", "");
                        data[1] = perferences.getString("ID", "");
                        data[2] = String.valueOf(rate.getRating());
                        data[3] = cat;
                        data[4] = desc;
                        PutData ptData = new PutData("http://192.168.1.35/loginRegister/feedback.php", "POST", field, data);//send info as a new suggestion to the feedback table
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
