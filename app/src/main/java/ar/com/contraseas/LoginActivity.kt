package ar.com.contraseas

import android.R.attr
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvMensajeEmail.visibility=View.INVISIBLE
        btLogin.setOnClickListener(){
            view->
            login(etEmailUsuario.text.toString(),
                etTextContraseña.text.toString())
        }

    }
    fun onClickRegistrar(vire: View){
        registrar(etEmailUsuario.text.toString(),
            etTextContraseña.text.toString())
    }
    fun procesarRegistro(){
        Log.d("LOGIN", "Logueado exitosamente con email")
        val user = mAuth.currentUser
        if(user!!.isEmailVerified){
            finish()
        }
        else{
            tvMensajeEmail.visibility=View.VISIBLE
            user.sendEmailVerification().addOnSuccessListener {
                Toast.makeText(this,
                    "Se ha mandandado un email a su casilla de correo",
                    Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(this,
                    "No se pudo mandar un email a su casilla de correo",
                    Toast.LENGTH_LONG).show()
            }
        }



    }
    fun login(email:String, password:String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    procesarRegistro()
                } else { // If sign in fails, display a message to the user.
                    Log.d("LOGIN", "Falló login con email", task.exception)
                    Toast.makeText(
                        this, "Falló la autenticación",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                // ...
            }
    }
    fun registrar(email:String, password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    procesarRegistro()

                } else { // If sign in fails, display a message to the user.
                    Log.d("REGISTER", "Falló login con email", task.exception)
                    Toast.makeText(
                        this, "Falló la registración",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                // ...
            }
    }
}
