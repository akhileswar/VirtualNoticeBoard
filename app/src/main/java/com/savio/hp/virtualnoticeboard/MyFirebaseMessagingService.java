package com.savio.hp.virtualnoticeboard;

/**
 * Created by HP on 10-09-2017.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by HP on 03-12-2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG="MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,"From"+remoteMessage.getFrom());
        if(remoteMessage.getData().size()>0)
        {
            Log.d(TAG,"Messagedata:"+remoteMessage.getData());
        }
        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"MessageBody:"+remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());

        }

    }

    private void sendNotification(String body) {

        Intent intent=new Intent(this,events.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifibuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Ente Idavaka Koovappally push notification")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notifibuilder.build());


    }
}
