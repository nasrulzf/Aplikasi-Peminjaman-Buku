/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.model;

import java.sql.Blob;
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
import sistempeminjaman.entity.Kategori;
import sistempeminjaman.entity.UserDetail;
import sistempeminjaman.entity.Users;

/**
 *
 * @author Nasrul
 */
public class UserModel {
    
    private final static String SQL_LOGIN = "select * from users where username = ? and password = md5(?) and s_aktifasi = 'AKTIF'";
    
    /*
    users
    id_user, no_id, username, password, l_login, u_tipe, s_aktifasi
    user_detail
    id_user, nama_d, nama_b, email, telp, alamat, tanggal_lahir, u_foto
    */
    
    private final static String SQL_SELECT_ALL_JOIN = "select b.no_id as \"no_id\", a.nama_d as \"nama_depan\", a.nama_b as \"nama_belakang\", a.email as \"email\", "
            + "a.telp as \"telp\", a.tanggal_lahir as \"tanggal_lahir\", a.alamat as \"alamat\" from user_detail a join users b using(no_id) where u_tipe = 'MEMBER'";
    private final static String SQL_SELECT_ALL_JOIN_W_NOID = "select b.no_id as \"no_id\", a.nama_d as \"nama_depan\", a.nama_b as \"nama_belakang\", a.email as \"email\", "
            + "a.telp as \"telp\", a.tanggal_lahir as \"tanggal_lahir\", a.alamat as \"alamat\" from user_detail a join users b using(no_id) where b.no_id like ? and u_tipe = 'MEMBER'";
    private final static String SQL_SELECT_ALL_JOIN_W_NAMA = "select b.no_id as \"no_id\", a.nama_d as \"nama_depan\", a.nama_b as \"nama_belakang\", a.email as \"email\", "
            + "a.telp as \"telp\", a.tanggal_lahir as \"tanggal_lahir\", a.alamat as \"alamat\" from user_detail a join users b using(no_id) where a.nama_d like ? and u_tipe = 'MEMBER'";
    private final static String SQL_SELECT_ALL_JOIN_W_EMAIL = "select b.no_id as \"no_id\", a.nama_d as \"nama_depan\", a.nama_b as \"nama_belakang\", a.email as \"email\", "
            + "a.telp as \"telp\", a.tanggal_lahir as \"tanggal_lahir\", a.alamat as \"alamat\" from user_detail a join users b using(no_id) where a.email like ? and u_tipe = 'MEMBER'";
    private final static String SQL_SELECT_ALL_JOIN_W_TELP = "select b.no_id as \"no_id\", a.nama_d as \"nama_depan\", a.nama_b as \"nama_belakang\", a.email as \"email\", "
            + "a.telp as \"telp\", a.tanggal_lahir as \"tanggal_lahir\", a.alamat as \"alamat\" from user_detail a join users b using(no_id) where a.telp like ? and u_tipe = 'MEMBER'";
    private final static String SQL_SELECT_ALL_JOIN_WHERE = "select b.id_user as \"id_user\", b.no_id as \"no_id\", a.nama_d as \"nama_depan\", a.nama_b as \"nama_belakang\", a.email as \"email\", "
            + "a.telp as \"telp\", a.tanggal_lahir as \"tanggal_lahir\", a.alamat as \"alamat\" from user_detail a join users b using(id_user)";
    private final static String SQL_INSERT_USER = "insert into users (no_id, username, password, u_tipe, s_aktifasi) values (?, ?, ?, ?, ?)";
    private final static String SQL_INSERT_USER_DETAIL = "insert into user_detail(no_id, nama_d, nama_b, email, telp, alamat, tanggal_lahir) values (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_USER_DETAIL = "update user_detail set nama_d = ?, nama_b = ?, email= ?, telp = ?, tanggal_lahir = ?, alamat = ? where no_id = ? ";
    private final static String SQL_SELECT_COUNT = "select count(*) as count from users where u_tipe = 'MEMBER'";
    private final static String SQL_SELECT_IMAGE = "select u_foto from user_detail where id_user = ?";
    private final static String SQL_UPDATE_LAST_LOGIN = "update users set l_login = ? where no_id = ?";
    
