package com.hdsoft.electra;

import java.util.Date;
import java.util.Random;

public class CalculatorEngine {
    public DataField[] getRequiredFieldsList(int circuitId){
        switch(circuitId){
            case 1:
                return ohmsLawFields();
            default:
                return new DataField[]{};
        }
    }

    public Double calculate(int circuitId, DataField[] data){
        switch(circuitId){
            case 1:
                return ohmsLawCalculation(data);
            default:
                return 0.0;
        }
    }

    public DataField[] ohmsLawFields(){
        Random rand = new Random();
        Date now = new Date();
        return new DataField[] {
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 1, "Voltage", 0.0, "(V)"),
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 1, "Resistance", 0.0, "(Ohm)")
        };
    }

    public Double ohmsLawCalculation(DataField[] data){
        return ((data[0].getValue()/data[1].getValue())*1000.0);
    }
}
