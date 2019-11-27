package ar.com.contraseas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener(){
            view-> val mainActivityIntent = Intent(this,MainActivity::class.java)
            startActivity(mainActivityIntent)
        }

    }
}
