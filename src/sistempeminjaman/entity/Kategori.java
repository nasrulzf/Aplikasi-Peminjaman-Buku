/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.entity;

/**
 *
 * @author Nasrul
 */
public class Kategori {
    
    private int idKategori;
    private String namaKategori;
    private int pId;
    private String namaParent;

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getNamaParent() {
        return namaParent;
    }

    public void setNamaParent(String namaParent) {
        this.namaParent = namaParent;
    }
    
    
    
}
