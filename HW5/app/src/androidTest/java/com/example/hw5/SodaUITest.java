package com.example.hw5;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import junit.extensions.ActiveTestSuite;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.core.AllOf.allOf;


//@RunWith(AndroidJUnit4.class)
@LargeTest
public class SodaUITest {

    @Rule
    public ActivityTestRule<MainActivity> activeTestRule = new ActivityTestRule<>(MainActivity.class);

    //large sugary soda @ supermarket
    @Test
    public void SodaPriceCalculationTest() {

        //set isSugar spinner
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sugary Drink"))).perform(click());

        //set price
        onView(withId(R.id.price_box)).perform(typeText("2.99"));

        //set volume
        onView(withId(R.id.vol_box)).perform(typeText("33.8"));

        //click submit
        onView(withId(R.id.submit)).perform(click());

        //check result
        onView(withId(R.id.priceResult)).check(matches(
                withText("Soda Price After Tax: 3.27")));
    }

    //no selection, no price and volume input
    @Test
    public void NullInputTest(){
        //click submit
        onView(withId(R.id.submit)).perform(click());

        //check result
        onView(withId(R.id.priceResult)).check(matches(
                withText("Soda Price After Tax: 0.0")));
    }

    //no selection, no price and has only volume input
    @Test
    public void VolumeInputOnlyTest() {
        //set volume
        onView(withId(R.id.vol_box)).perform(typeText("33.8"));

        //click submit
        onView(withId(R.id.submit)).perform(click());

        //check result
        onView(withId(R.id.priceResult)).check(matches(
                withText("Soda Price After Tax: 0.0")));
    }

    //price input only
    @Test
    public void PriceInputOnlyTest() {
        //set price
        onView(withId(R.id.price_box)).perform(typeText("2.99"));

        //click submit
        onView(withId(R.id.submit)).perform(click());

        //check result
        onView(withId(R.id.priceResult)).check(matches(
                withText("Soda Price After Tax: 0.0")));
    }

}
