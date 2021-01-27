package com.example.journeyMobile.service.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;

public class NetUtil {



    /**
     *
     * @param context context
     * @return return status of the network
     */
    public static boolean getNetStatus(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

            if (mNetworkInfo != null) {
                if (!mNetworkInfo.isAvailable()) {
                    return false;
                }

                NetworkCapabilities networkCapabilities = mConnectivityManager.getNetworkCapabilities(mConnectivityManager.getActiveNetwork());
                boolean availiable =  networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);

                if (availiable) {return ping();}
                else {return false;}

            }

        }

        return false;

    }

    private static boolean ping() {

        String result = null;
        try {
            // ping address
            String ip = "www.google.com";
            // ping 3 times
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);

            // the status of ping
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            Log.d("----result---", "result = " + result);
        }
        return false;
    }
}
