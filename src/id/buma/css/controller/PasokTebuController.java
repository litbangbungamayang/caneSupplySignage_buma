/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.controller;

import id.buma.css.dao.PasokTebuDAO;
import id.buma.css.dao.PasokTebuDAOSQL;
import id.buma.css.view.MainWindow;
import id.buma.css.view.PasokTebuHeaderRenderer;
import id.buma.css.view.PasokTebuRowRenderer;
import id.buma.css.view.PasokTebuTableModel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 * Version history
 * 
 * v.0.20112017.0005
 * + first production build
 * + basic info display
 * 
 * v.0.20112017.1654
 * + modifikasi tampilan tabel
 * 
 * v.1.23052018.1736
 * + Perubahan database menyesuaikan SIMPG
 * 
 * v.1.24052018.0439
 * + Perubahan setelan jam
 * 
 * v.1.25052018.0900
 * + Perubahan format tampilan, grouping per rayon
 * 
 * v.1.02082018.0129
 * + Perubahan format tampilan untuk Raspberry Pi
 */

public class PasokTebuController {
    private final String appVersion = "v.1.02082018.0129";
    
    private final MainWindow mw;
    
    private final PasokTebuDAO pasokTebuDao = new PasokTebuDAOSQL();
    
    private final PasokTebuHeaderRenderer pthr = new PasokTebuHeaderRenderer();
    
    private final PasokTebuRowRenderer ptrr = new PasokTebuRowRenderer();
    
    private int counter = 1;
    
    private int pageIndex = 1;
    
    private final int rowPerPage = 14;
    
    private final int maxRow = new PasokTebuTableModel(pasokTebuDao.getAllPasokTebu()).getRowCount();
    
    private final int maxPage = (maxRow / rowPerPage) + 1;
    
    public void setPengumuman(String isi){
        mw.getLblPengumuman().setText(isi);
    }
    
    public PasokTebuController (MainWindow mw){
        this.mw = mw;
    }
    
    public void setFont(){
        try { 
            Font segoeUi = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getClassLoader().getResource("lib/segoeui.ttf").openStream());
            mw.getLblJudul().setFont(segoeUi.deriveFont(Font.PLAIN,36));
            mw.getLblTanggal().setFont(segoeUi.deriveFont(Font.PLAIN,16));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(PasokTebuController.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void setVersionStatus(){
        mw.getLblVersi().setText(appVersion);
    }
    
    public void setTableHeader(){
        JTableHeader th = mw.getTblPasok().getTableHeader();
        th.setDefaultRenderer(pthr);
        mw.getTblPasok().setDefaultRenderer(Object.class, ptrr);
    }
    
    public void setTableModel(int pageIndex){
        PasokTebuTableModel pttm = new PasokTebuTableModel(pasokTebuDao.getPagedPasokTebu(pageIndex, rowPerPage));
        mw.getTblPasok().setModel(pttm);
    }
    
    public void timerStarting(){
        counter++;
        if (counter == 20){
            pageIndex++;
            if (pageIndex <= maxPage){
                setTableModel(pageIndex);
            } else {
                pageIndex = 1;
                setTableModel(pageIndex);
            }
            counter = 0;
        }
        JLabel lblTanggal = mw.getLblTanggal();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        
        /** UNTUK KEPERLUAN DEBUGGING
        lblTanggal.setText(df.format(now) + " " + counter + "; Page Counter = " + 
                pageIndex + "; Max Page = " + maxPage);
        **/
        
        lblTanggal.setVisible(true);
        lblTanggal.setText(df.format(now));
    }
    
}
