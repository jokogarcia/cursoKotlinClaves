package ar.com.contraseas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val claves = mutableMapOf<String,String>()
    lateinit var adapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("MisClaves", Context.MODE_PRIVATE)
        val copy = prefs.all
        for ((nombre,clave) in copy ){
            claves.put(nombre, clave as String)
        }

        val array = ArrayList(claves.keys)
        adapter = ArrayAdapter(this,R.layout.spinner_elemento_visible,
            array )

        adapter.setDropDownViewResource(R.layout.spinner_elemento_visible)
        spNombre.adapter = adapter
        spNombre.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(
                    spinner: AdapterView<*>?,
                    vista: View?,
                    indice: Int,
                    id: Long) {

                    val nombre = spinner?.adapter?.getItem(indice)
                    tvClave.text = claves[nombre]


                }

            }



    }
/*
* val nombre = etNombreClave.text.toString()
            val clave = tvClave.text.toString()
            claves.put(nombre,clave)
            val prefs = getSharedPreferences("MisClaves", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            adapter.add(nombre)
            adapter.notifyDataSetChanged()

            editor.putString(nombre,clave)
            editor.commit()*/
    fun onClickGenerar(view: View) {

        //view.isEnabled=false
        val intent = Intent(this,Generar::class.java)
        startActivityForResult(intent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0){
            if(resultCode == 0){
                if(data != null){
                    val ValorClave =
                        data.getStringExtra("ValorClave")
                    val NombreClave =
                        data.getStringExtra("NombreClave")
                    claves.put(NombreClave,ValorClave)
                    val prefs = getSharedPreferences("MisClaves",
                        Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString(NombreClave,ValorClave)
                    editor.commit()
                    adapter.add(NombreClave)
                    adapter.notifyDataSetChanged()
                    //btGenerarMain.isEnabled=true


                }
            }
        }

    }




    /*
    * Separar generar y guardar en otra actividad
    * eliminar claves
    * login
    * generar historial de claves sobreescritas
    * exportar claves al mail
    * compartir claves por whatsapp
    * No permitir nombres vacios
    * Elegir tipos de caracteres y cantidad
    * implementar almacenamiento en la nube
    *
    * */

}
