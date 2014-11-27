/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.entity;

import java.util.Date;

/**
 *
 * @author Nasrul
 */
public class Pengembalian {
    
    private int idPengembalian;
    private int idPeminjaman;
    private Date tanggal;
    private float denda;
    
    private String strIdPengembalian;
    private String strIdPeminjaman;

    public int getIdPengembalian() {
        return idPengembalian;
    }

    public void setIdPengembalian(int idPengembalian) {
        this.idPengembalian = idPengembalian;
    }

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public float getDenda() {
        return denda;
    }

    public void setDenda(float denda) {
        this.denda = denda;
    }

    public String getStrIdPengembalian() {
        return strIdPengembalian;
    }

    public void setStrIdPengembalian(String strIdPengembalian) {
        this.strIdPengembalian = strIdPengembalian;
    }

    public String getStrIdPeminjaman() {
        return strIdPeminjaman;
    }

    public void setStrIdPeminjaman(String strIdPeminjaman) {
        this.strIdPeminjaman = strIdPeminjaman;
    }
    
    
    
}
