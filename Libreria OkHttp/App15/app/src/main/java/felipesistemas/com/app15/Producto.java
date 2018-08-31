package felipesistemas.com.app15;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by FelipeHernandez on 27/12/17.
 */

public class Producto {

    @SerializedName("productos")
    ArrayList<HashMap<String, String>> listaProductos = new ArrayList<>();
}
