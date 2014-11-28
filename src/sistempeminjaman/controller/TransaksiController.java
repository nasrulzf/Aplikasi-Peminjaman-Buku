/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.controller;

import java.lang.reflect.Member;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.entity.Peminjaman;
import sistempeminjaman.entity.Pengembalian;
import sistempeminjaman.entity.Users;
import sistempeminjaman.model.PengembalianModel;
import sistempeminjaman.model.PinjamModel;
import sistempeminjaman.view.FormDialog;
import sistempeminjaman.view.peminjaman.FormCariPeminjaman;
import sistempeminjaman.view.peminjaman.FormListPeminjaman;
import sistempeminjaman.view.peminjaman.FormPeminjaman;
import sistempeminjaman.view.pengembalian.FormPengembalian;

/**
 *
 * @author Nasrul
 */
public class TransaksiController {
    
    private FormPeminjaman formPeminjaman;
    private FormListPeminjaman formListPeminjaman;
    private FormPengembalian formPengembalian;
    private FormCariPeminjaman formCariPeminjaman;
    private final PinjamModel model;
    private final PengembalianModel pgModel;
    
    private final float NILAI_DENDA = 1000;
    
    public TransaksiController(FormDialog dialog){
        setDialogType(dialog);
        model = new PinjamModel();
        pgModel = new PengembalianModel();
    }
    
    public void setDialogType(FormDialog dialog){
        
        if(dialog instanceof FormPeminjaman){
            this.formPeminjaman = (FormPeminjaman) dialog;
        }else if(dialog instanceof FormListPeminjaman){
            this.formListPeminjaman = (FormListPeminjaman) dialog;
        }else if(dialog instanceof FormPengembalian){
            this.formPengembalian = (FormPengembalian) dialog;
        }else if(dialog instanceof FormCariPeminjaman){
            this.formCariPeminjaman = (FormCariPeminjaman) dialog;
        }
        
    }
    
    public boolean isPengembalian(){
        if(formPengembalian != null){
            return true;
        }else{
            return false;
        }
    }
    
    public void cariPinjaman(){
        
        int xxx = formCariPeminjaman.getPilihanCari().getSelectedIndex();
        
        List<Peminjaman> list = model.getList(formCariPeminjaman.getTeksCari().getText(), xxx);
        if(list == null){
            formCariPeminjaman.showMessage("Data tidak ditemukan");
            formListPeminjaman.setData(list, false);
        }else{
            formListPeminjaman.setData(list, false);
        }
        
        formCariPeminjaman.dispose();
        
    }
    
    public void loadInitLists(int bulan, int tahun){
        List<Peminjaman> list = model.getList(bulan, tahun);
        if(list == null || bulan == -1){
            formListPeminjaman.showMessage("Data tidak ditemukan atau Bulan belum dipilih");
        }else{
            formListPeminjaman.setData(list, false);
        }
    }
    
    public void getDataPeminjaman(Peminjaman peminjaman){
        
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        
        formPengembalian.getTeksNoPeminjaman().setText(peminjaman.getStrIdPeminjaman());
        formPengembalian.getLabelJudul().setText("Judul Buku : " + peminjaman.getBuku().getJudul());
        formPengembalian.getLabelTanggal().setText("Tanggal Meminjam : " + df.format(peminjaman.getTanggal()));
        formPengembalian.getLabelTanggalKembali().setText("Batas Pengembalian : " + df.format(peminjaman.getTanggalKembali()));
        
        int rgHari = 0;
        Date tglSkrg = formPengembalian.getDateChooser().getDate();
        if(tglSkrg.after(peminjaman.getTanggalKembali())){
            long nn = tglSkrg.getTime() - peminjaman.getTanggalKembali().getTime();
            rgHari = (int)(nn / (24* 1000 * 60 * 60));
        }
        System.out.println("rgHari : " + rgHari);
        System.out.println("Denda " + rgHari + " x " + NILAI_DENDA);
        formPengembalian.getTeksDenda().setText(String.format("%.0f", rgHari * NILAI_DENDA));
        
    }
    
    public boolean validasi(){
        
        if(formPeminjaman.getTeksJudulBuku().getText().isEmpty() || formPeminjaman.getTeksNamaUser().getText().isEmpty()){
            formPeminjaman.showMessage("Lengkapi isian terlebih dahulu");
            return false;
        }else{
            return true;
        }
        
    }
    
    public boolean pengembalianValidasi(){
        
        if(formPengembalian.getDateChooser().getDate() == null){
            formPengembalian.showMessage("Isi tanggal terlebih dahului");
            return false;
        }
        
        String ndn = formPengembalian.getTeksDenda().getText();
        try{
            
            if(Integer.parseInt(ndn) > 0){
                String nby = formPengembalian.getTeksNilaiBayar().getText();
                if(Integer.parseInt(nby) == Integer.parseInt(ndn)){
                    return true;
                }else{
                    formPengembalian.showMessage("Periksa kembali nilai pembayaran denda");
                    return false;
                }
            }else{
                return true;
            }
            
        }catch(NumberFormatException ex){
            formPengembalian.showMessage("Gunakan angka untuk mengisi nilai pembayaran denda");
        }
        
        return false;
        
    }
    
    public String statusPinjam(int i){
        if (i == 0){
            return "Belum Kembali";
        }else{
            return "Kembali";
        }
    }
    
    public void simpanPengembalian(){
        
        try{
            Pengembalian pengembalian = new Pengembalian();
            pengembalian.setIdPeminjaman(Integer.parseInt(formPengembalian.getTeksNoPeminjaman().getText()));
            pengembalian.setTanggal(formPengembalian.getDateChooser().getDate());
            pengembalian.setDenda(Float.parseFloat(formPengembalian.getTeksDenda().getText()));
            System.out.println("controller.simpanPengembalian()");
            if(pgModel.insert(pengembalian) > 0){
                formPengembalian.showMessage("Data pengembalian berhasil disimpan");
                formPengembalian.dispose();
            }else{
                formPengembalian.showMessage("Data pengembalian gagal tersimpan");
            }
        }catch(NumberFormatException ex){
            formPengembalian.showMessage("Gunakan angka untuk mengisi nilai pembayaran denda");
        }
        
        
    }
    
    public void simpan(){
        
        Date tglPinjam = formPeminjaman.getDateChooser().getDate();
        Date tglKembali = hitungTglKembali(tglPinjam);
        Buku bukuPinjam = new Buku();
        bukuPinjam.setIdBuku(Integer.parseInt(formPeminjaman.getTeksNoBuku().getText()));
        Users member = new Users();
        member.setIdUser(Integer.parseInt(formPeminjaman.getTeksNoId().getText()));
        Users petugas = new Users();
        petugas.setIdUser(1);
        Peminjaman p_entity = new Peminjaman();
        p_entity.setBuku(bukuPinjam);
        p_entity.setMember(member);
        p_entity.setPetugas(petugas);
        p_entity.setTanggal(tglPinjam);
        p_entity.setTanggalKembali(tglKembali);
        
        if(model.insert(p_entity) > 0){
            formPeminjaman.showMessage("Peminjaman Berhasil disimpan !");
            formPeminjaman.clear();
        }else{
            formPeminjaman.showMessage("Gagal menyimpan peminjaman");
        }
        
    }
    
    private Date hitungTglKembali(Date tglPinjam){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(tglPinjam);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        return cal.getTime();
        
    }
    
}
