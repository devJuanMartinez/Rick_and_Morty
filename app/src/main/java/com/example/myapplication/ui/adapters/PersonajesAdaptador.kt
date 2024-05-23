package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.models.character.Character
import com.example.myapplication.data.models.character.CharacterResponse
import com.example.myapplication.databinding.HolderListadoBinding

class PersonajesAdaptador(
    val listado: List<Character>,
    private val listener: MyClick
) : RecyclerView.Adapter<PersonajesAdaptador.ClaseCelda>(){

//    private val personajes = ArrayList<CharacterResponse>()

    interface MyClick {
        fun onHolderClick(personaje: Character, position: Int)
    }

    inner class ClaseCelda(val binding: HolderListadoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseCelda {
        val inflater = LayoutInflater.from(parent.context)
        return ClaseCelda(HolderListadoBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: ClaseCelda, position: Int) {
        val personaje = listado[position]



        if (personaje != null){

            holder.binding.nombrelistado.text = personaje.name.toString()
            Glide.with(holder.itemView)
                .load(personaje.image)
                .into(holder.binding.imageView)
        }

        holder.itemView.setOnClickListener{
            listener.onHolderClick(personaje, position)

        }

    }

//    fun rerescarListado(listado: ArrayList<Character?>{
//        personajes.clear()
//        if (listado != null){
//            personajes.addAll(listado)
//        }
//    }
}
