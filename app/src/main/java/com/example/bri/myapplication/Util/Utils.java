package com.example.bri.myapplication.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

import java.net.InetAddress;

/**
 * Created by BRI on 30/10/2018.
 */

public class Utils  {
    public final static boolean isConnected(Context context) {
        boolean networkConnected = false;
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        networkConnected = true;
                    }
        }
        return networkConnected;
    }
    public final static Toast getToastMessage(Context c, String msg) {
        Toast toast = Toast.makeText(c, msg, Toast.LENGTH_SHORT);
        if(toast != null) toast.setGravity(Gravity.CENTER, 0, 0);
        return toast;
    }

    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("www.google.com"); //You can replace it with your name

            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
}
