package com.example.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btCadastrar.setOnClickListener(){
            if (binding.editEmailCadastro.text.toString().isEmpty() || binding.editSenhaCadastro.text.toString().isEmpty()){
                binding.menssagemCadastro.setText("Preencha todos os campos.")
            }
            else{
                CadastrarUsuario()
            }
        }

    }

    private fun CadastrarUsuario(){
        val email = binding.editEmailCadastro.text.toString()
        val senha = binding.editSenhaCadastro.text.toString()

        binding.menssagemCadastro.setText("")
        
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(){
            if(it.isSuccessful){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                binding.editEmailCadastro.setText("")
                binding.editSenhaCadastro.setText("")
            }
        }.addOnFailureListener(){
            val mensagem_erro = binding.menssagemCadastro
            var erro = it

            when{
                erro is FirebaseAuthWeakPasswordException -> mensagem_erro.setText("Digite uma senha com no mínimo 6 caracteres.")
                erro is FirebaseAuthUserCollisionException -> mensagem_erro.setText("E-mail já cadastrado.")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão com a internet")
                else -> binding.menssagemCadastro.setText("Erro ao Cadastrar Usuário.")
            }
        }
    }

}