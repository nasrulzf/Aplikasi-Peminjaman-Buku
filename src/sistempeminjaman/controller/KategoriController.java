/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import sistempeminjaman.entity.Kategori;
import sistempeminjaman.model.KategoriModel;
import sistempeminjaman.view.FormDialog;
import sistempeminjaman.view.buku.FormViewBuku;
import sistempeminjaman.view.kategori.FormListKategori;
import sistempeminjaman.view.kategori.FormViewKategori;

/**
 *
 * @author Nasrul
 */
public class KategoriController {
    
    private KategoriModel model;
    private FormViewBuku formViewBuku;
    private FormViewKategori formViewKategori;
    private FormListKategori formListKategori;
    
    public KategoriController(){
        model = new KategoriModel();
    }
    
    public KategoriController(FormDialog formDialog){
        this();
        setDialogType(formDialog);
    }
    
    public void setDialogType(FormDialog formDialog){
        
        if(formDialog instanceof FormViewBuku){
            this.formViewBuku = (FormViewBuku) formDialog;
        }else if(formDialog instanceof FormViewKategori){
            this.formViewKategori = (FormViewKategori) formDialog;
        }else if(formDialog instanceof FormListKategori){
            this.formListKategori = (FormListKategori) formDialog;
        }
        
    }
    
    public void loadInitLists(){
        List<Kategori> list = model.getAllKategori();
        if(list == null){
            formListKategori.showMessage("Data tidak ditemukan");
        }else{
            int totalQty = model.getSelectAllCount();
            String teksNotif = "Menampilkan total " + totalQty + " Data";
            formListKategori.setData(teksNotif, list);
        }
    }
    
    public void setKategoriBuku(){
        
        List<Kategori> list = model.getAllKategori();
        formViewBuku.setKategori(list);
        
    }
    
    public void setKategoriParent(){
        
        List<Kategori> list = model.getAllKategori();
        formViewKategori.setKategori(list);
        
    }
 
    
    public boolean validasi(FormViewKategori viewKategori){
        if(viewKategori.getTeksKategori().getText().isEmpty()){
            viewKategori.showMessage("Isi isian terlebih dahulu !");
            return false;
        }else{
            return true;
        }
    }
    
    public void simpanKategori(Kategori kategori, int mode){
        
        switch(mode){
            case 1:
                if(model.update(kategori) > 0){
                    formViewKategori.showMessage("Kategori berhasil diupdate");

                    if(formViewBuku != null){
                        setKategoriBuku();
                    }

                    formListKategori.reload();
                    formViewKategori.dispose();

                }
                break;
            case 0:
                if(model.insert(kategori) > 0){
                    formViewKategori.showMessage("Kategori berhasil ditambahkan");

                    if(formViewBuku != null){
                        setKategoriBuku();
                    }

                    if(formListKategori != null) formListKategori.reload();
                    if(formViewBuku != null) formViewBuku.reload(true);
                    formViewKategori.dispose();

                }
                break;
        }
        
    }
    
    public void hapus(String idKategori){
        
        if(model.delete(idKategori) > 0){
                    formViewKategori.showMessage("Kategori berhasil dihapus");

                    if(formViewBuku != null){
                        setKategoriBuku();
                    }

                    formListKategori.reload();
                    formViewKategori.dispose();

                }
        
    }
    
}
