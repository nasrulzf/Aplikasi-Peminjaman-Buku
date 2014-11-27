/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.view;

import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nasrul
 */
public class FormDialog extends javax.swing.JDialog {
    
    public FormDialog(Frame frame, boolean modal){
       super(frame, modal);
    }
    
    public FormDialog(Dialog frame, boolean modal){
       super(frame, modal);
    }
    
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    
}
