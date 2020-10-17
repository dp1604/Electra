package com.hdsoft.electra;

import java.util.Date;
import java.util.Random;
import java.lang.Math;

public class CalculatorEngine {
    public DataField[] getRequiredFieldsList(int circuitId){
        switch(circuitId){
            case 1:
                return ohmsLawFields();
            case 2:
                return oscillator555Fields();
            case 3:
                return colpittsOscillatorFields();
            default:
                return new DataField[]{};
        }
    }

    public Double calculate(int circuitId, DataField[] data){
        switch(circuitId){
            case 1:
                return ohmsLawCalculation(data);
            case 2:
                return oscillator555Calculation(data);
            case 3:
                return colpittsOscillatorCalculation(data);
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

    public DataField[] oscillator555Fields(){
        Random rand = new Random();
        Date now = new Date();
        return new DataField[] {
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 2, "Resistor 1", 0.0, "(Ohm)"),
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 2, "Resistor 2", 0.0, "(Ohm)"),
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 2, "Capacitor", 0.0, "(F)")
        };
    }

    public Double oscillator555Calculation(DataField[] data){
        return (1.44 / ((data[2].getValue()) * (data[0].getValue() + data[1].getValue() + data[1].getValue())));
    }

    public DataField[] colpittsOscillatorFields(){
        Random rand = new Random();
        Date now = new Date();
        return new DataField[] {
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 3, "Capacitor 1", 0.0, "(Ohm)"),
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 3, "Capacitor 2", 0.0, "(Ohm)"),
                new DataField(rand.nextInt(999999), ("" + now.getTime() + ""), 3, "Inductor", 0.0, "(H)")
        };
    }

    public Double colpittsOscillatorCalculation(DataField[] data){
        return (1.0 / ((2.0 * 22.0 * (Math.sqrt((data[0].getValue() * data[1].getValue()) / (data[0].getValue() + data[1].getValue()) * data[2].getValue()))) / 7));
    }
}
