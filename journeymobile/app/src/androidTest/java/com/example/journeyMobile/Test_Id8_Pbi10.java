package com.example.journeyMobile;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import java.io.IOException;

import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;



import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.transition.Transition;
import org.junit.runners.MethodSorters;


/*test plan and step

step1:
open app and click "menu" button
step2:
locate and click "services" button
step3:
enter "Bay Trail, Rye VIC 3941, Australia" in search bar and click search
button
step4:
locate and click "Bin"  "Toilet" button
*
*
*
*
* */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )

public class Test_Id8_Pbi10 extends UiAutomatorTestCase {
    private String expectedResult;
    private String actualResult;


    @Test

    //step1:open the app then locate the map button and click
    public void test_toiltandbin() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_nav_button")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().text("Services")).clickAndWaitForNewWindow();

        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/searchView")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("Bay Trail, Rye VIC 3941, Australia");
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_prediction_secondary_text")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/toilet_bt")).clickAndWaitForNewWindow();
        sleep(1000);
        new UiObject(new UiSelector().className("android.view.View").index(3)).clickAndWaitForNewWindow();
        expectedResult = "toilet5";
        actualResult = new UiObject(new UiSelector().text("toilet5")).getText();
        assertEquals(actualResult,expectedResult);










    }



}