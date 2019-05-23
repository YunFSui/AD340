package com.example.hw5;

import android.content.Context;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Soda {

    private double price;
    private double vol;
    private String isSugar;
    private boolean isSugary;
    private double taxPrice;
    final static double salesTax = 9.5;
    final static double sugaryTax = .0175;

    public Soda(double price, double vol, String isSugar){
        this.price = setPrice(price);
        this.vol = setVol(vol);
        this.isSugary = setSugary(isSugar);
        this.taxPrice = getAfterTaxPrice(price, vol, isSugary);
    }

    public Soda(Context context){
        this.isSugary = setSugary("Sugary Drink");
        this.price = Double.parseDouble("1");
        this.vol = Double.parseDouble("1");
    }


    public double getPrice() {
        return price;
    }

    public double getVol() {
        return vol;
    }

    public boolean isSugary() {
        return isSugary;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public double getAfterTaxPrice(double price, double vol, boolean isSugary){

        double result;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode((RoundingMode.DOWN));

        if(isSugary){
            result = price * (1 + salesTax/100) + (sugaryTax * vol);
        } else {
            result = price * (1 + salesTax/100);
        }

        if(price == 0 && vol != 0 || price !=0 && vol == 0){
            result = 0;
        }

        return Double.parseDouble(df.format(result));
    }

    public boolean setSugary(String isSugar) {


        if (isSugar == "Sugary Drink"){
            isSugary = true;
        }
        else{
            isSugary = false;
        }

        return isSugary;
    }

    public double setPrice(double price) {
        if(price <= 0){
            price = 0;
        }

        return price;
    }

    public double setVol(double vol) {
        if(vol <= 0){
            vol = 0;
        }

        return vol;
    }

}
