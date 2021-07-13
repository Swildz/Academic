package com.example.uastkbahmadsiddiq;

public class  rumah {

    private int id;
    private String nama;
    private String keterangan;
    private String gambar;
    private String harga;
    private String alamat;
    private String latitude;
    private String longitude;

    public rumah(int id, String nama, String keterangan, String gambar, String harga, String alamat, String latitude, String longitude) {
        this.id = id;
        this.nama = nama;
        this.keterangan = keterangan;
        this.gambar = gambar;
        this.harga = harga;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
