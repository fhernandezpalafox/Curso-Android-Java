package felipehernandez.com.app3kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logica()
    }

    fun logica() {

        btnAceptar.setOnClickListener{
            var cadenaTexto: String
            var genero: String

            if (validador() == false){

                if (rdbHombre.isChecked){
                    genero = rdbHombre.text.toString()
                }else {
                    genero = rdbMujer.text.toString()
                }

                cadenaTexto  = "Tu nombre es ${txtNombre.text.toString()}, " +
                        " apellido  es ${txtApellido.text.toString()} y tu genero es ${genero} "

                lblInformacion.text = cadenaTexto

            }

        }
    }

    fun validador() : Boolean {

        var validadorEntro =  false

         if (TextUtils.isEmpty(txtNombre.text.toString())){
             TextInputLayoutNombre.error = "Captura tu nombre"
             validadorEntro =  true
         }
        if (TextUtils.isEmpty(txtApellido.text.toString())){
            TextInputLayoutApellido.error = "Captura tu apellido"
            validadorEntro =  true
        }

        return validadorEntro
    }
}
