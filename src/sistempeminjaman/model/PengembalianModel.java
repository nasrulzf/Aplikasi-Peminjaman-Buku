/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistempeminjaman.db.Koneksi;
import sistempeminjaman.entity.Pengembalian;

/**
 *
 * @author Nasrul
 */
public class PengembalianModel {
    
    private static final String SQL_INSERT = "insert into pengembalian (id_peminjaman, tanggal, denda) values (?, ?, ?)";
    private static final String SQL_UPDATE_STAT_PEMINJAMAN = "update peminjaman set status = '1' where id_peminjaman = ?";
    
    public int insert(Pengembalian pengembalian){
        
        PreparedStatement ps;
        
        try {
            ps = Koneksi.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, pengembalian.getIdPeminjaman());
            ps.setDate(2, new java.sql.Date(pengembalian.getTanggal().getTime()));
            ps.setFloat(3, pengembalian.getDenda());
            
            if(ps.executeUpdate() > 0){
                ps = Koneksi.getConnection().prepareStatement(SQL_UPDATE_STAT_PEMINJAMAN);
                ps.setInt(1, pengembalian.getIdPeminjaman());
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {             JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            Logger.getLogger(PengembalianModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
