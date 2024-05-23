package com.example.myapplication.ui.holders

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.models.character.Character
import com.example.myapplication.databinding.HolderPersonajesBinding
import com.example.myapplication.ui.MyViewModel
import com.example.myapplication.ui.adapters.EpisodiosAdaptador


class PersonajesInfo: Fragment() {
    private var _binding: HolderPersonajesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HolderPersonajesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPersonaje().observe(viewLifecycleOwner) { personaje ->
            rellenaDatos(personaje)
        }
    }

    private fun rellenaDatos(c: Character) {
        with(binding) {
            nombre.text = c.name.toString()
            status.text = c.status.toString()
            species.text = c.species.toString()
            gender.text = c.gender.toString()
            origin.text = c.origin?.name.toString()
            btOrigin.setOnClickListener {
                val url = c.origin?.url.toString()
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(url))
                startActivity(i)
            }
            lastlocation.text = c.location?.name.toString()
            btLastlocation.setOnClickListener {
                val url = c.location?.url.toString()
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(url))
                startActivity(i)
            }

            Glide.with(requireContext())
                .load(c.image)
                .into(binding.imageView4)

            configRecycler(c.episode)
        }
    }
     private fun configRecycler(list: List<String>){
         binding.recyclerCapitulos.layoutManager = LinearLayoutManager(requireContext())
         binding.recyclerCapitulos.adapter= EpisodiosAdaptador(list , object : EpisodiosAdaptador.MyClick {
             override fun onHolderClick(episodios: String) {
                 val position = ""
                 val bundle = bundleOf("position" to position)
                 findNavController().navigate(R.id.action_personajesInfo_to_episodiosInfo)
             }

         })
     }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}