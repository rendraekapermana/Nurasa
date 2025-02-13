package com.example.nurasa.history;

public class MenuModel {
    private String namaPaket;
    private long hargaMakanan;
    private String tanggalPesananSelesai;

    public MenuModel() {
        // Default constructor required for calls to DataSnapshot.getValue(MenuModel.class)
    }

    public MenuModel(String namaPaket, long hargaMakanan, String tanggalPesananSelesai) {
        this.namaPaket = namaPaket;
        this.hargaMakanan = hargaMakanan;
        this.tanggalPesananSelesai = tanggalPesananSelesai;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public long getHargaMakanan() {
        return hargaMakanan;
    }

    public void setHargaMakanan(long hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    public String getTanggalPesananSelesai() {
        return tanggalPesananSelesai;
    }

    public void setTanggalPesananSelesai(String tanggalPesananSelesai) {
        this.tanggalPesananSelesai = tanggalPesananSelesai;
    }
}
