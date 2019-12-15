package com.example.firestoresmartchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edittext_email
import kotlinx.android.synthetic.main.activity_login.edittext_password
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    var email: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setViewListeners()
    }

    private fun setViewListeners() {
        button_login.setOnClickListener { submit() }
        textview_register.setOnClickListener { launchRegister() }
    }

    private fun submit() {
        button_login.isEnabled = false

        email = edittext_email.text.toString()
        password = edittext_password.text.toString()

        if (validate()) {
            login()
        } else {
            showErrorMessage()
        }
    }

    private fun validate(): Boolean {
        return !email.isNullOrEmpty()
            && !password.isNullOrEmpty()
            && isEmailValid(email!!)
    }

    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun login() {
        auth.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    launchLobby()
                } else {
                    showErrorMessage()
                }
            }
    }

    private fun launchLobby() {
        val intent = Intent(this, LobbyActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun launchRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun showErrorMessage() {
        textview_error_login.visibility = View.VISIBLE
        button_login.isEnabled = true
    }

}
