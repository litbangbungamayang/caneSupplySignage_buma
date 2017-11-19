/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.view;

import java.awt.Color;
import java.awt.Component;
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
            c.setBackground(new Color(204, 204, 204));
            c.setForeground(Color.darkGray);
        } else {
            c.setBackground(Color.white);
            c.setForeground(Color.darkGray);
        }
        return c;
    }
}
