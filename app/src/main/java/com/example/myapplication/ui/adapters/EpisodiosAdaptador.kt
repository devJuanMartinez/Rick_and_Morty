package com.example.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.HolderEpisodiosBinding
import com.example.myapplication.ui.MyViewModel

class EpisodiosAdaptador(
    val listado: List<String>,
    private val viewModel: MyViewModel,
    private val listener: MyClick
) : RecyclerView.Adapter<EpisodiosAdaptador.ClaseCelda>() {

    private lateinit var lifecycleOwner: LifecycleOwner

    interface MyClick {
        fun onHolderClick(episodios: String)
    }
    inner class ClaseCelda(val binding: HolderEpisodiosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClaseCelda {
        val inflater = LayoutInflater.from(parent.context)
        return ClaseCelda(HolderEpisodiosBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: ClaseCelda, position: Int) {
        val episodios = listado[position]



        val number = episodios.substringAfterLast("/")
        val title = "Episodio $number"
        holder.binding.episodio.text = title

        holder.itemView.setOnClickListener{
            listener.onHolderClick(episodios)
            viewModel.getCharacterEpisode(episodios).observe(lifecycleOwner){

            }
        }
    }
}