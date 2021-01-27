package com.example.journeyMobile;
import org.junit.Test;
import android.support.test.uiautomator.UiAutomatorTestCase;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;





/*test plan and step

step1:open the app and browse the homepage

step2:locate the time button and click and set right date

step3:locate the date button and click and set right time

step4:locate the cancel button and click

*
*
* */

public class Test_Id4_Pbi1 extends UiAutomatorTestCase {
    private String expectedResult;
    private String actualResult;




    @Test
    //step1:open the app and browse the homepage
    public void test_open_app() throws UiObjectNotFoundException {
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_welcome_text")).getText();
        expectedResult = "Welcome To Rye \n" +
                "Plan Your Journey";
        assertEquals(actualResult,expectedResult);

    }

    @Test
    //step2:locate the time button and click and set right date
    public void test_set_data()throws UiObjectNotFoundException{
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/month_text")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("android:id/next")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().text("15")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().text("OK")).clickAndWaitForNewWindow();
        sleep(2000);
        expectedResult = "15";
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/day_of_month_text")).getText();
        assertEquals(actualResult,expectedResult);
    }

    @Test
    //step3:locate the date button and click and set right time
    public void test_set_time() throws UiObjectNotFoundException{
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/hour_minute")).clickAndWaitForNewWindow();
        sleep(2000);
        new UiObject(new UiSelector().index(11)).click();
        sleep(2000);
        new UiObject(new UiSelector().resourceId("android:id/toggle_mode")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("android:id/input_minute")).setText("55");
        new UiObject(new UiSelector().text("OK")).clickAndWaitForNewWindow();
        sleep(2000);
        expectedResult = "12:55 AM";
        actualResult = new UiObject(new UiSelector().text("12:55 AM")).getText();
        assertEquals(actualResult,expectedResult);
    }

    @Test
    //step4:locate the cancel button and click
    public void test_cancel_button() throws UiObjectNotFoundException{
        getUiDevice().pressHome();
        new UiObject(new UiSelector().text("journey-mobile")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/hour_minute")).clickAndWaitForNewWindow();
        new UiObject(new UiSelector().resourceId("android:id/button2")).clickAndWaitForNewWindow();
        actualResult = new UiObject(new UiSelector().resourceId("com.example.journeyMobile:id/homepage_welcome_text")).getText();
        expectedResult = "Welcome To Rye \n" +
                "Plan Your Journey";
        assertEquals(actualResult,expectedResult);

    }



}
