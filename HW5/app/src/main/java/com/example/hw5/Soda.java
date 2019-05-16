package com.example.hw5;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

import android.content.Context;

public class Soda {

    private double price;
    private int vol;
    private String isSugar;
    private boolean isSugary;
    private double taxPrice;
    final static double salesTax = 9.5;
    final static double sugaryTax = .0175;

    public Soda(double price, int vol, String isSugar){
        this.price = price;
        this.vol = vol;
        this.isSugary = setSugary(isSugar);
        this.taxPrice = getAfterTaxPrice(price, vol, isSugary);
    }

    public double getPrice() {
        return price;
    }

    public int getVol() {
        return vol;
    }

    public boolean isSugary() {
        return isSugary;
    }

    public double getTaxPrice() {
        return taxPrice;
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

    public double getAfterTaxPrice(double price, int vol, boolean isSugary){

        double result;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode((RoundingMode.DOWN));

        if(isSugary){
            result = price * (1 + salesTax/100) + (sugaryTax * vol);
        } else {
            result = price * (1 + salesTax/100);
        }

        return Double.parseDouble(df.format(result));
    }


}
