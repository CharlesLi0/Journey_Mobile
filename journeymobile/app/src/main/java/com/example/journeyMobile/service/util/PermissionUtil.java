package com.example.journeyMobile.service.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionUtil {



    public static boolean checkPermission(Activity context,String[] perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    public static void requestPermission(Activity context,String tip,int requestCode,String[] perms) {
        EasyPermissions.requestPermissions(context, tip,requestCode,perms);
    }



    /**
     * check permission of Access_Fine_Loction
     * @param context context
     * @return boolean
     */
    public static boolean check_Access_Fine_Loction(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1
            );
            return false;
        }

        return true;
    }

    /**
     * check permission of internet
     * @param context context
     * @return boolean
     */
    public static boolean check_Internet(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.INTERNET}, 1
            );
            return false;
        }

        return true;


    }


    /**
     * check permission of read_contact
     * @param context context
     * @return boolean
     */
    public static boolean check_READ_CONTACTS(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.READ_CONTACTS}, 1
            );
            return false;
        }

        return true;
    }

    /**
     * check permission of write_contact
     * @param context context
     * @return boolean
     */
    public static boolean check_WRITE_CONTACTS(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.WRITE_CONTACTS}, 1
            );
            return false;
        }

        return true;
    }

    /**
     * check permission of Access_Coarse_Location
     * @param context context
     * @return boolean
     */
    public static boolean check_ACCESS_COARSE_LOCATION(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1
            );

            return false;
        }

        return true;
    }

    /**
     * check permission of ACCESS_NETOWRK_STATE
     * @param context context
     * @return boolean
     */
    public static boolean check_ACCESS_NETOWRK_STATE(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE}, 1
            );

            return false;
        }

        return true;
    }

    /**
     * check permission of ACCESS_WIFI_STATE
     * @param context context
     * @return boolean
     */
    public static boolean check_ACCESS_WIFI_STATE(Context context){
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.ACCESS_WIFI_STATE}, 1
            );

            return false;
        }

        return true;
    }

}
