package com.msmi.angkringansaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    int foto_profil = R.drawable.foto_bulat;
    String nim = "1167050107";
    String nama = "Muhammad Said Marzuqi";
    String nomor = "089619449046";
    String alamat = "https://goo.gl/maps/LFaKFz7EJiVsd3RZ7";
    String sosmed = "https://instagram.com/have_fahn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set myDb as DatabaseHelper class
        myDb = new DatabaseHelper(this);

        //Send Data to SQLite
        addDataProfile(nim,nama,nomor,alamat,sosmed,foto_profil);

        //Menampilkan data
        viewAll();
    }

    //add data profile
    public void addDataProfile(String nim, String nama, String nomor, String alamat, String sosmed,int foto){;
        myDb.insertData(nim,nama,nomor,alamat,sosmed,foto);
    }

    //View Data Profile
    public void viewAll() {
        TextView mData = (TextView) findViewById(R.id.data);
        TextView mNama = (TextView) findViewById(R.id.nama);
        TextView mNomor = (TextView) findViewById(R.id.nomor);
        TextView mAlamat = (TextView) findViewById(R.id.alamat);
        TextView mSosmed = (TextView) findViewById(R.id.sosmed);
        ImageView mFoto = (ImageView) findViewById(R.id.foto);
        Cursor res = myDb.getAllData();

        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            buffer.append(res.getString(0)+",");
            buffer.append(res.getString(1)+",");
            buffer.append(res.getString(2)+",");
            buffer.append(res.getString(3)+",");
            buffer.append(res.getString(4)+",");
            buffer.append(res.getInt(5));
        }
        mData.setText(buffer);
        String current = (String) mData.getText();
        final String[] split = current.split(",");
        mData.setText(split[0]);
        mNama.setText(split[1]);
        mNomor.setText(split[2]);
        mFoto.setImageResource(Integer.parseInt(split[5]));

        mSosmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(split[4]));
                startActivity(intent);
            }
        });

        mAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(split[3]));
                startActivity(intent);
            }
        });
    }
}
