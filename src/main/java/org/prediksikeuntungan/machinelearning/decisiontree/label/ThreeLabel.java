/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.prediksikeuntungan.machinelearning.decisiontree.label;

/**
 *
 * @author thread009
 */
public class ThreeLabel extends Label{
    
    Integer label;
    
    public ThreeLabel(Integer value){
        this.label = value;
    }
    
    public static Label newLabel(Integer label){
        return new ThreeLabel(label);
    }

    @Override
    public String getPrintValue() {
        return String.valueOf(this.label);
    }

    @Override
    public String getName() {
        return String.valueOf(this.label);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ThreeLabel other = (ThreeLabel) obj;
        if (label != other.label)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (label > 0 ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf("Integer Label: "+this.label);
    }
    
    
    
}
