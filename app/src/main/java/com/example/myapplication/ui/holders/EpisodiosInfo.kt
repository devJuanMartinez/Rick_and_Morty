package com.example.myapplication.ui.holders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.EpisodiosLayoutBinding
import com.example.myapplication.databinding.HolderEpisodiosBinding
import com.example.myapplication.databinding.OriginLayoutBinding
import com.example.myapplication.ui.MyViewModel

class EpisodiosInfo: Fragment() {
    private var _binding: EpisodiosLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EpisodiosLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}