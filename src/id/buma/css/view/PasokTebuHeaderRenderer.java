/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.view;

import id.buma.css.controller.PasokTebuController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            //lbl.setFont(new Font("Consolas", Font.PLAIN,26));
            lbl.setFont(Font.createFont(Font.TRUETYPE_FONT, 
                    getClass().getClassLoader().getResource("lib/consola.ttf").openStream()).deriveFont(Font.PLAIN,26));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(PasokTebuHeaderRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl.setForeground(Color.WHITE);
        lbl.setBackground(new Color(40,40,40));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        return lbl;
    }
    
}
