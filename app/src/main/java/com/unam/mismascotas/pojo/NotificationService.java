package com.unam.mismascotas.pojo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.unam.mismascotas.R;
import com.unam.mismascotas.pojo.MyWorker;

public class NotificationService extends FirebaseMessagingService {
//https://firebase.google.com/docs/cloud-messaging/android/first-message

    public static final String TAG = "FIREBASE";
    private static final String CHANNEL_ID = "notificacion";
    private static final String TAG2 = "FIREBASE_TOKEN";
    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        onTokenRefresh();
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            //Intent i = new Intent(this, MainActivity.class);
            Intent i = new Intent();
            i.setAction("TOQUE_ANIMAL");

            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.huella, "devolver like/follow", pi)
                    .build();

            Intent i1 = new Intent(this, MainActivity.class);
            //i.setAction("TOQUE_ANIMAL");
            PendingIntent pi1 = PendingIntent.getActivity(this, 0, i1, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Action action1 = new NotificationCompat.Action.Builder(R.drawable.hueso_blanco, "ver perfil/usuario", pi1)
                    .build();

            NotificationCompat.WearableExtender wearableExtender =
                    new NotificationCompat.WearableExtender()
                            .setHintHideIcon(true)
                            .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.huella))
                            .setGravity(Gravity.CENTER_VERTICAL)
                    ;

            NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, "notificacion")
                    .setSmallIcon(R.drawable.perro7)
                    .setContentTitle("Notificacion")
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSound(sonido)
                    .setContentIntent(pi)
                    .extend(wearableExtender.addAction(action))
                    .extend(wearableExtender.addAction(action1))
                    .setAutoCancel(true); //Para que la notificación se cancele

            NotificationManagerCompat nm = NotificationManagerCompat.from(this);
                    //(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            nm.notify(NOTIFICATION_ID, notificacion.build());
            //Toast.makeText(this, "Notificacion construida", Toast.LENGTH_LONG).show();
        }else {
            //Intent i = new Intent(this, MainActivity.class);
            Intent i = new Intent();
            i.setAction("TOQUE_ANIMAL");

            //PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

            Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.huella, "devolver like/follow", pi)
                    .build();

            Intent i1 = new Intent(this, MainActivity.class);
            //i.setAction("TOQUE_ANIMAL");
            PendingIntent pi1 = PendingIntent.getActivity(this, 0, i1, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Action action1 = new NotificationCompat.Action.Builder(R.drawable.hueso_blanco, "ver perfil/usuario", pi1)
                    .build();

            NotificationCompat.WearableExtender wearableExtender =
                    new NotificationCompat.WearableExtender()
                            .setHintHideIcon(true)
                            .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.huella))
                            .setGravity(Gravity.CENTER_VERTICAL)
                    ;

            NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.perro7)
                    .setContentTitle("Notificacion")
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setSound(sonido)
                    .setContentIntent(pi)
                    .extend(wearableExtender.addAction(action))
                    .extend(wearableExtender.addAction(action1))
                    .setAutoCancel(true); //Para que la notificación se cancele

            NotificationManagerCompat nm = NotificationManagerCompat.from(this);
                    // (NotificationManagerCompat) getSystemService(Context.NOTIFICATION_SERVICE);

            nm.notify(NOTIFICATION_ID, notificacion.build());
            Toast.makeText(this, "Notificacion construida", Toast.LENGTH_LONG).show();

        }
    }

    private void scheduleJob() {
        // [START dispatch_job]
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();
        WorkManager.getInstance().beginWith(work).enqueue();
        // [END dispatch_job]
    }

    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    public void onTokenRefresh(){
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG2,token);
    }
}
