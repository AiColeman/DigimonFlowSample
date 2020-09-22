package com.github.aicoleman.digimon_flow_sample.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.aicoleman.digimon_flow_sample.R
import com.github.aicoleman.digimon_flow_sample.data.model.State
import com.github.aicoleman.digimon_flow_sample.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding
    val viewModel by viewModels<DigimonViewModel>()
    val args: InfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = args.uri

        if (name != null) {
            binding.swipe.isRefreshing = true
            viewModel.getDigimonInfo(name)
        }

        binding.swipe.setOnRefreshListener {
            viewModel.getDigimonList()
            binding.swipe.isRefreshing = false
        }

        viewModel.digimonInfoLiveData.observe(
            viewLifecycleOwner, Observer { state ->
                when (state) {
                    is State.Success -> {
                        binding.digimon = state.data.get(0)
                        binding.img.transitionName = state.data.get(0).img
                        Glide.with(requireContext())
                            .load(state.data.get(0).img)
                            .apply(RequestOptions.circleCropTransform())
                            .into(binding.img)
                        binding.swipe.isRefreshing = false
                    }
                    is State.Loading -> {
                        binding.swipe.isRefreshing = true
                    }
                    is State.Error -> {

                    }
                }
            }
        )
    }
}