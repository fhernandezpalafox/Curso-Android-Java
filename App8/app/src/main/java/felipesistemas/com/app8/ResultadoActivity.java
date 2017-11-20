package felipesistemas.com.app8;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView lblparametro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        lblparametro  =  findViewById(R.id.lblparametro);

        Bundle bundle =  getIntent().getExtras();

        if (bundle.getString("parametro")!=null){
            lblparametro.setText(bundle.getString("parametro"));

            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager notificationManager  =  (NotificationManager) getBaseContext().getSystemService(ns);
            notificationManager.cancel(bundle.getInt("idNotificacion"));

        }

    }
}
