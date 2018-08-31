package felipesistemas.com.app2kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSumar.setOnClickListener{

            val numero1  =  txtNumero1.text.toString().toInt()
            val numero2  =  txtNumero2.text.toString().toInt()
            val suma = numero1 +  numero2

            Toast.makeText(this,"La suma es $suma",
                    Toast.LENGTH_LONG).show()

        }
    }
}
