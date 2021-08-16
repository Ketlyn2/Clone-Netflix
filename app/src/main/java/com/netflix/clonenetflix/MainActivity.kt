package com.netflix.clonenetflix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        Handler().postDelayed({abrirTelaLogin()}, 5000)

    }

    private fun abrirTelaLogin() {

        var intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()

    }

}