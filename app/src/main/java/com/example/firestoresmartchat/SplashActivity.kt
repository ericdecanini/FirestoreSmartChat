package com.example.firestoresmartchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkLoggedIn()
    }

    private fun checkLoggedIn() {
        val user = auth.currentUser

        if (user != null)
            launchLobby()
        else
            launchLogIn()
    }

    private fun launchLogIn() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun launchLobby() {
        startActivity(Intent(this, LobbyActivity::class.java))
    }

}
