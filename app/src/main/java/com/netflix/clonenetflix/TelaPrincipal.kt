package com.netflix.clonenetflix

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class TelaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)

        val serie1: ImageView = findViewById(R.id.serie1)
        val serie2: ImageView = findViewById(R.id.serie2)
        val serie3: ImageView = findViewById(R.id.serie3)
        val serie4: ImageView = findViewById(R.id.serie4)
        val imagens = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-clone-b0013.appspot.com/o/Imagens%2Fwitcher.png?alt=media&token=7c53fc16-5dcf-4f40-9385-100223b340b1")

        Picasso.get().load(imagens).fit().placeholder(R.drawable.gif).into(serie1)
        Picasso.get().load(imagens).fit().placeholder(R.drawable.gif).into(serie2)
        Picasso.get().load(imagens).fit().placeholder(R.drawable.gif).into(serie3)
        Picasso.get().load(imagens).fit().placeholder(R.drawable.gif).into(serie4)

        serie1.setOnClickListener {
            var intent = Intent(this,  Episodios::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_tela_principal, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_sair ->  {
                FirebaseAuth.getInstance().signOut()
                voltarTelaLogin()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun voltarTelaLogin() {
        var intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}