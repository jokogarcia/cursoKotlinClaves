package ar.com.contraseas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val claves = mutableMapOf<String,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("MisClaves", Context.MODE_PRIVATE)
        val copy = prefs.all
        for ((nombre,clave) in copy ){
            claves.put(nombre, clave as String)
        }

        val array = ArrayList(claves.keys)
        val adapter = ArrayAdapter(this,R.layout.spinner_elemento_visible,
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

    fun onClickGenerar(view: View) {
        val minusculas="qwertyuiopasdfghjklzxcvbnm"
        val mayusculas = minusculas.toUpperCase()
        val numeros = "1234567890"
        val charPool = (minusculas+mayusculas+numeros).toList()
        val clave = RandomString.generar(charPool,10)
        tvClave.text=clave
    }

    fun onClickGuardarClave(view: View) {
        val nombre = etNombreClave.text.toString()
        val clave = tvClave.text.toString()
        claves.put(nombre,clave)
        val prefs = getSharedPreferences("MisClaves", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(nombre,clave)
        editor.commit()
    }

    fun onClickVerClave(view: View) {
        val clave = claves.get(etNombreClave.text.toString())
        tvClave.text = clave

    }

}
