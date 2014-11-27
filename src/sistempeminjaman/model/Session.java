/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.model;

import java.util.Date;
import sistempeminjaman.entity.Users;

/**
 *
 * @author Nasrul
 */
public class Session {
    
    private static Users user;
    
    public static void setData(Users users){
        if(users != null){
            user = users;
        }
    }
    
    public static Users getData(){
        return user;
    }
    
}
