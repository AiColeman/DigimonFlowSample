package com.github.aicoleman.digimon_flow_sample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.aicoleman.digimon_flow_sample.R
import com.github.aicoleman.digimon_flow_sample.data.model.Digimon
import com.github.aicoleman.digimon_flow_sample.databinding.ItemDigimonBinding

class DigimonListAdapter(val context: Context, val fragment: Fragment) : RecyclerView.Adapter<DigimonListAdapter.DigimonViewHolder>() {

    private val items: MutableList<Digimon> = mutableListOf()

    class DigimonViewHolder(val binding: ItemDigimonBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemDigimonBinding>(inflater, R.layout.item_digimon, parent, false)
        return DigimonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            digimon = item
            executePendingBindings()

            Glide.with(context)
                .load(item.img)
                .apply(RequestOptions.circleCropTransform())
                .into(img)
            card.setOnClickListener {
                img.transitionName = item.img
                val extras = FragmentNavigatorExtras(
                    img to "digimon_img"
                )

                val action = ListFragmentDirections.actionListFragmentToInfoFragment(item.name)
                findNavController(fragment).navigate(action, extras)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addDigimonList(digimonList: List<Digimon>) {
        items.clear()
        items.addAll(digimonList)
        notifyDataSetChanged()
    }
}