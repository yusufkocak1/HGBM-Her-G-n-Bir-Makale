package yube.com.hgbmhergnbirmakale.firebase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import yube.com.hgbmhergnbirmakale.R;
import yube.com.hgbmhergnbirmakale.activity.SimpleTabsActivity;
import yube.com.hgbmhergnbirmakale.alertdialog.alertactivity;

import static android.app.Notification.FLAG_AUTO_CANCEL;

/**
 * Created by yusuf on 4.12.2017.
 */
//ad,soyad,email notnull insert table, after save list data
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getNotification().getBody()); // Mesaj içeriği alınıp bildirim gösteren metod çağırılıyor
    }

    private void showNotification(String message) {

        Intent i = new Intent(this,alertactivity.class); // Bildirime basıldığında hangi aktiviteye gidilecekse
        i.putExtra("message",message);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);


        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, FLAG_AUTO_CANCEL);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setAutoCancel(true) // Kullanıcı bildirime girdiğinde otomatik olarak silinsin. False derseniz bildirim kalıcı olur.
                .setContentTitle("Mesajınız var") // Bildirim başlığı
                .setContentText(message) // Bildirim mesajı
                .setSmallIcon(R.drawable.logo) // Bildirim simgesi
                .setContentIntent(pendingIntent)
                ;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());
    }
}