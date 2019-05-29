package com.example.hw5;

import org.junit.Before;
import org.junit.Test;

import android.content.Context;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class SodaUnitTestMockito {

    Soda testSoda;
    Soda testSodaNoSugar;

    @Mock
    Context mockContext;

    @Before
    public void setUp(){
        testSoda = new Soda(2.99, 34, "Sugary Drink");
        testSodaNoSugar = new Soda(2.99, 34, "Diet Drink");

        MockitoAnnotations.initMocks(this);

        when(mockContext.getString(R.string.default_price)).thenReturn("1");
        when(mockContext.getString(R.string.default_volume)).thenReturn("1");
        when(mockContext.getString(R.string.default_isSugar)).thenReturn("Sugary Drink");

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

    @Test
    public void defaultSodaValueHasDefault() {
        Soda defaultSoda = new Soda(mockContext);

        boolean defaultIsSugar = defaultSoda.setSugary("Sugary Drink");
        assertThat(defaultIsSugar).isTrue();

        assertThat(defaultSoda.getPrice()).isEqualTo(1.0);
        assertThat(defaultSoda.getVol()).isEqualTo(1.0);
    }
}
