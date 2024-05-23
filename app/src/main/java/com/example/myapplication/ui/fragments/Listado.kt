package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.models.character.Character
import com.example.myapplication.data.models.character.CharacterResponse
import com.example.myapplication.databinding.ListadoLayoutBinding
import com.example.myapplication.ui.MyViewModel
import com.example.myapplication.ui.adapters.PersonajesAdaptador

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Listado : Fragment() {

    private var _binding: ListadoLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyViewModel>()

    private var currentPage = 1
    private var totalPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListadoLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getCharactersByPage(currentPage).observe(viewLifecycleOwner, observer)

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            viewModel.getCharactersByPage(currentPage).observe(viewLifecycleOwner, observer)
        }

        binding.imageView2.setOnClickListener {
            currentPage--
            viewModel.getCharactersByPage(currentPage).observe(viewLifecycleOwner, observer)
//            if (currentPage == 1){
//                currentPage == totalPage
//            }else{
//                currentPage--
//            }
        }

        binding.imageView3.setOnClickListener {
            currentPage++
            viewModel.getCharactersByPage(currentPage).observe(viewLifecycleOwner, observer)
//            if (currentPage == totalPage){
//                currentPage == 1
//            }else{
//                currentPage++
//            }
        }

    }

    val observer = Observer<CharacterResponse>{
        binding.swipe.isRefreshing = false

        val info = it.info
        info?.pages?.let { totalPage = it }

        val personajes = it.results
        Toast.makeText(requireContext(), currentPage.toString(), Toast.LENGTH_SHORT).show()
        Log.d("PAGE", currentPage.toString())

        if (currentPage == 1){
            binding.imageView2.isVisible = false
        } else if (currentPage == totalPage){
            binding.imageView3.isVisible = false
        }else{
            binding.imageView2.isVisible = true
            binding.imageView3.isVisible = true
        }

        configRecycler(personajes)

    }

    private fun configRecycler(list: List<Character>) {
//        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.recyclerView.adapter = PersonajesAdaptador(list , object : PersonajesAdaptador.MyClick {
//            override fun onHolderClick(personaje: Character) {
//                viewModel.setPersonaje(personaje)
//                findNavController().navigate(R.id.action_listado_to_personajesInfo)
//
//            }
//
//            override fun onHolderClick(personaje: Character, position: Int) {
//                TODO("Not yet implemented")
//            }
//        })

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = PersonajesAdaptador(list, object : PersonajesAdaptador.MyClick {
            override fun onHolderClick(personaje: Character, position: Int) {
                viewModel.setPersonaje(personaje)
                findNavController().navigate(R.id.action_listado_to_personajesInfo)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}