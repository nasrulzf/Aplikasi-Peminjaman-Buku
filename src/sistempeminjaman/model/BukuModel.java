/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistempeminjaman.db.Koneksi;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.entity.Kategori;

/**
 *
 * @author Nasrul
 */
public class BukuModel {
    
    private static final String SQL_SELECT_ALL = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori)";
    private static final String SQL_SELECT_ALL_P = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where id_buku not in (select id_buku from peminjaman where status = 1)";
    private static final String SQL_SELECT_ALL_NO_ISBN = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where no_isbn like ?";
    private static final String SQL_SELECT_ALL_JUDUL = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where judul like ?";
    private static final String SQL_SELECT_ALL_PENERBIT = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where penerbit like ?";
    private static final String SQL_SELECT_ALL_NO_ISBN_P = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where no_isbn like ? and id_buku not in (select id_buku from peminjaman where status = 1)";
    private static final String SQL_SELECT_ALL_JUDUL_P = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where judul like ? and id_buku not in (select id_buku from peminjaman where status = 1)";
    private static final String SQL_SELECT_ALL_PENERBIT_P = "select id_buku, no_isbn, judul, tahun_terbit, penerbit, foto, buku.id_kategori as \"id_kategori\", nama_kategori, tanggal_tambah from buku join kategori using(id_kategori) where penerbit like ? and id_buku not in (select id_buku from peminjaman where status = 1)";
    private static final String SQL_SELECT_ALL_COUNT = "select count(*) from buku";
    private static final String SQL_SELECT_WHERE = "";
    private static final String SQL_INSERT = "insert into buku (no_isbn, judul, tahun_terbit, penerbit, foto, id_kategori, tanggal_tambah) values(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update buku set no_isbn = ?, judul = ?, tahun_terbit = ?, penerbit = ?, foto = ?, id_kategori = ? where id_buku = ?";
    
    public List<Buku> getAll(){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            
            List<Buku> list = new ArrayList<>();
            
            while(rs.next()){
                Buku buku = new Buku();
                
                buku.setStrIdBuku(rs.getString(1));
                buku.setIdBuku(rs.getInt(1));
                buku.setNoIsbn(rs.getString(2));
                buku.setJudul(rs.getString(3));
                buku.setTahunTerbit(rs.getInt(4));
                buku.setPenerbit(rs.getString(5));
                Kategori kategori = new Kategori();
                kategori.setIdKategori(rs.getInt(7));
                kategori.setNamaKategori(rs.getString(8));
                buku.setKategori(kategori);
                list.add(buku);
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Buku> getAllP(){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_P);
            rs = ps.executeQuery();
            
            List<Buku> list = new ArrayList<>();
            
            while(rs.next()){
                Buku buku = new Buku();
                
                buku.setStrIdBuku(rs.getString(1));
                buku.setIdBuku(rs.getInt(1));
                buku.setNoIsbn(rs.getString(2));
                buku.setJudul(rs.getString(3));
                buku.setTahunTerbit(rs.getInt(4));
                buku.setPenerbit(rs.getString(5));
                Kategori kategori = new Kategori();
                kategori.setIdKategori(rs.getInt(7));
                kategori.setNamaKategori(rs.getString(8));
                buku.setKategori(kategori);
                list.add(buku);
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Buku> getAll(String q, int sType){
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            
            switch(sType){
                case 0:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_NO_ISBN);
                    break;
                case 1:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JUDUL);
                    break;
                case 2:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_PENERBIT);
                    break;
            }
            
            ps.setString(1, "%" + q + "%");
            rs = ps.executeQuery();
            
            List<Buku> list = new ArrayList<>();
            
            while(rs.next()){
                Buku buku = new Buku();
                buku.setStrIdBuku(rs.getString(1));
                buku.setIdBuku(rs.getInt(1));
                buku.setNoIsbn(rs.getString(2));
                buku.setJudul(rs.getString(3));
                buku.setTahunTerbit(rs.getInt(4));
                buku.setPenerbit(rs.getString(5));
                Kategori kategori = new Kategori();
                kategori.setIdKategori(rs.getInt(7));
                kategori.setNamaKategori(rs.getString(8));
                buku.setKategori(kategori);
                list.add(buku);
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Buku> getAllP(String q, int sType){
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            
            switch(sType){
                case 0:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_NO_ISBN_P);
                    break;
                case 1:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JUDUL_P);
                    break;
                case 2:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_PENERBIT_P);
                    break;
            }
            
            ps.setString(1, "%" + q + "%");
            rs = ps.executeQuery();
            
            List<Buku> list = new ArrayList<>();
            
            while(rs.next()){
                Buku buku = new Buku();
                buku.setStrIdBuku(rs.getString(1));
                buku.setIdBuku(rs.getInt(1));
                buku.setNoIsbn(rs.getString(2));
                buku.setJudul(rs.getString(3));
                buku.setTahunTerbit(rs.getInt(4));
                buku.setPenerbit(rs.getString(5));
                Kategori kategori = new Kategori();
                kategori.setIdKategori(rs.getInt(7));
                kategori.setNamaKategori(rs.getString(8));
                buku.setKategori(kategori);
                list.add(buku);
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getSelectAllCount(){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_COUNT);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int insertBuku(Buku buku){
        
        PreparedStatement ps;
        // no_isbn, judul, tahun_terbit, penerbit, foto, id_kategori, tanggal_tambah
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, buku.getNoIsbn());
            ps.setString(2, buku.getJudul());
            ps.setInt(3, buku.getTahunTerbit());
            ps.setString(4, buku.getPenerbit());
            ps.setBlob(5, buku.getFoto());
            ps.setInt(6, buku.getKategori().getIdKategori());
            ps.setDate(7, new java.sql.Date(new Date().getTime()));
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
    public int updateBuku(Buku buku){
        
        PreparedStatement ps;
        // no_isbn, judul, tahun_terbit, penerbit, foto, id_kategori, tanggal_tambah
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, buku.getNoIsbn());
            ps.setString(2, buku.getJudul());
            ps.setInt(3, buku.getTahunTerbit());
            ps.setString(4, buku.getPenerbit());
            ps.setBlob(5, buku.getFoto());
            ps.setInt(6, buku.getKategori().getIdKategori());
            ps.setInt(7, buku.getIdBuku());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
}
