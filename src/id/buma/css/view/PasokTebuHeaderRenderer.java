/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public class PasokTebuHeaderRenderer extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        JLabel lbl;
        lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        lbl.setFont(new Font("Consolas", Font.PLAIN,26));
        lbl.setForeground(Color.WHITE);
        lbl.setBackground(new Color(40,40,40));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        return lbl;
    }
    
}
