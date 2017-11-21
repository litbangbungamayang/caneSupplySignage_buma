/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.view;

import id.buma.css.controller.PasokTebuController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.Timer;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public class MainWindow extends javax.swing.JFrame {
    
    private final PasokTebuController ptc = new PasokTebuController(this);

    /**
     * Creates new form MainWindow
     */
    
    public MainWindow() {
        initComponents();
        ptc.setVersionStatus();
        ptc.setTableHeader();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        lblTanggal.setVisible(false);
        ptc.setTableModel(1);
        
        /** TIMER untuk jam dan trigger update tabel **/
        Timer tim = new Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ptc.timerStarting();
            }
        });
        tim.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainWindowAtas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblVersi = new javax.swing.JLabel();
        pnlMainWindowTengah = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPasok = new javax.swing.JTable();
        pnlMainWindowBawah = new javax.swing.JPanel();
        lblPengumuman = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        setForeground(java.awt.Color.black);
        setLocationByPlatform(true);
        setName("frmUtama"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        pnlMainWindowAtas.setBackground(new java.awt.Color(0, 51, 153));
        pnlMainWindowAtas.setPreferredSize(new java.awt.Dimension(628, 100));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Informasi Pasok Tebu Pabrik Gula Bungamayang");

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(255, 255, 255));
        lblTanggal.setText("20 / 06 /2017  20:00");

        lblVersi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblVersi.setForeground(new java.awt.Color(255, 255, 255));
        lblVersi.setText("[version]");

        javax.swing.GroupLayout pnlMainWindowAtasLayout = new javax.swing.GroupLayout(pnlMainWindowAtas);
        pnlMainWindowAtas.setLayout(pnlMainWindowAtasLayout);
        pnlMainWindowAtasLayout.setHorizontalGroup(
            pnlMainWindowAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainWindowAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainWindowAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainWindowAtasLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addGroup(pnlMainWindowAtasLayout.createSequentialGroup()
                        .addComponent(lblTanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVersi)))
                .addContainerGap())
        );
        pnlMainWindowAtasLayout.setVerticalGroup(
            pnlMainWindowAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainWindowAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainWindowAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTanggal)
                    .addComponent(lblVersi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMainWindowTengah.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        tblPasok.setBackground(new java.awt.Color(51, 51, 51));
        tblPasok.setFont(new java.awt.Font("Trebuchet MS", 0, 30)); // NOI18N
        tblPasok.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPasok.setEnabled(false);
        tblPasok.setGridColor(new java.awt.Color(255, 255, 255));
        tblPasok.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblPasok.setName("tblPasok"); // NOI18N
        tblPasok.setRowHeight(40);
        tblPasok.setShowHorizontalLines(false);
        tblPasok.setShowVerticalLines(false);
        tblPasok.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(tblPasok);

        javax.swing.GroupLayout pnlMainWindowTengahLayout = new javax.swing.GroupLayout(pnlMainWindowTengah);
        pnlMainWindowTengah.setLayout(pnlMainWindowTengahLayout);
        pnlMainWindowTengahLayout.setHorizontalGroup(
            pnlMainWindowTengahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        pnlMainWindowTengahLayout.setVerticalGroup(
            pnlMainWindowTengahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pnlMainWindowBawah.setBackground(new java.awt.Color(0, 51, 153));
        pnlMainWindowBawah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pnlMainWindowBawah.setPreferredSize(new java.awt.Dimension(100, 50));

        lblPengumuman.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPengumuman.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlMainWindowBawahLayout = new javax.swing.GroupLayout(pnlMainWindowBawah);
        pnlMainWindowBawah.setLayout(pnlMainWindowBawahLayout);
        pnlMainWindowBawahLayout.setHorizontalGroup(
            pnlMainWindowBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainWindowBawahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPengumuman)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainWindowBawahLayout.setVerticalGroup(
            pnlMainWindowBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainWindowBawahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPengumuman)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainWindowAtas, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
            .addComponent(pnlMainWindowTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMainWindowBawah, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMainWindowAtas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMainWindowTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnlMainWindowBawah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPengumuman;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblVersi;
    private javax.swing.JPanel pnlMainWindowAtas;
    private javax.swing.JPanel pnlMainWindowBawah;
    private javax.swing.JPanel pnlMainWindowTengah;
    private javax.swing.JTable tblPasok;
    // End of variables declaration//GEN-END:variables

    public JTable getTblPasok(){
        return tblPasok;
    }
    
    public JLabel getLblTanggal(){
        return lblTanggal;
    }
    
    public JLabel getLblVersi(){
        return lblVersi;
    }
    
    public JLabel getLblPengumuman(){
        return lblPengumuman;
    }

    
}

