package com.msmi.angkringansaid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.msmi.angkringansaid.R;
import com.msmi.angkringansaid.adapter.JajananAdapter;
import com.msmi.angkringansaid.helper.DatabaseHelperEx;
import com.msmi.angkringansaid.helper.LocaleHelper;
import com.msmi.angkringansaid.model.JajananModel;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "in"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("language", "in");
        updateView((String) Paper.book().read("language"));

        db_jajanan = new DatabaseHelperEx(this);
        initRecyclerView();
    }

    private void updateView(String lang) {
        LocaleHelper.setLocale(this, lang);
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
        listJajanan.add(new JajananModel(getString(R.string.jajanan1), getString(R.string.harga) + " Rp.20.000,-", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi1), foto_1));
        listJajanan.add(new JajananModel(getString(R.string.jajanan2), getString(R.string.harga) + " Rp.17.000,-", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi2), foto_2));
        listJajanan.add(new JajananModel(getString(R.string.jajanan3), getString(R.string.harga) + " Rp.50.000,-", getString(R.string.porsi) + " 3 " + getString(R.string.orang), getString(R.string.deskripsi3), foto_3));
        listJajanan.add(new JajananModel(getString(R.string.jajanan4), getString(R.string.harga) + " Rp.10.000,-", getString(R.string.porsi) + " 2 " + getString(R.string.orang), getString(R.string.deskripsi4), foto_4));
        listJajanan.add(new JajananModel(getString(R.string.jajanan5), getString(R.string.harga) + " Rp.70.000,-", getString(R.string.porsi) + " 4 " + getString(R.string.orang), getString(R.string.deskripsi5), foto_5));
        listJajanan.add(new JajananModel(getString(R.string.jajanan6), getString(R.string.harga) + " Rp.7000,-", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi6), foto_6));
        listJajanan.add(new JajananModel(getString(R.string.jajanan7), getString(R.string.harga) + " Rp.10.000", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi7), foto_7));
        listJajanan.add(new JajananModel(getString(R.string.jajanan8), getString(R.string.harga) + " Rp.7000,-", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi8), foto_8));
        listJajanan.add(new JajananModel(getString(R.string.jajanan9), getString(R.string.harga) + " Rp.10.000,-", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi9), foto_9));
        listJajanan.add(new JajananModel(getString(R.string.jajanan10), getString(R.string.harga) + " Rp.15.000,-", getString(R.string.porsi) + " 1 " + getString(R.string.orang), getString(R.string.deskripsi10), foto_10));
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
                return true;
            case R.id.indonesia:
                Paper.book().write("language", "in");
                Toast.makeText(this, "Bahasa telah di ubah", Toast.LENGTH_SHORT).show();
                updateView((String) Paper.book().read("language"));
                finish();
                startActivity(getIntent());
                return true;
            case R.id.english:
                Paper.book().write("language", "en");
                Toast.makeText(this, "The language has been changed", Toast.LENGTH_SHORT).show();
                updateView((String) Paper.book().read("language"));
                finish();
                startActivity(getIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
