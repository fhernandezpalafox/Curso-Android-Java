package felipesistemas.com.app8;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button btnnotificacion1,btnnotificacion2,btnnotificacion3,btnnotificacion4, btnnotificacion5;
    private int NumeroNotificaciones = 5;

    private ConfiguracionNotificacionV8 configuracionNotificacionV8;


    public void shortCustDinamico(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return;

        int idNoticacion = 234;

        ShortcutManager shortcutManager =  getApplicationContext().getSystemService(ShortcutManager.class);

        Intent nuevaTareaIntent =  new Intent(getApplicationContext(),ResultadoActivity.class);
        nuevaTareaIntent.setAction(Intent.ACTION_VIEW);
        nuevaTareaIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        nuevaTareaIntent.putExtra("parametro","valor 1");
        nuevaTareaIntent.putExtra("idNotificacion",idNoticacion);

        Intent resultIntent = new Intent(MainActivity.this,ResultadoActivity.class);

        resultIntent.putExtra("parametro","valor 1");
        resultIntent.putExtra("idNotificacion",idNoticacion);

        ShortcutInfo postShortcut =
                new ShortcutInfo.Builder(getApplicationContext(),"1")
                .setShortLabel("Informacion")
                .setLongLabel("Informacion importante...")
                .setIcon(Icon.createWithResource(getApplicationContext(),R.mipmap.ic_launcher))
                .setIntent(nuevaTareaIntent)
                .build();
        shortcutManager.addDynamicShortcuts(Arrays.asList(postShortcut));




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        shortCustDinamico();


        btnnotificacion1  =  findViewById(R.id.btnotificacion1);
        btnnotificacion2  =  findViewById(R.id.btnotificacion2);
        btnnotificacion3  =  findViewById(R.id.btnotificacion3);
        btnnotificacion4  =  findViewById(R.id.btnotificacion4);

        btnnotificacion5  =  findViewById(R.id.btnnotificacion5);


        btnnotificacion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Notification.Builder Nbuilder;

              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                  Nbuilder =  new Notification.Builder(MainActivity.this,"1");
              }else {
                  Nbuilder  =  new Notification.Builder(MainActivity.this);
              }

              Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

              Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

              Nbuilder.setContentTitle("Ejemplo de notificacion");

              Nbuilder.setContentText("Ese es mi contenido de notificacion");

              Nbuilder.setTicker("Ejemplo de notificacion");

              NotificationManager notificationManager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

              notificationManager.notify(0,Nbuilder.build());
            }
        });


        btnnotificacion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idNoticacion = 234;

                Intent resultIntent = new Intent(MainActivity.this,ResultadoActivity.class);

                resultIntent.putExtra("parametro","valor 1");
                resultIntent.putExtra("idNotificacion",idNoticacion);

                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent pendingIntent =  PendingIntent.getActivity(MainActivity.this,
                                        1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);


                NotificationCompat.InboxStyle  inboxStyle  = new NotificationCompat.InboxStyle();
                inboxStyle.setBigContentTitle("Notificacion personalizada");
                inboxStyle.addLine("Esta es una linea numero 1");
                inboxStyle.addLine("Esta es una linea numero 2");
                inboxStyle.addLine("Esta es una linea numero 3");
                inboxStyle.addLine("Esta es una linea numero 4");
                inboxStyle.addLine("Esta es una linea numero 5");
                inboxStyle.addLine("Esta es una linea numero 6");

                NumeroNotificaciones  += 1;
                inboxStyle.setSummaryText(String.format("+ %d mas",NumeroNotificaciones));


                NotificationCompat.Builder Nbuilder;

                Nbuilder =  new NotificationCompat.Builder(MainActivity.this,"1");

                Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

                Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

                Nbuilder.setContentTitle("Ejemplo de notificacion");

                Nbuilder.setContentText("Esre es mi contenido de notificacion");

                Nbuilder.setTicker("Ejemplo de notificacion");

                Nbuilder.setVibrate(new long[] {100, 250, 100, 500});

                Nbuilder.setStyle(inboxStyle);

                Nbuilder.addAction(R.drawable.ic_chat_black_24dp,"Ver Mensaje",pendingIntent);

                NotificationManager notificationManager  =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(idNoticacion,Nbuilder.build());


            }
        });


        btnnotificacion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NotificationCompat.BigTextStyle  textoLargo  = new NotificationCompat.BigTextStyle();

                textoLargo.bigText("Android Studio es el entorno de desarrollo integrado (IDE) oficial para el desarrollo de aplicaciones para Android y se basa en IntelliJ IDEA . Además del potente editor de códigos y las herramientas para desarrolladores de IntelliJ, Android Studio ofrece aún más funciones que aumentan tu productividad durante la compilación de apps para Android");
                textoLargo.setBigContentTitle("Android Studio");
                textoLargo.setSummaryText("Hecho por: Android");


                NotificationCompat.Builder Nbuilder;

                Nbuilder =  new NotificationCompat.Builder(MainActivity.this,"1");

                Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

                Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

                Nbuilder.setContentTitle("Ejemplo de notificacion");

                Nbuilder.setContentText("Esre es mi contenido de notificacion");

                Nbuilder.setTicker("Ejemplo de notificacion");

                Nbuilder.setVibrate(new long[] {100, 250, 100, 500});

                Nbuilder.setStyle(textoLargo);

                NotificationManager notificationManager  =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0,Nbuilder.build());


            }
        });


        btnnotificacion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int idNoticacion = 234;

                Intent resultIntent = new Intent(MainActivity.this,ResultadoActivity.class);

                resultIntent.putExtra("parametro","valor 1");
                resultIntent.putExtra("idNotificacion",idNoticacion);

                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );

                PendingIntent pendingIntent =  PendingIntent.getActivity(MainActivity.this,
                        1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.BigPictureStyle bigPictureStyle =  new NotificationCompat.BigPictureStyle();

                bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.androidlogo2)).build();


                NotificationCompat.Builder Nbuilder;

                Nbuilder =  new NotificationCompat.Builder(MainActivity.this,"1");

                Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

                Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

                Nbuilder.setContentTitle("Ejemplo de notificacion");

                Nbuilder.setContentText("Esre es mi contenido de notificacion");

                Nbuilder.setTicker("Ejemplo de notificacion");

                Nbuilder.setVibrate(new long[] {100, 250, 100, 500});

                Nbuilder.setStyle(bigPictureStyle);

                Nbuilder.addAction(R.drawable.ic_share_black_24dp,"Compartir",pendingIntent);

                Nbuilder.addAction(R.drawable.ic_send_black_24dp,"Enviar",pendingIntent);

                NotificationManager notificationManager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(idNoticacion,Nbuilder.build());

            }
        });


        btnnotificacion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultIntent = new Intent(MainActivity.this,ResultadoActivity.class);

                resultIntent.putExtra("parametro","valor 1");
                resultIntent.putExtra("idNotificacion",3);

                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent pendingIntent =  PendingIntent.getActivity(MainActivity.this,
                        1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);


                configuracionNotificacionV8 =  new ConfiguracionNotificacionV8(MainActivity.this);

                Notification.Builder nb =
                        configuracionNotificacionV8.getAndroidChannelNotification("Titulo de prueba Android", "By " + "Felipe");

                nb.addAction(R.drawable.ic_send_black_24dp,"Enviar",pendingIntent);

                configuracionNotificacionV8.getManager().notify(101, nb.build());


                Notification.Builder nb2 = configuracionNotificacionV8
                        .getIosChannelNotification("Titulo de prueba iOS", "By " + "Felipe");

                configuracionNotificacionV8.getManager().notify(102, nb2.build());
            }
        });


    }
}
