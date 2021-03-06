/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.controller;

import java.util.List;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.model.BukuModel;
import sistempeminjaman.view.FormDialog;
import sistempeminjaman.view.buku.FormCariBuku;
import sistempeminjaman.view.buku.FormListBuku;
import sistempeminjaman.view.buku.FormViewBuku;
import sistempeminjaman.view.peminjaman.FormPeminjaman;

/**
 *
 * @author Nasrul
 */
public class BukuController {
    
    private FormViewBuku viewFormBuku;
    private FormListBuku viewListBuku;
    private FormCariBuku viewFormCari;
    private FormPeminjaman viewFormPeminjaman;
    private final BukuModel model;
    
    public BukuController(FormDialog viewForm){
        
        setDialogType(viewForm);
        
        model = new BukuModel();
    }
    
    public void initFormBuku(){
        
        if(viewFormBuku.getMode() != FormViewBuku.AKSI_EDIT){
            viewFormBuku.initInputForm(false, true);
        }else{
            viewFormBuku.getButtonOk().setVisible(false);
        }
        
        
    }
    
    public void setDialogType(FormDialog formDialog){
        
        if(formDialog instanceof FormListBuku){
            this.viewListBuku = (FormListBuku) formDialog;
        }else if(formDialog instanceof FormViewBuku){
            this.viewFormBuku = (FormViewBuku) formDialog;
        }else if(formDialog instanceof FormCariBuku){
            this.viewFormCari = (FormCariBuku) formDialog;
        }else if(formDialog instanceof FormPeminjaman){
            this.viewFormPeminjaman = (FormPeminjaman) formDialog;
        }
        
    }
    
    public void setPilihBuku(Buku buku){
        viewFormPeminjaman.getTeksNoBuku().setText(buku.getStrIdBuku());
        viewFormPeminjaman.getTeksJudulBuku().setText(buku.getJudul());
        viewFormBuku.dispose();
        viewListBuku.dispose();
    }
    
    public void isTransaksi(Buku buku){
        if(viewFormPeminjaman != null){
            viewFormBuku.initInputForm(true, true, false);
            viewFormBuku.getTeksIsbn().setText(buku.getNoIsbn());
            viewFormBuku.getTeksJudul().setText(buku.getJudul());
            viewFormBuku.getPenerbit().setText(buku.getPenerbit());
            viewFormBuku.getYearChooser().setYear(buku.getTahunTerbit());
            viewFormBuku.getKategori().setSelectedItem(buku.getKategori().getNamaKategori());
            viewFormBuku.setButtonPinjam(true);
            
        }else{
            viewFormBuku.setButtonPinjam(false);
        }
    }
    
    public void loadInitLists(){
        System.out.println("SYS INIT");
        List<Buku> list;
        
        if(viewFormPeminjaman == null){
            list = model.getAll();
        }else{
            list = model.getAllP();
        }
        
        if(list == null){
            viewListBuku.showMessage("Data tidak ditemukan");
        }else{
            int totalQty = model.getSelectAllCount();
            String teksNotif = "Menampilkan total " + totalQty + " Data";
            viewListBuku.setData(teksNotif, list, false);
        }
    }
    
    public boolean validasi(){
        if(viewFormBuku.getTeksIsbn().getText().isEmpty() || viewFormBuku.getTeksJudul().getText().isEmpty()
                || viewFormBuku.getPenerbit().getText().isEmpty()){
            viewFormBuku.showMessage("Lengkap isian terlebih dahulu");
            return false;
        }else{
            return true;
        }
    }
    
    public void simpan(Buku buku, int mode){
        
        switch(mode){
            case 1:
                if(model.updateBuku(buku) > 0){                    
                    viewFormBuku.showMessage("Buku berhasil diupdate");
                    
                    viewListBuku.reload();
                    viewFormBuku.dispose();

                }else{
                    viewFormBuku.showMessage("Buku gagal diupdate");
                }
                break;
            case 0:
                
                if(model.insertBuku(buku) > 0){
                    viewFormBuku.showMessage("Buku berhasil disimpan");

                    if(viewListBuku != null) viewListBuku.reload();
                    viewFormBuku.dispose();

                }else{
                    viewFormBuku.showMessage("Buku gagal disimpan");
                }
             
                break;
        }
        
    }
    
    public void cekBuku(){
        
        Buku buku = model.getBuku(viewFormBuku.getTeksIsbn().getText());
        
        if(buku != null){
            if(viewFormBuku.getMode() == FormViewBuku.AKSI_TAMBAH)
                viewFormBuku.initInputForm(buku, false, true);
            else
                viewFormBuku.initInputForm(buku, true, false);
        }else{
            viewFormBuku.initInputForm(true, false);
        }
        
    }
    
    public void cariBuku(){
        
        int xxx = viewFormCari.getPilihanCari().getSelectedIndex();
        List<Buku> list;
        
        if(viewFormPeminjaman == null){
            list = model.getAll(viewFormCari.getTeksCari().getText(), xxx);
        }else{
            list = model.getAllP(viewFormCari.getTeksCari().getText(), xxx);
        }
        
        if(list == null){
            viewListBuku.showMessage("Data tidak ditemukan");
            String teksNotif = "Hasil pencarian " + viewFormCari.getPilihanCari().getSelectedItem().toString() + " \"" + viewFormCari.getTeksCari().getText() + "\"";
            viewListBuku.setData(teksNotif, list, true);
        }else{
            String teksNotif = "Hasil pencarian " + viewFormCari.getPilihanCari().getSelectedItem().toString() + " \"" + viewFormCari.getTeksCari().getText() + "\"";
            viewListBuku.setData(teksNotif, list, true);
        }
        
        viewFormCari.dispose();
        
    }
    
}
