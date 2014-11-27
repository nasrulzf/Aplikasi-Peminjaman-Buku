/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.view.buku;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sistempeminjaman.controller.BukuController;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.view.FormDialog;

/**
 *
 * @author Nasrul
 */
public class FormListBuku extends FormDialog {

    private BukuController controller;
    private List<Buku> list;
    
    /**
     * Creates new form FormListBuku
     * @param parent
     * @param modal
     */
    public FormListBuku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        controller = new BukuController(this);
    }
    
    public FormListBuku(java.awt.Dialog parent, boolean modal){
        super(parent, modal);
        initComponents();
        controller = new BukuController(this);
    }
    
    public void reload(){
        System.out.println("RELOAD");
        init();
    }
    
    private void init(){
        
        controller.loadInitLists();
    }
    
    public void tampil(BukuController controller){
        setController(controller);
        reload();
        pack();
        setLocationRelativeTo(super.getParent());
        setVisible(true);
    }
    
    private void setController(BukuController controller){
        this.controller = controller;
        this.controller.setDialogType(this);
    }
            
    public void tampil(){
        reload();
        pack();
        setLocationRelativeTo(super.getParent());
        setVisible(true);
    }
    
    private void setLabelInfo(String teksInfo){
        labelDesc.setText(teksInfo);
    }
    
    private void aktifRefresh(boolean status){
        buttonRefresh.setVisible(status);
    }
    
    public void setData(String teksInfo, List<Buku> list, boolean rfButton){
        System.out.println("SET BRO");
        setList(list);
        setLabelInfo(teksInfo);
        aktifRefresh(rfButton);
        reloadTable();
    }
    
    private void setList(List<Buku> list){
        this.list = list;
    }
    
    private void reloadTable(){
        // no, ISBN, judul, penerbit, tahun terbit
        
        Object[][] listData = new Object[list.size()][6];
        int i = 0;
        for(Buku buku : list){
            listData[i][0] = i+1;
            listData[i][1] = buku.getStrIdBuku();
            listData[i][2] = buku.getNoIsbn();
            listData[i][3] = buku.getJudul();
            listData[i][4] = buku.getPenerbit();
            listData[i][5] = buku.getTahunTerbit();
            System.out.println("judul : " + buku.getJudul());
            i++;           
        }
        
        String[] columnNames = new String[]{
            "No", "ID Buku", "No ISBN", "Judul", "Penerbit", "Tahun Terbit"
        };
        
        tableListBuku.setModel(new DefaultTableModel(listData, columnNames));
        
        tableListBuku.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        int[] arrWidth = {40, 100, 100, 150, 100, 100};
        int x = 0;
        for(int w : arrWidth){
            tableListBuku.getColumnModel().getColumn(x).setPreferredWidth(w);
            x++;
        }
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableListBuku = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        buttonTutup = new javax.swing.JButton();
        buttonTambah = new javax.swing.JButton();
        labelDesc = new javax.swing.JLabel();
        buttonCari = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        tableListBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableListBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableListBuku);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        buttonTutup.setText("Tutup");
        buttonTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTutupActionPerformed(evt);
            }
        });

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        labelDesc.setText("Menampilkan 1 - 10 dari total 1000 data");

        buttonCari.setText("Cari");
        buttonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariActionPerformed(evt);
            }
        });

        buttonRefresh.setText("X");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonTambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonTutup)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTutup, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(buttonTambah)
                    .addComponent(labelDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCari)
                    .addComponent(buttonRefresh))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
        // TODO add your handling code here:
        
        FormCariBuku formCariBuku = new FormCariBuku(this, true);
        formCariBuku.tampil(controller);
        
    }//GEN-LAST:event_buttonCariActionPerformed

    private void tableListBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListBukuMouseClicked
        // TODO add your handling code here:
        
        int z = tableListBuku.getSelectedRow();
        
        if(z != -1){
            FormViewBuku fvb = new FormViewBuku(this, true);
            fvb.setAksi(FormViewBuku.AKSI_EDIT, list.get(z));
            fvb.tampil(controller);
        }
        
    }//GEN-LAST:event_tableListBukuMouseClicked

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        // TODO add your handling code here:
        FormViewBuku fvb = new FormViewBuku(this, true);
        fvb.tampil(controller);
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTutupActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_buttonTutupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonTutup;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDesc;
    private javax.swing.JTable tableListBuku;
    // End of variables declaration//GEN-END:variables
}
