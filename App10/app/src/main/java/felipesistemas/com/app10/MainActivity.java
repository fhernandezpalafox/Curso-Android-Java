package felipesistemas.com.app10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Switch uiSwitch;
    SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefe  = getSharedPreferences("datos", Context.MODE_PRIVATE);

        uiSwitch  =  findViewById(R.id.switch1);

        String idioma =prefe.getString("idioma", "");
        if (idioma.length()!=0){

            if (idioma.equals("es")){
                uiSwitch.setChecked(false);
            }else {
                uiSwitch.setChecked(true);
            }
        }




        uiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = null;
                Configuration config =  new Configuration();

                SharedPreferences.Editor editor = prefe.edit();

                if (uiSwitch.isChecked()){

                    locale = new Locale("en");
                    Locale.setDefault(locale);
                    config.locale = locale;

                    editor.putString("idioma","en");
                    editor.commit();
                }else{
                    locale = new Locale("es");
                    Locale.setDefault(locale);
                    config.locale = locale;

                    editor.putString("idioma","es");
                    editor.commit();
                }

                getResources().updateConfiguration(config,null);
                Intent refresh =  new Intent(MainActivity.this,MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });


    }
}
