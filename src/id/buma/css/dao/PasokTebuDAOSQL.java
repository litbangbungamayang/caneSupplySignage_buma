/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.dao;

import id.buma.css.controller.PasokTebuController;
import id.buma.css.database.DbTimbanganConnectionManager;
import id.buma.css.model.PasokTebu;
import id.buma.css.view.MainWindow;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public class PasokTebuDAOSQL implements PasokTebuDAO{
    
    private  MainWindow mw;
    

    @Override
    public Date getNewestDate() {
        String sql = "SELECT MAX(PERIODE) AS PERIODE FROM TIMBANG WHERE PERIODE > ?";
        java.sql.Date tglBaru = null;
        try{
            PreparedStatement ps = DbTimbanganConnectionManager.getConnection().prepareStatement(sql);
            java.sql.Date tglAwal = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2013-08-01").getTime());
            ps.setDate(1, tglAwal);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                 tglBaru = rs.getDate("PERIODE");
            }
        } catch (Exception e){  
            JOptionPane.showMessageDialog(mw, e.toString());
        }
        return tglBaru;
    }

    @Override
    public List<PasokTebu> getPagedPasokTebu(int pageIndex, int maxRow) {
        List<PasokTebu> plpt = new ArrayList<>();
        java.sql.Date tglBaru = getNewestDate();
        List<PasokTebu> lptDesa = getAllPasokTebuTR(tglBaru);
        List<PasokTebu> lptTR = getSummaryPasokTR(tglBaru);
        List<PasokTebu> lpt = new ArrayList<>();
        lpt.addAll(lptDesa);
        lpt.addAll(lptTR);
        for(int i = 0; i <= maxRow - 1; i++){
            int actualIndex = ((pageIndex - 1) * (maxRow - 1)) + i;
            if (actualIndex < lpt.size()){
                plpt.add(lpt.get(actualIndex));
            }
        }
        return plpt;
    }

    @Override
    public List<PasokTebu> getAllPasokTebuTR(Date periode) {
        List<PasokTebu> lpt = new ArrayList<>();
        try {
            if (DbTimbanganConnectionManager.isConnect()){
                String callSql = "exec MONITORING_PASOK_TR ?";
                java.sql.Date periodeSql = new java.sql.Date(periode.getTime());
                CallableStatement cst = DbTimbanganConnectionManager.getConnection().prepareCall(callSql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                cst.setDate(1, periodeSql);
                boolean results = cst.execute();
                int rowsAffected = 0;
                ResultSet rs = null;
                while (results || rowsAffected != -1){
                    if (results){
                        rs = cst.getResultSet();
                        break;
                    } else {
                        rowsAffected = cst.getUpdateCount();
                    }
                    results = cst.getMoreResults();
                }
                while (rs.next()){
                    PasokTebu pt = new PasokTebu(
                            rs.getString("IDAFD"), 
                            rs.getString("KODE_WILAYAH"),
                            rs.getDouble("TONASE"), 
                            rs.getInt("RIT_MASUK"), 
                            rs.getInt("RIT_BRUTO"), 
                            rs.getInt("RIT_NETTO"));
                    lpt.add(pt);
                }
            } else {
                mw.getLblPengumuman().setText("Database tidak terhubung!");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(mw, "Error getAllPasokTebuTR!\n Error code : " +
                    e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
        return lpt;
    }

    @Override
    public List<PasokTebu> getSummaryPasokTR(Date periode) {
        List<PasokTebu> lpt = new ArrayList<>();
        try {
            if (DbTimbanganConnectionManager.isConnect()){
                String callSql = "exec SUMMARY_AFD_TR ?";
                java.sql.Date periodeSql = new java.sql.Date(periode.getTime());
                CallableStatement cst = DbTimbanganConnectionManager.getConnection().prepareCall(callSql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                cst.setDate(1, periodeSql);
                boolean results = cst.execute();
                int rowsAffected = 0;
                ResultSet rs = null;
                while (results || rowsAffected != -1){
                    if (results){
                        rs = cst.getResultSet();
                        break;
                    } else {
                        rowsAffected = cst.getUpdateCount();
                    }
                    results = cst.getMoreResults();
                }
                int jmlRitMasuk = 0;
                int jmlRitBruto = 0;
                int jmlRitNetto = 0;
                double jmlTonase = 0.0;
                while (rs.next()){
                    PasokTebu pt = new PasokTebu(
                            "", 
                            "JUMLAH AFD " + rs.getString("IDAFD"),
                            rs.getDouble("TONASE"), 
                            rs.getInt("RIT_MASUK"), 
                            rs.getInt("RIT_BRUTO"), 
                            rs.getInt("RIT_NETTO"));
                    jmlRitMasuk += rs.getInt("RIT_MASUK");
                    jmlRitBruto += rs.getInt("RIT_BRUTO");
                    jmlRitNetto += rs.getInt("RIT_NETTO");
                    jmlTonase += rs.getDouble("TONASE");
                    lpt.add(pt);
                }
                lpt.add(new PasokTebu("", "JUMLAH TR", jmlTonase, jmlRitMasuk, jmlRitBruto, jmlRitNetto));
            } else {
                //mw.getLblPengumuman().setText("Database tidak terhubung!");
                //ptc.setPengumuman("Database tidak terhubung!");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(mw, "Error getSummaryPasokTR!\n Error code : " +
                    e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
        return lpt;
    }
    
}
