package com.example.myappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtTimeNow, txtTinhTime1, txtTinhTime2, txtTinhTime3, txtTinhTime4;
    PendingIntent pendingIntent;
    RadioGroup radioGroup;
    RadioButton rd1,rd2,rd3,rd4;
    Button btnXacNhan, btnHuy;
    wifiReceiver wifiBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiBroadcastReceiver = new wifiReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiBroadcastReceiver,intentFilter);



        Intent intentWifi = getIntent();
        int timeNow = intentWifi.getIntExtra("Wifi_Receiver",0);
        if (timeNow >=23){
            DiaLog();
        }


    }

    private  void DiaLog(){

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_main);
        dialog.setCanceledOnTouchOutside(false);

        Anhxa();

        Calendar calendarNow = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        Calendar calendar4 = Calendar.getInstance();

        SimpleDateFormat dinhdanggio = new SimpleDateFormat("HH:mm a");

        txtTimeNow.setText(dinhdanggio.format(calendarNow.getTime()));

        calendar1.add(Calendar.MINUTE, 90 * 3 + 14);
        txtTinhTime1.setText(dinhdanggio.format(calendar2.getTime()));

        calendar2.add(Calendar.MINUTE, 90 * 4 + 14);
        txtTinhTime2.setText(dinhdanggio.format(calendar2.getTime()));

        calendar3.add(Calendar.MINUTE, 90 * 5 + 14);
        txtTinhTime3.setText(dinhdanggio.format(calendar2.getTime()));

        calendar4.add(Calendar.MINUTE, 90 * 6 + 14);
        txtTinhTime4.setText(dinhdanggio.format(calendar2.getTime()));


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                // Cho phép truy cập hệ thống báo thức cảu máy
                // ALARM SERVICE báo thức

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//        Intent intent1 = new Intent(MainActivity.this, wifiReceiver.class);

                //pending intent tồn tại kể cả khi thoát khỏi ứng dụng
                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                if (rd1.isChecked()) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent);
                }
                if (rd2.isChecked()){
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),pendingIntent);
                }
                if (rd3.isChecked()){
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(),pendingIntent);
                }
                if (rd4.isChecked()){
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(),pendingIntent);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public void Anhxa() {
        txtTimeNow = (TextView) findViewById(R.id.txtThoiGianNow);
        txtTinhTime1 = (TextView) findViewById(R.id.txtTinhTime1);
        txtTinhTime2 = (TextView) findViewById(R.id.txtTinhTime2);
        txtTinhTime3 = (TextView) findViewById(R.id.txtTinhTime3);
        txtTinhTime4 = (TextView) findViewById(R.id.txtTinhTime4);
        radioGroup = findViewById(R.id.radioGroup);
        rd1 = findViewById(R.id.radioButton1);
        rd2 = findViewById(R.id.radioButton2);
        rd3 = findViewById(R.id.radioButton3);
        rd4 = findViewById(R.id.radioButton4);
        btnHuy = findViewById(R.id.btnXacNhan);
        btnXacNhan = findViewById(R.id.btnHuy);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiBroadcastReceiver);
    }
}
