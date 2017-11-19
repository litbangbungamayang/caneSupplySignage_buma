/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.dao;

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
    private MainWindow mw;

    @Override
    public List<PasokTebu> getAllPasokTebu(Date periode) {
        List<PasokTebu> lpt = new ArrayList<>();
        try {
            if (DbTimbanganConnectionManager.isConnect()){
                /*
                String sql = "SELECT T.TSTR AS TSTR, A.IDAFD AS AFDELING, R.RAYON AS RAYON,"
                        + "(SELECT COUNT(NUMERATOR) FROM TIMBANG TSUB WHERE "
                        + "A.IDAFD=TSUB.IDAFD "
                        + "AND TSUB.PERIODE=?) AS RIT_MASUK,"
                        + "(SELECT COUNT(NUMERATOR) FROM TIMBANG TSUB WHERE "
                        + "A.IDAFD=TSUB.IDAFD "
                        + "AND TSUB.BERAT1>0 AND TSUB.BERAT2=0"
                        + "AND TSUB.PERIODE=?) AS RIT_BRUTO,"
                        + "(SELECT COUNT(NUMERATOR) FROM TIMBANG TSUB WHERE "
                        + "A.IDAFD=TSUB.IDAFD "
                        + "AND TSUB.NETTO>0"
                        + "AND TSUB.PERIODE=?) AS RIT_NETTO,"
                        + "(SELECT SUM(NETTO/10) FROM TIMBANG TSUB WHERE "
                        + "A.IDAFD=TSUB.IDAFD "
                        + "AND TSUB.NETTO>0 AND TSUB.PERIODE=?) AS TONASE "
                        + "FROM TIMBANG T INNER JOIN AFDELING A ON T.IDAFD=A.IDAFD "
                        + "INNER JOIN RAYON R ON A.IDRAYON=R.IDRAYON "
                        + "WHERE T.PERIODE=? "
                        + "GROUP BY T.TSTR,R.RAYON,A.IDAFD "
                        + "ORDER BY T.TSTR,R.RAYON,A.IDAFD ";
                PreparedStatement ps = DbTimbanganConnectionManager.getConnection().prepareStatement(sql);
                java.sql.Date periodeSql = new java.sql.Date(periode.getTime());
                ps.setDate(1, periodeSql);
                ps.setDate(2, periodeSql);
                ps.setDate(3, periodeSql);
                ps.setDate(4, periodeSql);
                ps.setDate(5, periodeSql);
                ResultSet rs = ps.executeQuery();
                */
                String callSql = "exec MONITORING_PASOK ?";
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
                Double tonTs = 0.0;
                Double tonTr = 0.0;
                Double tonKut = 0.0;
                Double tonTsi = 0.0;
                Double tonTotal = 0.0;
                int ritMasukTs = 0;
                int ritMasukTr = 0;
                int ritMasukKut = 0;
                int ritMasukTsi = 0;
                int ritMasukTotal = 0;
                int ritBrutoTs = 0;
                int ritBrutoTr = 0;
                int ritBrutoKut = 0;
                int ritBrutoTsi = 0;
                int ritBrutoTotal = 0;
                int ritNettoTs = 0;
                int ritNettoTr = 0;
                int ritNettoKut = 0;
                int ritNettoTsi = 0;
                int ritNettoTotal = 0;
                while (rs.next()){
                    PasokTebu pt = new PasokTebu(
                            rs.getString("AFDELING"), 
                            rs.getString("RAYON"), 
                            rs.getString("TSTR"), 
                            rs.getDouble("TONASE"), 
                            rs.getInt("RIT_MASUK"), 
                            rs.getInt("RIT_BRUTO"), 
                            rs.getInt("RIT_NETTO"));
                    lpt.add(pt);
                    if (rs.getString("TSTR").equals("1")){
                        tonTs += rs.getDouble("TONASE");
                        ritMasukTs += rs.getInt("RIT_MASUK");
                        ritBrutoTs += rs.getInt("RIT_BRUTO");
                        ritNettoTs += rs.getInt("RIT_NETTO");
                    }
                    if (rs.getString("TSTR").equals("2")){
                        tonTr += rs.getDouble("TONASE");
                        ritMasukTr += rs.getInt("RIT_MASUK");
                        ritBrutoTr += rs.getInt("RIT_BRUTO");
                        ritNettoTr += rs.getInt("RIT_NETTO");
                    }
                    if (rs.getString("TSTR").equals("3")){
                        tonKut += rs.getDouble("TONASE");
                        ritMasukKut += rs.getInt("RIT_MASUK");
                        ritBrutoKut += rs.getInt("RIT_BRUTO");
                        ritNettoKut += rs.getInt("RIT_NETTO");
                    }
                    if (rs.getString("TSTR").equals("4")){
                        tonTsi += rs.getDouble("TONASE");
                        ritMasukTsi += rs.getInt("RIT_MASUK");
                        ritBrutoTsi += rs.getInt("RIT_BRUTO");
                        ritNettoTsi += rs.getInt("RIT_NETTO");
                    }
                    tonTotal = tonTs + tonTr + tonKut + tonTsi;
                    ritMasukTotal = ritMasukTs + ritMasukTr + ritMasukKut + ritMasukTsi;
                    ritBrutoTotal = ritBrutoTs + ritBrutoTr + ritBrutoKut + ritBrutoTsi;
                    ritNettoTotal = ritNettoTs + ritNettoTr + ritNettoKut + ritNettoTsi;
                }
                lpt.add(new PasokTebu("","TS","",tonTs,ritMasukTs,ritBrutoTs,ritNettoTs));
                lpt.add(new PasokTebu("","TR","",tonTr,ritMasukTr,ritBrutoTr,ritNettoTr));
                lpt.add(new PasokTebu("","KUT","",tonKut,ritMasukKut,ritBrutoKut,ritNettoKut));
                lpt.add(new PasokTebu("","TSI","",tonTsi,ritMasukTsi,ritBrutoTsi,ritNettoTsi));
                lpt.add(new PasokTebu("","TOTAL","",tonTotal,ritMasukTotal,ritBrutoTotal,ritNettoTotal));
            } else {
                
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(mw, "Error getAllPasokTebu!\nError code : " +
                    e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
        return lpt;
    }

    @Override
    public Date getNewestDate() {
        String sql = "SELECT MAX(PERIODE) AS PERIODE FROM TIMBANG WHERE PERIODE > ?";
        java.sql.Date tglBaru = null;
        if (DbTimbanganConnectionManager.isConnect()){
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
        } else JOptionPane.showMessageDialog(mw, "Database Tidak Terkoneksi");
        return tglBaru;
    }

    @Override
    public List<PasokTebu> getPagedPasokTebu(int pageIndex, int maxRow) {
        List<PasokTebu> plpt = new ArrayList<>();
        List<PasokTebu> lpt = getAllPasokTebu(getNewestDate());
        for(int i = 0; i <= maxRow - 1; i++){
            int actualIndex = ((pageIndex - 1) * (maxRow - 1)) + i;
            if (actualIndex < lpt.size()){
                plpt.add(lpt.get(actualIndex));
            }
        }
        return plpt;
    }
    
}
