package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflixclone.Adapter.FilmesAdapter
import com.example.netflixclone.Model.addFilmes
import com.example.netflixclone.databinding.ActivityDetalhesFilmeBinding
import com.squareup.picasso.Picasso

class DetalhesFilme : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val recycler_details = binding.recyclerOutrosFilmes
        recycler_details.adapter = FilmesAdapter(addFilmes())
        recycler_details.layoutManager = GridLayoutManager(applicationContext, 3)

        Toolbar()

        val capaTheWicher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-21ef4.appspot.com/o/video.jpg?alt=media&token=2b45197c-bff2-4de1-99fa-cad16808706c"
        val videoTheWicher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-21ef4.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=c630a181-4947-4965-bc8e-fdcffc72c6af"


        Picasso.get().load(capaTheWicher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener(){
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun Toolbar() {
        val tolbar_detalhes = binding.toolbarDetalhes
        tolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.arrow_back))
        tolbar_detalhes.setNavigationOnClickListener {
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()

        }
    }
}