    public int insertUser(Users user, UserDetail userDetails){
        
        PreparedStatement ps;
        ResultSet rs;
        // no_id, nama_depan, nama_belakang, email, telp, tanggal_lahir, alamat
        try {
            Koneksi.getConnection().setAutoCommit(false);
            ps = Koneksi.getConnection().prepareStatement(SQL_INSERT_USER);
            ps.setString(1, user.getNoId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());;
            ps.setString(4, user.getuTipe());
            ps.setString(5, user.getsAktifasi());
            
            if(ps.executeUpdate() > 0){
                
                ps = Koneksi.getConnection().prepareStatement(SQL_INSERT_USER_DETAIL);
                ps.setString(1, user.getNoId());
                ps.setString(2, userDetails.getNamaD());
                ps.setString(3, userDetails.getNamaB());
                ps.setString(4, userDetails.getEmail());
                ps.setString(5, userDetails.getTelp());
                ps.setString(6, userDetails.getAlamat());
                ps.setDate(7, new Date(userDetails.getTanggalLahir().getTime()));
                ps.executeUpdate();
                
                
                
            }
            Koneksi.getConnection().commit();
            
            
            return 1;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
    public int updateUser(UserDetail userDetail){
        
        PreparedStatement ps;
        // nama_depan, nama_belakang, email, telp, tanggal_lahir, alamat, 
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_UPDATE_USER_DETAIL);
            ps.setString(1, userDetail.getNamaD());
            ps.setString(2, userDetail.getNamaB());
            ps.setString(3, userDetail.getEmail());
            ps.setString(4, userDetail.getTelp());
            ps.setDate(5, new Date(userDetail.getTanggalLahir().getTime()));
            ps.setString(6, userDetail.getAlamat());
            ps.setString(7, userDetail.getNoId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return 0;
    }
    
    public Blob getImage(int id_user){
        
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_IMAGE);
            ps.setInt(1, id_user);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getBlob(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Users> getAll(){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JOIN);
            rs = ps.executeQuery();
            
            List<Users> list = new ArrayList<>();
            // no_id, nama, email, telp, tanggal_lahir
            while(rs.next()){
                UserDetail d_user = new UserDetail();
                d_user.setAlamat(rs.getString("alamat"));
                d_user.setEmail(rs.getString("email"));
                d_user.setNoId(rs.getString("no_id"));
                d_user.setNamaD(rs.getString("nama_depan"));
                d_user.setNamaB(rs.getString("nama_belakang"));
                d_user.setTanggalLahir(rs.getDate("tanggal_lahir"));
                d_user.setTelp(rs.getString("telp"));
                
                Users e_user = new Users();
                e_user.setNoId(rs.getString("no_id"));
                e_user.setDetail(d_user);
                list.add(e_user);
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Users> getAll(String q, int sType){
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            switch(sType){
                case 0:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JOIN_W_NOID);
                    break;
                case 1:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JOIN_W_NAMA);
                    break;
                case 2:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JOIN_W_EMAIL);
                    break;
                case 3:
                    ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JOIN_W_TELP);
                    break;
            }
            ps.setString(1, "%" + q + "%");
            rs = ps.executeQuery();
            
            List<Users> list = new ArrayList<>();
            // no_id, nama, email, telp, tanggal_lahir
            while(rs.next()){
                UserDetail d_user = new UserDetail();
                d_user.setAlamat(rs.getString("alamat"));
                d_user.setEmail(rs.getString("email"));
                d_user.setNoId(rs.getString("no_id"));
                d_user.setNamaD(rs.getString("nama_depan"));
                d_user.setNamaB(rs.getString("nama_belakang"));
                d_user.setTanggalLahir(rs.getDate("tanggal_lahir"));
                d_user.setTelp(rs.getString("telp"));
                
                
                Users e_user = new Users();
                e_user.setNoId(rs.getString("no_id"));
                e_user.setDetail(d_user);
                list.add(e_user);
            }
            
            return list;
            
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Users getUser(int idUser){
        try {
            
            PreparedStatement ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_ALL_JOIN_WHERE);
            ps.setInt(1, idUser);
            
            ResultSet rs = ps.executeQuery();
            
            Users e_user = null;
            
            if(rs.next()){
                UserDetail d_user = new UserDetail();
                d_user.setAlamat(rs.getString("alamat"));
                d_user.setEmail(rs.getString("email"));
                d_user.setIdUser(rs.getInt("id_user"));
                d_user.setNamaD(rs.getString("nama_depan"));
                d_user.setNamaB(rs.getString("nama_belakang"));
                d_user.setTanggalLahir(rs.getDate("tanggal_lahir"));
                d_user.setTelp(rs.getString("telp"));
                
                e_user = new Users();
                e_user.setIdUser(rs.getInt("id_user"));
                e_user.setNoId(rs.getString("no_id"));
                e_user.setUsername(rs.getString("username"));
                e_user.setlLogin(rs.getDate("l_login"));
                e_user.setuTipe(rs.getString("u_tipe"));
                e_user.setDetail(d_user);
            }
            
            return e_user;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Users getUser(Users user){
        
        try {
            
            PreparedStatement ps = Koneksi.getConnection().prepareStatement(SQL_LOGIN);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            
            ResultSet rs = ps.executeQuery();
            
            Users e_user = null;
            
            if(rs.next()){
                e_user = new Users();
                e_user.setNoId(rs.getString("no_id"));
                e_user.setUsername(rs.getString("username"));
                e_user.setlLogin(rs.getDate("l_login"));
                e_user.setuTipe(rs.getString("u_tipe"));
                
                ps = Koneksi.getConnection().prepareStatement(SQL_UPDATE_LAST_LOGIN);
                ps.setDate(1, new Date(new java.util.Date().getTime()));
                ps.setString(2, rs.getString("no_id"));
                ps.executeUpdate();
                
            }
            
            return e_user;
            
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public int getSelectAllCount(){
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_SELECT_COUNT);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
