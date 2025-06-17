package com.example.loginnex;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.ArrayList;

public class attend_act extends AppCompatActivity{

    private ImageView back;
    private Spinner time;
    private Spinner sub;
    private Spinner mode;
    private Button start;
    private ImageView qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);
        back = findViewById(R.id.backbutton);
        time = findViewById(R.id.frame1);
        qrcode = findViewById(R.id.qr);
        sub = findViewById(R.id.frame2);
        mode = findViewById(R.id.frame3);
        start = findViewById(R.id.logbutton);

        SharedPreferences perferences = getSharedPreferences("checkbox" ,MODE_PRIVATE);
        String subj = perferences.getString("sub","");
        String occ = perferences.getString("Occ","");

        ArrayList<CharSequence> list = new ArrayList<>();
        list.add(subj);//adds the users sub to the sun spinner
        ArrayAdapter<CharSequence> adpter = new ArrayAdapter<CharSequence>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        sub.setAdapter(adpter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        time.setAdapter(adapter);

        ArrayAdapter<CharSequence> adaptr = ArrayAdapter.createFromResource(this, R.array.scan, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        mode.setAdapter(adaptr);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                String clock= time.getSelectedItem().toString();
                String sbj= sub.getSelectedItem().toString();
                String mde= mode.getSelectedItem().toString();
                String qr ="";
                if (occ.equals("TA"))
                {
                    qr = sbj+","+clock+",sec"+","+mde;//qr code for the sections
                }
                if (occ.equals("Doctor"))
                {
                    qr = sbj+","+clock+",lec"+","+mde;// qr code for the lectures
                }

                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(qr.toString(), BarcodeFormat.QR_CODE, 300, 300);//encode text to qr code

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);//creates the qr code

                    qrcode.setImageBitmap(bitmap);//changes image to the new qr

                } catch (WriterException e) {
                    throw new RuntimeException(e);
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

