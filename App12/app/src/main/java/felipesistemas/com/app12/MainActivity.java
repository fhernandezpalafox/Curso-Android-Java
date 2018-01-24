package felipesistemas.com.app12;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgLlamar, imgMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgLlamar = findViewById(R.id.imgLlamar);
        imgMensaje = findViewById(R.id.imgMensaje);


        imgLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:4772409707"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });


        imgMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent compartirMensaje  = new Intent(Intent.ACTION_SEND);
                compartirMensaje.setType("text/plain");
                compartirMensaje.putExtra(Intent.EXTRA_TEXT,"Este es un mensaje de prueba");
                compartirMensaje.setPackage("com.whatsapp");
                startActivity(compartirMensaje);
            }
        });

    }
}
