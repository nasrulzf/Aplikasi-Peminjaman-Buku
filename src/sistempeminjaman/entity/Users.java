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
public class Users {
    
    public static final String T_ADMIN = "ADMIN";
    public static final String T_MEMBER = "MEMBER";
    public static final String T_AKTIF = "AKTIF";
    public static final String T_NONAKTIF = "NONAKTIF";
    
    private int idUser;
    private String noId;
    private String username;
    private String password;
    private Date lLogin;
    private String uTipe;
    private String sAktifasi;

    private UserDetail userDetail;
    
    public Users(){
        
    }
    
    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public void setDetail(UserDetail userDetail){
        this.userDetail = userDetail;
    }
    
    public UserDetail getDetail(){
        if(userDetail != null){
            return userDetail;
        }else{
            return null;
        }
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNoId() {
        return noId;
    }

    public void setNoId(String noId) {
        this.noId = noId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getlLogin() {
        return lLogin;
    }

    public void setlLogin(Date lLogin) {
        this.lLogin = lLogin;
    }

    public String getuTipe() {
        return uTipe;
    }

    public void setuTipe(String uTipe) {
        this.uTipe = uTipe;
    }

    public String getsAktifasi() {
        return sAktifasi;
    }

    public void setsAktifasi(String sAktifasi) {
        this.sAktifasi = sAktifasi;
    }
    
    
    
}
