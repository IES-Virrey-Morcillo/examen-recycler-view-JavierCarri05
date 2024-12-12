package com.iesvirreymorcillo.rvmontes.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iesvirreymorcillo.rvmontes.Montes
import com.iesvirreymorcillo.rvmontes.R

class MontesAdapter (private val montesList: MutableList<Montes>,
                     private val onClickDelete: (Int) -> Unit,
                     private val onClickListener: (Montes) -> Unit):
                    RecyclerView.Adapter<MontesViewHolder>(){
    /*
    esto lo pongo cuando vaya hacer lo de borrar y añadir
        private val onClickListener: (Montes) -> Unit,

     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MontesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MontesViewHolder(layoutInflater.inflate(R.layout.item_montes, parent, false))
    }

    override fun onBindViewHolder(holder: MontesViewHolder, position: Int) {
        val item = montesList[position]
        holder.reader(item, onClickDelete, onClickListener)
    }

    override fun getItemCount(): Int = montesList.size

    //funcion search view
    fun filterByName(montes: MutableList<Montes>){
        montesList.clear()
        montesList.addAll(montes)
        notifyDataSetChanged()
    }
}