/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Bayu Anandavi Muhardika
 */
public class PasokTebuRowRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column){
        Component c = super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
        try { 
            c.setFont(Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getClassLoader().getResource("lib/consola.ttf").openStream()).deriveFont(Font.PLAIN,36));
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(PasokTebuRowRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.getParent().setBackground(new Color(90,90,90));
        switch (column){
            case 0 :
                this.setHorizontalAlignment(CENTER);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
                break;
            case 1 :
                this.setHorizontalAlignment(CENTER);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
                break;
            case 2 :
                this.setHorizontalAlignment(CENTER);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
                break;
            case 3 :
                this.setHorizontalAlignment(CENTER);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
                break;
            case 4 :
                this.setHorizontalAlignment(CENTER);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
                break;
            case 5 :
                this.setHorizontalAlignment(RIGHT);
                table.getColumnModel().getColumn(column).setCellRenderer(this);
                break;
        }
        if (row % 2 == 0){
            //c.setBackground(new Color(204, 204, 204));
            c.setBackground(new Color(51,51,51));
            c.setForeground(Color.yellow);
        } else {
            c.setBackground(new Color(30,30,30));
            c.setForeground(Color.yellow);
        }
        return c;
    }
}
