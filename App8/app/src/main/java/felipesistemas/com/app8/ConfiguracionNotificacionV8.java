package felipesistemas.com.app8;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

public class ConfiguracionNotificacionV8 extends ContextWrapper {


    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "felipesistemas.com.app8.ANDROID";
    public static final String IOS_CHANNEL_ID = "felipesistemas.com.app8.IOS";
    public static final String ANDROID_CHANNEL_NAME = "CANAL DE ANDROID";
    public static final String IOS_CHANNEL_NAME = "CANAL DE IOS";

    public ConfiguracionNotificacionV8(Context base) {
        super(base);

        CreaCanales();
    }

    public void CreaCanales() {

        // Sets whether notifications posted to this channel should display notification lights
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // create android channel
            NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                    ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            androidChannel.enableLights(true);

            // Sets whether notification posted to this channel should vibrate.
            androidChannel.enableVibration(true);
            // Sets the notification light color for notifications posted to this channel
            androidChannel.setLightColor(Color.GREEN);
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            androidChannel.setLockscreenVisibility(Notification.BADGE_ICON_SMALL);

            getManager().createNotificationChannel(androidChannel);

            // create ios channel
            NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
                    IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            iosChannel.enableLights(true);
            iosChannel.enableVibration(true);
            iosChannel.setLightColor(Color.GRAY);
            iosChannel.setLockscreenVisibility(Notification.BADGE_ICON_SMALL);
            getManager().createNotificationChannel(iosChannel);
        }



    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public Notification.Builder getAndroidChannelNotification(String title, String body) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.drawable.androidlogo2)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.androidlogo2))
                    .setAutoCancel(true);
        }
        return null;
    }

    public Notification.Builder getIosChannelNotification(String title, String body) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), IOS_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.drawable.androidlogo2)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.androidlogo2))
                    .setAutoCancel(true);
        }
        return null;
    }


}
