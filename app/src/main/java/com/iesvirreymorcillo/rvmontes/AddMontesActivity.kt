package com.iesvirreymorcillo.rvmontes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iesvirreymorcillo.rvmontes.databinding.ActivityAddMontesBinding

class AddMontesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMontesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_add_montes)
        binding = ActivityAddMontesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
        binding.btnGuardar.setOnClickListener{
            val bundle = Bundle()
            //url, altura y continenete
            bundle.putString("nombre", binding.edtxNombreMonte.text.toString())
            bundle.putString("altura", binding.edtxAlturaMonte.text.toString())
            bundle.putString("urlmonte", binding.edtxUrlFotoMonte.text.toString())
            bundle.putString("continente", binding.edtxContinenteMonte.text.toString())
            bundle.putString("urlinfo", binding.edtxURLInfo.text.toString())

            val intent = Intent(this, RecyclerViewMontes::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}