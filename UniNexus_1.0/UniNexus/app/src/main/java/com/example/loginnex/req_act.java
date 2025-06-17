package com.example.loginnex;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;




public class req_act extends AppCompatActivity {

    private Button reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.req_act);

        reg = findViewById(R.id.regbutton);


    }

    public void reg(View view) {
        startActivity(new Intent(req_act.this, main_activity.class));
    }
}
