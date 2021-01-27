package com.example.journeyMobile;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.support.test.uiautomator.UiAutomatorTestCase;
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

public class Test_Id4_Pbi6 extends UiAutomatorTestCase {
    public void testDemo() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_fab")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/from_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("Building80");
        UiObject u =new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(0));
        sleep(2000);
        u.clickAndWaitForNewWindow();
        sleep(3000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/to_EditText")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/places_autocomplete_edit_text")).setText("Building100");
        UiObject u1 =new UiObject(new UiSelector().className("android.widget.RelativeLayout").index(0));
        sleep(2000);
        u1.clickAndWaitForNewWindow();
        sleep(3000);
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/route_bt")).click();
        sleep(5000);










        sleep(2000);
        getUiDevice().pressHome();





    }

}




