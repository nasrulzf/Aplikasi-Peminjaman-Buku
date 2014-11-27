/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.html.parser.DTDConstants;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.entity.UserDetail;
import sistempeminjaman.entity.Users;
import sistempeminjaman.model.BukuModel;
import sistempeminjaman.model.Session;
import sistempeminjaman.model.UserModel;
import sistempeminjaman.view.FormDialog;
import sistempeminjaman.view.FormLogin;
import sistempeminjaman.view.FormMain;
import sistempeminjaman.view.peminjaman.FormPeminjaman;
import sistempeminjaman.view.user.FormCariUser;
import sistempeminjaman.view.user.FormListUsers;
import sistempeminjaman.view.user.FormRegistrasi;

/**
 *
 * @author Nasrul
 */
public class UserController {
    
    private FormDialog dialog;
    private FormRegistrasi formRegistrasi;
    private FormListUsers formUsers;
    private FormCariUser formCariUser;
    private FormPeminjaman formPeminjaman;
    private FormLogin formLogin;
    private final UserModel uModel;
    
    public UserController(FormDialog dialog){
        
        setDialogType(dialog);
        uModel = new  UserModel();
        
    }
    
     public void setDialogType(FormDialog dialog){
        
        if(dialog instanceof FormRegistrasi){
            this.formRegistrasi = (FormRegistrasi) dialog;
        }else if (dialog instanceof FormListUsers){
            this.formUsers = (FormListUsers) dialog;
        }else if(dialog instanceof FormCariUser){
            this.formCariUser = (FormCariUser) dialog;
        }else if(dialog instanceof FormPeminjaman){
            this.formPeminjaman = (FormPeminjaman) dialog;
        }else if(dialog instanceof FormLogin){
            this.formLogin = (FormLogin) dialog;
        }else{
            this.dialog = dialog;
        }
        
    }
    
     public void setPilihBuku(Users user){
        formPeminjaman.getTeksNoId().setText(user.getNoId());
        formPeminjaman.getTeksNamaUser().setText(user.getDetail().getNamaD() + " " + user.getDetail().getNamaB());
        formRegistrasi.dispose();
        formUsers.dispose();
    }
    
    public void isTransaksi(){
        if(formPeminjaman != null){
            formRegistrasi.setButtonPinjam(true);
        }else{
            formRegistrasi.setButtonPinjam(false);
        }
    }
     
    public void loadInitLists(){
        List<Users> list = uModel.getAll();
        if(list == null){
            formUsers.showMessage("Data tidak ditemukan");
        }else{
            int totalQty = uModel.getSelectAllCount();
            String teksNotif = "Menampilkan total " + totalQty + " Data";
            formUsers.setData(teksNotif, list, false);
        }
    }
    
    public boolean validasi(){
        
        if(formRegistrasi.getTeksNoId().getText().isEmpty() || formRegistrasi.getTeksNamaDepan().getText().isEmpty()
                || formRegistrasi.getTeksEmail().getText().isEmpty() || formRegistrasi.getTeksNoTelp().getText().isEmpty()
                || formRegistrasi.getTeksAlamat().getText().isEmpty()){
            
            formRegistrasi.showMessage("Lengkapi form terlebih dahulu");
            
            return false;
            
        }else{
            
            if(validasiEmail(formRegistrasi.getTeksEmail().getText())){
                
                return true;
                
            }else{
                
                formRegistrasi.showMessage("Gunakan email yang valid");
                
            }
            
            return false;
            
        }
        
    }
    
    public boolean validasiEmail(String email){
        
        return email.contains("@");
        
    }
    
    public void isLoginValid(Users user){
        
        if(user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty()){
            dialog.showMessage("Isi Form Login terlebih dahulu");
            System.out.println("Login gagal");
        }else{      
            //System.out.println("Login Berhasil");
            cekUser(user.getUsername(), user.getPassword());
        }
        
    }
   
//    public void initGambar(int xx){
//        Blob bl = getBlob(xx);
//        if(bl != null){
//            try {
//                byte[] imageByte = bl.getBytes(1, (int) bl.length());
//                InputStream is=new ByteArrayInputStream(imageByte);
//                BufferedImage imag=ImageIO.read(is);
//                Image image = imag;
//                // img = Toolkit.getDefaultToolkit().createImage(imageByte);
//                //  img = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
//                ImageIcon icon =new ImageIcon(img);  
//                lblImage.setIcon(icon) ;
//            } catch (SQLException | IOException ex) {
//                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//    }
//    
//    private Blob getBlob(int xy){
//        return uModel.getImage(xy);
//    }
    
    private void cekUser(String username, String password){
        
        Users user = uModel.getUser(new Users(username, password));
        
        if(user != null){
            setSession(user);
            formLogin.showMessage("Login berhasil");
            FormMain fmn = new FormMain();
            fmn.tampil();
            formLogin.dispose();
        }else{
            formLogin.showMessage("Login gagal / periksa username dan password");
        }
        
    }
    
    public void closeListUser(){
        
        formUsers.dispose();
        
    }
    
    private void setSession(Users user){
        Session.setData(user);
    }
    
    public void simpanUser(Users user, UserDetail userDetail, int mode){
        
        switch(mode){
            case 1:
                if(uModel.updateUser(userDetail) > 0){
                    formRegistrasi.clear();
                    if(formUsers != null) formUsers.reload();
                    formRegistrasi.showMessage("User berhasil terupdate");
                    formRegistrasi.dispose();
                }else{
                    formRegistrasi.showMessage("Gagal melakukan update");
                }
                
                break;
            case 0:
                
                if(uModel.insertUser(user, userDetail) > 0){
                    formRegistrasi.clear();
                    if(formUsers != null) formUsers.reload();
                    formRegistrasi.showMessage("User berhasil tersimpan");
                    
                }else{
                    formRegistrasi.showMessage("Gagal menyimpan user");
                }

                break;
        }
        
    }
    
    public void cariUser(){
        
        int xxx = formCariUser.getPilihanCari().getSelectedIndex();
        
        List<Users> list = uModel.getAll(formCariUser.getTeksCari().getText(), xxx);
        if(list == null){
            formUsers.showMessage("Data tidak ditemukan");
            String teksNotif = "Hasil pencarian " + formCariUser.getPilihanCari().getSelectedItem().toString() + " \"" + formCariUser.getTeksCari().getText() + "\"";
            formUsers.setData(teksNotif, list, true);
        }else{
            String teksNotif = "Hasil pencarian " + formCariUser.getPilihanCari().getSelectedItem().toString() + " \"" + formCariUser.getTeksCari().getText() + "\"";
            formUsers.setData(teksNotif, list, true);
        }
        
        formCariUser.dispose();
        
    }
    
    
}
