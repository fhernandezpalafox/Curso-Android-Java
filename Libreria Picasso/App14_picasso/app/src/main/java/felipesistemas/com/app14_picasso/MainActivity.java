package felipesistemas.com.app14_picasso;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //declaraciones privadas a nivel de clase
    private ImageView imgDescarga,ImgDescargar2;
    private  String url  = "https://androidzone.org/wp-content/uploads/2013/02/android-musical2.jpg";

    //permisos
    private  static final int GUARDAR_IMAGEN=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgDescarga  =  findViewById(R.id.imgDescargar);
        ImgDescargar2  =  findViewById(R.id.ImgDescargar2);

        Picasso.with(getApplicationContext())
                .load(url)
                .into(ImgDescargar2);

        //Descargar la imagen de la forma manual
        new DownloadImageTask().execute(url);
    }



    private class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {

        /** El sistema Android ejecuta las sentencias del método doInBackground
         * en fondo a traves de un un Worker thread y le pasa los parámetros en la llamada
         * al método AsyncTask.execute()*/
        @Override
        protected Bitmap doInBackground(String... urls) {
            return downloadImagen(urls[0]);
        }


        /** El sistema Android llama a este método para realizar los trabajos necesarios
         * en el UI thread y le entrega el resultado obtenido de la ejecución realizada
         * en el método doInBackground() */
        protected void onPostExecute(Bitmap result) {

            //imagen del Image View para mostrala la imagen descargada
            imgDescarga.setImageBitmap(result);


            //Codigo para compartir la imagen
            final Bitmap imagenGuardar = result;


            File path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file = new File(path, "imagen.jpg");


            //verificar la version de android
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    //pregunta por el permiso
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},GUARDAR_IMAGEN);

                }else{
                    //no necesito, permitir permiso
                    try {
                        final FileOutputStream fileOutputStream = new FileOutputStream(file);

                        Thread t = new Thread(new Runnable() { public void run() {

                            imagenGuardar.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

                        }});


                        fileOutputStream.flush();
                        fileOutputStream.close();
                        file.setReadable(true, false);
                        if (!file.mkdirs()) {
                            Log.e("Crear carpeta", "Carpeta no creada");
                        }
                    }
                    catch (Exception e) {
                        Log.e("Ocurrio un error", e.getMessage());
                    }
                }

            }else {

                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(file);
                    Thread t = new Thread(new Runnable() { public void run() {

                        imagenGuardar.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

                    }});
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    file.setReadable(true, false);
                    if (!file.mkdirs()) {
                       Log.e("Crear carpeta", "Carpeta no creada");
                    }
                }
                catch (Exception e) {
                    Log.e("Ocurrio un error", e.getMessage());
                }
            }

        }


        private Bitmap downloadImagen(String baseUrl) {
            Bitmap myBitmap = null;
            try
            {
                //Se define el objeto URL
                URL url = new URL(baseUrl);
                //Se arma y configura un objeto de conexión HttpURLConnection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                //Recibimos la respuesta de la petición en formato InputStream
                InputStream input = connection.getInputStream();
                //Decodificamos el InputStream a un objeto BitMap
                myBitmap = BitmapFactory.decodeStream(input);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return myBitmap;
        }
    }
}
