package projects.com.codenicely.pehlakadam.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.home.views.HomeActivity;

/**
 * Created by ramya on 25/3/17.
 */

public class MyFirebaseService extends FirebaseMessagingService {
    private String TAG="MyFirebaseService";
    private static int nid=0;
    private Bitmap bitmap;
    private String imageUri;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getClickAction());

        }
        imageUri = remoteMessage.getData().get("image");
        if(imageUri.isEmpty()){
            sendNotification(remoteMessage);
        }
        else
        {
            imageUri=Urls.BASE_URL+ Urls.SUB_NOTIFICATION_IMAGES+imageUri;
            bitmap=getBitmapfromUrl(imageUri);
            Log.d("image is:",imageUri);
            sendImageNotification(remoteMessage,bitmap);
        }
    }

    //we need this module only for creating notification when app is in foreground
    private void sendImageNotification(RemoteMessage messageBody,Bitmap bitmap) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        intent.putExtra(Keys.KEY_FCM_ACTIVITY,true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, nid++ /* Request code*/ , intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("VegWorld")
                .setContentText(""+messageBody.getData().get("message"))
                .setTicker(messageBody.getData().get("message")+"")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOngoing(true)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setSound(defaultSoundUri)
                .setPriority(10011)

                .setSmallIcon(R.drawable.pk_icon10_text_green);

        Log.d("value of nid",String.valueOf(nid));

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(nid  /*ID of notification*/ , notificationBuilder.build());
    }

    private void sendNotification(RemoteMessage remoteMessage)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        intent.putExtra(Keys.KEY_FCM_ACTIVITY,true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, nid++ /* Request code*/ , intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("VegWorld")
                .setContentText(remoteMessage.getData().get("message"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setSmallIcon(R.drawable.pk_icon10_text_green);

        Log.d("value of nid",String.valueOf(nid));

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(nid  /*ID of notification*/ , notificationBuilder.build());

    }

    /*
    *To get a Bitmap image from the URL received
    * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
    }
