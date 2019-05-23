package com.example.hw5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void incompleteInputTest(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);

        activity.findViewById(R.id.submit).performClick();
    }
}