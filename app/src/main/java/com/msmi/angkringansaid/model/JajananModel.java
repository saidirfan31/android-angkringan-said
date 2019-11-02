package com.msmi.angkringansaid.model;

public class JajananModel {

    String name;
    String harga;
    String porsi;
    String DesCription;
    int imgJajanan;

    public JajananModel(String name, String harga, String porsi, String desCription, int imgJajanan) {
        this.name = name;
        this.harga = harga;
        this.porsi = porsi;
        this.DesCription = desCription;
        this.imgJajanan = imgJajanan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getharga() {
        return harga;
    }

    public void setharga(String harga) {
        this.harga = harga;
    }

    public String getporsi() {
        return porsi;
    }

    public void setporsi(String porsi) {
        this.porsi = porsi;
    }

    public String getDesCription() {
        return DesCription;
    }

    public void setDesCription(String desCription) {
        DesCription = desCription;
    }

    public int getimgJajanan() {
        return imgJajanan;
    }

    public void setimgJajanan(int imgJajanan) {
        this.imgJajanan = imgJajanan;
    }
}
