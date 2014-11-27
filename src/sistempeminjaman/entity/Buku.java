/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.entity;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Nasrul
 */
public class Buku {
    
    private int idBuku;
    private String noIsbn;
    private String judul;
    private int tahunTerbit;
    private String penerbit;
    private Blob foto;
    private int idKategori;
    private Kategori kategori;
    private Date tanggalTambah;
    private String strIdBuku;

    public String getStrIdBuku() {
        return strIdBuku;
    }

    public void setStrIdBuku(String strIdBuku) {
        this.strIdBuku = strIdBuku;
    }
    
    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public String getNoIsbn() {
        return noIsbn;
    }

    public void setNoIsbn(String noIsbn) {
        this.noIsbn = noIsbn;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public Date getTanggalTambah() {
        return tanggalTambah;
    }

    public void setTanggalTambah(Date tanggalTambah) {
        this.tanggalTambah = tanggalTambah;
    }
    
    
    
}
