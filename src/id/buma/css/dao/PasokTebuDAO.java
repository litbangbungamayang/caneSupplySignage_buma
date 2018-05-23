/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.dao;

import id.buma.css.model.PasokTebu;
import java.util.List;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public interface PasokTebuDAO {
    
    public List<PasokTebu> getAllPasokTebu();
    
    public List<PasokTebu> getPagedPasokTebu(int pageIndex, int maxRow);
    
    public java.sql.Date getNewestDate();
    
}
