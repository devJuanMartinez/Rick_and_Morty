package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.models.character.Character
import com.example.myapplication.databinding.HolderPersonajesepisodiosBinding

abstract class PersonajesEpisodiosAdaptador(val listado: List<Character>, private val listener: MyClick): RecyclerView.Adapter<PersonajesEpisodiosAdaptador.ClaseCelda>(){

    interface MyClick {
        fun onHolderClick(personajee: String)
    }
    inner class ClaseCelda(val binding: HolderPersonajesepisodiosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesEpisodiosAdaptador.ClaseCelda {
        val inflater = LayoutInflater.from(parent.context)
        return ClaseCelda(HolderPersonajesepisodiosBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: ClaseCelda, position: Int) {
        val personajee = listado[position]



        if (personajee != null){

            holder.binding.nomvrepersonajesepisodios.text = personajee.name.toString()
            Glide.with(holder.itemView)
                .load(personajee.image)
                .into(holder.binding.imgvpersonajesepisodios)
        }

//        holder.itemView.setOnClickListener{
//            listener.onHolderClick(personajee)
//
//        }
    }
}