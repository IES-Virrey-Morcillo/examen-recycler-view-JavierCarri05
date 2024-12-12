package com.iesvirreymorcillo.rvmontes.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iesvirreymorcillo.rvmontes.Montes
import com.iesvirreymorcillo.rvmontes.databinding.ItemMontesBinding

class MontesViewHolder (view: View): RecyclerView.ViewHolder(view){
    val binding = ItemMontesBinding.bind(view)

    fun reader(montesModel: Montes,
               onClickDelete: (Int) -> Unit,
               onClickListener: (Montes) -> Unit){
        binding.tvNombreMonte.text = montesModel.nombre
        binding.tvAltura.text = "Altura: " +montesModel.altura
        binding.tvContinente.text = "Continente: " + montesModel.continente
        Glide.with(binding.ivImagen.context).load(montesModel.foto)

        //el boton esta escuchando para borrar
        binding.btnBorrar.setOnClickListener { onClickDelete(adapterPosition) }

        //el boton esta escuchando para el toast
        binding.btnVerMas.setOnClickListener{
            Toast.makeText(binding.btnVerMas.context, montesModel.urlinfo, Toast.LENGTH_SHORT)
                .show()
        }

    }
}