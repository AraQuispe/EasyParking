package com.ges.easyparking.clases

data class CajaItem(
    val id: Int,
    val name: String,
    val thumb: String
)

fun getItem() = (1 .. myList.size).map{
    CajaItem(
        id = myList[it -1].id,
        name = myList[it -1].name,
        thumb = myList[it -1].thumb
    )
}

data class MyItem(val id: Int, val name: String, val thumb: String)

private val myList: List<MyItem> =
    listOf(
        MyItem(0,"Amazonas","https://www.mapasparacolorear.com/peru/mapa-departamento-amazonas-provincias.png"),
        MyItem(1,"Áncash","https://www.mapasparacolorear.com/peru/mapa-departamento-ancash-provincias.png"),
        MyItem(2,"Apurímac","https://www.mapasparacolorear.com/peru/mapa-departamento-apurimac-provincias.png"),
        MyItem(3,"Arequipa","https://www.mapasparacolorear.com/peru/mapa-departamento-arequipa-provincias.png"),
        MyItem(4,"Ayacucho","https://www.mapasparacolorear.com/peru/mapa-departamento-ayacucho-provincias.png"),
        MyItem(5,"Cajamarca","https://www.mapasparacolorear.com/peru/mapa-departamento-cajamarca-provincias.png"),
        MyItem(6,"Callao","https://www.mapasparacolorear.com/peru/mapa-provincia-callao-capital.png"),
        MyItem(7,"Cusco","https://www.mapasparacolorear.com/peru/mapa-departamento-cusco-provincias.png"),
        MyItem(8,"Huancavelica","https://www.mapasparacolorear.com/peru/mapa-departamento-huancavelica-provincias.png"),
        MyItem(9,"Huánuco","https://www.mapasparacolorear.com/peru/mapa-departamento-huanuco-provincias.png"),
        MyItem(10,"Ica","https://www.mapasparacolorear.com/peru/mapa-departamento-ica-provincias.png"),
        MyItem(11,"Junín","https://www.mapasparacolorear.com/peru/mapa-departamento-junin-provincias.png"),
        MyItem(12,"La Libertad","https://www.mapasparacolorear.com/peru/mapa-departamento-la-libertad-provincias.png"),
        MyItem(13,"Lambayeque","https://www.mapasparacolorear.com/peru/mapa-departamento-lambayeque-provincias.png"),
        MyItem(14,"Lima","https://www.mapasparacolorear.com/peru/mapa-departamento-lima-provincias.png"),

        )

