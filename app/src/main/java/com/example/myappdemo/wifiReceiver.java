package com.example.myappdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.PropertyPermission;

public class wifiReceiver extends BroadcastReceiver {

    int Time;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (isWifiChange(context)) {
                Toast.makeText(context, "Có mậnggggggggggg", Toast.LENGTH_SHORT).show();
            } else {
//              Toast.makeText(context, "Rớt mangjjjj", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                Time = calendar.get(Calendar.HOUR_OF_DAY);
                Intent intent1 = new Intent(context, MainActivity.class);
                intent1.putExtra("Wifi_Receiver", Time);
            }
        }
    }


    private boolean isWifiChange(Context context){
           ConnectivityManager connectivityManager = (ConnectivityManager) context
                   .getSystemService(Context.CONNECTIVITY_SERVICE);
           if (connectivityManager == null){
               return false;
           }
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
               // check mạng của mấy cái Andorid 6 trở lên
                Network  network = connectivityManager.getActiveNetwork();
                if (network == null){
                    return false;
                }

                NetworkCapabilities capabilities = connectivityManager
                        .getNetworkCapabilities(network);
                return capabilities !=null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
           }else {
               NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
               return networkInfo != null && networkInfo.isConnected();
           }
    }
}