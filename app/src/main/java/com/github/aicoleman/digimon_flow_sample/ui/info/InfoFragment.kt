package com.github.aicoleman.digimon_flow_sample.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.github.aicoleman.digimon_flow_sample.R
import com.github.aicoleman.digimon_flow_sample.data.model.State
import com.github.aicoleman.digimon_flow_sample.databinding.FragmentInfoBinding
import com.github.aicoleman.digimon_flow_sample.ui.list.DigimonListAdapter
import com.github.aicoleman.digimon_flow_sample.ui.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding
    val viewModel by viewModels<ListViewModel>()

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

        val name = arguments?.getString("name")

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
                        Glide.with(requireContext()).load(state.data.get(0).img).into(binding.img)
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