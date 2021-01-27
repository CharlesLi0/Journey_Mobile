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
open the app and click "menu" button
step2:
locate and click "car parking" button
step3:
enter "Bay Trail, Rye VIC 3941, Australia" in search bar and click search
button
*
* */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )

public class Test_Id9_Pbi11 extends UiAutomatorTestCase {
    private String expectedResult;
    private String actualResult;


    @Test

    //step1:open the app then locate the map button and click
    public void test_carparking() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_nav_button")).clickAndWaitForNewWindow();


        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/design_menu_item_text")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/searchView")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("Bay Trail, Rye VIC 3941, Australia");
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_prediction_secondary_text")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().className("android.view.View").index(3)).clickAndWaitForNewWindow();
        sleep(2000);
        getUiDevice().pressBack();
        sleep(2000);
        new UiObject(new UiSelector().className("android.widget.ImageView").index(1)).clickAndWaitForNewWindow();
        sleep(20000);
        expectedResult = "parking11";
        actualResult = new UiObject(new UiSelector().className("android.widget.TextView").index(0)).getText();
        System.out.print(actualResult);
        assertEquals(actualResult,expectedResult);








    }



}