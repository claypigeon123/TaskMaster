package com.c.taskmaster.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationWrapper extends Application {

    public static final String CHANNEL_1_ID = "levelup";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        // Channel 1
        NotificationChannel channel1 = new NotificationChannel(
                CHANNEL_1_ID,
                "Level up!",
                NotificationManager.IMPORTANCE_HIGH
        );

        channel1.setDescription("This channel is for levelup notifications");

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel1);
    }
}
