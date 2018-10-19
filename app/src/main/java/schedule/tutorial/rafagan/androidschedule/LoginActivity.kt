package schedule.tutorial.rafagan.androidschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import schedule.tutorial.rafagan.androidschedule.firebase.Auth


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

        val password = findViewById<EditText>(R.id.passwordLogin)

        val button = findViewById<Button>(R.id.authBtnLogin)
        button.setOnClickListener { _ ->
            val emailTxt = email.text.toString()
            val passwordTxt = password.text.toString()

            if(emailTxt.isEmpty()) return@setOnClickListener

            if (!isValidEmail(emailTxt)) {
                Toast.makeText(applicationContext, "E-mail inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Auth.instance.signInWithEmailAndPassword(emailTxt, passwordTxt)
                    .addOnCompleteListener(this) {
                        if(it.isSuccessful) {
                            val myIntent = Intent(this, MainActivity::class.java)
                            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(myIntent)
                        } else {
                            Toast.makeText(
                                    applicationContext,
                                    "E-mail ou senha incorretos",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
        }
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        if (target == null) return false
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
