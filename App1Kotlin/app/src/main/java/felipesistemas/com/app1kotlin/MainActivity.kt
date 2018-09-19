package felipesistemas.com.app1kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Imprimir
        println("hola Mundo desde kotlin!")

        //variables
        var edad = 28
            edad  = 30

        val nombre  =  "Felipe" //let
            //nombre  = "Oscaar"


        //Tipos explicitos
        val explicidato : String  = "Felipe Hernandez"
        var edad2 : Int = 28
        val estatura : Double  = 1.79


        //Combinacion de tipos de datos
        val carrera = "Sistemas"
        val generacion = 2018
        val datoCompleto  =  carrera+" Generación "+2017


        //String interpolaciones

        val numero1  = 3
        val numero2  = 5
        val respuesta  = "La suma de $numero1 + $numero2 el resultado es ${numero1  + numero2}"
        println(respuesta)


        //Arreglos
        /*  ByteArray
            ShortArray
            LongArray
            FloatArray
            DoubleArray
            BooleanArray
            charArrayOf()
         */

        val calificaciones : IntArray = IntArray(5)

        calificaciones[0] = 10
        calificaciones[1] = 8
        calificaciones[2] = 7
        calificaciones[3] = 6
        calificaciones[4] = 8


        for (i in 0..4){
            println("Tu calificacion es ${calificaciones[i]}")
        }


        //String
        val nombres  =  arrayOf("Felipe","Oscar","Juan","Pedro","Leo")

        for (i in nombres.indices){
            println("La persona ${i+1} se llama ${nombres[i]}")
        }

        //for
        for (index in 1..5){
            println("tabla del 5 x $index = ${index * 5}")
        }


        val nombres2  =  arrayOf("Felipe","Oscar","Juan","Pedro")
        nombres2[1]  = "Jesus"

        for (i in nombres.indices){
            println("La persona ${i+1} se llama ${nombres[i]}")
        }

        //maps
        val ocupaciones =  mutableMapOf("Felipe" to "sistemas", "Oscar" to "Arquitectura")
        ocupaciones["Pedro"] = "Recursos Humanos"

        println(ocupaciones)


        //Incializacion de Lista de Objetos
        val arragloVacio  =  arrayOf<String>() //List<String>
        val mapVacio = mapOf<String,Float>()



        //if y if como expression

        //if
        val valor1 = 35
        val valor2 = 40

        if (valor1 > valor2)
            println("El mayor valor es $valor1")
        else
            println("El mayor valor es $valor2")

        //if como expression
        val mayor = if (valor1 > valor2) valor1 else valor2
        println("El mayor entre $valor1 y $valor2 es $mayor")


        //while , do while siguen siendo lo mismo que en otros lenguajes

        //instrucion when

        var  semestre  = 2

        when (semestre) {
            1 -> println("Primer Semestre")
            2 -> println("Segundo Semestre")
            3 -> println("Tercer Semestre")
            4 -> println("Cuarto Semestre")
            else -> println("No te encuentras en los rangos del 1 al 4")
        }


        //Instrucion  when como funcion
        var tipoAlumno = "Bueno" //malo, Regular
        var nombreAlumno = "Felipe Hernandez"

        nombreAlumno += when {
            (tipoAlumno.equals("Bueno")==true)  -> {
                println("Saco 10  de calificacion")
                " porque saco 10 de calificacion"
            }
            (tipoAlumno.equals("Regular")==true)  -> {
                println("Saco 8  de calificacion")
                " porque saco 8 de calificacion"
            }
            else -> {
                println("Saco 5  de calificacion")
                " porque saco 5 de calificacion"
            }
        }

        println(nombreAlumno)


        //funciones
        println(misDatos("felipe","hernandez",28))




        var laSuma  = suma(8,7)
        println("la suma de los dos numero fue $laSuma")

        //funciones  como expression
        println("la suma de tres numeros es ${suma(3,6,8)}")

        //funciones con parametros nombrados
        println(suma(numero2= 5,numero1 = 8,numero3 = 5))


        imprimirNombre("felipe hernandez")


        //funciones valores por default
        resta(3)

        resta(3,1)


        //funciones internas locales
        evaluacionAlumno(8,"Felipe Hernandez")



        //clases
        var alumno = Alumno()
        alumno.inicio("Felipe",28,"Sistemas")
        alumno.imprimirDatos()
        alumno.cuantosSemestres()


        //Clase de con contructor
        var persona = Persona("Felipe",28,"Casado")
        persona.imprimirDatos()
        persona.esMayorEdad()
    }


    fun suma(numero1:Int,numero2:Int):Int{
        println(numero1 + numero2)
        return numero1 + numero2
    }

    fun suma(numero1: Int, numero2:Int,numero3:Int) = numero1 + numero2 + numero3


    fun misDatos(nombre: String, apellido: String, edad: Int): String {
        return "Mi nombre es $nombre mi apellido es $apellido  y mi edad es $edad"
    }

    fun imprimirNombre (nombre : String){
        println("Tu nombre es  $nombre")
    }

    fun resta(numero1 : Int, numero2 : Int = 2){
        println("la resta de los dos numeros es ${numero1 - numero2}")
    }


    fun evaluacionAlumno(calificacion:Int, alumno:String)
    {
        fun BuenooMalo(cal: Int) =  if (cal <= 10 && cal >= 8) "bueno" else "Malo"

        println("el alumno $alumno se considera que es" +
                " ${BuenooMalo(calificacion)} porque saco $calificacion")

    }


    //creacion de clases
    class Alumno {
        var nombre: String = ""
        var carrera: String = ""
        var edad: Int = 0

        fun inicio(nombre: String, edad: Int, carrera: String) {
            this.nombre = nombre
            this.edad = edad
            this.carrera =  carrera
        }

        fun imprimirDatos() {
            println("tu nombre es $nombre y tienes una edad de " +
                    "$edad y actualmente cursas esta carrera $carrera")
        }

        fun cuantosSemestres() {
            if (carrera.equals("Sistemas"))
                println("Los semestres a cursar son 8")
            else
                println("No se tiene registro de otra carrera")
        }
    }

}
