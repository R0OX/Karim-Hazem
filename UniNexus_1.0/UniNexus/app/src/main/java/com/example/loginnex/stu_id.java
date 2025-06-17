package com.example.loginnex;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class stu_id extends AppCompatActivity {
    private ImageView btnback;
    private ImageView pp;
    private ImageView qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_stu);
        pp = findViewById(R.id.profilepic);
        btnback = findViewById(R.id.backbutton);
        qrcode = findViewById(R.id.QR);
        Loading_dialog loadingdialog = new Loading_dialog(stu_id.this);
        loadingdialog.StartLoadingDialog();
        SharedPreferences perferences = getSharedPreferences("checkbox" ,MODE_PRIVATE);
        String ID = perferences.getString("ID","");
        String photo = perferences.getString("pp","");
        if (!photo.equals("")) {
            byte[] imageBytes = Base64.decode(photo, Base64.DEFAULT);
            InputStream prop = new ByteArrayInputStream(imageBytes);
            Bitmap image = BitmapFactory.decodeStream(prop);
            pp.setImageBitmap(image);
        }

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(ID.toString(), BarcodeFormat.QR_CODE, 300,300);

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);//creates the students id

            qrcode.setImageBitmap(bitmap);

        }catch (WriterException e){
            throw new RuntimeException(e);
        }
        loadingdialog.CancelLoadingDialog();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
