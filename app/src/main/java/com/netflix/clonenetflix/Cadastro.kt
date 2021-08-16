package com.netflix.clonenetflix

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class Cadastro : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar!!.hide()

        val btnVoltar: ImageButton = findViewById(R.id.btnVoltar)
        var btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnVoltar.visibility = View.VISIBLE

        btnVoltar.setOnClickListener {
            val intent: Intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        btnCadastrar.setOnClickListener {
            CadastrarUsuario()
        }

    }

    private fun CadastrarUsuario() {

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
            txtMensagem.setText("Preencha o campo com uma senha!")
       } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {

                if(it.isSuccessful) {
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    voltarTelaLogin()
                }

            }.addOnFailureListener {

                var error = it

                when {
                    error is FirebaseAuthInvalidCredentialsException -> txtMensagem.setText("Digite um email válido!")
                    error is FirebaseAuthWeakPasswordException -> txtMensagem.setText("Digite uma senha com no mínimo 6 caracteres!")
                    error is FirebaseAuthUserCollisionException -> txtMensagem.setText("Esta conta ja foi cadastrada!")
                    error is FirebaseNetworkException -> txtMensagem.setText("Sem conexão com a internet!")
                    else -> txtMensagem.setText("Erro ao cadastrar o usuário. Tente Novamente!")
                }

            }

        }

    }

    private fun voltarTelaLogin() {
        val intent: Intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}