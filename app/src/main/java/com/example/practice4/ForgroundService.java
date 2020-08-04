package com.example.practice4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForgroundService extends Service {

    public static Integer J = 3;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(700, getNotification());
    }

    private Notification getNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID1",
                    "YOUR_CHANNEL_NAME1",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION1");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID1").setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Service is running!") // title for notification
                .setContentText("This is an important service")// message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        return mBuilder.build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        J = 3;
        showToast();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showToast() {

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                if (J > 0)
                {
                    Toast.makeText(ForgroundService.this, "سلام این یک سرویس می باشد", Toast.LENGTH_SHORT).show();
                    J -= 1;
                    showToast();
                }
                else{
                    onDestroy();
                }
            }
        }, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
