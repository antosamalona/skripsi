/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.database;

/**
 *
 * @author thread009
 */
public class TrainDetailModel {
    
    private String trainCode;
    private String pekerjaan;
    private Integer pendapatan;
    private int statusMenikah;
    private Integer keuntungan;

    public TrainDetailModel(String trainCode, String pekerjaan, Integer pendapatan, int statusMenikah, Integer keuntungan) {
        this.trainCode = trainCode;
        this.pekerjaan = pekerjaan;
        this.pendapatan = pendapatan;
        this.statusMenikah = statusMenikah;
        this.keuntungan = keuntungan;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public Integer getPendapatan() {
        return pendapatan;
    }

    public int getStatusMenikah() {
        return statusMenikah;
    }

    public Integer getKeuntungan() {
        return keuntungan;
    }
    
    public static String getColName(int index){
        switch(index){
            case 0:
                return "Pekerjaan";
            case 1:
                return "Pendapatan";
            case 2:
                return "Status Menikah";
            case 3:
                return "Keuntungan";
            default:
                return null;
        }
    }
    
    public Object get(int index){
        switch(index){
            case 0:
                return this.pekerjaan;
            case 1:
                return this.pendapatan;
            case 2:
                return this.statusMenikah;
            case 3:
                return this.keuntungan;
            default:
                return null;
        }
    }
    
}
