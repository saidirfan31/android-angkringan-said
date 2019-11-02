package com.msmi.angkringansaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.msmi.angkringansaid.adapter.JajananAdapter;
import com.msmi.angkringansaid.model.JajananModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelperEx db_jajanan;

    JajananAdapter adapter;
    RecyclerView rv;
    List<JajananModel> listJajanan = new ArrayList<>();

    int foto_1 = R.drawable.ic_hamburger;
    int foto_2 = R.drawable.ic_kentang_goreng;
    int foto_3 = R.drawable.ic_ayam_goreng;
    int foto_4 = R.drawable.ic_donat;
    int foto_5 = R.drawable.ic_pizza;
    int foto_6 = R.drawable.ic_sandwitch;
    int foto_7 = R.drawable.ic_sandwitch_telur;
    int foto_8 = R.drawable.ic_es_krim;
    int foto_9 = R.drawable.ic_soda_gembira;
    int foto_10 = R.drawable.ic_es_kopi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set myDb as DatabaseHelper class
        db_jajanan = new DatabaseHelperEx(this);
        initRecyclerView();
    }

    //add data jajanan
    public void addDataEx(String nama, String harga, String porsi, int foto) {
        ;
        db_jajanan.insertData(nama, harga, porsi, foto);
    }

    public void initRecyclerView() {
        adapter = new JajananAdapter(this);
        rv = findViewById(R.id.rv_jajanan);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        rv.setNestedScrollingEnabled(false);
        rv.hasFixedSize();
        adapter.setOnItemClickListener(new JajananAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent i = new Intent(MainActivity.this, DetailJajananActivity.class);
                i.putExtra("nama", listJajanan.get(position).getName());
                i.putExtra("harga", listJajanan.get(position).getharga());
                i.putExtra("porsi", listJajanan.get(position).getporsi());
                i.putExtra("deskripsi", listJajanan.get(position).getDesCription());
                i.putExtra("foto", listJajanan.get(position).getimgJajanan());
                startActivity(i);
            }
        });
        loadItem();
    }

    public void loadItem() {
        listJajanan.add(new JajananModel("Nama : Hamburger", "Harga : Rp.20.000,-", "Porsi : 1 Orang", "Description", foto_1));
        listJajanan.add(new JajananModel("Nama : Kentang Goreng", "Harga : Rp.17.000,-", "Porsi : 1 Orang", "Description", foto_2));
        listJajanan.add(new JajananModel("Nama : Ayam Goreng", "Harga : Rp.50.000,-", "Porsi : 3 Orang", "Description", foto_3));
        listJajanan.add(new JajananModel("Nama : Donat", "Harga : Rp.10.000,-", "Porsi : 2 Orang", "Description", foto_4));
        listJajanan.add(new JajananModel("Nama : Pizza", "Harga : Rp.70.000,-", "porsi : 4 Orang", "Description", foto_5));
        listJajanan.add(new JajananModel("Nama : Sandwitch", "Harga : Rp.7000,-", "Porsi : 1 Orang", "Description", foto_6));
        listJajanan.add(new JajananModel("Nama : Sandwitch Telur", "Harga : Rp.10.000", "Porsi : 1 Orang", "Description", foto_7));
        listJajanan.add(new JajananModel("Nama : Es Krim", "Harga : Rp.7000,-", "Porsi : 1 Orang", "Description", foto_8));
        listJajanan.add(new JajananModel("Nama : Soda Gembira", "Harga : Rp.10.000,-", "Porsi : 1 Orang", "Description", foto_9));
        listJajanan.add(new JajananModel("Nama : Es Kopi", "Harga : Rp.15.000,-", "Porsi : 1 Orang", "Description", foto_10));
        adapter.addAll(listJajanan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profil:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.language:
                Toast.makeText(this, "Pilih bahasa", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.indonesia:
                Toast.makeText(this, "Bahasa telah di ubah", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.english:
                Toast.makeText(this, "The language has been changed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
