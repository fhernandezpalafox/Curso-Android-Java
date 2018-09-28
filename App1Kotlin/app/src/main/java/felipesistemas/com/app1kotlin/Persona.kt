package felipesistemas.com.app1kotlin

/**
 * Created by FelipeHernandez on 18/01/18.
 */
class Persona
       constructor(nombre: String, edad: Int, edocivil: String) {

    var nombre: String = nombre
    var edad: Int = edad
    var edocivil: String = edocivil



    fun imprimirDatos() {
        println("Su nombre es : $nombre y tiene una edad de " +
                "$edad, su estado civil $edocivil")
    }


    fun esMayorEdad() {
        if (edad >= 18)
            println("Es mayor de edad $nombre")
        else
            println("No es mayor de edad $nombre")
    }
}