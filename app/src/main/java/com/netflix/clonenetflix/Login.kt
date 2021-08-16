package com.netflix.clonenetflix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import androidx.fragment.app.FragmentActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import java.security.AccessController.getContext

class Login : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        supportActionBar!!.hide()

        verificarUsuarioLogado()

        val btnVoltar: ImageButton = findViewById(R.id.btnVoltar)
        var btnEntrar: Button = findViewById(R.id.btnEntrar)
        var textoCadastro: TextView = findViewById(R.id.textCadastrar)

        btnVoltar.visibility = View.INVISIBLE

        textoCadastro.setOnClickListener {
            abrirTelaCadastro()
        }

        btnEntrar.setOnClickListener {
            autenticarUsuario()
        }

    }

    private fun autenticarUsuario() {

        var edtEmail: EditText = findViewById(R.id.edtEmail)
        var edtSenha: EditText = findViewById(R.id.edtSenha)
        var txtMensagem: TextView = findViewById(R.id.mensagemError)

        var email = edtEmail.text.toString()
        var senha = edtSenha.text.toString()

        if (email.isEmpty() || senha.isEmpty()) {
            txtMensagem.setText("Preencha os campos com seu email e senha!")
        } else if(email.isEmpty()){
            txtMensagem.setText("Preencha o campo com seu email!")
        } else if(senha.isEmpty()) {
            txtMensagem.setText("Preencha o campo com sua senha!")
        } else {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    abrirTelaPrincipal()
                }

            }.addOnFailureListener {

                var error = it

                when {
                    error is FirebaseAuthInvalidCredentialsException -> txtMensagem.setText("Email ou senha estão incorretos!")
                    error is FirebaseNetworkException -> txtMensagem.setText("Sem conexão com a internet!")
                    else -> txtMensagem.setText("Error ao logar o usuáro!")
                }

            }

        }

    }

    private fun verificarUsuarioLogado() {

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
            abrirTelaPrincipal()
        }

    }

    private fun abrirTelaPrincipal() {
        var intent: Intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

    private fun abrirTelaCadastro() {
        var intent: Intent = Intent(this, Cadastro::class.java)
        startActivity(intent)
    }
}