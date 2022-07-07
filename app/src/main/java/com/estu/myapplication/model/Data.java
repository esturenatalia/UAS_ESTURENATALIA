package com.estu.myapplication.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Data implements Serializable{

    private String nama;
    private String nim;
    private String kelas;
    private String key;

    public Data(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    @Override
    public String toString() {
        return " "+nama+"\n" +
                " "+nim +"\n" +
                " "+kelas;
    }

    public Data(String nm, String n, String kls){
        nama = nm;
        nim = n;
        kelas = kls;
    }
}
