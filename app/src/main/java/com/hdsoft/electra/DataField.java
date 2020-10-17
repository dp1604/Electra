package com.hdsoft.electra;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataField {
    private int id;
    private String timestamp;
    private int circuitId;
    private String key;
    private Double value;
    private String unit;

    public DataField(int id, String timestamp, int circuitId, String key, Double value, String unit){
        Date now = new Date();
        this.timestamp = ("" + now.getTime() + "");
        this.circuitId = circuitId;
        this.key = key;
        this.value = value;
        this.unit = unit;
    }

    public DataField(){
        Random rand = new Random();
        id = rand.nextInt(999999);
        Date now = new Date();
        this.timestamp = ("" + now.getTime() + "");
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(String timestamp){ this.timestamp = timestamp; }

    public int getCircuitId(){
        return circuitId;
    }

    public void setCircuitId(int circuitId){
        this.circuitId = circuitId;
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public Double getValue(){
        return value;
    }

    public void setValue(Double value){
        this.value = value;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }
}
