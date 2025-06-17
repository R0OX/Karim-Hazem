package com.example.loginnex;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class staff_id extends AppCompatActivity {
    private ImageView btnback;
    private ImageView qrcode;
    private Button punchmode;
    private TextView punchtext;
    private  View idcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_staff);

        btnback = findViewById(R.id.backbutton);
        qrcode = findViewById(R.id.QR);
        punchmode = findViewById(R.id.submit);
        punchtext = findViewById(R.id.buliding);
        idcard = findViewById(R.id.idcard);
        Loading_dialog loadingdialog = new Loading_dialog(staff_id.this);
        loadingdialog.StartLoadingDialog();
        SharedPreferences perferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String ID = perferences.getString("ID", "");
        String qr;
        final String[] mode = {",punchin"}; //the mode of qr code
        qr =ID + mode[0];//the id and the mode
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(qr.toString(), BarcodeFormat.QR_CODE, 300, 300);//encodes the qr code

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);//creates the qr

            qrcode.setImageBitmap(bitmap);//changes the image to the qr code

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        loadingdialog.CancelLoadingDialog();


        punchmode.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 loadingdialog.StartLoadingDialog();
                 if (mode[0].equals(",punchin")) {//sees if the mode is in punch in
                     String qr;
                     mode[0] =",punchout"; //changes the mode into punchouy
                     qr =ID + mode[0];//the new qr code
                     punchtext.setText("Punch Out");//changes the apperance
                     punchmode.setText("Punch In");
                     punchmode.setBackgroundResource(R.drawable.punchoutgrad);
                     idcard.setBackgroundResource(R.drawable.idpurple);
                     try {
                         BitMatrix bitMatrix = multiFormatWriter.encode(qr.toString(), BarcodeFormat.QR_CODE, 300, 300);

                         BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                         Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                         qrcode.setImageBitmap(bitmap);

                     } catch (WriterException e) {
                         throw new RuntimeException(e);
                     }
                 }
                 else if (mode[0].equals(",punchout")){//the same as the above but in reverse
                     String qr;
                     mode[0] =",punchin";
                     qr =ID + mode[0];
                     punchtext.setText("Punch In");
                     punchmode.setText("Punch Out");
                     punchmode.setBackgroundResource(R.drawable.punchingrad);
                     idcard.setBackgroundResource(R.drawable.idblue);
                     try {
                         BitMatrix bitMatrix = multiFormatWriter.encode(qr.toString(), BarcodeFormat.QR_CODE, 300, 300);

                         BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                         Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                         qrcode.setImageBitmap(bitmap);

                     } catch (WriterException e) {
                         throw new RuntimeException(e);
                     }
                 }
                    loadingdialog.CancelLoadingDialog();
             }
         }
        );

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
