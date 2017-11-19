/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.view;

import id.buma.css.model.PasokTebu;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public class PasokTebuTableModel extends AbstractTableModel{

    private final List<PasokTebu> pasokTebu;
    
    public PasokTebuTableModel(List<PasokTebu> pasokTebu){
        this.pasokTebu = pasokTebu;
    }
    
    private final String[] columnNames = new String[]{
        "RAYON",
        "AFD",
        "ANTRIAN",
        "CANEYARD",
        "TOTAL RIT",
        "TON TEBU"
    };
    
    @Override
    public int getRowCount() {
        return pasokTebu.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int rowLimit = 10;
        PasokTebu pt = pasokTebu.get(rowIndex);
        switch (columnIndex){
            case 0 :
                return pt.getRayon();
            case 1 :
                return pt.getAfdeling();
            case 2 :
                return pt.getRitJalur();
            case 3 :
                return pt.getRitBruto();
            case 4 :
                return pt.getRitMasuk();
            case 5 :
                DecimalFormatSymbols decSym = new DecimalFormatSymbols(Locale.UK);
                decSym.setDecimalSeparator(',');
                decSym.setGroupingSeparator('.');
                DecimalFormat df = new DecimalFormat("###,##0.00", decSym);
                return df.format(pt.getTonase());
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];   
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}
