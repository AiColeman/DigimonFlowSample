package com.github.aicoleman.digimon_flow_sample.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.aicoleman.digimon_flow_sample.R
import com.github.aicoleman.digimon_flow_sample.data.model.Digimon
import com.github.aicoleman.digimon_flow_sample.data.model.State
import com.github.aicoleman.digimon_flow_sample.databinding.ItemDigimonListBinding

class DigimonListAdapter(val context: Context) : RecyclerView.Adapter<DigimonListAdapter.DigimonViewHolder>() {

    private val items: MutableList<Digimon> = mutableListOf()

    class DigimonViewHolder(val binding: ItemDigimonListBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemDigimonListBinding>(inflater, R.layout.item_digimon_list, parent, false)
        return DigimonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            digimon = item
            executePendingBindings()

            Glide.with(context).load(item.img).into(img)
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