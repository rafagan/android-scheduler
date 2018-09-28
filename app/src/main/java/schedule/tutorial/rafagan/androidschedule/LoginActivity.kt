package schedule.tutorial.rafagan.androidschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.emailLogin)
        email.onFocusChangeListener = View.OnFocusChangeListener { _, _ ->
            val text = email.text
            if (text.isNotEmpty() && !isValidEmail(text)) {
                Toast.makeText(applicationContext, "E-mail inválido", Toast.LENGTH_SHORT).show()
            }
        }

        val button = findViewById<Button>(R.id.authBtnLogin)
        button.setOnClickListener {
            val text = email.text

            if(text.isEmpty()) return@setOnClickListener

            if (!isValidEmail(text)) {
                Toast.makeText(applicationContext, "E-mail inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Utils.isLogged = true
            val myIntent = Intent(this, MainActivity::class.java)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(myIntent)
        }

        // TODO: Desafio
        // a) Tela de recuperação de senha
        // b) Validação de senha: ter pelo menos 4 caracteres
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        if (target == null) return false
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
