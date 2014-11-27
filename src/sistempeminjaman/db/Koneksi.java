/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.db;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nasrul
 */
public class Koneksi {
    
    private static Connection conn;
    
    public static Connection getConnection(){
        
        if(conn == null){
            try {
                DriverManager.registerDriver(new Driver());
                conn = DriverManager.getConnection("jdbc:mysql://localhost/dbs_pinjaman", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
        
    }
    
}
