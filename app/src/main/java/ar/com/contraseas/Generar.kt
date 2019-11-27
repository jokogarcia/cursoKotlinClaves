package ar.com.contraseas

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_generar.*
import kotlinx.android.synthetic.main.contenido_dialogo_longitud.*

import kotlinx.android.synthetic.main.content_generar.*


class Generar : AppCompatActivity() {
    var longitud = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar)
        setSupportActionBar(toolbar)
        btGenerarClave.callOnClick()
        fab.setOnClickListener { view ->
            val resultIntent = Intent()
            resultIntent.putExtra("NombreClave",
                etNombreClave.text.toString())
            resultIntent.putExtra("ValorClave",
                tvClave.text.toString())
            setResult(0,resultIntent)
            finish()
        }

    }
    fun onClickGenerar(view: View) {

        val minusculas="qwertyuiopasdfghjklzxcvbnm"
        val mayusculas = minusculas.toUpperCase()
        val numeros = "1234567890"
        val simbolos = "!?¿-_#@$"
        var caracteres = ""
        if(btMayusculas.isChecked)
            caracteres = caracteres + mayusculas
        if(btMinusculas.isChecked)
            caracteres = caracteres + minusculas
        if(btNumeros.isChecked)
            caracteres += numeros
        if(btSimbolos.isChecked)
            caracteres+= simbolos
        val charPool = caracteres.toList()
        val clave = RandomString.generar(charPool,longitud)
        tvClave.text=clave
    }
    fun onClickBotonBarraSuperior(view : View){
        if(view is ToggleButton){
           if(view.isChecked){
               view.setTextColor(resources.getColor(R.color.textoSeleccionado))
           }
            else{
               view.setTextColor(resources.getColor(R.color.textoNoSeleccionado))
           }
        }
        if (!btSimbolos.isChecked &&
            !btMayusculas.isChecked &&
            !btMinusculas.isChecked &&
            !btNumeros.isChecked
                ){
            btMinusculas.isChecked=true
            btMinusculas.setTextColor(resources.getColor(R.color.textoSeleccionado))
        }
        btGenerarClave.callOnClick()
    }
    fun onClickBotonLargo(view : View){
        val builder = AlertDialog.Builder(this)
        val contenido = layoutInflater.inflate(R.layout.contenido_dialogo_longitud,null)
        builder.setView(contenido)
        builder.setTitle("Ingrese una logngitud")
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener(){
            dialog,id ->
            longitud = contenido.findViewById<EditText>(R.id.etLargoClave).text.toString().toIntOrNull()?:10
            btGenerarClave.callOnClick()


        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener(){
                dialog,id ->{

        }
        })
        builder.show()




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
