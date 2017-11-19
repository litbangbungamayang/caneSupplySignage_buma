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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public class PasokTebuController {
    
    private final MainWindow mw;
    private final PasokTebuDAO pasokTebuDao = new PasokTebuDAOSQL();
    private final PasokTebuHeaderRenderer pthr = new PasokTebuHeaderRenderer();
    private final PasokTebuRowRenderer ptrr = new PasokTebuRowRenderer();
    private int counter = 1;
    
    public PasokTebuController (MainWindow mw){
        this.mw = mw;
    }
    
    
    public void setTableHeader(){
        JTableHeader th = mw.getTblPasok().getTableHeader();
        th.setDefaultRenderer(pthr);
        mw.getTblPasok().setDefaultRenderer(Object.class, ptrr);
    }
    
    public void setTableModel(){
        PasokTebuTableModel pttm = new PasokTebuTableModel(pasokTebuDao.getAllPasokTebu(pasokTebuDao.getNewestDate()));
        mw.getTblPasok().setModel(pttm);
    }
    
    public void timerStarting(){
        counter++;
        if (counter == 30){
            setTableModel();
            counter = 0;
        }
        JLabel lblTanggal = mw.getLblTanggal();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        lblTanggal.setText(df.format(now));
    }
    
    public void tablePage(){
        TableRowSorter tblSorter = new TableRowSorter(mw.getTblPasok().getModel());
        
    }
    
    private RowFilter<PasokTebuTableModel,Integer> showFilter(final int itemsPerPage, final int target){
        return new RowFilter<PasokTebuTableModel, Integer>() {
            @Override
            public boolean include(RowFilter.Entry<? extends PasokTebuTableModel, ? extends Integer> entry) {
                int ei = entry.getIdentifier();
                return (target*itemsPerPage <= ei && ei < target*itemsPerPage+itemsPerPage);
            }
        };
    }
}
