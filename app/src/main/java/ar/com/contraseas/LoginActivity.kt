package ar.com.contraseas

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btLogin.setOnClickListener(){
            val email = etNombreUsuario.text.toString()
            val password = etContraseña.text.toString()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                        Log.d("LOGIN", "createUserWithEmail:success")
                        val user=mAuth.currentUser
                        if(user!!.isEmailVerified){
                            finish();

                        }
                        else{
                            user.sendEmailVerification().addOnCompleteListener{
                                if(it.isSuccessful){
                                    Log.d("LOGIN", "se envió email de validación")
                                    tvMensajeValidacion.visibility= View.VISIBLE
                                }
                                else{
                                    Log.d("LOGIN", "error en envío de email de validación")
                                }
                            }
                        }


                    } else { // If sign in fails, display a message to the user.
                        Log.w(
                            "LOGIN",
                            "createUserWithEmail:failure",
                            task.exception
                        )
                        Toast.makeText(
                            this, "Falló la autenticación",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // ...
                }
        }
        btRegistrar.setOnClickListener(){
            val email = etNombreUsuario.text.toString()
            val password = etContraseña.text.toString()
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                        Log.d("REGISTRAR", "createUserWithEmail:success")
                        finish()
                    } else { // If sign in fails, display a message to the user.
                        Log.w(
                            "REGISTRAR",
                            "createUserWithEmail:failure",
                            task.exception
                        )
                        Toast.makeText(
                            this, "Falló la auntenticación.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    // ...
                }
        }

    }
}
