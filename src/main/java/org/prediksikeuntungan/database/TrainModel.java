/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.database;

import java.util.Date;

/**
 *
 * @author thread009
 */
public class TrainModel {
    
    private String trainCode;
    private Date trainDate;
    
    public TrainModel(String trainCode, Date trainDate){
        this.trainCode = trainCode;
        this.trainDate = trainDate;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public Date getTrainDate() {
        return trainDate;
    }
    
    
    
}
