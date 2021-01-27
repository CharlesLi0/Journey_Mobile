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

step1:open the app then locate the map button and click

step2:locate the search bar and click

step3:input the incomplete address and click search button

step4:input the complete address and click the automatically generated address

step5:locate the icon of address and click

step6:locate and click the add button

step7:go to "Daily Plan" page and check the address has been added

*
*
*
*
*
*
* */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING )

public class Test_Id5_Pbi22 extends UiAutomatorTestCase {
    private String expectedResult;
    private String actualResult;



    @Test

    //step1:open the app then locate the map button and click
    public void test1_map_button() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        expectedResult = "Your spot";
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).getText();
        assertEquals(actualResult, expectedResult);

    }

    @Test
    //step2:locate the search bar and click
    public void test2_search_bar() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        expectedResult = "Search";
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).getText();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    //step3:input the incomplete address and click search button
    public void test3_search_address_1() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("bui");
        expectedResult = "get search button";
        actualResult = "cannot find search button";
        assertEquals(actualResult, expectedResult);
    }

    @Test
    //step4:input the complete address and click the automatically generated address
    public void test4_search_address_2() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("building56");
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_prediction_primary_text")).clickAndWaitForNewWindow();
        expectedResult = "Building 56, 115 Queensberry St, Carlton VIC 3053, Australia";
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).getText();
        assertEquals(actualResult, expectedResult);


    }

    @Test
    //step5:locate the icon of address and click
    public void test5_search_result() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("building56");
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_prediction_primary_text")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().className("android.view.View").index(0)).clickAndWaitForNewWindow();
        expectedResult = "Building 56, 115 Queensberry St, Carlton VIC 3053, Australia";
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/popup_title_TV")).getText();
        assertEquals(actualResult, expectedResult);

    }

    @Test
    //step6:locate and click the add button
    public void test6_add_button() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("building56");
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_prediction_primary_text")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().className("android.view.View").index(0)).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/popup_add_BT")).clickAndWaitForNewWindow();
        expectedResult = "Building 56, 115 Queensberry St, Carlton VIC 3053, Australia";
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/popup_title_TV")).getText();
        assertEquals(actualResult, expectedResult);



    }


    @Test
    //step7:go to "Daily Plan" page and check the address has been added
    public void test7_Daily_Plan()throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("building56");
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_prediction_primary_text")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().className("android.view.View").index(0)).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/popup_add_BT")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/back_bt")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_nav_button")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().text("Daily Plan")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().className("android.widget.LinearLayout").index(0)).performMultiPointerGesture();
        sleep(5000);



    }

}