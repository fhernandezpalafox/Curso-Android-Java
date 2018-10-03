package felipehernandez.com.app4kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listaElementos = arrayOf("Sumar","Restar")

        val adaptador = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listaElementos)

        spinnerOperaciones.adapter  = adaptador


        btnAceptar.setOnClickListener {

            var elementoSelecionado = spinnerOperaciones.selectedItem.toString()

            var resultado = 0

            if (elementoSelecionado.equals("Sumar")){

                 resultado  =  9 +  txtNumero.text.toString().toInt()
            }else {
                 resultado  =  9 -  txtNumero.text.toString().toInt()
            }

            var OperacionesExtras = ""

            if(chkMultiplicar.isChecked){
                OperacionesExtras += " El resultado de la operacion Multiplicar es ${9 * txtNumero.text.toString().toInt()}"
            }

            if(chkDividir.isChecked){
                OperacionesExtras += " El resultado de la operacion Dividir es ${9 / txtNumero.text.toString().toInt()}"
            }

            lblResultado.text = "El resultado de la operacion ${elementoSelecionado} ${resultado} ${OperacionesExtras}"

        }
    }
}
