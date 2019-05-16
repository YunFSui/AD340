package com.example.hw5;

import org.junit.Before;
import org.junit.Test;

import android.content.Context;

import static com.google.common.truth.Truth.assertThat;


public class SodaTest {

    Soda testSoda;
    Soda testSodaNoSugar;

    @Before
    public void setUp(){
        testSoda = new Soda(2.99, 34, "Sugary Drink");
        testSodaNoSugar = new Soda(2.99, 34, "Diet Drink");
    }

    @Test
    public void sodaIsSugary() {
        testSoda.setSugary("Sugary Drink");

        assertThat(testSoda.isSugary()).isTrue();
    }

    @Test // invariants
    public void sodaIsSugarInputOutBound() {
        testSoda.setSugary("Power Puff Girls!");

        assertThat(testSoda.isSugary()).isFalse();
    }

    @Test
    public void sugarySodaIsMoreExpensiveThanNoneSugarySoda() {
        assertThat(testSoda.getTaxPrice()).isGreaterThan(testSodaNoSugar.getTaxPrice());
    }


}
