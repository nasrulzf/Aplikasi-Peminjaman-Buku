/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistempeminjaman.view.buku;

import com.toedter.calendar.JYearChooser;
import java.awt.Dialog;
import java.awt.TextField;
import java.io.File;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import sistempeminjaman.controller.BukuController;
import sistempeminjaman.controller.KategoriController;
import sistempeminjaman.entity.Buku;
import sistempeminjaman.entity.Kategori;
import sistempeminjaman.model.BukuModel;
import sistempeminjaman.view.FormDialog;
import sistempeminjaman.view.kategori.FormViewKategori;

/**
 *
 * @author Nasrul
 */
public class FormViewBuku extends FormDialog {

    public final static int AKSI_TAMBAH = 0;
    public final static int AKSI_EDIT = 1;
    
    private Buku buku;
    private BukuController controller;
    private KategoriController kController;
    private File imgFile;
    
    private List<Kategori> list;
    
    private int inputMode = AKSI_TAMBAH;
    
    /**
     * Creates new form FormViewBuku
     */
    public FormViewBuku(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public FormViewBuku(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    
    public void tampil(){
        buttonPinjam.setVisible(false);
        setController(new BukuController(this));
        kController = new KategoriController(this);
        kController.setKategoriBuku();
        controller.initFormBuku();
        pack();
        setLocationRelativeTo(super.getParent());
        setVisible(true);
    }
    
    public void tampil(BukuController controller){
        buttonPinjam.setVisible(false);
        setController(controller);
        controller.setDialogType(this);
        controller.isTransaksi();
        kController = new KategoriController(this);
        kController.setKategoriBuku();
        controller.initFormBuku();
        pack();
        setLocationRelativeTo(super.getParent());
        setVisible(true);
        
    }
    
    
    
    public void setButtonPinjam(boolean status){
        buttonPinjam.setVisible(status);
    }
    
    public void setController(BukuController controller){
        this.controller = controller;
    }

    public void setAksi(int tipeAksi){
        setMode(tipeAksi);
    }
    
    public void setAksi(int tipeAksi, Buku data){
        setMode(tipeAksi);
        setData(data);
        fill();
    }
    
    public void setData(Buku data){
        this.buku = data;
    }
    
    private void setMode(int mode){
        inputMode = mode;    
    }
    
    private void fill(){
        getTeksIsbn().setText(buku.getNoIsbn());
        getTeksJudul().setText(buku.getJudul());
        getPenerbit().setText(buku.getPenerbit());
        getKategori().setSelectedItem(buku.getKategori().getNamaKategori());
        getYearChooser().setYear(buku.getTahunTerbit());
        
    }
    
    public void setKategori(List<Kategori> list){
        this.list = list;
        changeCbKategori();
    }
    
    private void changeCbKategori(){
        
        getKategori().removeAllItems();
        
        for(Kategori kategori : list){
            
            getKategori().addItem(kategori.getNamaKategori());
            
        }
        
    }
    
    private void init(){
        kController.setKategoriBuku();
    }
    
    public void reload(boolean cl){
        init();
        if (cl) clear();
    }
    
    private void clear(){
        getTeksIsbn().setText("");
        getTeksJudul().setText("");
        getPenerbit().setText("");
        getKategori().setSelectedIndex(0);
    }
    
    public JTextField getTeksIsbn(){
        return teksISBN;
    }
    
    public JTextField getTeksJudul(){
        return teksJudul;
    }
    
    public JTextField getPenerbit(){
        return teksPenerbit;
    }
    
    public JComboBox getKategori(){
        return cbKategori;
    }
    
    public File getFile(){
        return imgFile;
    }
    
    public JYearChooser getYearChooser(){
        return yearChooser;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        teksISBN = new javax.swing.JTextField();
        teksJudul = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        yearChooser = new com.toedter.calendar.JYearChooser();
        cbKategori = new javax.swing.JComboBox();
        btnTambahKategori = new javax.swing.JButton();
        teksPenerbit = new javax.swing.JTextField();
        buttonCek = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        buttonSimpan = new javax.swing.JButton();
        buttonBatal = new javax.swing.JButton();
        buttonPinjam = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("No ISBN : ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Judul : ");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Penerbit : ");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tahun Terbit : ");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Kategori : ");

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(yearChooser, java.awt.BorderLayout.CENTER);

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnTambahKategori.setText("+");
        btnTambahKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKategoriActionPerformed(evt);
            }
        });

        buttonCek.setText("Ok");
        buttonCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(teksJudul)
                    .addComponent(teksPenerbit)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbKategori, 0, 176, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTambahKategori)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(teksISBN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCek)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(teksISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCek))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(teksJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(teksPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahKategori))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        buttonSimpan.setText("Simpan");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonBatal.setText("Batal");

        buttonPinjam.setText("Pinjam");
        buttonPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPinjamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonPinjam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(buttonBatal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSimpan)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSimpan)
                    .addComponent(buttonBatal)
                    .addComponent(buttonPinjam))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        // TODO add your handling code here:
        
        if(controller.validasi()){
            
            if(inputMode == AKSI_TAMBAH){
                buku = new Buku();
            }
            
            buku.setNoIsbn(getTeksIsbn().getText());
            buku.setJudul(getTeksJudul().getText());
            buku.setPenerbit(getPenerbit().getText());
            buku.setTahunTerbit(yearChooser.getYear());
            buku.setKategori(list.get(getKategori().getSelectedIndex()));
            
            controller.simpan(buku, inputMode);
        }
        
    }//GEN-LAST:event_buttonSimpanActionPerformed

    private void btnTambahKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKategoriActionPerformed
        // TODO add your handling code here:
        FormViewKategori fvk = new FormViewKategori(this, true);
        fvk.tampil(kController);
    }//GEN-LAST:event_btnTambahKategoriActionPerformed

    private void buttonPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPinjamActionPerformed
        // TODO add your handling code here:
        
        controller.setPilihBuku(buku);
        
    }//GEN-LAST:event_buttonPinjamActionPerformed
    
    public void initInputForm(boolean status, boolean isbn){
        
        teksJudul.setEnabled(status);
        teksPenerbit.setEnabled(status);
        yearChooser.setEnabled(status);
        cbKategori.setEnabled(status);
        teksISBN.setEnabled(isbn);
        
        teksJudul.setText("");
        teksPenerbit.setText("");
        yearChooser.setYear(2014);
        cbKategori.setSelectedIndex(0);
        
    }
    
    public void initInputForm(Buku buku, boolean status, boolean isbn){
        
        initInputForm(status, isbn);
        
        teksJudul.setText(buku.getJudul());
        teksPenerbit.setText(buku.getPenerbit());
        yearChooser.setYear(buku.getTahunTerbit());
        cbKategori.setSelectedItem(buku.getKategori().getNamaKategori());
        
    }
    
    private void buttonCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCekActionPerformed
        // TODO add your handling code here:
        
       controller.cekBuku();
        
    }//GEN-LAST:event_buttonCekActionPerformed

    public int getMode(){
        return inputMode;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTambahKategori;
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonCek;
    private javax.swing.JButton buttonPinjam;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JComboBox cbKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField teksISBN;
    private javax.swing.JTextField teksJudul;
    private javax.swing.JTextField teksPenerbit;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
