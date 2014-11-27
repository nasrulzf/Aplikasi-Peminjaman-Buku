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
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistempeminjaman.db.Koneksi;
import sistempeminjaman.entity.Kategori;

/**
 *
 * @author Nasrul
 */
public class KategoriModel {
    
    private static final String SQL_SELECT_ALL = "select k.id_kategori as id_kategori, k.nama_kategori as nama_kategori, k." +
                                                                            "p_id as p_id, p.nama_kategori as nama_parent from kategori k left join kategori " +
                                                                            "p on k.p_id = p.id_kategori";
    private static final String SQL_INSERT = "insert into kategori (id_kategori, nama_kategori, p_id) values (?, ?, ?)";
    private static final String SQL_UPDATE = "update kategori set nama_kategori = ?, p_id = ? where id_kategori = ?";
    private static final String SQL_SELECT_ALL_COUNT = "select count(*) as count from kategori";
    private static final String SQL_DELETE = "delete from kategori where id_kategori = ?";
    
    
    public int insert(Kategori kategori){
        
        PreparedStatement ps = null;
        
        try {
            
            System.out.println("Tambah : " + kategori.getIdKategori() + " - " + kategori.getNamaKategori() + " - " + kategori.getpId());
            
            ps = Koneksi.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, kategori.getIdKategori());
            ps.setString(2, kategori.getNamaKategori());
            ps.setInt(3, kategori.getpId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public int update(Kategori kategori){
        
        PreparedStatement ps = null;
        
        try {
            
            System.out.println("Update : " + kategori.getIdKategori() + " - " + kategori.getNamaKategori() + " - " + kategori.getpId());
            
            ps = Koneksi.getConnection().prepareStatement(SQL_UPDATE);          
            ps.setString(1, kategori.getNamaKategori());
            ps.setInt(2, kategori.getpId());
            ps.setInt(3, kategori.getIdKategori());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    public List<Kategori> getAllKategori(){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            
            List<Kategori> list = new ArrayList<>();
            
            while(rs.next()){
                Kategori kategori = new Kategori();
                kategori.setIdKategori(rs.getInt(1));
                kategori.setNamaKategori(rs.getString(2));
                kategori.setpId(rs.getInt(3));
                kategori.setNamaParent(rs.getString(4));
                list.add(kategori);
            }
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public int delete(String idKategori){
        PreparedStatement ps;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_DELETE);
            ps.setString(1, idKategori);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
}
