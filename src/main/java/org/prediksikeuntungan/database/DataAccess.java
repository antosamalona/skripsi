/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thread009
 */
public class DataAccess {
    
    public Connection connection;
    
    public DataAccess(){
        Database database = new Database();
        database.setConnection("root", "livelove", "warehouse");
        this.connection = database.getConnection();
    }
    
    public boolean INSERT_TRAINING_DATA(TrainModel train, List<TrainDetailModel> trainDetail) throws SQLException{
        PreparedStatement statement1 = connection.prepareStatement("INSERT INTO t_train "
                + "VALUES(?, ?)");
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO t_train_detail "
                + "VALUES(?, ?, ?, ?, ?)");
        statement1.setString(1, train.getTrainCode());
        statement1.setDate(2, dateToSQL(train.getTrainDate(), "yyyy-MM-dd"));
        
        for(TrainDetailModel data : trainDetail){
            statement2.setString(1, data.getTrainCode());
            statement2.setString(2, data.getPekerjaan());
            statement2.setDouble(3, data.getPendapatan());
            statement2.setInt(4, data.getStatusMenikah());
            statement2.setDouble(5, data.getKeuntungan());
            statement2.addBatch();
        }
        
        return (statement1.executeUpdate() == 1 && statement2.executeBatch().length  > 0)? true:false;
    }
    
    public List<TrainModel> GET_TRAIN_MODEL() throws SQLException{
        List<TrainModel> data = new ArrayList<TrainModel>();
        PreparedStatement st = connection.prepareStatement("SELECT *FROM t_train");
        ResultSet res = st.executeQuery();
        while(res.next()){
            data.add(new TrainModel(res.getString(1), res.getDate(2)));
        }
        return data;
    }
    
    public List<TrainDetailModel> GET_TRAIN_DETAIL(String trainCode)throws SQLException{
        List<TrainDetailModel> data = new ArrayList<TrainDetailModel>();
        PreparedStatement st = connection.prepareStatement("SELECT *FROM t_train_detail WHERE train_code = ?");
        st.setString(1, trainCode);
        ResultSet res = st.executeQuery();
        while(res.next()){
            data.add(new TrainDetailModel(res.getString(1), res.getString(2), res.getInt(3), res.getInt(4), res.getInt(5)));
        }
        return data;
        
    }
    
    public boolean commitLocal() {
        boolean status = false;
        try {
            connection.commit();
            status = true;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            try {
                if(!status) connection.rollback();
            } catch (SQLException ex1) {
                
                ex1.printStackTrace();
            }
        } finally {
            try {
                if(!status) connection.rollback();
            } catch (SQLException ex) {
                
                ex.printStackTrace();
            }
        }
        return status;
    }
    
    public java.sql.Date dateToSQL(java.util.Date dateUtil, String format) {
        java.sql.Date sqlDate = null;
        try {
            sqlDate = new java.sql.Date(dateUtil.getTime());
        } catch (Exception e) {
        }
        return sqlDate;
    }
    
    public java.util.Date indoDateToDate(String tanggal){
        String[] a = tanggal.split(",");
        String[] b = a[1].split(" ");
        java.util.Date d = null;
        
        String hari = b[1], bulan = b[2], tahun = b[3];
        
        switch(bulan){
            case "January":
                bulan = "01";
                break;
            case " February":
                bulan = "02";
                break;
            case "March":
                bulan = "03";
                break;
            case "April":
                bulan = "04";
                break;
            case "May":
                bulan = "05";
                break;
            case "June":
                bulan = "06";
                break;
            case "July":
                bulan = "07";
                break;
            case "August":
                bulan = "08";
                break;
            case "September":
                bulan = "09";
                break;
            case "October":
                bulan = "10";
                break;
            case "November":
                bulan = "11";
                break;
            case "December":
                bulan = "12";
                break;
        }
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        try {
            d = sdf.parse(hari+"/"+bulan+"/"+tahun);
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return d;
        
    }    
    
    public String dateToIndoDate(java.util.Date tanggal) {
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd MMMM yyyy");
        java.text.SimpleDateFormat hf = new java.text.SimpleDateFormat("EEEE");
        String Hari = "Minggu";
        
        switch (hf.format(tanggal)) {
            case "Monday":
                Hari = "Senin";
                break;
            case "Tuesday":
                Hari = "Selasa";
                break;
            case "Wednesday":
                Hari = "Rabu";
                break;
            case "Thursday":
                Hari = "Kamis";
                break;
            case "Friday":
                Hari = "Jumat";
                break;
            case "Saturday":
                Hari = "Sabtu";
                break;
        }
        
        
        String tgl = df.format(tanggal);
        String hri = Hari;
        return hri+", "+tgl;
    }
    
}
