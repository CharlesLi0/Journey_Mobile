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
step1
open app
step2
click "map" button

* */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )

public class Test_Id3_Pbi6 extends UiAutomatorTestCase {
    private String expectedResult;
    private String actualResult;


    @Test

    //step1:open the app then locate the map button and click
    public void test_jump_google_map() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(10000);
        expectedResult = "Search here";
        actualResult = new UiObject(new UiSelector().text("Search here")).getText();
        assertEquals(actualResult,expectedResult);






    }



}