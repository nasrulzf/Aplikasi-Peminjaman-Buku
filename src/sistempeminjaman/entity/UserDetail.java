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
public class UserDetail {
    
    private int idUser;
    private String noId;
    private String namaD;
    private String namaB;
    private String email;
    private String telp;
    private String alamat;
    private Date tanggalLahir;

//    private Blob uFoto;
    
    public UserDetail(){}
    
    public UserDetail(String namaDepan, String email, String telp, Date tanggalLahir){
        this.namaD = namaDepan;
        this.email = email;
        this.telp = telp;
        this.tanggalLahir = tanggalLahir;
    }
    
    public UserDetail(String namaDepan, String namaBelakang, String email, String telp, String alamat, Date tanggalLahir){
        this(namaDepan, email, telp, tanggalLahir);
        this.namaB = namaBelakang;
        this.alamat = alamat;
    }

//    public Blob getuFoto() {
//        return uFoto;
//    }
//
//    public void setuFoto(Blob uFoto) {
//        this.uFoto = uFoto;
//    }
    
    public String getNoId() {
        return noId;
    }

    public void setNoId(String noId) {
        this.noId = noId;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNamaD() {
        return namaD;
    }

    public void setNamaD(String namaD) {
        this.namaD = namaD;
    }

    public String getNamaB() {
        return namaB;
    }

    public void setNamaB(String namaB) {
        this.namaB = namaB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }
    
    
    
}
