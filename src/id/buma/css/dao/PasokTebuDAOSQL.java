/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.css.dao;

import id.buma.css.database.DbTimbanganConnectionManager;
import id.buma.css.model.PasokTebu;
import id.buma.css.view.MainWindow;
import id.buma.spt.database.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public List<PasokTebu> getAllPasokTebu() {
        List<PasokTebu> lpt = new ArrayList<>();
        Connection conn = new DBConnection().getConn();
        String callSql = "call monitoring_pasok(?,?)";
        try (CallableStatement cst = conn.prepareCall(callSql)){
            if (DbTimbanganConnectionManager.isConnect()){              
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date periodeAwal = new java.util.Date();
                java.util.Date periodeAkhir;
                Calendar c  = Calendar.getInstance();
                c.setTime(periodeAwal);
                c.add(Calendar.DATE, 1);
                periodeAkhir = c.getTime();
                String strPeriodeAwal = sdfNow.format(periodeAwal);
                String strPeriodeAkhir = sdfNow.format(periodeAkhir);
                String strJamAwal = "06:00:00";
                String strJamAkhir = "05:59:59";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                java.sql.Timestamp tsAwal = new Timestamp(sdf.parse(strPeriodeAwal + " " + strJamAwal).getTime());
                java.sql.Timestamp tsAkhir = new Timestamp(sdf.parse(strPeriodeAkhir + " " + strJamAkhir).getTime());
                /*
                CallableStatement cst = DbTimbanganConnectionManager.getConnection().prepareCall(callSql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                */
                cst.setTimestamp(1, tsAwal);
                cst.setTimestamp(2, tsAkhir);
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
                int totalritTs = 0;
                int totalritTr = 0;
                int totalritKut = 0;
                int totalritTsi = 0;
                int totalritTotal = 0;
                int antrianTs = 0;
                int antrianTr = 0;
                int antrianKut = 0;
                int antrianTsi = 0;
                int antrianTotal = 0;
                int caneyardTs = 0;
                int caneyardTr = 0;
                int caneyardKut = 0;
                int caneyardTsi = 0;
                int caneyardTotal = 0;
                while (rs.next()){
                    PasokTebu pt = new PasokTebu(
                            rs.getString("afdeling"), 
                            rs.getString("rayon"), 
                            rs.getString("tstr"), 
                            rs.getDouble("netto"), 
                            rs.getInt("totalrit"), 
                            rs.getInt("caneyard"), 
                            rs.getInt("antrian"));
                    lpt.add(pt);
                    if (rs.getString("tstr").equals("TS")){
                        tonTs += rs.getDouble("netto");
                        totalritTs += rs.getInt("totalrit");
                        antrianTs += rs.getInt("antrian");
                        caneyardTs += rs.getInt("caneyard");
                    }
                    if (rs.getString("tstr").equals("TR")){
                        tonTr += rs.getDouble("netto");
                        totalritTr += rs.getInt("totalrit");
                        antrianTr += rs.getInt("antrian");
                        caneyardTr += rs.getInt("caneyard");
                    }
                    if (rs.getString("tstr").equals("TSI")){
                        tonTsi += rs.getDouble("netto");
                        totalritTsi += rs.getInt("totalrit");
                        antrianTsi += rs.getInt("antrian");
                        caneyardTsi += rs.getInt("caneyard");
                    }
                    tonTotal = tonTs + tonTr + tonKut + tonTsi;
                    totalritTotal = totalritTs + totalritTr + totalritKut + totalritTsi;
                    antrianTotal = antrianTs + antrianTr + antrianKut + antrianTsi;
                    caneyardTotal = caneyardTs + caneyardTr + caneyardKut + caneyardTsi;
                }
                lpt.add(new PasokTebu("","JML TS","",tonTs,totalritTs,caneyardTs,antrianTs));
                lpt.add(new PasokTebu("","JML TR","",tonTr,totalritTr,caneyardTr,antrianTr));
                lpt.add(new PasokTebu("","JML TSI","",tonTsi,totalritTsi,caneyardTsi,antrianTsi));
                lpt.add(new PasokTebu("","TOTAL","",tonTotal,totalritTotal,caneyardTotal,antrianTotal));
                //JOptionPane.showMessageDialog(null, lpt.size());
            } else {
                mw.getLblPengumuman().setText("Database tidak terhubung!");
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
        } else {
            // JOptionPane.showMessageDialog(mw, "Database tidak  terhubung");
            // mw.getLblPengumuman().setText("Database tidak terhubung!");
        }
        return tglBaru;
    }

    @Override
    public List<PasokTebu> getPagedPasokTebu(int pageIndex, int maxRow) {
        List<PasokTebu> plpt = new ArrayList<>();
        List<PasokTebu> lpt = getAllPasokTebu();
        for(int i = 0; i <= maxRow - 1; i++){
            int actualIndex = ((pageIndex - 1) * (maxRow - 1)) + i;
            if (actualIndex < lpt.size()){
                plpt.add(lpt.get(actualIndex));
            }
        }
        return plpt;
    }
    
}
