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
public class Peminjaman {
    
    private int idPeminjaman;
    private Date tanggal;
    private Buku buku;
    private Users member;
    private Users petugas;
    private Date tanggalKembali;
    private float nDenda;
    private int status;
    private String strIdPeminjaman;
    
    
    public String getStrIdPeminjaman(){
        return strIdPeminjaman;
    }
    
    public void setStrIdPeminjaman(String strIdPeminjaman){
        this.strIdPeminjaman = strIdPeminjaman;
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

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Users getMember() {
        return member;
    }

    public void setMember(Users member) {
        this.member = member;
    }

    public Users getPetugas() {
        return petugas;
    }

    public void setPetugas(Users petugas) {
        this.petugas = petugas;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public float getnDenda() {
        return nDenda;
    }

    public void setnDenda(float nDenda) {
        this.nDenda = nDenda;
    }
    
    public int getStatus(){
        return status;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
}
