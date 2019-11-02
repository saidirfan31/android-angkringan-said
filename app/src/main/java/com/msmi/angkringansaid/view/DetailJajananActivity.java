package com.msmi.angkringansaid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.msmi.angkringansaid.R;

public class DetailJajananActivity extends AppCompatActivity {
    ImageView imgView;
    TextView mNama;
    TextView mharga;
    TextView mporsi;
    TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jajanan);
        setTitle("Detail");
        setData();
    }

    public void setData() {
        imgView = findViewById(R.id.img);
        mNama = findViewById(R.id.txt_name_jajanan);
        mharga = findViewById(R.id.txt_harga);
        mporsi = findViewById(R.id.txt_porsi);
        mDesc = findViewById(R.id.txt_deskripsi);

        mNama.setText(getIntent().getStringExtra("nama"));
        mharga.setText(getIntent().getStringExtra("harga"));
        mporsi.setText(getIntent().getStringExtra("porsi"));
        mDesc.setText(getIntent().getStringExtra("deskripsi"));
        imgView.setImageResource(getIntent().getIntExtra("foto", 0));
    }
}
