package felipesistemas.com.app8;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button btnnotificacion1,btnnotificacion2,btnnotificacion3,btnnotificacion4;
    private int NumeroNotificaciones = 5;

    private NotificationManager mManager;

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }


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


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            NotificationChannel canal1Prueba = new NotificationChannel("com.prueba.canalPrueba1",
                    "Canal de prueba 1", NotificationManager.IMPORTANCE_HIGH);
            canal1Prueba.enableLights(true);
            canal1Prueba.enableVibration(true);
            canal1Prueba.setLightColor(Color.GREEN);
            canal1Prueba.setLockscreenVisibility(Notification.BADGE_ICON_SMALL);
            getManager().createNotificationChannel(canal1Prueba);


            NotificationChannel canal2Prueba = new NotificationChannel("com.prueba.canalPrueba2",
                    "Canal de prueba 2", NotificationManager.IMPORTANCE_HIGH);
            canal2Prueba.enableLights(true);
            canal2Prueba.enableVibration(true);
            canal2Prueba.setLightColor(Color.GREEN);
            canal2Prueba.setLockscreenVisibility(Notification.BADGE_ICON_SMALL);
            getManager().createNotificationChannel(canal2Prueba);
        }
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



        createNotificationChannel();

        btnnotificacion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "com.prueba.canalPrueba1")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setContentTitle("Ejemplo de notificacion")
                    .setContentText("Ese es mi contenido de notificacion")
                    .setTicker("Ejemplo de notificacion")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    getManager().notify(0,builder.build());


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

                Nbuilder =  new NotificationCompat.Builder(MainActivity.this,"com.prueba.canalPrueba2");

                Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

                Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

                Nbuilder.setContentTitle("Ejemplo de notificacion");

                Nbuilder.setContentText("Esre es mi contenido de notificacion");

                Nbuilder.setTicker("Ejemplo de notificacion");

                Nbuilder.setVibrate(new long[] {100, 250, 100, 500});

                Nbuilder.setStyle(inboxStyle);

                Nbuilder.addAction(R.drawable.ic_chat_black_24dp,"Ver Mensaje",pendingIntent);


                getManager().notify(0,Nbuilder.build());



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

                Nbuilder =  new NotificationCompat.Builder(MainActivity.this,"com.prueba.canalPrueba1");

                Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

                Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

                Nbuilder.setContentTitle("Ejemplo de notificacion");

                Nbuilder.setContentText("Esre es mi contenido de notificacion");

                Nbuilder.setTicker("Ejemplo de notificacion");

                Nbuilder.setVibrate(new long[] {100, 250, 100, 500});

                Nbuilder.setStyle(textoLargo);

                getManager().notify(0,Nbuilder.build());


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

                Nbuilder =  new NotificationCompat.Builder(MainActivity.this,"com.prueba.canalPrueba2");

                Nbuilder.setSmallIcon(R.mipmap.ic_launcher);

                Nbuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

                Nbuilder.setContentTitle("Ejemplo de notificacion");

                Nbuilder.setContentText("Esre es mi contenido de notificacion");

                Nbuilder.setTicker("Ejemplo de notificacion");

                Nbuilder.setVibrate(new long[] {100, 250, 100, 500});

                Nbuilder.setStyle(bigPictureStyle);

                Nbuilder.addAction(R.drawable.ic_share_black_24dp,"Compartir",pendingIntent);

                Nbuilder.addAction(R.drawable.ic_send_black_24dp,"Enviar",pendingIntent);

                getManager().notify(0,Nbuilder.build());

            }
        });



    }
}
