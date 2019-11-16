package ar.com.contraseas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_generar.*

import kotlinx.android.synthetic.main.content_generar.*


class Generar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
        val resultIntent = Intent()
        resultIntent.putExtra("NombreClave",
            etNombreClave.text.toString())
        resultIntent.putExtra("ValorClave",
            tvClave.text.toString())
        setResult(0,resultIntent)
        finish()

    }


}
