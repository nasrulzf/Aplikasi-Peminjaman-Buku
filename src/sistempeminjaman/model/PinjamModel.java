/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistempeminjaman.db.Koneksi;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.entity.Peminjaman;
import sistempeminjaman.entity.Users;

/**
 *
 * @author Nasrul
 */
public class PinjamModel {
    
    private static final String SQL_INSERT = "insert into peminjaman (id_buku, id_member, id_petugas, tanggal_kembali, tanggal) values (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_MY = "select p.id_peminjaman as \"id_peminjaman\", u.nama_d as \"peminjam\", b.judul as \"judul\", p.tanggal as \"tanggal_pinjam\", p.tanggal_kembali as \"tanggal_kembali\", datediff(p.tanggal_kembali, p.tanggal) as \"sisa_hari\", p.status as \"status\"  from peminjaman p join buku b using(id_buku) join user_detail u on p.id_member = u.no_id where (date_format(p.tanggal, '%m') = ?) and (date_format(p.tanggal, '%Y') = ?) and p.status = '0'";
    private static final String SQL_SELECT = "select p.id_peminjaman as \"id_peminjaman\", u.nama_d as \"peminjam\", b.judul as \"judul\", p.tanggal as \"tanggal_pinjam\", p.tanggal_kembali as \"tanggal_kembali\", datediff(p.tanggal_kembali, p.tanggal) as \"sisa_hari\", p.status as \"status\"  from peminjaman p join buku b using(id_buku) join user_detail u on p.id_member = u.no_id";
    private static final String SQL_SELECT_W_NO_ID = "select p.id_peminjaman as \"id_peminjaman\", u.nama_d as \"peminjam\", b.judul as \"judul\", p.tanggal as \"tanggal_pinjam\", p.tanggal_kembali as \"tanggal_kembali\", datediff(p.tanggal_kembali, p.tanggal) as \"sisa_hari\", p.status as \"status\"  from peminjaman p join buku b using(id_buku) join user_detail u on p.id_member = u.no_id where u.no_id like ? and p.status = '0'";
    
    public int insert(Peminjaman pinjam){
        
        PreparedStatement ps;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, pinjam.getBuku().getIdBuku());
            ps.setInt(2, pinjam.getMember().getIdUser());
            ps.setInt(3, pinjam.getPetugas().getIdUser());
            ps.setDate(4, new Date(pinjam.getTanggalKembali().getTime()));
            ps.setDate(5, new Date(pinjam.getTanggal().getTime()));
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PinjamModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<Peminjaman> getList(){
        
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            
            List<Peminjaman> list = new ArrayList<>();
            // id_peminjaman, peminjam, judul, tanggal_pinjam, tanggal_kembali, sisa_hari, status
            while(rs.next()){
                
                Users member = new Users();
                member.setUsername(rs.getString("peminjam"));
                member.setIdUser(rs.getInt("sisa_hari"));
                
                Buku buku = new Buku();
                buku.setJudul(rs.getString("judul"));
                
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setStrIdPeminjaman(rs.getString("id_peminjaman"));
                peminjaman.setMember(member);
                peminjaman.setBuku(buku);
                peminjaman.setTanggal(rs.getDate("tanggal_pinjam"));
                peminjaman.setTanggalKembali(rs.getDate("tanggal_kembali"));                
                peminjaman.setStatus(rs.getInt("status"));
                
                list.add(peminjaman);
                
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(PinjamModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public List<Peminjaman> getList(int bulan, int tahun){
        
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_MY);
            ps.setInt(1, bulan);
            ps.setInt(2, tahun);
            rs = ps.executeQuery();
            
            List<Peminjaman> list = new ArrayList<>();
            // id_peminjaman, peminjam, judul, tanggal_pinjam, tanggal_kembali, sisa_hari, status
            while(rs.next()){
                
                Users member = new Users();
                member.setUsername(rs.getString("peminjam"));
                member.setIdUser(rs.getInt("sisa_hari"));
                
                Buku buku = new Buku();
                buku.setJudul(rs.getString("judul"));
                
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setStrIdPeminjaman(rs.getString("id_peminjaman"));
                peminjaman.setMember(member);
                peminjaman.setBuku(buku);
                peminjaman.setTanggal(rs.getDate("tanggal_pinjam"));
                peminjaman.setTanggalKembali(rs.getDate("tanggal_kembali"));                
                peminjaman.setStatus(rs.getInt("status"));
                
                list.add(peminjaman);
                
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(PinjamModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public List<Peminjaman> getList(String q, int sType){
        
        PreparedStatement ps = null;
        ResultSet rs;
        
        try {
            
            switch(sType){
                case 0:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_W_NO_ID);
                    break;
            }
            
            ps.setString(1, "%" + q + "%");
            
            rs = ps.executeQuery();
            
            List<Peminjaman> list = new ArrayList<>();
            // id_peminjaman, peminjam, judul, tanggal_pinjam, tanggal_kembali, sisa_hari, status
            while(rs.next()){
                
                Users member = new Users();
                member.setUsername(rs.getString("peminjam"));
                member.setIdUser(rs.getInt("sisa_hari"));
                
                Buku buku = new Buku();
                buku.setJudul(rs.getString("judul"));
                
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setStrIdPeminjaman(rs.getString("id_peminjaman"));
                peminjaman.setMember(member);
                peminjaman.setBuku(buku);
                peminjaman.setTanggal(rs.getDate("tanggal_pinjam"));
                peminjaman.setTanggalKembali(rs.getDate("tanggal_kembali"));                
                peminjaman.setStatus(rs.getInt("status"));
                
                list.add(peminjaman);
                
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(PinjamModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
}
