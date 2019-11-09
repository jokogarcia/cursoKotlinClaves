package ar.com.contraseas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val claves = mutableMapOf<String,String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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


    }

    fun onClickVerClave(view: View) {
        val clave = claves.get(etNombreClave.text.toString())
        tvClave.text = clave

    }

}
