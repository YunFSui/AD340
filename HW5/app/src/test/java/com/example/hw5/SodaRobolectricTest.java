package com.example.hw5;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SodaRobolectricTest {

    Context roboCon = ApplicationProvider.getApplicationContext();
    Soda defaultSoda = new Soda(roboCon);

    @Test
    public void defaultSodaPriceWithRobolectric(){
        assertThat(defaultSoda.getPrice()).isEqualTo(1.0);
    }

    @Test
    public void defaultSodaVolumeWithRobolectric(){
        assertThat(defaultSoda.getVol()).isEqualTo(1.0);
    }

    @Test
    public void defaultSodaIsSugaryWithRobolectric(){
        assertThat(defaultSoda.isSugary()).isTrue();
    }

    @Test
    public void inBoundIsSugarInputWithRobolectric(){
        assertThat(defaultSoda.setSugary("Sugary Drink")).isTrue();
    }

    @Test
    public void outBoundIsSugarInputWithRobolectric(){
        assertThat(defaultSoda.setSugary("Cutie Honey")).isFalse();
    }

    @Test
    public void negativePriceOrVolumeInputWithRobolectric(){

        assertThat(defaultSoda.setPrice(-1)).isEqualTo(0.0);
        assertThat(defaultSoda.setVol(-100)).isEqualTo(0.0);
    }
}
