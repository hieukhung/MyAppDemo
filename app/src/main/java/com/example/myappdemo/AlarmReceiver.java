package com.example.myappdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "DKM", Toast.LENGTH_SHORT).show();

        Intent myintent = new Intent(context,Music.class);
        context.startService(myintent);
        // yêu cầu Music thực hiện cùng lúc vs nó.

    }
}
