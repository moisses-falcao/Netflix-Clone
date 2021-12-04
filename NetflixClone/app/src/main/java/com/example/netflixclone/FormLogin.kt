package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.txtTelaCadastro.setOnClickListener(){
            var intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
            finish()
        }

        VerificarUsarioLogado()

        binding.btEntrar.setOnClickListener(){
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.menssagemLogin

            if (email.isEmpty() || senha.isEmpty()){
                mensagem_erro.setText("Preencha todos os campos.")
            }
            else{
                AutenticarUsuario()

            }
        }

    }
    private fun AutenticarUsuario(){

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.menssagemLogin

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(this,"Login efetuado com sucesso", Toast.LENGTH_LONG).show()
                IrParaTelaDeFilmes()
            }
        }.addOnFailureListener(){
            var erro = it
            when{
                erro is FirebaseAuthInvalidCredentialsException -> mensagem_erro.setText("E-mail ou senha estão incorretos.")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão com a internet!")
                else -> mensagem_erro.setText("Erro ao logar!")
            }
        }
    }

    private fun VerificarUsarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if (usuarioLogado != null){
            IrParaTelaDeFilmes()
        }
    }

    private fun IrParaTelaDeFilmes(){
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}